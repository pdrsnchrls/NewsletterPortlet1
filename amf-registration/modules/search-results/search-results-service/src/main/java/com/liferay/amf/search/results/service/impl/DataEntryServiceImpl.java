/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.amf.search.results.service.impl;

import com.liferay.amf.search.results.service.DataEntryLocalService;
import com.liferay.amf.search.results.service.Tracker;
import com.liferay.amf.search.results.service.base.DataEntryServiceBaseImpl;
import com.liferay.amf.search.results.service.internal.security.permission.resource.SearchResultsPermission;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the data entry remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.amf.search.results.service.DataEntryService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DataEntryServiceBaseImpl
 */
@Component(
	property = {
		"json.web.service.context.name=searchresults",
		"json.web.service.context.path=DataEntry"
	},
	service = AopService.class
)
public class DataEntryServiceImpl extends DataEntryServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use <code>com.liferay.amf.search.results.service.DataEntryServiceUtil</code> to access the data entry remote service.
	 */
	
	public static final String ACTION_ID = "VIEW_SEARCH";
	
	public List<User> getPermission(long groupId, String zip, int start, int end, Tracker tracker) throws PortalException {
		
		List<User> results = new ArrayList<User>();
		if (_searchResultsPermission.contains(getPermissionChecker(), groupId, ACTION_ID)) {
			results = _dataEntryLocalService.getUsers(zip, start, end, tracker);
		}

		return results;
	}
	
	@Reference
	protected SearchResultsPermission _searchResultsPermission;
	
	@Reference
	protected DataEntryLocalService _dataEntryLocalService;
}
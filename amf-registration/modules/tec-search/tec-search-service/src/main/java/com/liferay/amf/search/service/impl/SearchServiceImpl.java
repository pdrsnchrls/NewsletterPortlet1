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

package com.liferay.amf.search.service.impl;

import com.liferay.amf.search.exception.SearchValidationException;
import com.liferay.amf.search.service.SearchLocalService;
import com.liferay.amf.search.service.base.SearchServiceBaseImpl;
import com.liferay.amf.search.service.internal.security.permission.resource.SearchPermission;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the search remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.amf.search.service.SearchService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SearchServiceBaseImpl
 */
@Component(
	property = {
		"json.web.service.context.name=search",
		"json.web.service.context.path=Search"
	},
	service = AopService.class
)
public class SearchServiceImpl extends SearchServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use <code>com.liferay.amf.search.service.SearchServiceUtil</code> to access the search remote service.
	 */
	public static final String ACTION_ID = "SEARCH";

	public void sendRequest (String zip, ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SearchValidationException {
		
		User user = PortalUtil.getUser(actionRequest);
		if (_searchPermission.contains(getPermissionChecker(), user.getGroupId(), ACTION_ID)) {
			try {
				_searchLocalService.sendZip(zip, actionResponse);
			}
			catch (SearchValidationException e) {
				// TODO Auto-generated catch block
				e.getErrors().forEach(key -> SessionErrors.add(actionRequest, key));
				SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			}
		}
		else {
			SessionErrors.add(actionRequest, "noPermissions");
			SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			actionResponse.getRenderParameters().setValue("", "");
		}
	}
	
	@Reference
	SearchPermission _searchPermission;
	
	@Reference
	SearchLocalService _searchLocalService;
}
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
import com.liferay.amf.search.service.base.SearchLocalServiceBaseImpl;
import com.liferay.amf.search.validator.SearchValidator;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the search local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.amf.search.service.SearchLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SearchLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.amf.search.model.Search",
	service = AopService.class
)
public class SearchLocalServiceImpl extends SearchLocalServiceBaseImpl {

	public void sendZip(String zip) throws SearchValidationException {
		System.out.println("AH GOT EM - " + zip);
		// validate zip
		_searchValidator.validate(zip);
		
	}
	@Reference
	private SearchValidator _searchValidator;

}
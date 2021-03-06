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

package com.liferay.amf.search.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link SearchLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SearchLocalService
 * @generated
 */
@ProviderType
public class SearchLocalServiceWrapper
	implements SearchLocalService, ServiceWrapper<SearchLocalService> {

	public SearchLocalServiceWrapper(SearchLocalService searchLocalService) {
		_searchLocalService = searchLocalService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _searchLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public void sendZip(String zip, javax.portlet.ActionResponse actionResponse)
		throws com.liferay.amf.search.exception.SearchValidationException {

		_searchLocalService.sendZip(zip, actionResponse);
	}

	@Override
	public SearchLocalService getWrappedService() {
		return _searchLocalService;
	}

	@Override
	public void setWrappedService(SearchLocalService searchLocalService) {
		_searchLocalService = searchLocalService;
	}

	private SearchLocalService _searchLocalService;

}
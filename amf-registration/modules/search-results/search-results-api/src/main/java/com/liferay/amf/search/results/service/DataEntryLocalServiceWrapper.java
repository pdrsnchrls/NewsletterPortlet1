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

package com.liferay.amf.search.results.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link DataEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DataEntryLocalService
 * @generated
 */
@ProviderType
public class DataEntryLocalServiceWrapper
	implements DataEntryLocalService, ServiceWrapper<DataEntryLocalService> {

	public DataEntryLocalServiceWrapper(
		DataEntryLocalService dataEntryLocalService) {

		_dataEntryLocalService = dataEntryLocalService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _dataEntryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public void getResults(
			javax.portlet.RenderRequest renderRequest,
			javax.portlet.RenderResponse renderResponse)
		throws com.liferay.portal.kernel.search.SearchException {

		_dataEntryLocalService.getResults(renderRequest, renderResponse);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> getUsers(
			String zip)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dataEntryLocalService.getUsers(zip);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> getUsers(
			String zip, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _dataEntryLocalService.getUsers(zip, start, end);
	}

	@Override
	public DataEntryLocalService getWrappedService() {
		return _dataEntryLocalService;
	}

	@Override
	public void setWrappedService(DataEntryLocalService dataEntryLocalService) {
		_dataEntryLocalService = dataEntryLocalService;
	}

	private DataEntryLocalService _dataEntryLocalService;

}
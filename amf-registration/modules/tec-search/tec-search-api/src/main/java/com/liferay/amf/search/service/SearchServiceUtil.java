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

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Search. This utility wraps
 * <code>com.liferay.amf.search.service.impl.SearchServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SearchService
 * @generated
 */
@ProviderType
public class SearchServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.amf.search.service.impl.SearchServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void sendRequest(
			String zip, javax.portlet.ActionRequest actionRequest,
			javax.portlet.ActionResponse actionResponse)
		throws com.liferay.amf.search.exception.SearchValidationException,
			   com.liferay.portal.kernel.exception.PortalException {

		getService().sendRequest(zip, actionRequest, actionResponse);
	}

	public static SearchService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SearchService, SearchService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SearchService.class);

		ServiceTracker<SearchService, SearchService> serviceTracker =
			new ServiceTracker<SearchService, SearchService>(
				bundle.getBundleContext(), SearchService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
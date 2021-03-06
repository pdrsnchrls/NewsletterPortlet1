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

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for DataEntry. This utility wraps
 * <code>com.liferay.amf.search.results.service.impl.DataEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see DataEntryService
 * @generated
 */
@ProviderType
public class DataEntryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.amf.search.results.service.impl.DataEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.portal.kernel.model.User>
			getPermission(
				long groupId, String zip, int start, int end, Tracker tracker)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPermission(groupId, zip, start, end, tracker);
	}

	public static DataEntryService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DataEntryService, DataEntryService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DataEntryService.class);

		ServiceTracker<DataEntryService, DataEntryService> serviceTracker =
			new ServiceTracker<DataEntryService, DataEntryService>(
				bundle.getBundleContext(), DataEntryService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
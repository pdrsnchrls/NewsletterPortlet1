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
 * Provides the local service utility for DataEntry. This utility wraps
 * <code>com.liferay.amf.search.results.service.impl.DataEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see DataEntryLocalService
 * @generated
 */
@ProviderType
public class DataEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.amf.search.results.service.impl.DataEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.portal.kernel.model.User> getUsers(
			String zip, int start, int end, Tracker tracker)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getUsers(zip, start, end, tracker);
	}

	public static int getUsersSize(String zip)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getUsersSize(zip);
	}

	public static DataEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DataEntryLocalService, DataEntryLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DataEntryLocalService.class);

		ServiceTracker<DataEntryLocalService, DataEntryLocalService>
			serviceTracker =
				new ServiceTracker
					<DataEntryLocalService, DataEntryLocalService>(
						bundle.getBundleContext(), DataEntryLocalService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
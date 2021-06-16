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

package com.liferay.amf.registration.service;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for newUser. This utility wraps
 * <code>com.liferay.amf.registration.service.impl.newUserServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Charles Pederson
 * @see newUserService
 * @generated
 */
@ProviderType
public class newUserServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.amf.registration.service.impl.newUserServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void registerUser(
			String firstName, String lastName, String email, String username,
			boolean male, String birthday, String password1, String password2,
			String homePhone, String mobilePhone, String street1,
			String street2, String city, String state, String zip,
			String secQuestion, String secAnswer, boolean ToU)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().registerUser(
			firstName, lastName, email, username, male, birthday, password1,
			password2, homePhone, mobilePhone, street1, street2, city, state,
			zip, secQuestion, secAnswer, ToU);
	}

	public static newUserService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<newUserService, newUserService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(newUserService.class);

		ServiceTracker<newUserService, newUserService> serviceTracker =
			new ServiceTracker<newUserService, newUserService>(
				bundle.getBundleContext(), newUserService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
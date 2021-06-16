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
 * Provides the local service utility for newUser. This utility wraps
 * <code>com.liferay.amf.registration.service.impl.newUserLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Charles Pederson
 * @see newUserLocalService
 * @generated
 */
@ProviderType
public class newUserLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.amf.registration.service.impl.newUserLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static long getId() {
		return getService().getId();
	}

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
		throws com.liferay.amf.registration.exception.UserValidationException,
			   com.liferay.portal.kernel.exception.PortalException {

		getService().registerUser(
			firstName, lastName, email, username, male, birthday, password1,
			password2, homePhone, mobilePhone, street1, street2, city, state,
			zip, secQuestion, secAnswer, ToU);
	}

	public static com.liferay.portal.kernel.model.Address setAddressAttributes(
		com.liferay.portal.kernel.model.Address address, String street1,
		String street2, String city, String zip, long addressId, long userId,
		String userName, long regionId) {

		return getService().setAddressAttributes(
			address, street1, street2, city, zip, addressId, userId, userName,
			regionId);
	}

	public static com.liferay.portal.kernel.model.Phone setPhoneAttributes(
		com.liferay.portal.kernel.model.Phone phone, String homePhone,
		String mobilePhone, long userId, String username) {

		return getService().setPhoneAttributes(
			phone, homePhone, mobilePhone, userId, username);
	}

	public static newUserLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<newUserLocalService, newUserLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(newUserLocalService.class);

		ServiceTracker<newUserLocalService, newUserLocalService>
			serviceTracker =
				new ServiceTracker<newUserLocalService, newUserLocalService>(
					bundle.getBundleContext(), newUserLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
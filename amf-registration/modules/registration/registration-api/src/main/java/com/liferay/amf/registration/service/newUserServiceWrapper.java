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

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link newUserService}.
 *
 * @author Charles Pederson
 * @see newUserService
 * @generated
 */
@ProviderType
public class newUserServiceWrapper
	implements newUserService, ServiceWrapper<newUserService> {

	public newUserServiceWrapper(newUserService newUserService) {
		_newUserService = newUserService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _newUserService.getOSGiServiceIdentifier();
	}

	@Override
	public void registerUser(
			String firstName, String lastName, String email, String username,
			boolean male, String birthday, String password1, String password2,
			String homePhone, String mobilePhone, String street1,
			String street2, String city, String state, String zip,
			String secQuestion, String secAnswer, boolean ToU)
		throws com.liferay.portal.kernel.exception.PortalException {

		_newUserService.registerUser(
			firstName, lastName, email, username, male, birthday, password1,
			password2, homePhone, mobilePhone, street1, street2, city, state,
			zip, secQuestion, secAnswer, ToU);
	}

	@Override
	public newUserService getWrappedService() {
		return _newUserService;
	}

	@Override
	public void setWrappedService(newUserService newUserService) {
		_newUserService = newUserService;
	}

	private newUserService _newUserService;

}
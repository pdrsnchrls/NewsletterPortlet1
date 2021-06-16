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
 * Provides a wrapper for {@link newUserLocalService}.
 *
 * @author Charles Pederson
 * @see newUserLocalService
 * @generated
 */
@ProviderType
public class newUserLocalServiceWrapper
	implements newUserLocalService, ServiceWrapper<newUserLocalService> {

	public newUserLocalServiceWrapper(newUserLocalService newUserLocalService) {
		_newUserLocalService = newUserLocalService;
	}

	@Override
	public long getId() {
		return _newUserLocalService.getId();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _newUserLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public void registerUser(
			String firstName, String lastName, String email, String username,
			boolean male, String birthday, String password1, String password2,
			String homePhone, String mobilePhone, String street1,
			String street2, String city, String state, String zip,
			String secQuestion, String secAnswer, boolean ToU)
		throws com.liferay.amf.registration.exception.UserValidationException,
			   com.liferay.portal.kernel.exception.PortalException {

		_newUserLocalService.registerUser(
			firstName, lastName, email, username, male, birthday, password1,
			password2, homePhone, mobilePhone, street1, street2, city, state,
			zip, secQuestion, secAnswer, ToU);
	}

	@Override
	public com.liferay.portal.kernel.model.Address setAddressAttributes(
		com.liferay.portal.kernel.model.Address address, String street1,
		String street2, String city, String zip, long addressId, long userId,
		String userName, long regionId) {

		return _newUserLocalService.setAddressAttributes(
			address, street1, street2, city, zip, addressId, userId, userName,
			regionId);
	}

	@Override
	public com.liferay.portal.kernel.model.Phone setPhoneAttributes(
		com.liferay.portal.kernel.model.Phone phone, String homePhone,
		String mobilePhone, long userId, String username) {

		return _newUserLocalService.setPhoneAttributes(
			phone, homePhone, mobilePhone, userId, username);
	}

	@Override
	public newUserLocalService getWrappedService() {
		return _newUserLocalService;
	}

	@Override
	public void setWrappedService(newUserLocalService newUserLocalService) {
		_newUserLocalService = newUserLocalService;
	}

	private newUserLocalService _newUserLocalService;

}
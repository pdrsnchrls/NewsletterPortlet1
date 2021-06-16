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

import com.liferay.amf.registration.exception.UserValidationException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for newUser. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Charles Pederson
 * @see newUserLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface newUserLocalService extends BaseLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link newUserLocalServiceUtil} to access the new user local service. Add custom service methods to <code>com.liferay.amf.registration.service.impl.newUserLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getId();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public void registerUser(
			String firstName, String lastName, String email, String username,
			boolean male, String birthday, String password1, String password2,
			String homePhone, String mobilePhone, String street1,
			String street2, String city, String state, String zip,
			String secQuestion, String secAnswer, boolean ToU)
		throws PortalException, UserValidationException;

	public Address setAddressAttributes(
		Address address, String street1, String street2, String city,
		String zip, long addressId, long userId, String userName,
		long regionId);

	public Phone setPhoneAttributes(
		Phone phone, String homePhone, String mobilePhone, long userId,
		String username);

}
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

package com.liferay.amf.registration.service.impl;

import com.liferay.amf.registration.service.base.newUserServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the new user remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.amf.registration.service.newUserService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Charles Pederson
 * @see newUserServiceBaseImpl
 */
@Component(
	property = {
		"json.web.service.context.name=registration",
		"json.web.service.context.path=newUser"
	},
	service = AopService.class
)
public class newUserServiceImpl extends newUserServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use <code>com.liferay.amf.registration.service.newUserServiceUtil</code> to access the new user remote service.
	 */
	
	public void registerUser(
			String firstName, String lastName, String email, String username, boolean male, 
			String birthday, String password1, String password2, String homePhone, String mobilePhone, 
			String street1, String street2, String city, String state, String zip,
			String secQuestion, String secAnswer, boolean ToU) 
			throws PortalException {
		
		newUserLocalService.registerUser(firstName, lastName, email, username, male, birthday, password1, 
				password2, homePhone, mobilePhone, street1, street2, city, state, zip, secQuestion, secAnswer, ToU);
	}
	
}
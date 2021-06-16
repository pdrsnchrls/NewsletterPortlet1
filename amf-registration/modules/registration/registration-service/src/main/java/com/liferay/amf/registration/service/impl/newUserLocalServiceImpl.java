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

import com.liferay.amf.registration.exception.UserValidationException;
import com.liferay.amf.registration.service.base.newUserLocalServiceBaseImpl;
import com.liferay.amf.registration.validator.UserValidator;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.service.RegionServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.SessionErrors;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.portlet.ActionRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the new user local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.amf.registration.service.newUserLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Charles Pederson
 * @see newUserLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.amf.registration.model.newUser",
	service = AopService.class
)
public class newUserLocalServiceImpl extends newUserLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.liferay.amf.registration.service.newUserLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.amf.registration.service.newUserLocalServiceUtil</code>.
	 */
	
	//ID generator for all your ID needs - utilizes Liferay counterLocalService.increment()
	public long getId () {
		long ID = counterLocalService.increment();
		return ID;
	}
	
	//set phone attributes
	public Phone setPhoneAttributes(
			Phone phone, String homePhone, String mobilePhone, long userId, String username) {
		//mobile 11008, home(personal) 11011

		long typeId = 0;
		String number = "";
		if (!homePhone.isEmpty()) {
			typeId = 11011;
			number = homePhone;
		}
		else if (!mobilePhone.isEmpty()) {
			typeId = 11008;
			number = mobilePhone;
		}
		
		phone.setNumber(number);
		phone.setTypeId(typeId);
		phone.setUserId(userId);
		phone.setUserName(username);
		
		return phone;
	}
	
	//set address attributes
	public Address setAddressAttributes(
			Address address, String street1, String street2, String city, String zip,
			long addressId, long userId, String userName, long regionId) {

		address.setStreet1(street1);
		address.setStreet2(street2);
		address.setCity(city);
		address.setZip(zip);
		address.setAddressId(addressId);
		address.setUserId(userId);
		address.setUserName(userName);
		address.setRegionId(regionId);
		
		return address;
	}
	
	//use this class to add a user to the database lportal table Contact_ and User_
	public void registerUser(
			String firstName, String lastName, String email, String username, boolean male, 
			String birthday, String password1, String password2, String homePhone, String mobilePhone, 
			String street1, String street2, String city, String state, String zip,
			String secQuestion, String secAnswer, boolean ToU)
			throws UserValidationException, PortalException {
		
		
		_userValidator.validate(firstName, lastName, email, username, male, birthday, password1, 
			password2, homePhone, mobilePhone, street1, street2, city, state, zip, 
			secQuestion, secAnswer, ToU);

		
		//get userId, contactId, regionId and create user and contact
		long userId = 20105, phoneId = getId(), addressId = getId();
		
		//get birthday month, day and year
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date = LocalDate.parse(birthday, formatter);

		int birthdayMonth = date.getMonthValue()-1; //function uses 0 to 11 for months
		int birthdayDay = date.getDayOfMonth();
		int birthdayYear = date.getYear();
		
		//create user, phone and address
		Phone phone = phoneLocalService.createPhone(phoneId);
		Address address = addressLocalService.createAddress(addressId);
		
		//Get region id to store it in Address
		long regionId = 0;
		//19001 to 19052
		for (long i = 19001; i < 19053; i++) {
			Region region;
			region = RegionServiceUtil.getRegion(i);
			if (region.getName().contains(state)) {
				regionId = i;
			}
		}
		
		//set attributes
		ServiceContext service = null;
		long def = 0, defComp = 20101, defUserId = 20105;
		long defArr[] = new long[0];
		Locale currentLocale = Locale.getDefault();

		userLocalService.addUser(defUserId, defComp, false, password1, password2, false, 
				username, email, def, "", currentLocale, firstName, "", lastName, 
				def, def, male, birthdayMonth, birthdayDay, birthdayYear, "", defArr, 
				defArr, defArr, defArr, true, service);
			
		userId = userLocalService.getUserIdByScreenName(defComp, username);
		userLocalService.updateAgreedToTermsOfUse(userId, ToU);
		userLocalService.updateReminderQuery(userId, secQuestion, secAnswer);


		if (userId != 20105) {
			phone = setPhoneAttributes(phone, homePhone, mobilePhone, userId, username);
			address = setAddressAttributes(address, street1, street2, city, zip, 
					addressId, userId, username, regionId);
		}
		
		//after user created update variables for secQuestion, secAnswer, ToU
		
		//add data
		phoneLocalService.addPhone(phone);
		addressLocalService.addAddress(address);

	}
	
	@Reference
	UserValidator _userValidator;
		
}






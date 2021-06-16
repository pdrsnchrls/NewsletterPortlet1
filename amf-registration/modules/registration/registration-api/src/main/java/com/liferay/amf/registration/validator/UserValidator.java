package com.liferay.amf.registration.validator;

import com.liferay.amf.registration.exception.UserValidationException;
import com.liferay.portal.kernel.exception.PortalException;

public interface UserValidator {

	//firstName, lastName, email, username, male, birthday, password1, 
	//password2, homePhone, mobilePhone, street1, street2, city, state, zip, secQuestion, secAnswer, ToU
	public void validate(
		String firstName, String lastName, String email, String username, boolean male, String birthday, 
		String password1, String password2, String homePhone, String mobilePhone, String address1, 
		String address2, String city, String state, String zip, String secQuestion, String secAnswer, boolean tOU)
			throws UserValidationException;
}
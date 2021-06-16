package com.liferay.amf.registration.util.validator;

import com.liferay.amf.registration.exception.UserValidationException;
import com.liferay.amf.registration.validator.UserValidator;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RegionServiceUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;

import org.osgi.service.component.annotations.Component;


@Component(
		immediate = true,
		service = UserValidator.class
)
public class UserValidatorImpl implements UserValidator {
	/*
	 * Validates User information and throws UserValidationException if the user values are not
	 * valid.
	 * */
	
	@Override
	public void validate(
			String firstName, String lastName, String email, String username, boolean male, 
			String birthday, String password1, String password2, String homePhone, String mobilePhone, String address1, String address2,
			String city, String state, String zip, String secQuestion, String secAnswer, boolean tOU)
			throws UserValidationException {
		List<String> errors = new ArrayList<>();
		
		if (!isUserValid(firstName, lastName, email, username, male, birthday, password1, password2, homePhone,
				mobilePhone, address1, address2, city, state, zip, secQuestion, secAnswer, tOU, errors)) {
			throw new UserValidationException(errors);
		}
	}
	
	private boolean isUserValid(final String firstName, final String lastName, final String email, 
			final String username, final boolean male, final String birthday, final String password1,
			final String password2, final String homePhone, final String mobilePhone, final String address1, 
			final String address2, final String city, final String state, final String zip,
			final String secQuestion, final String secAnswer, final boolean ToU, final List<String> errors) {

		boolean result = true;
		
		result &= isNameValid(firstName, errors); 
		result &= isNameValid(lastName, errors);
		result &= isEmailValid(email, errors); 
		result &= isUsernameValid(username, errors);
		result &= isBirthdayValid(birthday, errors);
		result &= isPasswordValid(password1, password2, errors);
		result &= isPhoneValid(homePhone, errors);
		result &= isPhoneValid(mobilePhone, errors);
		result &= isAddressValid(address1, address2, city, state, zip, errors);
		result &= isSecurityValid(secQuestion, secAnswer, errors);
		result &= isToUValid(ToU, errors); 
		
		return result;
	}
	
	//alphanumeric, 50 characters
	private boolean isNameValid(
			final String name, final List<String> errors) {
		
		boolean result = true;
		if (name.isEmpty()) {
			errors.add("nameEmpty");
			return false;
		}
		if (name.length() > 50) {
			errors.add("nameTooLong");
			result = false;
		}
		if (!Validator.isAlphanumericName(name)) {
			errors.add("nameNonAlphanumeric");
			result = false;
		}
		return result;
	}
	
	//alphanumeric, 255 characters, valid email
	private boolean isEmailValid(
			final String email, final List<String> errors) {

		boolean result = true;
		if (email.isEmpty()) {
			errors.add("emailEmpty");
			return false;
		}
		if (email.length() > 255) {
			errors.add("emailTooLong");
			result = false;
		}
		if(!Validator.isEmailAddress(email)) {
			errors.add("emailNotValid");
			result = false;
		}
		return result;
	}
	
	//alphanumeric, 4 to 16 chars, ***unique
	private boolean isUsernameValid(
			final String username, final List<String> errors) {

		boolean result = true;
		if (username.isEmpty()) {
			errors.add("usernameEmpty");
			return false;
		}
		if (username.length() > 16) {
			result = false;
			errors.add("usernameTooLong");
		}
		else if (username.length() < 4) {
			result = false;
			errors.add("usernameTooShort");
		}
		if (!Validator.isAlphanumericName(username)) {
			errors.add("usernameNonAlphanumeric");
			result = false;
		}
		//select screenName from User_;
		if (!isUnique(username)) {
			errors.add("usernameNotUnique");
			result=false;
		}
		return result;
	}
	
	//required, must be 13 to register
	private boolean isBirthdayValid(
			final String birthday, final List<String> errors) {

		boolean result = true;
		
		if (birthday.isEmpty()) {
			result = false;
			errors.add("birthdayEmpty");
		}
		else { //birthday is not empty so check if user is at least 13 years old
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			try {
				LocalDate date = LocalDate.parse(birthday, formatter);
				LocalDate thirteen = LocalDate.now();
				thirteen = thirteen.minus(13, ChronoUnit.YEARS);
				
				if (!date.isBefore(thirteen)) {
					result = false;
					errors.add("birthdayYoung");
				}
			} catch (Exception e){
				errors.add("birthdayInvalid");
				result = false;
			}

		}
		return result;
	}

	//6 char min, one uppercase, one number, one special character, 1 and 2 must match
	private boolean isPasswordValid(
			final String pass1, final String pass2, final List<String> errors) {

		boolean result = true;
		if (pass1.isEmpty()) {
			errors.add("passwordEmpty");
			return false;
		}
		if (!pass1.equals(pass2)) {
			result = false;
			errors.add("passwordNoMatch");
		}
		// Regex used to check valid password
        String regex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{6,100}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pass1);
        if (!m.matches()) {
        	result = false;
        	errors.add("passwordInvalid");
        }
		return result;
	}
	
	//10 digits long, not required
	private boolean isPhoneValid(
			final String phone, final List<String> errors) {
		
		boolean result = true;
		//check if it is digits
		if (!phone.isEmpty()) {
			if (phone.length() != 10) {
				errors.add("phoneLengthError");
				result = false;
			}
			if (!Validator.isPhoneNumber(phone)) {
				result = false;
				errors.add("phoneInvalid");
			}
		}
		return result;
	}
	
	//add2 not required, state not empty add1, add2, city alphanumeric, max 255 characters. zip 5 digit
	private boolean isAddressValid(
			final String add1, final String add2, final String city, 
			final String state, final String zip, final List<String> errors) {
		

		boolean result = true;
		// address1/address2
		if (add1.isEmpty()) {
			errors.add("addressEmpty");
			result = false;
		}
		if (!Validator.isAlphanumericName(add1) && !add1.isEmpty() || (!add2.isEmpty() && !Validator.isAlphanumericName(add2))) {
			errors.add("addressNonAlphanumeric");
			result = false;
		}
		if ((add1.length() > 255  && !add1.isEmpty()) || add2.length() > 255) {
			errors.add("addressLengthError");
			result = false;
		}
		// city
		if (city.isEmpty()) {
			result = false;
			errors.add("cityEmpty");
		}
		if (!Validator.isAlphanumericName(city) && !city.isEmpty()) {
			errors.add("cityNonAlphanumeric");
			result = false;
		}
		if (city.length() > 255 && !city.isEmpty()) {
			errors.add("cityLengthError");
			result = false;
		}
		if (state.isEmpty()) {
			result = false;
			errors.add("stateEmpty");
		}
		// check state is valid in database
		long regionId = 0;
		for (long i = 19001; i < 19053; i++) {
			Region region;
			try {
				region = RegionServiceUtil.getRegion(i);
				if (region.getName().toLowerCase().contains(state.toLowerCase())) {
					regionId = i;
				}
			} catch (PortalException e) {

			}
		}
		if (regionId == 0) {
			result = false;
			errors.add("stateIllegal");
		}
		//ZIP Code
		if (zip.isEmpty()) {
			result = false;
			errors.add("zipEmpty");
		}
		if (zip.length() != 5 & !zip.isEmpty()) {
			result = false;
			errors.add("zipLengthError");
		}
		if (!zip.isEmpty()) {
			try {
				int num = Integer.parseInt(zip);
				
			} catch (NumberFormatException e) {
				result = false;
				errors.add("zipNonNumeric");
			}
		}

		return result;
	}
	
	//required, alphanumeric max 255 characters for answer
	private boolean isSecurityValid(
			final String secQ, final String secA, final List<String> errors) {

		boolean result = true;
		if (secQ.isEmpty()) {
			result = false;
			errors.add("secQuestionEmpty");
		}
		if (secA.isEmpty()) {
			errors.add("secAnswerEmpty");
			return false;
		}
		if (!Validator.isAlphanumericName(secA)) {
			result = false;
			errors.add("secNonAlphanumeric");
		}
		if (secA.length() > 255) {
			result = false;
			errors.add("secAnswerLength");
		}
		
		return result;
	}
	
	//terms of use must be checked
	private boolean isToUValid(boolean ToU, List<String> errors) {

		if (ToU == false) {
			errors.add("ToUNotAgreed");
			return false;
		}
		else 
			return true;
	}
	
	//run a dynamic query to 
	private boolean isUnique(String username) {
		ClassLoader classLoader = getClass().getClassLoader();
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(User.class, classLoader)
				.setProjection(ProjectionFactoryUtil.property("screenName"));
		List<String> results = UserLocalServiceUtil.dynamicQuery(query);
		
		for (int i = 0; i<results.size(); i++) {
			if (username.equals(results.get(i))) {
				return false;
			}
		}

		return true;
	}

}

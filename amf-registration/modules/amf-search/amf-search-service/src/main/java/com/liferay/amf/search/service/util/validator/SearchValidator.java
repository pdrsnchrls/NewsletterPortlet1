package com.liferay.amf.search.service.util.validator;

import java.util.ArrayList;
import java.util.List;

public class SearchValidator {

	public void validate(String zip)
	{
		List<String> errors = new ArrayList<>();
		
		if (!isZipValid(zip, errors)) {
			System.out.println("Invalid Zip!");
		}
	}
	
	//ZIP Code
	public boolean isZipValid(
			final String zip, final List<String> errors) {
		boolean result = true;	
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
	
}

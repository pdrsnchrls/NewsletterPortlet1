package com.liferay.amf.search.service.util.validator;

import com.liferay.amf.search.exception.SearchValidationException;
import com.liferay.amf.search.validator.SearchValidator;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		service = SearchValidator.class
)
public class SearchValidatorImpl implements SearchValidator{

	@Override
	public void validate(String zip) throws SearchValidationException
	{
		List<String> errors = new ArrayList<>();
		
		if (!isZipValid(zip, errors)) {
			throw new SearchValidationException(errors);
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

package com.liferay.amf.search.validator;

import com.liferay.amf.search.exception.SearchValidationException;

public interface SearchValidator {

		public void validate(String zip) 
				throws SearchValidationException;
}


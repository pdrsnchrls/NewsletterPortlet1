package com.liferay.amf.search.validator;

import com.liferay.amf.search.exception.SearchValidationException;

import javax.portlet.ActionResponse;

public interface SearchValidator {

		public void validate(String zip) 
				throws SearchValidationException;
}


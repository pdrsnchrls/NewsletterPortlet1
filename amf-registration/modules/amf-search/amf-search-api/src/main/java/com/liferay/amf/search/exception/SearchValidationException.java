package com.liferay.amf.search.exception;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

public class SearchValidationException extends PortalException {

	// Custom constructor taking a list as a parameter for errors @param errors
	public SearchValidationException(List<String> errors) {
		super(String.join(",", errors));
		_errors = errors;
	}
		
	public List<String> getErrors() {
		return _errors;
	}
		
	private List<String> _errors;
		
		
		
	public SearchValidationException() {
	}

	public SearchValidationException(String msg) {
		super(msg);
	}

	public SearchValidationException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SearchValidationException(Throwable cause) {
		super(cause);
	}

}

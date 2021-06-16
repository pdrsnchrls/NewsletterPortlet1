package com.liferay.amf.registration.web.portlet;

import com.liferay.amf.registration.exception.UserValidationException;
import com.liferay.amf.registration.service.newUserLocalService;
import com.liferay.amf.registration.web.constants.RegistrationPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.servlet.RequestDispatcher;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author charles
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Registration",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/sign_up.jsp",
		"javax.portlet.name=" + RegistrationPortletKeys.Registration,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"com.liferay.portlet.display-category=category.amf",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.init-param.add-process-action-success-action=false"
	},
	service = Portlet.class
)
public class RegistrationPortlet extends MVCPortlet { // move this to a package for portlet.action in registration-web?
	public void addUser(
			ActionRequest request, ActionResponse response) 
			throws Exception {
		
		String firstName = ParamUtil.getString(request, "firstName");
		String lastName = ParamUtil.getString(request, "lastName");
		String email = ParamUtil.getString(request, "email");
		String username = ParamUtil.getString(request, "username");
		
		//Determine gender
		String gender = ParamUtil.getString(request, "male");
		boolean male = true;
		if (gender.contains("Female"))
			male = false;
		
		String birthday = ParamUtil.getString(request, "birthday"); //update validation to account for date being a STRING now
		String password1 = ParamUtil.getString(request, "password1");
		String password2 = ParamUtil.getString(request, "password2");

		String homePhone = ParamUtil.getString(request, "homePhone");
		String mobilePhone = ParamUtil.getString(request, "mobilePhone");
		
		String address1 = ParamUtil.getString(request, "address1");
		String address2 = ParamUtil.getString(request, "address2");
		String city = ParamUtil.getString(request, "city");
		String state = ParamUtil.getString(request, "state");
		String zip = ParamUtil.getString(request, "zip");
		
		String secQuestion = ParamUtil.getString(request, "secQuestion");
		String secAnswer = ParamUtil.getString(request, "secAnswer");
		
		String terms = ParamUtil.getString(request, "termsOfUse");
		boolean tOU = false;
		if (terms.equals("accepted")) {
			tOU = true;
		}

		try {
			_userLocalService.registerUser(firstName, lastName, email, username, male, birthday, password1, password2, homePhone,
			mobilePhone, address1, address2, city, state, zip, secQuestion, secAnswer, tOU);
			
			SessionMessages.add(request, "userAdded");
			
			sendRedirect(request, response);
		}
		catch(UserValidationException ave) {
			ave.getErrors().forEach(key -> SessionErrors.add(request, key));
			response.setRenderParameter("", "registration/user/add");
		}
		catch(PortalException pe) {
			pe.printStackTrace();
			SessionErrors.add(request,  "serviceErrorDetails", pe);
			response.setRenderParameter("", "registration/user/add");
		}
	}
	@Reference
	protected newUserLocalService _userLocalService;
}







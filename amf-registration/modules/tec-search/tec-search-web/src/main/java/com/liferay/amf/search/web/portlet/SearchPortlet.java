package com.liferay.amf.search.web.portlet;

import com.liferay.amf.search.exception.SearchValidationException;
import com.liferay.amf.search.service.SearchLocalService;
import com.liferay.amf.search.validator.SearchValidator;
import com.liferay.amf.search.web.constants.SearchPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;

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
		"javax.portlet.display-name=Search",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + SearchPortletKeys.SEARCH,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"com.liferay.portlet.display-category=category.amf",
		"com.liferay.portlet.instanceable=false"
	},
	service = Portlet.class
)
public class SearchPortlet extends MVCPortlet {
	
	//put in MVCAction command?
	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse) {
		
		String zip = ParamUtil.getString(actionRequest, "zip");
		System.out.println("Here is the ZIP: " + zip);
		
		try {
			_searchLocalService.sendZip(zip);
		} catch (SearchValidationException e) {
			// TODO Auto-generated catch block
			e.getErrors().forEach(key -> SessionErrors.add(actionRequest, key));
			actionResponse.setRenderParameter("", "search");
		}
		
	}
	@Reference
	private SearchLocalService _searchLocalService;
	
}
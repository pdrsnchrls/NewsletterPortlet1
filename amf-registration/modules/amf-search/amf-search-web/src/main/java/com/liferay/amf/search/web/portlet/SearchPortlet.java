package com.liferay.amf.search.web.portlet;

import com.liferay.amf.search.web.constants.SearchPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

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
		"javax.portlet.init-param.view-template=/search.jsp",
		"javax.portlet.name=" + SearchPortletKeys.SEARCH,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"com.liferay.portlet.display-category=category.amf",
		"com.liferay.portlet.instanceable=false"
	},
	service = Portlet.class
)
public class SearchPortlet extends MVCPortlet {
	
	/* ZIP VALIDATION CODE
	 * //ZIP Code
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
	 * 
	 * */
}
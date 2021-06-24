package com.liferay.amf.search.web.portlet;

import com.liferay.amf.search.service.SearchService;
import com.liferay.amf.search.web.constants.SearchPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

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
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.supported-public-render-parameter=zip"
	},
	service = Portlet.class
)
public class SearchPortlet extends MVCPortlet {
	
	//put in MVCAction command?
	public void search(
			ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException {
		
		String zip = ParamUtil.getString(actionRequest, "zip");

		_searchService.sendRequest(zip, actionRequest, actionResponse);

	}
	@Reference
	private SearchService _searchService;
	
}
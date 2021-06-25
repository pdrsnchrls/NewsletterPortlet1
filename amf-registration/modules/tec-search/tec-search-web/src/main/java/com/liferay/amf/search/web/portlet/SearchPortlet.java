package com.liferay.amf.search.web.portlet;

import com.liferay.amf.search.service.SearchService;
import com.liferay.amf.search.web.constants.SearchPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.ProcessAction;
import javax.xml.namespace.QName;

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
		"javax.portlet.supported-publishing-event=ipc.search;http://event.search/zip",
		"javax.portlet.init-param.add-process-action-success-action=false",

	},
	service = Portlet.class
)
public class SearchPortlet extends MVCPortlet {
	
	@ProcessAction(name="search")
	public void processEvent(ActionRequest request, ActionResponse response) throws PortalException{

		String zipCode = ParamUtil.getString(request, "zip");
		System.out.println(zipCode);
		
		_searchService.sendRequest(zipCode, request, response);
		
	}
	
	@Reference
	private SearchService _searchService;
	
}
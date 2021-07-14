package com.liferay.amf.search.results.web.portlet;

import com.liferay.amf.search.results.service.Tracker;
import com.liferay.amf.search.results.web.constants.SearchResultsPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.Portlet;
import javax.portlet.ProcessEvent;

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
		"javax.portlet.display-name=SearchResults",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/search-results-view.jsp",
		"javax.portlet.name=" + SearchResultsPortletKeys.SEARCHRESULTS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"com.liferay.portlet.display-category=category.amf",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.supported-processing-event=ipc.search;http://event.search/zipcode"
	},
	service = Portlet.class
)
public class SearchResultsPortlet extends MVCPortlet {
	
	@ProcessEvent(qname="{http://event.search/zipcode}ipc.search")
	public void processEvent(EventRequest request, EventResponse response) {

		// get zip from request
		Event event = request.getEvent();
		String zipCode = (String)event.getValue();
		response.setRenderParameter("zip", zipCode);
		response.setRenderParameter("mvcRenderCommandName", "/search-results/view");		
	}

}
package com.liferay.amf.search.results.web.portlet;

import com.liferay.amf.search.results.service.DataEntryService;
import com.liferay.amf.search.results.web.constants.SearchResultsPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;

import javax.portlet.Portlet;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

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
		"javax.portlet.display-name=SearchResults",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/search-results-view.jsp",
		"javax.portlet.name=" + SearchResultsPortletKeys.SEARCHRESULTS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"com.liferay.portlet.display-category=category.amf",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.supported-public-render-parameter=zip"
	},
	service = Portlet.class
)
public class SearchResultsPortlet extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) {

		try {
			_dataEntryService.getResults(renderRequest, renderResponse);
			super.doView(renderRequest, renderResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			SessionErrors.add(renderRequest, "systemFailure");
		}

	}

	@Reference
	protected DataEntryService _dataEntryService;
}
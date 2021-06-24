package com.liferay.amf.search.results.web.portlet;

import com.liferay.amf.search.results.service.DataEntryLocalService;
import com.liferay.amf.search.results.service.DataEntryService;
import com.liferay.amf.search.results.web.constants.SearchResultsPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
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
		"javax.portlet.init-param.view-template=/view.jsp",
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
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
		
		_dataEntryService.getResults(renderRequest);
			String zip= ParamUtil.get(renderRequest, "zip", "");
			try {
				List<User> results = _dataEntryLocalService.getUsers( zip );
				renderRequest.setAttribute("usersSize", results.size());
				renderRequest.setAttribute("users", results);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			renderRequest.setAttribute("zip", zip);
			
			super.doView(renderRequest, renderResponse);
	}

	@Reference
	DataEntryService _dataEntryService;
	
	@Reference
	protected DataEntryLocalService _dataEntryLocalService;
}
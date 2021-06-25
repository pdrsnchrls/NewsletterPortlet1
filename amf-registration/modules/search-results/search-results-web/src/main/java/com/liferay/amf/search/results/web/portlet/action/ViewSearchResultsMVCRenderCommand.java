package com.liferay.amf.search.results.web.portlet.action;

import com.liferay.amf.search.results.service.DataEntryLocalService;
import com.liferay.amf.search.results.service.DataEntryService;
import com.liferay.amf.search.results.web.constants.SearchResultsPortletKeys;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(
		property = {
			"javax.portlet.name=" + SearchResultsPortletKeys.SEARCHRESULTS,
			"mvc.command.name=/search-results/view" //set render command name for Iterator URL use this
		},
		service = MVCRenderCommand.class
	)
public class ViewSearchResultsMVCRenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest request, RenderResponse response) throws PortletException {
		// TODO Auto-generated method stub
		//code for render request/set attribute - calling local service here
		
		String zipCode = request.getParameter("zipCode");
		SearchContainer<User> searchContainer = new SearchContainer<User>();
		searchContainer.setDelta(5);
		
		// cur, delta variables to calculate start and end
		List<User> results = new ArrayList<User>();
		User user = PortalUtil.getUser(request);
		
		_dataEntryService.getPermission(user, zipCode, results);
		
		searchContainer.setResults(results);

		//call local service to get users
		//renderrequest.setAttribute("searchContainer", searchContainer)
		

		
		return null;
	}
	@Reference
	protected DataEntryService _dataEntryService;

	
}

package com.liferay.amf.search.results.web.portlet.action;

import com.liferay.amf.search.results.service.DataEntryLocalService;
import com.liferay.amf.search.results.service.DataEntryService;
import com.liferay.amf.search.results.service.Tracker;
import com.liferay.amf.search.results.web.constants.SearchResultsPortletKeys;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
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
		
		String zipCode = request.getParameter("zip");
		PortletURL iteratorURL = response.createRenderURL();
		iteratorURL.setParameter("mvcRenderCommandName", "/search-results/view");
		iteratorURL.setParameter("zip", zipCode);
		
		SearchContainer<User> searchContainer = new SearchContainer<User>(	request, null, null, 
				SearchContainer.DEFAULT_CUR_PARAM, 5, iteratorURL, null,
				"no-results-found-please-try-a-different-search-criteria");
		
		//calculate start and end
		int page = ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_CUR);
		int delta = searchContainer.getDelta();
		
		int end = page*delta;
		int start = end-delta;
		
		System.out.println("In render command. The ZIP Code is " + zipCode);

		System.out.println("Start: " + start + " End: " + end + " Delta: " + delta + " Current: " + page);
		// cur, delta variables to calculate start and end
		Tracker tracker = new Tracker();
		List<User> results = new ArrayList<User>();
		int usersSize = 0;
		try {
			User user = PortalUtil.getUser(request);
			long groupId = user.getGroupId();
			results = _dataEntryService.getPermission(groupId, zipCode, start, end, tracker); // should send in start, end and page uwu
			usersSize = _dataEntryLocalService.getUsersSize(zipCode);

			searchContainer.setResults(results);
			searchContainer.setTotal(usersSize);
		}
		catch (PortalException e) {
			e.printStackTrace();
		}
		//set searchContainer
		request.setAttribute("searchContainer", searchContainer);
		request.setAttribute("zip", zipCode);
		
		return null;
	}
	@Reference
	protected DataEntryService _dataEntryService;

	@Reference
	protected DataEntryLocalService _dataEntryLocalService;
}

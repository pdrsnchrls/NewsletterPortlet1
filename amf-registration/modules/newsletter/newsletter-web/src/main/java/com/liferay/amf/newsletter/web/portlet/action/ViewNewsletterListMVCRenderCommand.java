package com.liferay.amf.newsletter.web.portlet.action;

import com.liferay.amf.newsletter.model.Issue;
import com.liferay.amf.newsletter.service.IssueLocalService;
import com.liferay.amf.newsletter.web.constants.NewsletterPortletKeys;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate=true,
		property = {
			"javax.portlet.name=" + NewsletterPortletKeys.NEWSLETTER,
			"mvc.command.name=/newsletter-list/view" //set render command name for Iterator URL use this
		},
		service = MVCRenderCommand.class
)
public class ViewNewsletterListMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest request, RenderResponse response) throws PortletException {
		// TODO Auto-generated method stub

		System.out.println("In render command :)");
		PortletURL iteratorURL = response.createRenderURL();
		iteratorURL.setParameter("mvcRenderCommandName", "/newsletter-list/view");

		SearchContainer<Issue> searchContainer = new SearchContainer<Issue>(request, null, null, 
				SearchContainer.DEFAULT_CUR_PARAM, 12, iteratorURL, null,
				"no-issues-found");
		
		List<Issue> issuesList = _issueLocalService.getIssues(searchContainer.getStart(), searchContainer.getEnd());
		
		searchContainer.setResults(issuesList);
		searchContainer.setTotal(issuesList.size());
		
		request.setAttribute("searchContainer", searchContainer);
		
		return null;
	}
	
	@Reference
	protected IssueLocalService _issueLocalService;
	
}

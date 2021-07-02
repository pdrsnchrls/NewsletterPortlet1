package com.liferay.amf.newsletter.web.portlet.action;

import com.liferay.amf.newsletter.model.Issue;
import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.amf.newsletter.service.IssueLocalService;
import com.liferay.amf.newsletter.service.NewsletterLocalService;
import com.liferay.amf.newsletter.service.persistence.NewsletterPersistence;
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
			"mvc.command.name=/newsletter-list/view", //set render command name for Iterator URL use this
			"mvc.command.name=/"
		},
		service = MVCRenderCommand.class
)
public class ViewNewsletterListMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest request, RenderResponse response) throws PortletException {
		// TODO Auto-generated method stub

		System.out.println("In render command");
		PortletURL iteratorURL = response.createRenderURL();
		iteratorURL.setParameter("mvcRenderCommandName", "/newsletter-list/view");
				
		SearchContainer<Newsletter> newsletterSearchContainer= new SearchContainer<Newsletter>(request, null, null, 
				SearchContainer.DEFAULT_CUR_PARAM, 4, iteratorURL, null,
				"no-newsletters-found");
		
		List<Newsletter> newslettersList = _newsletterLocalService.getNewsletters(newsletterSearchContainer.getStart(), newsletterSearchContainer.getEnd());
		List<Issue> issuesList = _issueLocalService.getIssues(newsletterSearchContainer.getStart(), newsletterSearchContainer.getEnd());

		newsletterSearchContainer.setIteratorURL(iteratorURL);
		newsletterSearchContainer.setResults(newslettersList);
		newsletterSearchContainer.setTotal(newslettersList.size());
		
		request.setAttribute("issuesList", issuesList);
		request.setAttribute("newsletterSearchContainer", newsletterSearchContainer);

		// query all issues
		List<Issue> allIssues = _issueLocalService.getIssues(0, _issueLocalService.getIssuesCount());
		for (Issue e: allIssues) {
			System.out.println(e.getIssueNumber());
			List<Newsletter> newsletterList = _newsletterLocalService.findByIssueNumber(e.getIssueNumber());
			for (int index = 0; index < newsletterList.size(); index++)
				System.out.print(newsletterList.get(index));
		}
		
		
		
		return null;
	}
	
	@Reference
	protected IssueLocalService _issueLocalService;
	
	@Reference
	protected NewsletterPersistence _newsletterPersistence;
	
	@Reference
	protected NewsletterLocalService _newsletterLocalService;
	
}

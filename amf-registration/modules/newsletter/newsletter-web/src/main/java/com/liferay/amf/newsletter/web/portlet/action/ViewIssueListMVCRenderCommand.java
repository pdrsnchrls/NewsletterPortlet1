package com.liferay.amf.newsletter.web.portlet.action;

import com.liferay.amf.newsletter.model.Issue;
import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.amf.newsletter.service.IssueLocalService;
import com.liferay.amf.newsletter.service.NewsletterLocalService;
import com.liferay.amf.newsletter.service.persistence.NewsletterPersistence;
import com.liferay.amf.newsletter.web.constants.NewsletterPortletKeys;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import java.util.*;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate=true,
		property = {
			"javax.portlet.name=" + NewsletterPortletKeys.NEWSLETTER,
			"mvc.command.name=/issue-list/view", //set render command name for Iterator URL use this
			"mvc.command.name=/"
		},
		service = MVCRenderCommand.class
)
public class ViewIssueListMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest request, RenderResponse response) throws PortletException {

		System.out.println("In render command");
		List<Issue> issuesList = _issueLocalService.getIssues(0, _issueLocalService.getIssuesCount());
		request.setAttribute("issuesList", issuesList);

		// create set of all the years in the newsletters
		TreeSet<Integer> years = new TreeSet<>(); // use a set to guarantee uniqueness
		for (Issue i: issuesList) {
			Date date = i.getIssueDate();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			System.out.println("Issue Year: " + year);
			years.add(year);
		}
		years=(TreeSet<Integer>) years.descendingSet(); // reverse order so tabs display from highest year to lowest year
		request.setAttribute("years", years);

		int selectedYear = ParamUtil.getInteger(request, "tab"); // gets selectedYear from tab
		//get issues based on year
		
		// unused search container?
		PortletURL iteratorURL = response.createRenderURL();
		iteratorURL.setParameter("mvcRenderCommandName", "/issue-list/view");
		SearchContainer<Newsletter> newsletterSearchContainer= new SearchContainer<Newsletter>(request, null, null, 
				SearchContainer.DEFAULT_CUR_PARAM, 4, iteratorURL, null,
				"no-newsletters-found");

		List<Newsletter> newslettersList = _newsletterLocalService.getNewsletters(newsletterSearchContainer.getStart(), newsletterSearchContainer.getEnd());

		newsletterSearchContainer.setIteratorURL(iteratorURL);
		newsletterSearchContainer.setTotal(newslettersList.size());

		// query all issues
		for (Issue e: issuesList) {
			long issueNumber = e.getIssueNumber();
			request.setAttribute("issueNumber", issueNumber);
			System.out.println("\n" + "Issue Number: " + issueNumber);
			List<Newsletter> newsletterList = _newsletterLocalService.findByIssueNumber(issueNumber);
			
			for (int index = 0; index < newsletterList.size(); index++)
				System.out.print(newsletterList.get(index));
			newsletterSearchContainer.setResults(newsletterList);
			request.setAttribute("newsletterSearchContainer" + issueNumber, newsletterSearchContainer);
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

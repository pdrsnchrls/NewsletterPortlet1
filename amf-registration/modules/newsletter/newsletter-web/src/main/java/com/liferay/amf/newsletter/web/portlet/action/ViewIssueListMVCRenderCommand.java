package com.liferay.amf.newsletter.web.portlet.action;

import com.liferay.amf.newsletter.model.Issue;
import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.amf.newsletter.service.IssueLocalService;
import com.liferay.amf.newsletter.service.NewsletterLocalService;
import com.liferay.amf.newsletter.service.persistence.NewsletterPersistence;
import com.liferay.amf.newsletter.web.constants.MVCCommandNames;
import com.liferay.amf.newsletter.web.constants.NewsletterPortletKeys;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import java.util.*;

import javax.portlet.*;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.taglib.portlet.RenderURLTag;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate=true,
		property = {
			"javax.portlet.name=" + NewsletterPortletKeys.NEWSLETTER,
			"mvc.command.name=" + MVCCommandNames.VIEW_ISSUE_LIST,
			"mvc.command.name=/"
		},
		service = MVCRenderCommand.class
)
public class ViewIssueListMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest request, RenderResponse response) throws PortletException {

		// get all issue years
		List<Integer> years = _issueLocalService.getAllIssueYears();
		String yearString = "";
		for (Integer i = years.size(); i > 0; i--) {
			yearString += years.get(i-1) + ", ";
		}
		request.setAttribute("years", yearString);
		request.setAttribute("defaultTab", years.get(years.size()-1));
		String temp = ParamUtil.getString(request, "tab");
		if (temp.isEmpty()) { // on first load up, it will be wrong so set the default tab to first year...
			temp = years.get(years.size()-1).toString();
		}
		int selectedYear = Integer.valueOf(temp);

		//get issues based on year - dynamicQuery in service layer
		List<Issue> issuesBySelectedYear = _issueLocalService.getIssuesByYear(selectedYear);
		Map<Integer, List<Integer>> monthsMap = new HashMap<Integer, List<Integer>>();
		for (Integer year: years) {
			List<Integer> months = _issueLocalService.getIssueMonths(year);
			monthsMap.put(year, months);
		}

//		Map<Issue, List<Newsletter>> issueListMap = new HashMap<Issue, List<Newsletter>>(); //map of years and list of months
//
//		for (Issue i: issuesBySelectedYear) {
//			List<Newsletter> newsletterList = _newsletterLocalService.findByIssueNumber(i.getIssueNumber());
////			issueListMap.put(i, newsletterList);
//		}

		request.setAttribute("year", selectedYear);
		request.setAttribute("issuesBySelectedYear", issuesBySelectedYear);

		PortletURL portletURL = response.createRenderURL();
		portletURL.setProperty("tab", String.valueOf(selectedYear));
//		portletURL.setParameter("tab", String.valueOf(selectedYear));
		request.setAttribute("portletURL", portletURL);

		request.setAttribute("issueLocalService", _issueLocalService);
		request.setAttribute("newsletterLocalService", _newsletterLocalService);
		return "/view.jsp";
	}

	@Reference
	protected IssueLocalService _issueLocalService;
	
	@Reference
	protected NewsletterLocalService _newsletterLocalService;
	
}

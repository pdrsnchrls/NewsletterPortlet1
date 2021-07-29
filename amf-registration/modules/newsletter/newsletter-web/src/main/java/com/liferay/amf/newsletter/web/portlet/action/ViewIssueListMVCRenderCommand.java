package com.liferay.amf.newsletter.web.portlet.action;

import com.liferay.amf.newsletter.model.Issue;
import com.liferay.amf.newsletter.service.IssueLocalService;
import com.liferay.amf.newsletter.service.NewsletterLocalService;
import com.liferay.amf.newsletter.web.constants.MVCCommandNames;
import com.liferay.amf.newsletter.web.constants.NewsletterPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import java.util.*;

import javax.portlet.*;

import com.liferay.portal.kernel.util.ParamUtil;
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

		List<Integer> monthsBySelectedYear = _issueLocalService.getIssueMonthsByYear(selectedYear);
		List<Integer> monthsBySelectedYearReverse = new ArrayList<Integer>(monthsBySelectedYear);
		Collections.reverse(monthsBySelectedYearReverse);

		request.setAttribute("year", selectedYear);
		request.setAttribute("monthsBySelectedYear", monthsBySelectedYearReverse);

		PortletURL portletURL = response.createRenderURL();
		portletURL.setProperty("tab", String.valueOf(selectedYear));
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

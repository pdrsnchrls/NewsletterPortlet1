package com.liferay.amf.newsletter.web.portlet.action;

import com.liferay.amf.newsletter.model.Issue;
import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.amf.newsletter.service.IssueLocalService;
import com.liferay.amf.newsletter.service.NewsletterLocalService;
import com.liferay.amf.newsletter.web.constants.MVCCommandNames;
import com.liferay.amf.newsletter.web.constants.NewsletterPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import java.sql.Timestamp;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + NewsletterPortletKeys.NEWSLETTER,
		"mvc.command.name=" + MVCCommandNames.VIEW_ISSUE
	},
	service = MVCRenderCommand.class
)
public class ViewIndividualIssueMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest request, RenderResponse response)
		throws PortletException {

		Long issueId = ParamUtil.get(request, "issueId", 0L);

		try {
			Issue issue = _issueLocalService.getIssue(issueId);

			Timestamp timestamp = new Timestamp(
				issue.getIssueDate(
				).getTime());

			request.setAttribute("authorByline", issue.getByline());
			request.setAttribute(
				"issueDate", _issueLocalService.formatIssueDate(timestamp));
			request.setAttribute("issueDescription", issue.getDescription());
			request.setAttribute("issueNumber", issue.getIssueNumber());
			request.setAttribute("issueTitle", issue.getTitle());
			List<Newsletter> newsletterList =
				_newsletterLocalService.findByIssueNumber(
					issue.getIssueNumber());
			request.setAttribute("newsletterList", newsletterList);
		}
		catch (PortalException pe) {
			pe.printStackTrace();
		}

		return "/issue-view.jsp";
	}

	@Reference
	IssueLocalService _issueLocalService;

	@Reference
	NewsletterLocalService _newsletterLocalService;

}
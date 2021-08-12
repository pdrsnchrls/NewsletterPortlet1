package com.liferay.amf.newsletter.web.portlet.action;

import com.liferay.amf.newsletter.model.Issue;
import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.amf.newsletter.service.IssueLocalService;
import com.liferay.amf.newsletter.service.NewsletterLocalService;
import com.liferay.amf.newsletter.web.constants.MVCCommandNames;
import com.liferay.amf.newsletter.web.constants.NewsletterPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import java.sql.Timestamp;

import javax.portlet.*;

import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + NewsletterPortletKeys.NEWSLETTER,
		"mvc.command.name=" + MVCCommandNames.VIEW_ARTICLE
	},
	service = MVCRenderCommand.class
)
public class ViewArticleMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest request, RenderResponse response)
		throws PortletException {

		Long newsletterId = ParamUtil.get(request, "newsletterId", 0L);
		Long issueId = ParamUtil.get(request, "issueId", 0L);

		Newsletter newsletter = _newsletterLocalService.fetchNewsletter(
			newsletterId);

		if (Validator.isNull(newsletter)) {
			_log.error("Newsletter with id " + newsletterId + " not found.");
			return null;
		}

		Issue issue = _issueLocalService.fetchIssue(issueId);

		if (Validator.isNull(issue)) {
			_log.error("Issue with id " + issueId + " not found.");
			return null;
		}

		Timestamp timestamp = new Timestamp(
			issue.getIssueDate(
			).getTime());

		request.setAttribute(
			"issueDate", _issueLocalService.formatIssueDate(timestamp));
		request.setAttribute("issueNumber", newsletter.getIssueNumber());
		request.setAttribute("newsletterAuthor", newsletter.getAuthor());
		request.setAttribute("newsletterContent", newsletter.getContent());
		request.setAttribute("newsletterTitle", newsletter.getTitle());

		return "/article-view.jsp";
	}

	@Reference
	private NewsletterLocalService _newsletterLocalService;

	@Reference
	private IssueLocalService _issueLocalService;

	private static Log _log = LogFactoryUtil.getLog(
			"html.issuewebportlet.search_view_jsp");

}
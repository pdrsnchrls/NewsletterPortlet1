package com.liferay.amf.newsletter.web.portlet.action;

import com.liferay.amf.newsletter.model.Issue;
import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.amf.newsletter.service.IssueLocalService;
import com.liferay.amf.newsletter.service.NewsletterLocalService;
import com.liferay.amf.newsletter.web.constants.MVCCommandNames;
import com.liferay.amf.newsletter.web.constants.NewsletterPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.*;
import java.util.Enumeration;

@Component(
        immediate = true,
        property = {
            "javax.portlet.name=" + NewsletterPortletKeys.NEWSLETTER,
            "mvc.command.name=" + MVCCommandNames.VIEW_ARTICLE,
        },
        service = MVCRenderCommand.class
)
public class ViewArticleMVCRenderCommand implements MVCRenderCommand {

    @Override
    public String render(RenderRequest request, RenderResponse response) throws PortletException {

        System.out.println("What's up");

        Long newsletterId = ParamUtil.get(request, "newsletterId", 0L);
        Long issueId = ParamUtil.get(request, "issueId", 0L);
        //request.getParameter("newsletterId"); //
        System.out.println(newsletterId);

        try {
            Newsletter newsletter = _newsletterLocalService.getNewsletter(newsletterId);
            Issue issue = _issueLocalService.getIssue(issueId);

            request.setAttribute("issueNumber", newsletter.getIssueNumber());
            request.setAttribute("issueDate", issue.getIssueDate());
            request.setAttribute("newsletterTitle", newsletter.getTitle());
            request.setAttribute("newsletterAuthor", newsletter.getAuthor());
            request.setAttribute("newsletterContent", newsletter.getContent());
        } catch (PortalException e) {
            e.printStackTrace();
        }

        return "/article-view.jsp";
    }
    @Reference
    NewsletterLocalService _newsletterLocalService;
    @Reference
    IssueLocalService _issueLocalService;

}
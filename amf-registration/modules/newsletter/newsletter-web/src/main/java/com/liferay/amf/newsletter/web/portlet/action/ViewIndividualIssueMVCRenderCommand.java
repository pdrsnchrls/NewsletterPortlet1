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
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + NewsletterPortletKeys.NEWSLETTER,
                "mvc.command.name=" + MVCCommandNames.VIEW_ISSUE,
        },
        service = MVCRenderCommand.class
)
public class ViewIndividualIssueMVCRenderCommand implements MVCRenderCommand {
    @Override
    public String render(RenderRequest request, RenderResponse response) throws PortletException {

        Long issueId = ParamUtil.get(request, "issueId", 0L);
        try {
            Issue issue = _issueLocalService.getIssue(issueId);
            request.setAttribute("issueDate", issue.getIssueDate());
            request.setAttribute("issueNumber", issue.getIssueNumber());
            request.setAttribute("issueTitle", issue.getTitle());
            request.setAttribute("authorByline", issue.getByline());
            List<Newsletter> newsletterList = _newsletterLocalService.findByIssueNumber(issue.getIssueNumber());
            request.setAttribute("newsletterList", newsletterList);

        } catch (PortalException e) {
            e.printStackTrace();
        }

        return "/issue.jsp";
    }
    @Reference
    IssueLocalService _issueLocalService;

    @Reference
    NewsletterLocalService _newsletterLocalService;
}

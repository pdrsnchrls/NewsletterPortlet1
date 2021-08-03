package com.liferay.amf.newsletter.web.portlet.action;

import com.liferay.amf.newsletter.model.Issue;
import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.amf.newsletter.service.IssueLocalServiceUtil;
import com.liferay.amf.newsletter.service.NewsletterLocalServiceUtil;
import com.liferay.amf.newsletter.web.constants.MVCCommandNames;
import com.liferay.amf.newsletter.web.constants.NewsletterPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + NewsletterPortletKeys.NEWSLETTER,
                "mvc.command.name=" + MVCCommandNames.VIEW_SEARCH_RESULT
        },
        service = MVCActionCommand.class
)
public class SearchArticlesMVCRenderCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest request, ActionResponse response) throws Exception {
        System.out.println("Testing implementation");

        String keywords = ParamUtil.getString(request, "keywords");
        System.out.println("KEYWORDS: " + keywords);

        Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
        long companyId = company.getCompanyId();
        User user = UserLocalServiceUtil.getDefaultUser(companyId);

        SearchContext searchContext = new SearchContext();

        searchContext.setKeywords(keywords);
        searchContext.setAttribute("paginationType", "more");
        searchContext.setStart(0);
        searchContext.setEnd(5);
        searchContext.setCompanyId(companyId);
        searchContext.setUserId(user.getUserId());

        Indexer indexer = IndexerRegistryUtil.getIndexer(Newsletter.class);

        Hits hits = null;
        try {
            hits = indexer.search(searchContext);
        } catch (SearchException e) {
            System.out.println("Something went wrong here\n");
            e.printStackTrace();
        }

//        List<Newsletter> newsletters = new ArrayList<>();
//
//        for (int i = 0; i < hits.getDocs().length; i++) {
//            Document doc = hits.doc(i);
//
//            long newsletterId = GetterUtil
//                    .getLong(doc.get(Field.ENTRY_CLASS_PK));
//
//            Newsletter newsletter = null;
//            try {
//                newsletter = NewsletterLocalServiceUtil.getNewsletter(newsletterId);
//            } catch (PortalException pe) {
//                _log.error(pe.getLocalizedMessage());
//            } catch (SystemException se) {
//                _log.error(se.getLocalizedMessage());
//            }
//
//            newsletters.add(newsletter);
//        }
//        List<Issue> issues = IssueLocalServiceUtil.getIssues(0, IssueLocalServiceUtil.getIssuesCount());
//
//        Map<String, String> issuesMap = new HashMap<String, String>();
//
//        for (Issue issue : issues) {
//            issuesMap.put(Long.toString(issue.getIssueId()), issue.getTitle());
//        }
//        return;
    }

//    @Override
//    public String render(RenderRequest request, RenderResponse response) throws PortletException {
//        String keywords = ParamUtil.getString(request, "keywords");
//
//
//        SearchContext searchContext = new SearchContext();
//
//        searchContext.setKeywords(keywords);
//        searchContext.setAttribute("paginationType", "more");
//        searchContext.setStart(0);
//        searchContext.setEnd(5);
//
//        Indexer indexer = IndexerRegistryUtil.getIndexer(Newsletter.class);
//
//        Hits hits = null;
//        try {
//            hits = indexer.search(searchContext);
//        } catch (SearchException e) {
//            e.printStackTrace();
//        }
//
//        List<Newsletter> newsletters = new ArrayList<>();
//
//        for (int i = 0; i < hits.getDocs().length; i++) {
//            Document doc = hits.doc(i);
//
//            long newsletterId = GetterUtil
//                    .getLong(doc.get(Field.ENTRY_CLASS_PK));
//
//            Newsletter newsletter = null;
//            try {
//                newsletter = NewsletterLocalServiceUtil.getNewsletter(newsletterId);
//            } catch (PortalException pe) {
//                _log.error(pe.getLocalizedMessage());
//            } catch (SystemException se) {
//                _log.error(se.getLocalizedMessage());
//            }
//
//            newsletters.add(newsletter);
//        }
//        List<Issue> issues = IssueLocalServiceUtil.getIssues(0, IssueLocalServiceUtil.getIssuesCount());
//
//        Map<String, String> issuesMap = new HashMap<String, String>();
//
//        for (Issue issue : issues) {
//            issuesMap.put(Long.toString(issue.getIssueId()), issue.getTitle());
//        }
//
//        return "search-view.jsp";
//    }

    private static Log _log = LogFactoryUtil.getLog("html.issuewebportlet.search_view_jsp");

}

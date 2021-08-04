package com.liferay.amf.newsletter.web.portlet.action;

import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.amf.newsletter.service.NewsletterLocalServiceUtil;
import com.liferay.amf.newsletter.web.constants.MVCCommandNames;
import com.liferay.amf.newsletter.web.constants.NewsletterPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
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
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + NewsletterPortletKeys.NEWSLETTER,
                "mvc.command.name=" + MVCCommandNames.VIEW_SEARCH_RESULT
        },
        service = MVCRenderCommand.class
)
public class SearchArticlesMVCRenderCommand implements MVCRenderCommand {

    @Override
    public String render(RenderRequest request, RenderResponse response) throws PortletException {

        System.out.println("Hello there");
        String keywords = ParamUtil.getString(request, "keywords");
        System.out.println("KEYWORDS: " + keywords);

        SearchContext searchContext = new SearchContext();
        searchContext.setKeywords(keywords);
        searchContext.setAttribute("paginationType", "more");
        searchContext.setStart(0);
        searchContext.setEnd(5);

        try {
            Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
            long companyId = company.getCompanyId();
            User user = UserLocalServiceUtil.getDefaultUser(companyId);

            System.out.println("Company: " + company.getName() + " User " + user.getUserId());

            searchContext.setCompanyId(companyId);
            searchContext.setUserId(user.getUserId());
        }
        catch(PortalException e) {
            e.printStackTrace();
        }
        Indexer indexer = IndexerRegistryUtil.getIndexer(Newsletter.class);

        Hits hits = null;
        try {
            hits = indexer.search(searchContext);
        } catch (SearchException e) {
            System.out.println("Something went wrong here\n");
            e.printStackTrace();
        }

        List<Newsletter> newsletters = new ArrayList<>();

        for (int i = 0; i < hits.getDocs().length; i++) {
            Document doc = hits.doc(i);

            long newsletterId = GetterUtil
                    .getLong(doc.get(Field.ENTRY_CLASS_PK));

            Newsletter newsletter = null;
            try {
                newsletter = NewsletterLocalServiceUtil.getNewsletter(newsletterId);
            } catch (PortalException pe) {
                _log.error(pe.getLocalizedMessage());
            } catch (SystemException se) {
                _log.error(se.getLocalizedMessage());
            }

            newsletters.add(newsletter);
        }
        for (Newsletter newsletter : newsletters) {
            System.out.println("Hey there " + newsletter.getTitle());
        }

        request.setAttribute("newslettersSize", newsletters.size());
        request.setAttribute("newsletters", newsletters);

        return "/search-view.jsp";
    }

    private static Log _log = LogFactoryUtil.getLog("html.issuewebportlet.search_view_jsp");

}

package com.liferay.amf.newsletter.search;

import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.amf.newsletter.service.NewsletterLocalService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.util.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.Locale;

@Component(
        immediate = true,
        service = Indexer.class
)
public class NewsletterIndexer extends BaseIndexer<Newsletter> {

    public NewsletterIndexer() {
        setDefaultSelectedFieldNames(
                Field.COMPANY_ID, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
                Field.UID, Field.SCOPE_GROUP_ID, Field.GROUP_ID);
        setDefaultSelectedLocalizedFieldNames(Field.TITLE, Field.CONTENT);
        setFilterSearch(true);
    }

    protected void reindexNewsletter(long companyId)
            throws PortalException {

        final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
                _newsletterLocalService.getIndexableActionableDynamicQuery();

        indexableActionableDynamicQuery.setCompanyId(companyId);

        indexableActionableDynamicQuery.setPerformActionMethod(

                new ActionableDynamicQuery.PerformActionMethod<Newsletter>() {
                    @Override
                    public void performAction(Newsletter newsletter) {
                        try {
                            Document document = getDocument(newsletter);
                            indexableActionableDynamicQuery.addDocuments(document);
                        }
                        catch (PortalException pe) {
                            if (_log.isWarnEnabled()) {
                                _log.warn(
                                        "Unable to index guestbook " +
                                                newsletter.getNewsletterId(),
                                        pe);
                            }
                        }
                    }
                });
        indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
        indexableActionableDynamicQuery.performActions();
    }

    @Override
    public void postProcessContextBooleanFilter(
            BooleanFilter contextBooleanFilter, SearchContext searchContext)
            throws Exception {
        addStatus(contextBooleanFilter, searchContext);
    }

    @Override
    public void postProcessSearchQuery(
            BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
            SearchContext searchContext)
            throws Exception {

        addSearchLocalizedTerm(searchQuery, searchContext, Field.TITLE, false);
        addSearchLocalizedTerm(searchQuery, searchContext, Field.CONTENT, false);
        addSearchLocalizedTerm(searchQuery, searchContext, "author", false);
    }

    @Override
    protected void doDelete(Newsletter object) throws Exception {
        long companyId = getCompany().getCompanyId();
        deleteDocument(companyId, object.getNewsletterId());
    }

    @Override
    protected Document doGetDocument(Newsletter object) throws Exception {
        Document document = getBaseModelDocument(CLASS_NAME, object);
        long groupId = getCompany().getGroup().getGroupId();
        Locale defaultLocale =
                PortalUtil.getSiteDefaultLocale(groupId);
        String localizedTitle = LocalizationUtil.getLocalizedName(Field.TITLE, defaultLocale.toString());
        String localizedContent = LocalizationUtil.getLocalizedName(Field.CONTENT, defaultLocale.toString());

        document.addText(localizedTitle, object.getTitle());
        document.addText(localizedContent, object.getContent());

        long newsletterId = object.getNewsletterId();
        Newsletter newsletter = _newsletterLocalService.getNewsletter(newsletterId);
        String newsletterAuthor = newsletter.getAuthor();
        String localizedNLAuthor = LocalizationUtil.getLocalizedName("author", defaultLocale.toString());

        document.addText(localizedNLAuthor, newsletterAuthor);

        return document;
    }

    @Override
    protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest, PortletResponse portletResponse) throws Exception {
        Summary summary = createSummary(document);
        summary.setMaxContentLength(200);
        return summary;
    }

    @Override
    protected void doReindex(String className, long classPK) throws Exception {

        Newsletter newsletter = _newsletterLocalService.getNewsletter(classPK);
        doReindex(newsletter);
    }

    @Override
    protected void doReindex(String[] ids) throws Exception {
        long companyId = GetterUtil.getLong(ids[0]);
        reindexNewsletter(companyId);
    }

    @Override
    protected void doReindex(Newsletter newsletter) throws Exception {

        Document document = getDocument(newsletter);
        indexWriterHelper.updateDocument(
                getSearchEngineId(), getCompany().getCompanyId(), document,
                isCommitImmediately());
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    public Company getCompany() throws PortalException {
        Company company = CompanyLocalServiceUtil.getCompanyByWebId(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
        return company;
    }

    public static final String CLASS_NAME = Newsletter.class.getName();

    private static final Log _log = LogFactoryUtil.getLog(NewsletterIndexer.class);

    @Reference
    protected IndexWriterHelper indexWriterHelper;

    @Reference
    protected NewsletterLocalService _newsletterLocalService;
}

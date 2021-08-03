package com.liferay.amf.newsletter.search;

import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.amf.newsletter.service.NewsletterLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.amf.newsletter.model.Newsletter",
        service = ModelIndexerWriterContributor.class
)
public class NewsletterModelIndexerWriterContributor implements ModelIndexerWriterContributor<Newsletter>{

    @Override
    public void customize(
            BatchIndexingActionable batchIndexingActionable,
            ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

        batchIndexingActionable.setPerformActionMethod((Newsletter newsletter) -> {
            Document document = modelIndexerWriterDocumentHelper.getDocument(
                    newsletter);

            batchIndexingActionable.addDocuments(document);

        });
    }

    @Override
    public BatchIndexingActionable getBatchIndexingActionable() {
        return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
                newsletterLocalService.getIndexableActionableDynamicQuery());
    }

    @Override
    public long getCompanyId(Newsletter newsletter) {
        Company company = null;
        try {
            company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
        } catch (PortalException e) {
            e.printStackTrace();
        }

        return company.getCompanyId();
    }

    @Reference
    protected DynamicQueryBatchIndexingActionableFactory
            dynamicQueryBatchIndexingActionableFactory;

    @Reference
    protected NewsletterLocalService newsletterLocalService;

}

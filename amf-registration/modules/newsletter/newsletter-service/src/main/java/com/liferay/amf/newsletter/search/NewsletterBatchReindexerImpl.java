package com.liferay.amf.newsletter.search;

import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.indexer.IndexerDocumentBuilder;
import com.liferay.portal.search.indexer.IndexerWriter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = NewsletterBatchReindexer.class)
public class NewsletterBatchReindexerImpl implements NewsletterBatchReindexer{

    @Override
    public void reindex(long issueId, long companyId) {
        BatchIndexingActionable batchIndexingActionable =
                indexerWriter.getBatchIndexingActionable();

        batchIndexingActionable.setAddCriteriaMethod(dynamicQuery -> {
            Property issueIdPropery = PropertyFactoryUtil.forName(
                    "issueId");

            dynamicQuery.add(issueIdPropery.eq(issueId));
        });

        batchIndexingActionable.setCompanyId(companyId);

        batchIndexingActionable.setPerformActionMethod((Newsletter newsletter) -> {
            Document document = indexerDocumentBuilder.getDocument(newsletter);

            batchIndexingActionable.addDocuments(document);
        });

        batchIndexingActionable.performActions();
    }

    @Reference(target = "(indexer.class.name=com.liferay.amf.newsletter.model.Newsletter)")
    protected IndexerDocumentBuilder indexerDocumentBuilder;

    @Reference(target = "(indexer.class.name=com.liferay.amf.newsletter.model.Newsletter)")
    protected IndexerWriter<Newsletter> indexerWriter;

}

package com.liferay.amf.newsletter.search;

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.search.query.QueryHelper;
import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.KeywordQueryContributorHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.amf.newsletter.model.Newsletter",
        service = KeywordQueryContributor.class
)
public class NewsletterKeywordQueryContributor implements KeywordQueryContributor {
    @Override
    public void contribute(String keywords, BooleanQuery booleanQuery, KeywordQueryContributorHelper keywordQueryContributorHelper) {
        SearchContext searchContext =
                keywordQueryContributorHelper.getSearchContext();

        //TODO add search localized terms...
        queryHelper.addSearchLocalizedTerm(
                booleanQuery, searchContext, Field.TITLE, false);
        queryHelper.addSearchLocalizedTerm(
                booleanQuery, searchContext, Field.CONTENT, false);
        queryHelper.addSearchLocalizedTerm(
                booleanQuery, searchContext, "newsletterEmail", false);
    }

    @Reference
    protected QueryHelper queryHelper;

}

package com.liferay.amf.newsletter.search;

import com.liferay.amf.newsletter.model.Issue;
import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.amf.newsletter.service.IssueLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;

@Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.amf.newsletter.model.Newsletter",
        service = ModelDocumentContributor.class
)
public class NewsletterModelDocumentContributor implements ModelDocumentContributor<Newsletter> {
    @Override
    public void contribute(Document document, Newsletter newsletter) {
        try {
            Locale defaultLocale = LocaleUtil.getDefault();

            String localizedTitle = LocalizationUtil.getLocalizedName(
                    Field.TITLE, defaultLocale.toString());
            String localizedContent = LocalizationUtil.getLocalizedName(
                    Field.CONTENT, defaultLocale.toString());

            document.addText(localizedTitle, newsletter.getTitle());
            document.addText(localizedContent, newsletter.getContent());

            document.addText("newsletterAuthor", newsletter.getAuthor());
            document.addNumber("newsletterIssueNumber", newsletter.getIssueNumber());
            document.addNumber("newsletterId", newsletter.getNewsletterId());

            long issueNumber = newsletter.getIssueNumber();

            Issue issue = _issueLocalService.getIssueByIssueNumber(issueNumber);

            String issueTitle = issue.getTitle();

            String localizedGbName = LocalizationUtil.getLocalizedName(
                    Field.NAME, defaultLocale.toString());

            document.addText(localizedGbName, issueTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final Log _log = LogFactoryUtil.getLog(
            NewsletterModelDocumentContributor.class);

    @Reference
    private IssueLocalService _issueLocalService;

}

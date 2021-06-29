package com.liferay.amf.newsletter.service.events;

import com.liferay.amf.newsletter.service.IssueLocalService;
import com.liferay.amf.newsletter.service.NewsletterLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate=true,
	property= {
			
	},
	service=ModelListener.class
)
public class JournalArticleEntryModelListener extends BaseModelListener<JournalArticle> {

	public void onAfterCreate(JournalArticle journalArticle) {
		
		long articleId = journalArticle.getId();
		String articleContent = journalArticle.getContent();
		System.out.println("ArticleID: " + articleId);
		
		// get the Structure information
		DDMStructure structure = journalArticle.getDDMStructure();
		String structureName = structure.getName();
		boolean newsletterType = false, issueType = false;

		if(structureName.contains("Newsletter")) {
			// web content is newsletter
			newsletterType = true;
			
			//parse newsletter content to get issue_number, order_number, newsletter_title, newsletter_author, newsletter_content
			_newsletterLocalService.parseContent(articleContent);
		}
		else if(structureName.contains("Issue")) {
			// web content is issue
			issueType = true;
			
			//parse issue content to get issue_number, issue_title, issue_description, issue_date, byline
			_newsletterLocalService.parseContent(articleContent);
		}
		else {
			System.out.print("Irrelevant journal article creation");
		}
	}

	public void onBeforeUpdate(JournalArticle journalArticle) {
		System.out.print("\nI am here to update stuff.\n");
		long articleId = journalArticle.getId();
		System.out.println("ArticleID before update: " + articleId);

	}
	public void onAfterUpdate(JournalArticle journalArticle) {
		System.out.print("\nI am here after update\n");
		long articleId = journalArticle.getId();
		System.out.println("ArticleID after update: " + articleId + "\n");

	}
	
	@Reference
	private NewsletterLocalService _newsletterLocalService;
	
	@Reference
	private IssueLocalService _issueLocalService;
}

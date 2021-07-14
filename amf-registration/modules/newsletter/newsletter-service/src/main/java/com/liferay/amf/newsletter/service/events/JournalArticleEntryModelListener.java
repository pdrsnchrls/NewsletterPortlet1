package com.liferay.amf.newsletter.service.events;

import com.liferay.amf.newsletter.service.ContentLocalService;
import com.liferay.amf.newsletter.service.IssueLocalService;
import com.liferay.amf.newsletter.service.NewsletterLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate=true,
	property= {
			//key?
	},
	service=ModelListener.class
)
public class JournalArticleEntryModelListener extends BaseModelListener<JournalArticle> {

	public void onAfterCreate(JournalArticle journalArticle) {
		
		long resourcePrimKey = journalArticle.getResourcePrimKey(); // a constant, can be used to check if we are adding or updating the database
		String articleContent = journalArticle.getContent();
		
		// get the Structure information
		DDMStructure structure = journalArticle.getDDMStructure();
		String structureName = structure.getName();
		boolean newsletterType = false, issueType = false;

		if(structureName.contains("Newsletter")) {
			// web content is newsletter
			newsletterType = true;
		}
		else if(structureName.contains("Issue")) {
			// web content is issue
			issueType = true;
		}
		
		//parse content to get relevant information
		_contentLocalService.parseContent(articleContent, resourcePrimKey, newsletterType, issueType);

	}
	
	public void onAfterRemove(JournalArticle journalArticle) { // D in CRUD
		
		long resourcePrimKey = journalArticle.getResourcePrimKey();
		System.out.println("I see you're trying to delete something hmm... " + resourcePrimKey);
		
		// get the Structure information
		DDMStructure structure = journalArticle.getDDMStructure();
		String structureName = structure.getName();

		if(structureName.contains("Newsletter")) {
			// web content is newsletter
			try {
				_newsletterLocalService.deleteNewsletter(resourcePrimKey);
				System.out.println("Successfully deleted newsletter");
			} catch (PortalException e) {
				System.out.println("Unable to delete newsletter");
			}
		}
		else if(structureName.contains("Issue")) {
			// web content is issue
			try {
				_issueLocalService.deleteIssue(resourcePrimKey);
				System.out.println("Successfully deleted issue");
			} catch (PortalException e) {
				System.out.println("Unable to delete issue");
			}
		}
	}

//	public void onBeforeUpdate(JournalArticle journalArticle) {
//		System.out.print("\nI am here to update stuff.\n");
//		long articleId = journalArticle.getId();
//		System.out.println("ArticleID before update: " + articleId);
//
//	}
//	public void onAfterUpdate(JournalArticle journalArticle) {
//		System.out.print("\nI am here after update\n");
//		long articleId = journalArticle.getId();
//		System.out.println("ArticleID after update: " + articleId + "\n");
//
//	}
	
	@Reference
	ContentLocalService _contentLocalService;

	@Reference
	NewsletterLocalService _newsletterLocalService;
	
	@Reference
	IssueLocalService _issueLocalService;
}

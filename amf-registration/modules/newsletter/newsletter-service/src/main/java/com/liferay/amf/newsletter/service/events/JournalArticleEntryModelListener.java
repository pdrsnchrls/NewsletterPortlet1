package com.liferay.amf.newsletter.service.events;

import com.liferay.amf.newsletter.service.ContentLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.model.JournalArticle;
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
		double versionNo = journalArticle.getVersion();
		
		System.out.println("Primary Key: " + resourcePrimKey + " Version No: " + versionNo);
		//check if versionNo is 1 or not
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

	//figure out how to differentiate between a creation and a before update mf
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
	ContentLocalService _contentLocalService;

}

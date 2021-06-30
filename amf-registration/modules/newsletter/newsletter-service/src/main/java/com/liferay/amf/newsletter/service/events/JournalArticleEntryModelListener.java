package com.liferay.amf.newsletter.service.events;

import com.liferay.amf.newsletter.service.action.ProcessCreateWebContent;
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
		}
		else if(structureName.contains("Issue")) {
			// web content is issue
			issueType = true;
		}
		
		//parse content to get relevant information
		_processCreateWebContent.parseContent(articleContent, newsletterType, issueType);

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
	ProcessCreateWebContent _processCreateWebContent;

}

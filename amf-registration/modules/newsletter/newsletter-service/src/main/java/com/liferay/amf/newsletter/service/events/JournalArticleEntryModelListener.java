package com.liferay.amf.newsletter.service.events;

import com.liferay.amf.newsletter.service.IssueLocalService;
import com.liferay.amf.newsletter.service.NewsletterLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
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
		
		// get the Structure information
		DDMStructure structure = journalArticle.getDDMStructure();
		String structureName = structure.getName();
		boolean newsletterType = false, issueType = false;

		if(structureName.contains("Newsletter")) {
			// web content is newsletter
			newsletterType = true;
			System.out.println("Newsletter Content: " + articleContent);
			// search for name="title" then search for [CDATA[Everything here should be grabbed by
			//        program for respective title]

			//parse newsletter content to get issue_number, order_number, newsletter_title, newsletter_author, newsletter_content
			
		}
		else if(structureName.contains("Issue")) {
			// web content is issue
			issueType = true;
			System.out.println("Issue Content: " + articleContent);
			// search for name="title" then search for [CDATA[Everything here should be grabbed by
			//        program for respective title]
			
			//parse issue content to get issue_number, issue_title, issue_description, issue_date, byline
		}
	}

	
	@Reference
	private NewsletterLocalService _newsletterLocalService;
	
	@Reference
	private IssueLocalService _issueLocalService;
}

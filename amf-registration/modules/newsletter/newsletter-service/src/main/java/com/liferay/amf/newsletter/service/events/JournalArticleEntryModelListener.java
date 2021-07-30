package com.liferay.amf.newsletter.service.events;

import com.liferay.amf.newsletter.service.ContentLocalService;
import com.liferay.amf.newsletter.service.IssueLocalService;
import com.liferay.amf.newsletter.service.NewsletterLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

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

	public static final String ISSUE = "issue";

	public static final String NEWSLETTER = "newsletter";

	public void onAfterCreate(JournalArticle journalArticle) {
		long resourcePrimKey = journalArticle.getResourcePrimKey(); // a constant, can be used to check if we are adding or updating the database
		String articleContent = journalArticle.getContent();
		Integer articleStatus = journalArticle.getStatus();

		// get the Structure information

		DDMStructure structure = journalArticle.getDDMStructure();

		String structureName = structure.getName();
		boolean newsletterType = false, issueType = false;

		if (structureName.contains(NEWSLETTER)) {

			// web content is newsletter

			newsletterType = true;
		}
		else if (structureName.contains(ISSUE)) {

			// web content is issue

			issueType = true;
		}

		//parse content to get relevant information

		if (articleStatus == WorkflowConstants.STATUS_APPROVED) {
			_contentLocalService.parseContent(
				articleContent, resourcePrimKey, newsletterType, issueType);
		}
	}

	public void onAfterRemove(JournalArticle journalArticle) { // D in CRUD

		long resourcePrimKey = journalArticle.getResourcePrimKey();

		// get the Structure information

		DDMStructure structure = journalArticle.getDDMStructure();

		String structureName = structure.getName();

		if (structureName.contains(NEWSLETTER)) {

			// web content is newsletter

			try {
				_newsletterLocalService.deleteNewsletter(resourcePrimKey);
			}
			catch (PortalException pe) {
				System.out.println("Unable to delete newsletter");
			}
		}
		else if (structureName.contains(ISSUE)) {

			// web content is issue

			try {
				_issueLocalService.deleteIssue(resourcePrimKey);
			}
			catch (PortalException pe) {
				System.out.println("Unable to delete issue");
			}
		}
	}

	public void onAfterUpdate(JournalArticle journalArticle) {
		int articleStatus = journalArticle.getStatus();

		if (articleStatus == WorkflowConstants.STATUS_APPROVED) {
			onAfterCreate(journalArticle);
		}
		else {
			return;
		}
	}

	@Reference
	ContentLocalService _contentLocalService;

	@Reference
	NewsletterLocalService _newsletterLocalService;

	@Reference
	IssueLocalService _issueLocalService;

}
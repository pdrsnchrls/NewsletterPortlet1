package com.liferay.amf.newsletter.service.events;

import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.journal.model.JournalArticleModel;
import org.osgi.service.component.annotations.Component;


@Component(
	immediate=true,
	property= {
			
	},
	service=ModelListener.class
)
public class JournalArticleEntryModelListener extends BaseModelListener<JournalArticle> {

	public void onAfterCreate(JournalArticle journalArticle) {
		
	}
}

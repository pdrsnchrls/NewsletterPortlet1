package com.liferay.amf.newsletter.web.portlet.action;

import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.amf.newsletter.service.IssueLocalService;
import com.liferay.amf.newsletter.service.NewsletterLocalServiceUtil;
import com.liferay.amf.newsletter.web.constants.MVCCommandNames;
import com.liferay.amf.newsletter.web.constants.NewsletterPortletKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.util.*;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.*;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + NewsletterPortletKeys.NEWSLETTER,
		"mvc.command.name=" + MVCCommandNames.VIEW_SEARCH_RESULT
	},
	service = MVCRenderCommand.class
)
public class SearchArticlesMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest request, RenderResponse response)
		throws PortletException {

		String keywords = ParamUtil.getString(request, "keywords");

		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(
			request);

		SearchContext searchContext = SearchContextFactory.getInstance(
			httpServletRequest);

		searchContext.setKeywords(keywords);
		searchContext.setAttribute("paginationType", "more");
		searchContext.setStart(0);
		searchContext.setEnd(5);

		Indexer indexer = IndexerRegistryUtil.getIndexer(JournalArticle.class);

		Hits hits = null;

		try {
			hits = indexer.search(searchContext);
		}
		catch (SearchException se) {
			se.printStackTrace();
		}

		List<Newsletter> newsletters = new ArrayList<>();

		for (int i = 0; i < hits.getDocs().length; i++) {
			Document doc = hits.doc(i);

			String newsletterUID = GetterUtil.getString(doc.get(Field.UID));

			String[] info = newsletterUID.split("_");

			String id = info[info.length - 1];

			long newsletterId = Long.parseLong(id);

			Newsletter newsletter = null;

			try {
				newsletter = NewsletterLocalServiceUtil.getNewsletter(
					newsletterId);
				if (Validator.isNotNull(newsletter)) {
					newsletters.add(newsletter);
				}
			}
			catch (PortalException pe) {
				_log.error(pe.getLocalizedMessage());
				continue;
			}
			catch (SystemException se) {
				_log.error(se.getLocalizedMessage());
			}
		}

		request.setAttribute("issueLocalService", _issueLocalService);
		request.setAttribute("keywords", keywords);
		request.setAttribute("newsletters", newsletters);
		request.setAttribute("newslettersSize", newsletters.size());

		return "/search-view.jsp";
	}

	@Reference
	private IssueLocalService _issueLocalService;

	private static Log _log = LogFactoryUtil.getLog(
		"html.issuewebportlet.search_view_jsp");

}
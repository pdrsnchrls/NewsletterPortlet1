package com.liferay.amf.newsletter.web.portlet.action;

import com.liferay.amf.newsletter.web.constants.NewsletterPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate=true,
		property = {
			"javax.portlet.name=" + NewsletterPortletKeys.NEWSLETTER,
			"mvc.command.name=/newsletter-list/view", //set render command name for Iterator URL use this
			"mvc.command.name=/"
		},
		service = MVCRenderCommand.class
)
public class ViewNewsletterListMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest request, RenderResponse response) throws PortletException {
		// TODO Auto-generated method stub
		
		System.out.println("Getting the issue Number: " + request.getAttribute("issueNumber"));
		
		PortletURL iteratorURL = response.createRenderURL();
		iteratorURL.setParameter("mvcRenderCommandName", "/newsletter-list/view");
		
		
		return null;
	}

}

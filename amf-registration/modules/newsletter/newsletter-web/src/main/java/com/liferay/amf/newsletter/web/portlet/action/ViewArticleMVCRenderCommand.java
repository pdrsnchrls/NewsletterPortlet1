package com.liferay.amf.newsletter.web.portlet.action;

import com.liferay.amf.newsletter.web.constants.NewsletterPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

//@Component(
//        immediate=true,
//        property = {
//                "javax.portlet.name=" + NewsletterPortletKeys.NEWSLETTER,
//                "mvc.command.name=/article/view", //set render command name for Iterator URL use this
//                "mvc.command.name=/"
//        },
//        service = MVCRenderCommand.class
//)
public class ViewArticleMVCRenderCommand implements MVCRenderCommand{
    @Override
    public String render(RenderRequest request, RenderResponse response) throws PortletException {


        return null;
    }
}

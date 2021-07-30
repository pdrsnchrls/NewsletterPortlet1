package com.liferay.amf.newsletter.web.portlet.friendly.url;

import com.liferay.amf.newsletter.web.constants.NewsletterPortletKeys;
import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"com.liferay.portlet.friendly-url-routes=META-INF/resources/friendly-url-routes/routes.xml",
		"javax.portlet.name=" + NewsletterPortletKeys.NEWSLETTER
	},
	service = FriendlyURLMapper.class
)
public class IssueFriendlyURLMapper extends DefaultFriendlyURLMapper {

	@Override
	public String getMapping() {
		return _MAPPING;
	}

	private static final String _MAPPING = "newsletter";

}
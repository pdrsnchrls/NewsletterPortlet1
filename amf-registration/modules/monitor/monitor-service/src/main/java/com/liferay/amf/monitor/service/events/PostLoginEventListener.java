package com.liferay.amf.monitor.service.events;

import com.liferay.amf.monitor.service.EventLocalService;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserService;

import java.util.Date;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
				"key=login.events.post"
		},
		service = LifecycleAction.class
	)
public class PostLoginEventListener implements LifecycleAction {

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) 
			throws ActionException {	
		try {
			User user = _userService.getCurrentUser();
			long userId = user.getUserId();
			Date date = user.getLoginDate();
			String screenName = user.getScreenName();
			String eventType = "login";
			String ip = user.getLoginIP();
			
			_eventLocalService.addEvent(userId, date, screenName, eventType, ip);
			
		} catch (PortalException e) {
			e.printStackTrace();
		}

	}
	@Reference
	protected UserService _userService;
	
	@Reference
	protected EventLocalService _eventLocalService;
}

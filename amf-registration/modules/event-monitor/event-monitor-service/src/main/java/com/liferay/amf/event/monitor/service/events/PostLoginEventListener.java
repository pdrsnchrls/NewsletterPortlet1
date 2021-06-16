package com.liferay.amf.event.monitor.service.events;

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

/**
 * @author charles
 */
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
			System.out.println("processLifecycleEvent()");
			User user = _userService.getCurrentUser();
			//get login date
			Date date = user.getLoginDate();
			//get user screenname
			String screenName = user.getScreenName();
			//set event type
			String eventType = "login";
			//get client IP address
			String ip = user.getLoginIP();
			
			MailMessage message = new MailMessage();
			message.setSubject("Login Notification");
			message.setBody("User: " + screenName + " " + eventType + " at " + date + " with " + ip);
			InternetAddress toAddress = new InternetAddress(user.getEmailAddress());
			InternetAddress fromAddress = new InternetAddress("do-not-reply@liferay.com");
			message.setTo(toAddress);
			message.setFrom(fromAddress);
			_mailService.sendEmail(message);
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
		}
	}

	@Reference
	protected MailService _mailService;

	@Reference
	protected UserService _userService;
}
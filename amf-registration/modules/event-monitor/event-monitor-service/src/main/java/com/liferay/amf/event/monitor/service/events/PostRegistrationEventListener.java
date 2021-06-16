package com.liferay.amf.event.monitor.service.events;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
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
			"key=registration.events.post"
	},
	service = ModelListener.class
)
public class PostRegistrationEventListener extends BaseModelListener<User> {

	@Override
	public void onAfterCreate(User user) throws ModelListenerException {
		try {
			System.out.println("onAfterCreate()");

			//get user id
			long id = user.getUserId();
			//get user screenname
			String screenName = user.getScreenName();
			//set event type
			String eventType = "Registration";
			//get client IP address
			String ip = "0.0.0.0";
			//get timestamp
			Date date = user.getModifiedDate();
			
			MailMessage message = new MailMessage();
			message.setSubject("Registration Notification");
			message.setBody("User: " + id + ":" + screenName + " " + eventType + " at " + date + " with " + ip);
			InternetAddress toAddress = new InternetAddress(user.getEmailAddress());
			InternetAddress fromAddress = new InternetAddress("do-not-reply@liferay.com");
			message.setTo(toAddress);
			message.setFrom(fromAddress);
			_mailService.sendEmail(message);
		} catch (AddressException e) {
			e.printStackTrace();
		}
	}
	// TODO enter required service methods

	@Reference
	protected MailService _mailService;
	
	@Reference
	protected UserService _userService;
}
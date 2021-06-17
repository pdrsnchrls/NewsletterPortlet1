package com.liferay.amf.event.monitor.service.events;

import com.liferay.amf.event.monitor.service.EventLocalService;
import com.liferay.amf.event.monitor.service.impl.EventServiceImpl;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserService;

import java.util.Date;

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
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException{	
		System.out.println("processLifecycleEvent()");
		try {
			User user = _userService.getCurrentUser();
			Long userId = user.getUserId();
			String screenName = user.getScreenName();
			String eventType = "login";
			String ip = user.getLoginIP();
			Date date = user.getLastLoginDate();

			System.out.println("Adding event for " + screenName + " " + eventType + " "+ date + " " + ip + " " + userId);
			_eventLocalService.addEvent(userId, screenName, eventType, ip, date);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Reference
	protected EventLocalService _eventLocalService;
	@Reference
	protected UserService _userService;
	
}
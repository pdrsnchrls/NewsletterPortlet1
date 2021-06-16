package com.liferay.amf.event.monitor.service.events;

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
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
			throws ActionException {
			try {
				
				System.out.println("processLifecycleEvent()");
				User user;
				
				user = _userService.getCurrentUser();
				//get user id
				long id = user.getUserId();
				//get login date
				Date date = user.getLoginDate();
				//get user screenname
				String screenName = user.getScreenName();
				//set event type
				String eventType = "login";
				//get client IP address
				String ip = user.getLoginIP();
				System.out.println("Hey there bob");
//				try {
//					System.out.println("Adding event");
//					_eventService.addEvent(id, screenName, eventType, ip, date);
//				} catch (PortalException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			} catch (PortalException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

	@Reference
	protected UserService _userService;
	
	@Reference
	protected EventServiceImpl _eventService;
}
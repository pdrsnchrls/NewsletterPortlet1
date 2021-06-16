package com.liferay.amf.event.monitor.service.events;

import com.liferay.amf.event.monitor.service.impl.EventServiceImpl;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;

import java.util.Date;

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
		
		try {
			_eventService.addEvent(id, screenName, eventType, ip, date);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// TODO enter required service methods

	@Reference
	protected EventServiceImpl _eventService;
}
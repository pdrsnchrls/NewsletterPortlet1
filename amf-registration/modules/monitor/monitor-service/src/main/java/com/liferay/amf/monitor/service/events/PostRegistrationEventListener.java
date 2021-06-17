package com.liferay.amf.monitor.service.events;

import com.liferay.amf.monitor.service.EventLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component (
		immediate = true,
		property = {
				"key=registration.events.post"
		},
		service = ModelListener.class
)
public class PostRegistrationEventListener extends BaseModelListener<User>{
	
	@Override
	public void onAfterCreate(User user) throws ModelListenerException {		
		long id = user.getUserId();
		String screenName = user.getScreenName();
		String eventType = "registration";
		String ipAddress = "0.0.0.0";
		Date date = user.getModifiedDate();
		
		_eventLocalService.addEvent(id, date, screenName, eventType, ipAddress );
	}
	@Reference
	protected EventLocalService _eventLocalService;
}

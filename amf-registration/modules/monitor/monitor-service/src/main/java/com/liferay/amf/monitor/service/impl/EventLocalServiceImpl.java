/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.amf.monitor.service.impl;

import com.liferay.amf.monitor.model.Event;
import com.liferay.amf.monitor.service.EventLocalService;
import com.liferay.amf.monitor.service.EventService;
import com.liferay.amf.monitor.service.base.EventLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the event local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.amf.monitor.service.EventLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.amf.monitor.model.Event",
	service = AopService.class
)
public class EventLocalServiceImpl extends EventLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.liferay.amf.monitor.service.EventLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.amf.monitor.service.EventLocalServiceUtil</code>.
	 */
	
	public void addEvent(long userId, Date date, String screenName, String eventType, String ipAddress) {
		System.out.println("Made it to the add event thingy: " + screenName + " " + userId + " " + date + " " + eventType + " " + ipAddress);
		long eventId = counterLocalService.increment(Event.class.getName());
		Event event = createEvent(eventId);
		
		event.setUserId(userId);
		event.setDate(date);
		event.setScreenName(screenName);
		event.setEventType(eventType);
		event.setIpAddress(ipAddress);
		
		_eventLocalService.addEvent(event);
	}
	@Reference
	EventLocalService _eventLocalService;
}
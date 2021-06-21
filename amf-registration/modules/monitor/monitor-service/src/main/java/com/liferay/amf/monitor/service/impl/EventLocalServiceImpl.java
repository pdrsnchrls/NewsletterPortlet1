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
import com.liferay.amf.monitor.service.EventLocalServiceUtil;
import com.liferay.amf.monitor.service.base.EventLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		long eventId = counterLocalService.increment(Event.class.getName());
		Event event = eventLocalService.createEvent(eventId);
		
		event.setUserId(userId);
		event.setDate(date);
		event.setScreenName(screenName);
		event.setEventType(eventType);
		event.setIpAddress(ipAddress);
		
		
		eventLocalService.addEvent(event);
		try {
			User user = userLocalService.getUser(userId);
			long defCompId = 0;
			resourceLocalService.addResources(user.getCompanyId(), defCompId, userId, Event.class.getName(), event.getEventId(), false, 
					true, true);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			System.out.println("FAILED TO ADD RESOURCE");
			e.printStackTrace();
		}
	}
	
	// a method to return a list of all events
	public List<Event> getEventList() {
		
		int range = com.liferay.portal.kernel.dao.orm.QueryUtil.ALL_POS;
		
		List<Event> results = EventLocalServiceUtil.getEvents(range, range);
		return results;
	}
	
	public List<Event> getUserEventList() throws PortalException {
		// get all events in db
		List<Event> allEvents = getEventList();
		// initialize a list to put in results from particular user.
		List<Event> results = new ArrayList<Event>();
		// get current user's ID
		long userId = _userService.getCurrentUser().getUserId();

		// for all the events in the db that have the userid, add to results.
		for (int i = 0; i < allEvents.size(); i++) {
			if (allEvents.get(i).getUserId() == userId) {
				results.add(allEvents.get(i));
			}
		}
		
		return results;
	}
	
	@Reference
	protected UserService _userService;
}
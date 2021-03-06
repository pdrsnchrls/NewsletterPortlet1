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
import com.liferay.amf.monitor.service.base.EventServiceBaseImpl;
import com.liferay.amf.monitor.service.internal.security.permission.resource.EventPermission;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the event remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.amf.monitor.service.EventService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventServiceBaseImpl
 */
@Component(
	property = {
		"json.web.service.context.name=monitor",
		"json.web.service.context.path=Event"
	},
	service = AopService.class
)
public class EventServiceImpl extends EventServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use <code>com.liferay.amf.monitor.service.EventServiceUtil</code> to access the event remote service.
	 */
	
	//call contains method from EventPermission, if user has permission than call localservice method
	
	public List<Event> getEvents() throws PortalException {
		List<Event> results; // the list of event results that the user can view
				
		// user has permission to "VIEW_ALL" events
		if (_eventPermission.contains(getPermissionChecker(), 0, "VIEW_ALL")) {
			results = eventLocalService.getEventList();
		}
		else {
			results = eventLocalService.getUserEventList();
		}
		
		return results;
	}
	@Reference
	EventPermission _eventPermission;
	
}
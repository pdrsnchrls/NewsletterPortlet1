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

import com.liferay.amf.monitor.constants.MonitorConstants;
import com.liferay.amf.monitor.model.Event;
import com.liferay.amf.monitor.service.base.EventServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

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
	
//	public void addEvent(long userId, Date date, String screenName, String eventType, String ipAddress) 
//		throws PortalException {
//		_portletResourcePermission.check(
//				getPermissionChecker(), serviceContext.getScopeGroupId(),
//					ActionKeys.ADD_ENTRY
//				);
//		
//	}
//	/*	public Assignment addAssignment(
//		long groupId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
//		Date dueDate, ServiceContext serviceContext)
//		throws PortalException {
//
//		// Check permissions.
//		
//		_portletResourcePermission.check(
//			getPermissionChecker(), serviceContext.getScopeGroupId(),
//			ActionKeys.ADD_ENTRY);
//		
//		return assignmentLocalService.addAssignment(
//			groupId, titleMap, descriptionMap, dueDate, serviceContext);
//	}*/
//	
//	@Reference(
//		policy = ReferencePolicy.DYNAMIC,
//		policyOption = ReferencePolicyOption.GREEDY,
//		target = "(model.class.name=com.liferay.amf.monitor.model.Event)"
//	)
//	private volatile ModelResourcePermission<Event>
//		_assignmentModelResourcePermission;
//
//	@Reference(
//		policy = ReferencePolicy.DYNAMIC,
//		policyOption = ReferencePolicyOption.GREEDY,
//		target = "(resource.name=" + MonitorConstants.RESOURCE_NAME + ")"
//	)
//	private volatile PortletResourcePermission _portletResourcePermission;
}
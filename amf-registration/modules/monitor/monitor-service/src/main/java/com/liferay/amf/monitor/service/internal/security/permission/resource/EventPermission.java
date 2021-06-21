package com.liferay.amf.monitor.service.internal.security.permission.resource;

import com.liferay.amf.monitor.model.Event;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate=true,
		service=EventPermission.class
)
public class EventPermission {
//	public static boolean contains(
//			PermissionChecker permissionChecker, Event event, String actionId)
//		throws PortalException {
//
//		return _eventModelResourcePermission.contains(
//			permissionChecker, event, actionId);
//	}

	public boolean contains(
			PermissionChecker permissionChecker, long eventId, String actionId)
		throws PortalException {

		System.out.println("Inside EventPermission.contains");
		return permissionChecker.hasPermission(0,
			"com.liferay.amf.monitor.model.Event", eventId, actionId);
	}

//	@Reference(
//		target = "(model.class.name=com.liferay.amf.monitor.model.Event)",
//		unbind = "-"
//	)
//	protected void setEntryModelPermission(
//		ModelResourcePermission<Event> modelResourcePermission) {
//
//		_eventModelResourcePermission = modelResourcePermission;
//	}

//	private static ModelResourcePermission<Event>
//	_eventModelResourcePermission;
	
	
}

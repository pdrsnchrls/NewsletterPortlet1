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

	public boolean contains(
			PermissionChecker permissionChecker, long eventId, String actionId)
		throws PortalException {

		return permissionChecker.hasPermission(0,
			"com.liferay.amf.monitor.model.Event", eventId, actionId);
	}
	
	
}

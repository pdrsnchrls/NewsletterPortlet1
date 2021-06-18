package com.liferay.amf.monitor.service.internal.security.permission.resource;

import com.liferay.amf.monitor.constants.MonitorConstants;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate=true
)

public class EventTopLevelPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, String actionId) {
		return _portletResourcePermission.contains(permissionChecker, groupId, actionId);
	}
	
	@Reference(
			target = "(resource.name=" + MonitorConstants.RESOURCE_NAME + ".Event)",
			unbind = "-"
	)
	protected void setPortletResourcePermission(
		PortletResourcePermission portletResourcePermission) {

		_portletResourcePermission = portletResourcePermission;
	}

	private static PortletResourcePermission _portletResourcePermission;
}

package com.liferay.amf.search.service.internal.security.permission.resource;

import com.liferay.admin.kernel.util.PortalSearchApplicationType.Search;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate=true,
		service=SearchPermission.class
)
public class SearchPermission {

	public static final String RESOURCE_NAME = Search.class.getName();
	
	public boolean contains(
			PermissionChecker permissionChecker, long groupId, String actionId)
					throws PortalException {

		return permissionChecker.hasPermission(groupId,
				RESOURCE_NAME, groupId, actionId);
	}

}

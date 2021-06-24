package com.liferay.amf.search.results.service.internal.security.permission.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate=true,
		service=SearchResultsPermission.class
)
public class SearchResultsPermission {

	public static final String RESOURCE_NAME = "com_liferay_amf_search_results_web_portlet_SearchResultsPortlet";
	
	public boolean contains(
			PermissionChecker permissionChecker, long groupId, String actionId)
					throws PortalException {

		return permissionChecker.hasPermission(groupId, RESOURCE_NAME, 0, actionId);
	}
}

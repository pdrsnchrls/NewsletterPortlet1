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

package com.liferay.amf.newsletter.service;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Content. This utility wraps
 * <code>com.liferay.amf.newsletter.service.impl.ContentLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Charles Pederson
 * @see ContentLocalService
 * @generated
 */
@ProviderType
public class ContentLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.amf.newsletter.service.impl.ContentLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>ContentLocalService</code> via injection or a <code>ServiceTracker</code> or use <code>ContentLocalServiceUtil</code>.
	 */
	public static void parseContent(
		String articleContent, long resourcePrimKey, boolean newsletterType,
		boolean issueType) {

		getService().parseContent(
			articleContent, resourcePrimKey, newsletterType, issueType);
	}

	public static String splitString(
		String string, String searchName, Character stopChar) {

		return getService().splitString(string, searchName, stopChar);
	}

	public static ContentLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ContentLocalService, ContentLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ContentLocalService.class);

		ServiceTracker<ContentLocalService, ContentLocalService>
			serviceTracker =
				new ServiceTracker<ContentLocalService, ContentLocalService>(
					bundle.getBundleContext(), ContentLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
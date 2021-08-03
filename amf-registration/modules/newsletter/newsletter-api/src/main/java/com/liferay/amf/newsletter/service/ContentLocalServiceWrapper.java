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

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link ContentLocalService}.
 *
 * @author Charles Pederson
 * @see ContentLocalService
 * @generated
 */
@ProviderType
public class ContentLocalServiceWrapper
	implements ContentLocalService, ServiceWrapper<ContentLocalService> {

	public ContentLocalServiceWrapper(ContentLocalService contentLocalService) {
		_contentLocalService = contentLocalService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contentLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>ContentLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>ContentLocalServiceUtil</code>.
	 */
	@Override
	public void parseContent(
		String articleContent, long resourcePrimKey, boolean newsletterType,
		boolean issueType) {

		_contentLocalService.parseContent(
			articleContent, resourcePrimKey, newsletterType, issueType);
	}

	@Override
	public String splitString(
		String string, String searchName, Character stopChar) {

		return _contentLocalService.splitString(string, searchName, stopChar);
	}

	@Override
	public ContentLocalService getWrappedService() {
		return _contentLocalService;
	}

	@Override
	public void setWrappedService(ContentLocalService contentLocalService) {
		_contentLocalService = contentLocalService;
	}

	private ContentLocalService _contentLocalService;

}
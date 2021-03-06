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

package com.liferay.amf.newsletter.service.impl;

import com.liferay.amf.newsletter.service.IssueLocalService;
import com.liferay.amf.newsletter.service.NewsletterLocalService;
import com.liferay.amf.newsletter.service.base.ContentLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;

import java.util.HashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the content local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.amf.newsletter.service.ContentLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Charles Pederson
 * @see ContentLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.amf.newsletter.model.Content",
	service = AopService.class
)
public class ContentLocalServiceImpl extends ContentLocalServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.liferay.amf.newsletter.service.ContentLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.amf.newsletter.service.ContentLocalServiceUtil</code>.
	 */
	public void parseContent(
		String articleContent, long resourcePrimKey, boolean newsletterType,
		boolean issueType) {

		// if it is neither newsletter/issue, it will return

		if (!newsletterType && !issueType) {
			System.out.println("Error - cannot determine web content type...");

			return;
		}

		// split string for each dynamic element

		String[] content = articleContent.split("</dynamic-element>", 5);

		// a HashMap for storing the values of the newsletter/issue content

		HashMap<String, String> contentData = new HashMap<String, String>(); // a hashmap to store the data

		// data for storing and searching

		String[] key = new String[content.length], value = new String[content.length];
		String keySearchName = "name=\"", valueSearchName = "CDATA["; // string to search content for
		Character keyStopChar = '\"', valueStopChar = ']'; // character to stop at

		int i = 0; // iterator to keep track of positioning

		for (String c : content) {
			key[i] = splitString(c, keySearchName, keyStopChar);
			value[i] = splitString(c, valueSearchName, valueStopChar);
			contentData.put(key[i], value[i]);
			i++;
		}

		// get data from contentData map

		if (newsletterType) {
			//call newsletter local service
			_newsletterLocalService.checkNewsletterStatus(
				contentData, resourcePrimKey);
		}
		else if (issueType) {

			// persist to database

			_issueLocalService.checkIssueStatus(contentData, resourcePrimKey);
		}
	}

	public String splitString(
		String string, String searchName, Character stopChar) {

		String result = ""; // String to be returned

		// find location of searchName and add its length to start at right place

		int location = string.indexOf(searchName) + searchName.length();

		Character charValue = string.charAt(location); // get first character

		while (!charValue.equals(stopChar)) { // as long as the character is not the token to stop at...
			result += charValue;
			location++;
			charValue = string.charAt(location); // new character
		}

		return result;
	}

	@Reference
	protected IssueLocalService _issueLocalService;

	@Reference
	protected NewsletterLocalService _newsletterLocalService;

}
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

import com.liferay.amf.newsletter.service.base.NewsletterLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;

import java.util.HashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the newsletter local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.amf.newsletter.service.NewsletterLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Charles Pederson
 * @see NewsletterLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.amf.newsletter.model.Newsletter",
	service = AopService.class
)
public class NewsletterLocalServiceImpl extends NewsletterLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.liferay.amf.newsletter.service.NewsletterLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.amf.newsletter.service.NewsletterLocalServiceUtil</code>.
	 */

	public void parseContent(String articleContent) {
		
		//parse newsletter content to get issue_number, order_number, newsletter_title, newsletter_author, newsletter_content
		// search for name="title" then search for [CDATA[Everything here should be grabbed by
		//        program for respective title]
		String[] content = articleContent.split("</dynamic-element>", 5);
		
		HashMap<String, String> contentData = new HashMap<String, String>(); // a hashmap to store the data

		String[] key = new String[content.length], value = new String[content.length];
		String keySearchName = "name=\"", valueSearchName = "CDATA["; //String to search content for
		Character keyStopChar = '\"', valueStopChar = ']';
		
		int i = 0;
		for (String c : content) {
			key[i]=splitString(c, keySearchName, keyStopChar);
			value[i]=splitString(c, valueSearchName, valueStopChar);
			contentData.put(key[i], value[i]);
			i++;
		}
		
		for (HashMap.Entry<String, String> e : contentData.entrySet())
            System.out.println("Key: " + e.getKey()+ " Value: " + e.getValue());
		
	}
	
	public String splitString(String string, String searchName, Character stopChar) {

		String result = "";
		int location = string.indexOf(searchName) + searchName.length();
		Character charValue = string.charAt(location);
		while (!charValue.equals(stopChar)) {
			result += charValue;
			location++;
			charValue = string.charAt(location);
		}
		return result;
	}


}
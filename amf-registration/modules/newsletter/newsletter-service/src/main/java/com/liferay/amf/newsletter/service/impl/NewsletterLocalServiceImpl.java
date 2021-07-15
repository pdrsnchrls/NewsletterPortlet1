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

import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.amf.newsletter.service.base.NewsletterLocalServiceBaseImpl;
import com.liferay.amf.newsletter.service.constants.NewsletterConstants;
import com.liferay.amf.newsletter.service.persistence.NewsletterUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.*;

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
	
	public List<Newsletter> findByIssueNumber(long issueNumber) throws SystemException {

		List<Newsletter> unorderedNewsletterList = NewsletterUtil.findByIssueNumber(issueNumber);

		// Map for holding order and newsletter to order the thing
		Map<Integer, Long> newsletterTracker = new TreeMap<Integer, Long>();
		for (Newsletter n : unorderedNewsletterList)
			newsletterTracker.put(n.getOrder(), n.getNewsletterId());

		// Order list based on order number
		List<Newsletter> orderedNewsletterList = new ArrayList<Newsletter>();
		for (Map.Entry<Integer, Long> entry : newsletterTracker.entrySet())
			orderedNewsletterList.add(NewsletterUtil.fetchByPrimaryKey(entry.getValue()));

		return orderedNewsletterList;
	}
	
	public void checkNewsletterStatus(HashMap <String, String> contentData, long resourcePrimKey) {
		
		// if the newsletter already exists, then grab it and update it. (U in CRUD)
		try {
			Newsletter newsletter = newsletterLocalService.getNewsletter(resourcePrimKey);
			System.out.println("Newsletter exists...");
			setNewsletterAttributes(contentData, newsletter, resourcePrimKey);

			// persist to database
			newsletterLocalService.updateNewsletter(newsletter);
		}
		catch (PortalException e) { // otherwise  (C in CRUD)
			System.out.println("New newsletter!");
			Newsletter newsletter = newsletterLocalService.createNewsletter(resourcePrimKey);
			setNewsletterAttributes(contentData, newsletter, resourcePrimKey);

			// persist to database
			newsletterLocalService.addNewsletter(newsletter);
		}
		
	}
	
	public void setNewsletterAttributes(HashMap <String, String> contentData, Newsletter newsletter,
			long newsletterId) {
		
		// get data from hashmap contentData
		String issueNumber = contentData.get(NewsletterConstants.ISSUE_NUMBER);
		String orderNumber = contentData.get(NewsletterConstants.ORDER_NUMBER);
		String title = contentData.get(NewsletterConstants.NEWSLETTER_TITLE);
		String author = contentData.get(NewsletterConstants.NEWSLETTER_AUTHOR);
		String newsletterContent = contentData.get(NewsletterConstants.NEWSLETTER_CONTENT);
		
		System.out.println("\nHere is the info:\nIssue Number - " + Long.valueOf(issueNumber)
				+ "\nOrder Number - " + Integer.valueOf(orderNumber) + "\nTitle - " + title
				+ "\nAuthor - " + author + "\nContent - " + newsletterContent);
		
		// set data in newsletter
		newsletter.setAuthor(author);
		newsletter.setIssueNumber(Long.valueOf(issueNumber));
		newsletter.setOrder(Integer.valueOf(orderNumber));
		newsletter.setTitle(title);
		newsletter.setContent(newsletterContent);
		newsletter.setNewsletterId(newsletterId);
	}

}
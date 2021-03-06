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

import com.liferay.amf.newsletter.model.Issue;
import com.liferay.amf.newsletter.service.IssueLocalServiceUtil;
import com.liferay.amf.newsletter.service.base.IssueLocalServiceBaseImpl;
import com.liferay.amf.newsletter.service.constants.NewsletterConstants;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;

import java.util.*;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the issue local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.amf.newsletter.service.IssueLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Charles Pederson
 * @see IssueLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.amf.newsletter.model.Issue",
	service = AopService.class
)
public class IssueLocalServiceImpl extends IssueLocalServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.liferay.amf.newsletter.service.IssueLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>IssueLocalServiceUtil</code>.
	 */
	public void checkIssueStatus(
		HashMap<String, String> contentData, long resourcePrimKey) {

		// if the issue already exists, then grab it and update it. (U in CRUD)

		try {
			Issue issue = issueLocalService.getIssue(resourcePrimKey);

			setIssueAttributes(contentData, issue, resourcePrimKey);

			// persist to database

			issueLocalService.updateIssue(issue);
		}
		catch (PortalException e) { // otherwise  (C in CRUD)
			Issue issue = issueLocalService.createIssue(resourcePrimKey);

			setIssueAttributes(contentData, issue, resourcePrimKey);

			// persist to database

			issueLocalService.addIssue(issue);
		}
	}

	public String formatIssueDate(java.sql.Timestamp date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"MMMM dd, yyyy");

		String formattedDate = simpleDateFormat.format(date);

		return formattedDate;
	}

	public List<Integer> getAllIssueYears() {
		ClassLoader classLoader = getClass().getClassLoader();

		DynamicQuery issueQuery = DynamicQueryFactoryUtil.forClass(
			Issue.class, classLoader
		).setProjection(
			ProjectionFactoryUtil.sqlGroupProjection(
				"year(issueDate) as year", "year", new String[] {"year"},
				new Type[] {Type.INTEGER})
		);

		List<Integer> issueYears = issueLocalService.dynamicQuery(issueQuery);

		return issueYears;
	}

	public Date getEndDateOfYear(int year) {
		Calendar calendarEnd = Calendar.getInstance();

		calendarEnd.set(Calendar.DAY_OF_MONTH, 31);
		calendarEnd.set(Calendar.MONTH, Calendar.DECEMBER);
		calendarEnd.set(Calendar.YEAR, year);
		calendarEnd.set(Calendar.HOUR, 0);
		calendarEnd.set(Calendar.MINUTE, 0);
		calendarEnd.set(Calendar.SECOND, 0);

		return calendarEnd.getTime();
	}

	public List<Integer> getIssueMonthsByYear(Integer year) {
		ClassLoader classLoader = getClass().getClassLoader();

		Date startDate = getStartDateOfYear(year);
		Date endDate = getEndDateOfYear(year);

		DynamicQuery monthQuery = DynamicQueryFactoryUtil.forClass(
			Issue.class, "issue", classLoader
		).setProjection(
			ProjectionFactoryUtil.sqlGroupProjection(
				"month(issueDate) as month", "month", new String[] {"month"},
				new Type[] {Type.INTEGER})
		).add(
			PropertyFactoryUtil.forName(
				"issueDate"
			).between(
				startDate, endDate
			)
		);

		List<Integer> months = issueLocalService.dynamicQuery(monthQuery);

		return months;
	}

	public List<Issue> getIssuesByYearAndMonth(int year, int month) {
		ClassLoader classLoader = getClass().getClassLoader();
		List<Issue> issueList = new ArrayList<>();

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date startDate = calendar.getTime();

		calendar.set(
			Calendar.DAY_OF_MONTH,
			calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		Date endDate = calendar.getTime();

		DynamicQuery issueQuery = DynamicQueryFactoryUtil.forClass(
			Issue.class, classLoader
		).add(
			RestrictionsFactoryUtil.between("issueDate", startDate, endDate)
		).addOrder(
			OrderFactoryUtil.desc("issueDate")
		);

		try {
			issueList = IssueLocalServiceUtil.dynamicQuery(issueQuery);
		}
		catch (SystemException se) {
			System.out.println("Query Failed...\n");
			se.printStackTrace();
		}

		return issueList;
	}

	public String getMonthForInt(int num) {
		String month = "wrong";
		DateFormatSymbols dfs = new DateFormatSymbols();

		String[] months = dfs.getMonths();

		if ((num >= 0) && (num <= 11)) {
			month = months[num];
		}

		return month;
	}

	public Date getStartDateOfYear(int year) {
		Calendar calendarStart = Calendar.getInstance();

		calendarStart.set(Calendar.DAY_OF_MONTH, 1);
		calendarStart.set(Calendar.MONTH, Calendar.JANUARY);
		calendarStart.set(Calendar.YEAR, year);
		calendarStart.set(Calendar.HOUR, 0);
		calendarStart.set(Calendar.MINUTE, 0);
		calendarStart.set(Calendar.SECOND, 0);

		return calendarStart.getTime();
	}

	public void setIssueAttributes(
		HashMap<String, String> contentData, Issue issue, long issueId) {

		//call issue local service and do the same thang
		String issueNumber = contentData.get(NewsletterConstants.ISSUE_NUMBER);
		String issueTitle = contentData.get(NewsletterConstants.ISSUE_TITLE);
		String issueDescription = contentData.get(
			NewsletterConstants.ISSUE_DESCRIPTION);
		String issueDate = contentData.get(NewsletterConstants.ISSUE_DATE);

		String authorByline = contentData.get(NewsletterConstants.BYLINE);

		// set issue data

		issue.setIssueNumber(Long.valueOf(issueNumber));
		issue.setTitle(issueTitle);
		issue.setDescription(issueDescription);
		issue.setIssueDate(java.sql.Date.valueOf(issueDate)); //hardcode to sql for inputting date...
		issue.setByline(authorByline);
		issue.setIssueId(issueId);
	}

}
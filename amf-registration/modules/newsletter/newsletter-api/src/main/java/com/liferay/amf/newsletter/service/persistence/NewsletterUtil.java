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

package com.liferay.amf.newsletter.service.persistence;

import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the newsletter service. This utility wraps <code>com.liferay.amf.newsletter.service.persistence.impl.NewsletterPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Charles Pederson
 * @see NewsletterPersistence
 * @generated
 */
@ProviderType
public class NewsletterUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Newsletter newsletter) {
		getPersistence().clearCache(newsletter);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Newsletter> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Newsletter> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Newsletter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Newsletter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Newsletter> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Newsletter update(Newsletter newsletter) {
		return getPersistence().update(newsletter);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Newsletter update(
		Newsletter newsletter, ServiceContext serviceContext) {

		return getPersistence().update(newsletter, serviceContext);
	}

	/**
	 * Returns all the newsletters where issueNumber = &#63;.
	 *
	 * @param issueNumber the issue number
	 * @return the matching newsletters
	 */
	public static List<Newsletter> findByIssueNumber(long issueNumber) {
		return getPersistence().findByIssueNumber(issueNumber);
	}

	/**
	 * Returns a range of all the newsletters where issueNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NewsletterModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param issueNumber the issue number
	 * @param start the lower bound of the range of newsletters
	 * @param end the upper bound of the range of newsletters (not inclusive)
	 * @return the range of matching newsletters
	 */
	public static List<Newsletter> findByIssueNumber(
		long issueNumber, int start, int end) {

		return getPersistence().findByIssueNumber(issueNumber, start, end);
	}

	/**
	 * Returns an ordered range of all the newsletters where issueNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NewsletterModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByIssueNumber(long, int, int, OrderByComparator)}
	 * @param issueNumber the issue number
	 * @param start the lower bound of the range of newsletters
	 * @param end the upper bound of the range of newsletters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching newsletters
	 */
	@Deprecated
	public static List<Newsletter> findByIssueNumber(
		long issueNumber, int start, int end,
		OrderByComparator<Newsletter> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByIssueNumber(
			issueNumber, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns an ordered range of all the newsletters where issueNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NewsletterModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param issueNumber the issue number
	 * @param start the lower bound of the range of newsletters
	 * @param end the upper bound of the range of newsletters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching newsletters
	 */
	public static List<Newsletter> findByIssueNumber(
		long issueNumber, int start, int end,
		OrderByComparator<Newsletter> orderByComparator) {

		return getPersistence().findByIssueNumber(
			issueNumber, start, end, orderByComparator);
	}

	/**
	 * Returns the first newsletter in the ordered set where issueNumber = &#63;.
	 *
	 * @param issueNumber the issue number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching newsletter
	 * @throws NoSuchNewsletterException if a matching newsletter could not be found
	 */
	public static Newsletter findByIssueNumber_First(
			long issueNumber, OrderByComparator<Newsletter> orderByComparator)
		throws com.liferay.amf.newsletter.exception.NoSuchNewsletterException {

		return getPersistence().findByIssueNumber_First(
			issueNumber, orderByComparator);
	}

	/**
	 * Returns the first newsletter in the ordered set where issueNumber = &#63;.
	 *
	 * @param issueNumber the issue number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching newsletter, or <code>null</code> if a matching newsletter could not be found
	 */
	public static Newsletter fetchByIssueNumber_First(
		long issueNumber, OrderByComparator<Newsletter> orderByComparator) {

		return getPersistence().fetchByIssueNumber_First(
			issueNumber, orderByComparator);
	}

	/**
	 * Returns the last newsletter in the ordered set where issueNumber = &#63;.
	 *
	 * @param issueNumber the issue number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching newsletter
	 * @throws NoSuchNewsletterException if a matching newsletter could not be found
	 */
	public static Newsletter findByIssueNumber_Last(
			long issueNumber, OrderByComparator<Newsletter> orderByComparator)
		throws com.liferay.amf.newsletter.exception.NoSuchNewsletterException {

		return getPersistence().findByIssueNumber_Last(
			issueNumber, orderByComparator);
	}

	/**
	 * Returns the last newsletter in the ordered set where issueNumber = &#63;.
	 *
	 * @param issueNumber the issue number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching newsletter, or <code>null</code> if a matching newsletter could not be found
	 */
	public static Newsletter fetchByIssueNumber_Last(
		long issueNumber, OrderByComparator<Newsletter> orderByComparator) {

		return getPersistence().fetchByIssueNumber_Last(
			issueNumber, orderByComparator);
	}

	/**
	 * Returns the newsletters before and after the current newsletter in the ordered set where issueNumber = &#63;.
	 *
	 * @param newsletterId the primary key of the current newsletter
	 * @param issueNumber the issue number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next newsletter
	 * @throws NoSuchNewsletterException if a newsletter with the primary key could not be found
	 */
	public static Newsletter[] findByIssueNumber_PrevAndNext(
			long newsletterId, long issueNumber,
			OrderByComparator<Newsletter> orderByComparator)
		throws com.liferay.amf.newsletter.exception.NoSuchNewsletterException {

		return getPersistence().findByIssueNumber_PrevAndNext(
			newsletterId, issueNumber, orderByComparator);
	}

	/**
	 * Removes all the newsletters where issueNumber = &#63; from the database.
	 *
	 * @param issueNumber the issue number
	 */
	public static void removeByIssueNumber(long issueNumber) {
		getPersistence().removeByIssueNumber(issueNumber);
	}

	/**
	 * Returns the number of newsletters where issueNumber = &#63;.
	 *
	 * @param issueNumber the issue number
	 * @return the number of matching newsletters
	 */
	public static int countByIssueNumber(long issueNumber) {
		return getPersistence().countByIssueNumber(issueNumber);
	}

	/**
	 * Caches the newsletter in the entity cache if it is enabled.
	 *
	 * @param newsletter the newsletter
	 */
	public static void cacheResult(Newsletter newsletter) {
		getPersistence().cacheResult(newsletter);
	}

	/**
	 * Caches the newsletters in the entity cache if it is enabled.
	 *
	 * @param newsletters the newsletters
	 */
	public static void cacheResult(List<Newsletter> newsletters) {
		getPersistence().cacheResult(newsletters);
	}

	/**
	 * Creates a new newsletter with the primary key. Does not add the newsletter to the database.
	 *
	 * @param newsletterId the primary key for the new newsletter
	 * @return the new newsletter
	 */
	public static Newsletter create(long newsletterId) {
		return getPersistence().create(newsletterId);
	}

	/**
	 * Removes the newsletter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsletterId the primary key of the newsletter
	 * @return the newsletter that was removed
	 * @throws NoSuchNewsletterException if a newsletter with the primary key could not be found
	 */
	public static Newsletter remove(long newsletterId)
		throws com.liferay.amf.newsletter.exception.NoSuchNewsletterException {

		return getPersistence().remove(newsletterId);
	}

	public static Newsletter updateImpl(Newsletter newsletter) {
		return getPersistence().updateImpl(newsletter);
	}

	/**
	 * Returns the newsletter with the primary key or throws a <code>NoSuchNewsletterException</code> if it could not be found.
	 *
	 * @param newsletterId the primary key of the newsletter
	 * @return the newsletter
	 * @throws NoSuchNewsletterException if a newsletter with the primary key could not be found
	 */
	public static Newsletter findByPrimaryKey(long newsletterId)
		throws com.liferay.amf.newsletter.exception.NoSuchNewsletterException {

		return getPersistence().findByPrimaryKey(newsletterId);
	}

	/**
	 * Returns the newsletter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsletterId the primary key of the newsletter
	 * @return the newsletter, or <code>null</code> if a newsletter with the primary key could not be found
	 */
	public static Newsletter fetchByPrimaryKey(long newsletterId) {
		return getPersistence().fetchByPrimaryKey(newsletterId);
	}

	/**
	 * Returns all the newsletters.
	 *
	 * @return the newsletters
	 */
	public static List<Newsletter> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the newsletters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NewsletterModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of newsletters
	 * @param end the upper bound of the range of newsletters (not inclusive)
	 * @return the range of newsletters
	 */
	public static List<Newsletter> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the newsletters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NewsletterModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findAll(int, int, OrderByComparator)}
	 * @param start the lower bound of the range of newsletters
	 * @param end the upper bound of the range of newsletters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of newsletters
	 */
	@Deprecated
	public static List<Newsletter> findAll(
		int start, int end, OrderByComparator<Newsletter> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns an ordered range of all the newsletters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NewsletterModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of newsletters
	 * @param end the upper bound of the range of newsletters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of newsletters
	 */
	public static List<Newsletter> findAll(
		int start, int end, OrderByComparator<Newsletter> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Removes all the newsletters from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of newsletters.
	 *
	 * @return the number of newsletters
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static NewsletterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NewsletterPersistence, NewsletterPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(NewsletterPersistence.class);

		ServiceTracker<NewsletterPersistence, NewsletterPersistence>
			serviceTracker =
				new ServiceTracker
					<NewsletterPersistence, NewsletterPersistence>(
						bundle.getBundleContext(), NewsletterPersistence.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}
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

package com.liferay.amf.newsletter.service.persistence.impl;

import com.liferay.amf.newsletter.exception.NoSuchNewsletterException;
import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.amf.newsletter.model.impl.NewsletterImpl;
import com.liferay.amf.newsletter.model.impl.NewsletterModelImpl;
import com.liferay.amf.newsletter.service.persistence.NewsletterPersistence;
import com.liferay.amf.newsletter.service.persistence.impl.constants.NewsletterPersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the newsletter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Charles Pederson
 * @generated
 */
@Component(service = NewsletterPersistence.class)
@ProviderType
public class NewsletterPersistenceImpl
	extends BasePersistenceImpl<Newsletter> implements NewsletterPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>NewsletterUtil</code> to access the newsletter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		NewsletterImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByIssueId;
	private FinderPath _finderPathWithoutPaginationFindByIssueId;
	private FinderPath _finderPathCountByIssueId;

	/**
	 * Returns all the newsletters where issueId = &#63;.
	 *
	 * @param issueId the issue ID
	 * @return the matching newsletters
	 */
	@Override
	public List<Newsletter> findByIssueId(long issueId) {
		return findByIssueId(
			issueId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the newsletters where issueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NewsletterModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param issueId the issue ID
	 * @param start the lower bound of the range of newsletters
	 * @param end the upper bound of the range of newsletters (not inclusive)
	 * @return the range of matching newsletters
	 */
	@Override
	public List<Newsletter> findByIssueId(long issueId, int start, int end) {
		return findByIssueId(issueId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the newsletters where issueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NewsletterModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByIssueId(long, int, int, OrderByComparator)}
	 * @param issueId the issue ID
	 * @param start the lower bound of the range of newsletters
	 * @param end the upper bound of the range of newsletters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching newsletters
	 */
	@Deprecated
	@Override
	public List<Newsletter> findByIssueId(
		long issueId, int start, int end,
		OrderByComparator<Newsletter> orderByComparator,
		boolean useFinderCache) {

		return findByIssueId(issueId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the newsletters where issueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NewsletterModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param issueId the issue ID
	 * @param start the lower bound of the range of newsletters
	 * @param end the upper bound of the range of newsletters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching newsletters
	 */
	@Override
	public List<Newsletter> findByIssueId(
		long issueId, int start, int end,
		OrderByComparator<Newsletter> orderByComparator) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByIssueId;
			finderArgs = new Object[] {issueId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByIssueId;
			finderArgs = new Object[] {issueId, start, end, orderByComparator};
		}

		List<Newsletter> list = (List<Newsletter>)finderCache.getResult(
			finderPath, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Newsletter newsletter : list) {
				if ((issueId != newsletter.getIssueId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_NEWSLETTER_WHERE);

			query.append(_FINDER_COLUMN_ISSUEID_ISSUEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(NewsletterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(issueId);

				if (!pagination) {
					list = (List<Newsletter>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Newsletter>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first newsletter in the ordered set where issueId = &#63;.
	 *
	 * @param issueId the issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching newsletter
	 * @throws NoSuchNewsletterException if a matching newsletter could not be found
	 */
	@Override
	public Newsletter findByIssueId_First(
			long issueId, OrderByComparator<Newsletter> orderByComparator)
		throws NoSuchNewsletterException {

		Newsletter newsletter = fetchByIssueId_First(
			issueId, orderByComparator);

		if (newsletter != null) {
			return newsletter;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("issueId=");
		msg.append(issueId);

		msg.append("}");

		throw new NoSuchNewsletterException(msg.toString());
	}

	/**
	 * Returns the first newsletter in the ordered set where issueId = &#63;.
	 *
	 * @param issueId the issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching newsletter, or <code>null</code> if a matching newsletter could not be found
	 */
	@Override
	public Newsletter fetchByIssueId_First(
		long issueId, OrderByComparator<Newsletter> orderByComparator) {

		List<Newsletter> list = findByIssueId(issueId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last newsletter in the ordered set where issueId = &#63;.
	 *
	 * @param issueId the issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching newsletter
	 * @throws NoSuchNewsletterException if a matching newsletter could not be found
	 */
	@Override
	public Newsletter findByIssueId_Last(
			long issueId, OrderByComparator<Newsletter> orderByComparator)
		throws NoSuchNewsletterException {

		Newsletter newsletter = fetchByIssueId_Last(issueId, orderByComparator);

		if (newsletter != null) {
			return newsletter;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("issueId=");
		msg.append(issueId);

		msg.append("}");

		throw new NoSuchNewsletterException(msg.toString());
	}

	/**
	 * Returns the last newsletter in the ordered set where issueId = &#63;.
	 *
	 * @param issueId the issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching newsletter, or <code>null</code> if a matching newsletter could not be found
	 */
	@Override
	public Newsletter fetchByIssueId_Last(
		long issueId, OrderByComparator<Newsletter> orderByComparator) {

		int count = countByIssueId(issueId);

		if (count == 0) {
			return null;
		}

		List<Newsletter> list = findByIssueId(
			issueId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the newsletters before and after the current newsletter in the ordered set where issueId = &#63;.
	 *
	 * @param newsletterId the primary key of the current newsletter
	 * @param issueId the issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next newsletter
	 * @throws NoSuchNewsletterException if a newsletter with the primary key could not be found
	 */
	@Override
	public Newsletter[] findByIssueId_PrevAndNext(
			long newsletterId, long issueId,
			OrderByComparator<Newsletter> orderByComparator)
		throws NoSuchNewsletterException {

		Newsletter newsletter = findByPrimaryKey(newsletterId);

		Session session = null;

		try {
			session = openSession();

			Newsletter[] array = new NewsletterImpl[3];

			array[0] = getByIssueId_PrevAndNext(
				session, newsletter, issueId, orderByComparator, true);

			array[1] = newsletter;

			array[2] = getByIssueId_PrevAndNext(
				session, newsletter, issueId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Newsletter getByIssueId_PrevAndNext(
		Session session, Newsletter newsletter, long issueId,
		OrderByComparator<Newsletter> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_NEWSLETTER_WHERE);

		query.append(_FINDER_COLUMN_ISSUEID_ISSUEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(NewsletterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(issueId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(newsletter)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Newsletter> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the newsletters where issueId = &#63; from the database.
	 *
	 * @param issueId the issue ID
	 */
	@Override
	public void removeByIssueId(long issueId) {
		for (Newsletter newsletter :
				findByIssueId(
					issueId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(newsletter);
		}
	}

	/**
	 * Returns the number of newsletters where issueId = &#63;.
	 *
	 * @param issueId the issue ID
	 * @return the number of matching newsletters
	 */
	@Override
	public int countByIssueId(long issueId) {
		FinderPath finderPath = _finderPathCountByIssueId;

		Object[] finderArgs = new Object[] {issueId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_NEWSLETTER_WHERE);

			query.append(_FINDER_COLUMN_ISSUEID_ISSUEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(issueId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ISSUEID_ISSUEID_2 =
		"newsletter.issueId = ?";

	public NewsletterPersistenceImpl() {
		setModelClass(Newsletter.class);

		setModelImplClass(NewsletterImpl.class);
		setModelPKClass(long.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("order", "order_");

		setDBColumnNames(dbColumnNames);
	}

	/**
	 * Caches the newsletter in the entity cache if it is enabled.
	 *
	 * @param newsletter the newsletter
	 */
	@Override
	public void cacheResult(Newsletter newsletter) {
		entityCache.putResult(
			entityCacheEnabled, NewsletterImpl.class,
			newsletter.getPrimaryKey(), newsletter);

		newsletter.resetOriginalValues();
	}

	/**
	 * Caches the newsletters in the entity cache if it is enabled.
	 *
	 * @param newsletters the newsletters
	 */
	@Override
	public void cacheResult(List<Newsletter> newsletters) {
		for (Newsletter newsletter : newsletters) {
			if (entityCache.getResult(
					entityCacheEnabled, NewsletterImpl.class,
					newsletter.getPrimaryKey()) == null) {

				cacheResult(newsletter);
			}
			else {
				newsletter.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all newsletters.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NewsletterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the newsletter.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Newsletter newsletter) {
		entityCache.removeResult(
			entityCacheEnabled, NewsletterImpl.class,
			newsletter.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Newsletter> newsletters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Newsletter newsletter : newsletters) {
			entityCache.removeResult(
				entityCacheEnabled, NewsletterImpl.class,
				newsletter.getPrimaryKey());
		}
	}

	/**
	 * Creates a new newsletter with the primary key. Does not add the newsletter to the database.
	 *
	 * @param newsletterId the primary key for the new newsletter
	 * @return the new newsletter
	 */
	@Override
	public Newsletter create(long newsletterId) {
		Newsletter newsletter = new NewsletterImpl();

		newsletter.setNew(true);
		newsletter.setPrimaryKey(newsletterId);

		return newsletter;
	}

	/**
	 * Removes the newsletter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsletterId the primary key of the newsletter
	 * @return the newsletter that was removed
	 * @throws NoSuchNewsletterException if a newsletter with the primary key could not be found
	 */
	@Override
	public Newsletter remove(long newsletterId)
		throws NoSuchNewsletterException {

		return remove((Serializable)newsletterId);
	}

	/**
	 * Removes the newsletter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the newsletter
	 * @return the newsletter that was removed
	 * @throws NoSuchNewsletterException if a newsletter with the primary key could not be found
	 */
	@Override
	public Newsletter remove(Serializable primaryKey)
		throws NoSuchNewsletterException {

		Session session = null;

		try {
			session = openSession();

			Newsletter newsletter = (Newsletter)session.get(
				NewsletterImpl.class, primaryKey);

			if (newsletter == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNewsletterException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(newsletter);
		}
		catch (NoSuchNewsletterException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Newsletter removeImpl(Newsletter newsletter) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(newsletter)) {
				newsletter = (Newsletter)session.get(
					NewsletterImpl.class, newsletter.getPrimaryKeyObj());
			}

			if (newsletter != null) {
				session.delete(newsletter);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (newsletter != null) {
			clearCache(newsletter);
		}

		return newsletter;
	}

	@Override
	public Newsletter updateImpl(Newsletter newsletter) {
		boolean isNew = newsletter.isNew();

		if (!(newsletter instanceof NewsletterModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(newsletter.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(newsletter);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in newsletter proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Newsletter implementation " +
					newsletter.getClass());
		}

		NewsletterModelImpl newsletterModelImpl =
			(NewsletterModelImpl)newsletter;

		Session session = null;

		try {
			session = openSession();

			if (newsletter.isNew()) {
				session.save(newsletter);

				newsletter.setNew(false);
			}
			else {
				newsletter = (Newsletter)session.merge(newsletter);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {newsletterModelImpl.getIssueId()};

			finderCache.removeResult(_finderPathCountByIssueId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByIssueId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((newsletterModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByIssueId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					newsletterModelImpl.getOriginalIssueId()
				};

				finderCache.removeResult(_finderPathCountByIssueId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByIssueId, args);

				args = new Object[] {newsletterModelImpl.getIssueId()};

				finderCache.removeResult(_finderPathCountByIssueId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByIssueId, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, NewsletterImpl.class,
			newsletter.getPrimaryKey(), newsletter, false);

		newsletter.resetOriginalValues();

		return newsletter;
	}

	/**
	 * Returns the newsletter with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the newsletter
	 * @return the newsletter
	 * @throws NoSuchNewsletterException if a newsletter with the primary key could not be found
	 */
	@Override
	public Newsletter findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNewsletterException {

		Newsletter newsletter = fetchByPrimaryKey(primaryKey);

		if (newsletter == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNewsletterException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return newsletter;
	}

	/**
	 * Returns the newsletter with the primary key or throws a <code>NoSuchNewsletterException</code> if it could not be found.
	 *
	 * @param newsletterId the primary key of the newsletter
	 * @return the newsletter
	 * @throws NoSuchNewsletterException if a newsletter with the primary key could not be found
	 */
	@Override
	public Newsletter findByPrimaryKey(long newsletterId)
		throws NoSuchNewsletterException {

		return findByPrimaryKey((Serializable)newsletterId);
	}

	/**
	 * Returns the newsletter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsletterId the primary key of the newsletter
	 * @return the newsletter, or <code>null</code> if a newsletter with the primary key could not be found
	 */
	@Override
	public Newsletter fetchByPrimaryKey(long newsletterId) {
		return fetchByPrimaryKey((Serializable)newsletterId);
	}

	/**
	 * Returns all the newsletters.
	 *
	 * @return the newsletters
	 */
	@Override
	public List<Newsletter> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Newsletter> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<Newsletter> findAll(
		int start, int end, OrderByComparator<Newsletter> orderByComparator,
		boolean useFinderCache) {

		return findAll(start, end, orderByComparator);
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
	@Override
	public List<Newsletter> findAll(
		int start, int end, OrderByComparator<Newsletter> orderByComparator) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Newsletter> list = (List<Newsletter>)finderCache.getResult(
			finderPath, finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NEWSLETTER);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NEWSLETTER;

				if (pagination) {
					sql = sql.concat(NewsletterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Newsletter>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Newsletter>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the newsletters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Newsletter newsletter : findAll()) {
			remove(newsletter);
		}
	}

	/**
	 * Returns the number of newsletters.
	 *
	 * @return the number of newsletters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NEWSLETTER);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "newsletterId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_NEWSLETTER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return NewsletterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the newsletter persistence.
	 */
	@Activate
	public void activate() {
		NewsletterModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		NewsletterModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, NewsletterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, NewsletterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByIssueId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, NewsletterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByIssueId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByIssueId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, NewsletterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByIssueId",
			new String[] {Long.class.getName()},
			NewsletterModelImpl.ISSUEID_COLUMN_BITMASK |
			NewsletterModelImpl.ORDER_COLUMN_BITMASK);

		_finderPathCountByIssueId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByIssueId",
			new String[] {Long.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(NewsletterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = NewsletterPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.liferay.amf.newsletter.model.Newsletter"),
			true);
	}

	@Override
	@Reference(
		target = NewsletterPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = NewsletterPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_NEWSLETTER =
		"SELECT newsletter FROM Newsletter newsletter";

	private static final String _SQL_SELECT_NEWSLETTER_WHERE =
		"SELECT newsletter FROM Newsletter newsletter WHERE ";

	private static final String _SQL_COUNT_NEWSLETTER =
		"SELECT COUNT(newsletter) FROM Newsletter newsletter";

	private static final String _SQL_COUNT_NEWSLETTER_WHERE =
		"SELECT COUNT(newsletter) FROM Newsletter newsletter WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "newsletter.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Newsletter exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Newsletter exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		NewsletterPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"order"});

	static {
		try {
			Class.forName(NewsletterPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException cnfe) {
			throw new ExceptionInInitializerError(cnfe);
		}
	}

}
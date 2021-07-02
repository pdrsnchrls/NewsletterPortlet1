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

import com.liferay.amf.newsletter.exception.NoSuchIssueException;
import com.liferay.amf.newsletter.model.Issue;
import com.liferay.amf.newsletter.model.impl.IssueImpl;
import com.liferay.amf.newsletter.model.impl.IssueModelImpl;
import com.liferay.amf.newsletter.service.persistence.IssuePersistence;
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

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the issue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Charles Pederson
 * @generated
 */
@Component(service = IssuePersistence.class)
@ProviderType
public class IssuePersistenceImpl
	extends BasePersistenceImpl<Issue> implements IssuePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>IssueUtil</code> to access the issue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		IssueImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByIssueDate;
	private FinderPath _finderPathWithoutPaginationFindByIssueDate;
	private FinderPath _finderPathCountByIssueDate;

	/**
	 * Returns all the issues where issueDate = &#63;.
	 *
	 * @param issueDate the issue date
	 * @return the matching issues
	 */
	@Override
	public List<Issue> findByIssueDate(Date issueDate) {
		return findByIssueDate(
			issueDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issues where issueDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>IssueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param issueDate the issue date
	 * @param start the lower bound of the range of issues
	 * @param end the upper bound of the range of issues (not inclusive)
	 * @return the range of matching issues
	 */
	@Override
	public List<Issue> findByIssueDate(Date issueDate, int start, int end) {
		return findByIssueDate(issueDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the issues where issueDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>IssueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByIssueDate(Date, int, int, OrderByComparator)}
	 * @param issueDate the issue date
	 * @param start the lower bound of the range of issues
	 * @param end the upper bound of the range of issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching issues
	 */
	@Deprecated
	@Override
	public List<Issue> findByIssueDate(
		Date issueDate, int start, int end,
		OrderByComparator<Issue> orderByComparator, boolean useFinderCache) {

		return findByIssueDate(issueDate, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the issues where issueDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>IssueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param issueDate the issue date
	 * @param start the lower bound of the range of issues
	 * @param end the upper bound of the range of issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching issues
	 */
	@Override
	public List<Issue> findByIssueDate(
		Date issueDate, int start, int end,
		OrderByComparator<Issue> orderByComparator) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByIssueDate;
			finderArgs = new Object[] {_getTime(issueDate)};
		}
		else {
			finderPath = _finderPathWithPaginationFindByIssueDate;
			finderArgs = new Object[] {
				_getTime(issueDate), start, end, orderByComparator
			};
		}

		List<Issue> list = (List<Issue>)finderCache.getResult(
			finderPath, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Issue issue : list) {
				if (!Objects.equals(issueDate, issue.getIssueDate())) {
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

			query.append(_SQL_SELECT_ISSUE_WHERE);

			boolean bindIssueDate = false;

			if (issueDate == null) {
				query.append(_FINDER_COLUMN_ISSUEDATE_ISSUEDATE_1);
			}
			else {
				bindIssueDate = true;

				query.append(_FINDER_COLUMN_ISSUEDATE_ISSUEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(IssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIssueDate) {
					qPos.add(new Timestamp(issueDate.getTime()));
				}

				if (!pagination) {
					list = (List<Issue>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Issue>)QueryUtil.list(
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
	 * Returns the first issue in the ordered set where issueDate = &#63;.
	 *
	 * @param issueDate the issue date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue
	 * @throws NoSuchIssueException if a matching issue could not be found
	 */
	@Override
	public Issue findByIssueDate_First(
			Date issueDate, OrderByComparator<Issue> orderByComparator)
		throws NoSuchIssueException {

		Issue issue = fetchByIssueDate_First(issueDate, orderByComparator);

		if (issue != null) {
			return issue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("issueDate=");
		msg.append(issueDate);

		msg.append("}");

		throw new NoSuchIssueException(msg.toString());
	}

	/**
	 * Returns the first issue in the ordered set where issueDate = &#63;.
	 *
	 * @param issueDate the issue date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue, or <code>null</code> if a matching issue could not be found
	 */
	@Override
	public Issue fetchByIssueDate_First(
		Date issueDate, OrderByComparator<Issue> orderByComparator) {

		List<Issue> list = findByIssueDate(issueDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last issue in the ordered set where issueDate = &#63;.
	 *
	 * @param issueDate the issue date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue
	 * @throws NoSuchIssueException if a matching issue could not be found
	 */
	@Override
	public Issue findByIssueDate_Last(
			Date issueDate, OrderByComparator<Issue> orderByComparator)
		throws NoSuchIssueException {

		Issue issue = fetchByIssueDate_Last(issueDate, orderByComparator);

		if (issue != null) {
			return issue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("issueDate=");
		msg.append(issueDate);

		msg.append("}");

		throw new NoSuchIssueException(msg.toString());
	}

	/**
	 * Returns the last issue in the ordered set where issueDate = &#63;.
	 *
	 * @param issueDate the issue date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue, or <code>null</code> if a matching issue could not be found
	 */
	@Override
	public Issue fetchByIssueDate_Last(
		Date issueDate, OrderByComparator<Issue> orderByComparator) {

		int count = countByIssueDate(issueDate);

		if (count == 0) {
			return null;
		}

		List<Issue> list = findByIssueDate(
			issueDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the issues before and after the current issue in the ordered set where issueDate = &#63;.
	 *
	 * @param issueId the primary key of the current issue
	 * @param issueDate the issue date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next issue
	 * @throws NoSuchIssueException if a issue with the primary key could not be found
	 */
	@Override
	public Issue[] findByIssueDate_PrevAndNext(
			long issueId, Date issueDate,
			OrderByComparator<Issue> orderByComparator)
		throws NoSuchIssueException {

		Issue issue = findByPrimaryKey(issueId);

		Session session = null;

		try {
			session = openSession();

			Issue[] array = new IssueImpl[3];

			array[0] = getByIssueDate_PrevAndNext(
				session, issue, issueDate, orderByComparator, true);

			array[1] = issue;

			array[2] = getByIssueDate_PrevAndNext(
				session, issue, issueDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Issue getByIssueDate_PrevAndNext(
		Session session, Issue issue, Date issueDate,
		OrderByComparator<Issue> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ISSUE_WHERE);

		boolean bindIssueDate = false;

		if (issueDate == null) {
			query.append(_FINDER_COLUMN_ISSUEDATE_ISSUEDATE_1);
		}
		else {
			bindIssueDate = true;

			query.append(_FINDER_COLUMN_ISSUEDATE_ISSUEDATE_2);
		}

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
			query.append(IssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindIssueDate) {
			qPos.add(new Timestamp(issueDate.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(issue)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Issue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the issues where issueDate = &#63; from the database.
	 *
	 * @param issueDate the issue date
	 */
	@Override
	public void removeByIssueDate(Date issueDate) {
		for (Issue issue :
				findByIssueDate(
					issueDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(issue);
		}
	}

	/**
	 * Returns the number of issues where issueDate = &#63;.
	 *
	 * @param issueDate the issue date
	 * @return the number of matching issues
	 */
	@Override
	public int countByIssueDate(Date issueDate) {
		FinderPath finderPath = _finderPathCountByIssueDate;

		Object[] finderArgs = new Object[] {_getTime(issueDate)};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ISSUE_WHERE);

			boolean bindIssueDate = false;

			if (issueDate == null) {
				query.append(_FINDER_COLUMN_ISSUEDATE_ISSUEDATE_1);
			}
			else {
				bindIssueDate = true;

				query.append(_FINDER_COLUMN_ISSUEDATE_ISSUEDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIssueDate) {
					qPos.add(new Timestamp(issueDate.getTime()));
				}

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

	private static final String _FINDER_COLUMN_ISSUEDATE_ISSUEDATE_1 =
		"issue.issueDate IS NULL";

	private static final String _FINDER_COLUMN_ISSUEDATE_ISSUEDATE_2 =
		"issue.issueDate = ?";

	public IssuePersistenceImpl() {
		setModelClass(Issue.class);

		setModelImplClass(IssueImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the issue in the entity cache if it is enabled.
	 *
	 * @param issue the issue
	 */
	@Override
	public void cacheResult(Issue issue) {
		entityCache.putResult(
			entityCacheEnabled, IssueImpl.class, issue.getPrimaryKey(), issue);

		issue.resetOriginalValues();
	}

	/**
	 * Caches the issues in the entity cache if it is enabled.
	 *
	 * @param issues the issues
	 */
	@Override
	public void cacheResult(List<Issue> issues) {
		for (Issue issue : issues) {
			if (entityCache.getResult(
					entityCacheEnabled, IssueImpl.class,
					issue.getPrimaryKey()) == null) {

				cacheResult(issue);
			}
			else {
				issue.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all issues.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IssueImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the issue.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Issue issue) {
		entityCache.removeResult(
			entityCacheEnabled, IssueImpl.class, issue.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Issue> issues) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Issue issue : issues) {
			entityCache.removeResult(
				entityCacheEnabled, IssueImpl.class, issue.getPrimaryKey());
		}
	}

	/**
	 * Creates a new issue with the primary key. Does not add the issue to the database.
	 *
	 * @param issueId the primary key for the new issue
	 * @return the new issue
	 */
	@Override
	public Issue create(long issueId) {
		Issue issue = new IssueImpl();

		issue.setNew(true);
		issue.setPrimaryKey(issueId);

		return issue;
	}

	/**
	 * Removes the issue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param issueId the primary key of the issue
	 * @return the issue that was removed
	 * @throws NoSuchIssueException if a issue with the primary key could not be found
	 */
	@Override
	public Issue remove(long issueId) throws NoSuchIssueException {
		return remove((Serializable)issueId);
	}

	/**
	 * Removes the issue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the issue
	 * @return the issue that was removed
	 * @throws NoSuchIssueException if a issue with the primary key could not be found
	 */
	@Override
	public Issue remove(Serializable primaryKey) throws NoSuchIssueException {
		Session session = null;

		try {
			session = openSession();

			Issue issue = (Issue)session.get(IssueImpl.class, primaryKey);

			if (issue == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIssueException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(issue);
		}
		catch (NoSuchIssueException nsee) {
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
	protected Issue removeImpl(Issue issue) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(issue)) {
				issue = (Issue)session.get(
					IssueImpl.class, issue.getPrimaryKeyObj());
			}

			if (issue != null) {
				session.delete(issue);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (issue != null) {
			clearCache(issue);
		}

		return issue;
	}

	@Override
	public Issue updateImpl(Issue issue) {
		boolean isNew = issue.isNew();

		if (!(issue instanceof IssueModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(issue.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(issue);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in issue proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Issue implementation " +
					issue.getClass());
		}

		IssueModelImpl issueModelImpl = (IssueModelImpl)issue;

		Session session = null;

		try {
			session = openSession();

			if (issue.isNew()) {
				session.save(issue);

				issue.setNew(false);
			}
			else {
				issue = (Issue)session.merge(issue);
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
			Object[] args = new Object[] {issueModelImpl.getIssueDate()};

			finderCache.removeResult(_finderPathCountByIssueDate, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByIssueDate, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((issueModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByIssueDate.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					issueModelImpl.getOriginalIssueDate()
				};

				finderCache.removeResult(_finderPathCountByIssueDate, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByIssueDate, args);

				args = new Object[] {issueModelImpl.getIssueDate()};

				finderCache.removeResult(_finderPathCountByIssueDate, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByIssueDate, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, IssueImpl.class, issue.getPrimaryKey(), issue,
			false);

		issue.resetOriginalValues();

		return issue;
	}

	/**
	 * Returns the issue with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the issue
	 * @return the issue
	 * @throws NoSuchIssueException if a issue with the primary key could not be found
	 */
	@Override
	public Issue findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIssueException {

		Issue issue = fetchByPrimaryKey(primaryKey);

		if (issue == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIssueException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return issue;
	}

	/**
	 * Returns the issue with the primary key or throws a <code>NoSuchIssueException</code> if it could not be found.
	 *
	 * @param issueId the primary key of the issue
	 * @return the issue
	 * @throws NoSuchIssueException if a issue with the primary key could not be found
	 */
	@Override
	public Issue findByPrimaryKey(long issueId) throws NoSuchIssueException {
		return findByPrimaryKey((Serializable)issueId);
	}

	/**
	 * Returns the issue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param issueId the primary key of the issue
	 * @return the issue, or <code>null</code> if a issue with the primary key could not be found
	 */
	@Override
	public Issue fetchByPrimaryKey(long issueId) {
		return fetchByPrimaryKey((Serializable)issueId);
	}

	/**
	 * Returns all the issues.
	 *
	 * @return the issues
	 */
	@Override
	public List<Issue> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>IssueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of issues
	 * @param end the upper bound of the range of issues (not inclusive)
	 * @return the range of issues
	 */
	@Override
	public List<Issue> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>IssueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findAll(int, int, OrderByComparator)}
	 * @param start the lower bound of the range of issues
	 * @param end the upper bound of the range of issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of issues
	 */
	@Deprecated
	@Override
	public List<Issue> findAll(
		int start, int end, OrderByComparator<Issue> orderByComparator,
		boolean useFinderCache) {

		return findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>IssueModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of issues
	 * @param end the upper bound of the range of issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of issues
	 */
	@Override
	public List<Issue> findAll(
		int start, int end, OrderByComparator<Issue> orderByComparator) {

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

		List<Issue> list = (List<Issue>)finderCache.getResult(
			finderPath, finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ISSUE);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ISSUE;

				if (pagination) {
					sql = sql.concat(IssueModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Issue>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Issue>)QueryUtil.list(
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
	 * Removes all the issues from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Issue issue : findAll()) {
			remove(issue);
		}
	}

	/**
	 * Returns the number of issues.
	 *
	 * @return the number of issues
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ISSUE);

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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "issueId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ISSUE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return IssueModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the issue persistence.
	 */
	@Activate
	public void activate() {
		IssueModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		IssueModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, IssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, IssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByIssueDate = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, IssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByIssueDate",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByIssueDate = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, IssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByIssueDate",
			new String[] {Date.class.getName()},
			IssueModelImpl.ISSUEDATE_COLUMN_BITMASK);

		_finderPathCountByIssueDate = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByIssueDate",
			new String[] {Date.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(IssueImpl.class.getName());
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
				"value.object.column.bitmask.enabled.com.liferay.amf.newsletter.model.Issue"),
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

	private Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_ISSUE =
		"SELECT issue FROM Issue issue";

	private static final String _SQL_SELECT_ISSUE_WHERE =
		"SELECT issue FROM Issue issue WHERE ";

	private static final String _SQL_COUNT_ISSUE =
		"SELECT COUNT(issue) FROM Issue issue";

	private static final String _SQL_COUNT_ISSUE_WHERE =
		"SELECT COUNT(issue) FROM Issue issue WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "issue.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Issue exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Issue exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		IssuePersistenceImpl.class);

	static {
		try {
			Class.forName(NewsletterPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException cnfe) {
			throw new ExceptionInInitializerError(cnfe);
		}
	}

}
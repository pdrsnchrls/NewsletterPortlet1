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

package com.liferay.amf.monitor.service.persistence.impl;

import com.liferay.amf.monitor.exception.NoSuchEventException;
import com.liferay.amf.monitor.model.Event;
import com.liferay.amf.monitor.model.impl.EventImpl;
import com.liferay.amf.monitor.model.impl.EventModelImpl;
import com.liferay.amf.monitor.service.persistence.EventPersistence;
import com.liferay.amf.monitor.service.persistence.impl.constants.MonitorPersistenceConstants;
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
 * The persistence implementation for the event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = EventPersistence.class)
@ProviderType
public class EventPersistenceImpl
	extends BasePersistenceImpl<Event> implements EventPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>EventUtil</code> to access the event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		EventImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByEventId;
	private FinderPath _finderPathWithoutPaginationFindByEventId;
	private FinderPath _finderPathCountByEventId;

	/**
	 * Returns all the events where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the matching events
	 */
	@Override
	public List<Event> findByEventId(long eventId) {
		return findByEventId(
			eventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the events where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EventModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @return the range of matching events
	 */
	@Override
	public List<Event> findByEventId(long eventId, int start, int end) {
		return findByEventId(eventId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the events where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EventModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByEventId(long, int, int, OrderByComparator)}
	 * @param eventId the event ID
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching events
	 */
	@Deprecated
	@Override
	public List<Event> findByEventId(
		long eventId, int start, int end,
		OrderByComparator<Event> orderByComparator, boolean useFinderCache) {

		return findByEventId(eventId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the events where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EventModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching events
	 */
	@Override
	public List<Event> findByEventId(
		long eventId, int start, int end,
		OrderByComparator<Event> orderByComparator) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByEventId;
			finderArgs = new Object[] {eventId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByEventId;
			finderArgs = new Object[] {eventId, start, end, orderByComparator};
		}

		List<Event> list = (List<Event>)finderCache.getResult(
			finderPath, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Event event : list) {
				if ((eventId != event.getEventId())) {
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

			query.append(_SQL_SELECT_EVENT_WHERE);

			query.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(EventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

				if (!pagination) {
					list = (List<Event>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Event>)QueryUtil.list(
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
	 * Returns the first event in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event
	 * @throws NoSuchEventException if a matching event could not be found
	 */
	@Override
	public Event findByEventId_First(
			long eventId, OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException {

		Event event = fetchByEventId_First(eventId, orderByComparator);

		if (event != null) {
			return event;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventId=");
		msg.append(eventId);

		msg.append("}");

		throw new NoSuchEventException(msg.toString());
	}

	/**
	 * Returns the first event in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event, or <code>null</code> if a matching event could not be found
	 */
	@Override
	public Event fetchByEventId_First(
		long eventId, OrderByComparator<Event> orderByComparator) {

		List<Event> list = findByEventId(eventId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last event in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event
	 * @throws NoSuchEventException if a matching event could not be found
	 */
	@Override
	public Event findByEventId_Last(
			long eventId, OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException {

		Event event = fetchByEventId_Last(eventId, orderByComparator);

		if (event != null) {
			return event;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventId=");
		msg.append(eventId);

		msg.append("}");

		throw new NoSuchEventException(msg.toString());
	}

	/**
	 * Returns the last event in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event, or <code>null</code> if a matching event could not be found
	 */
	@Override
	public Event fetchByEventId_Last(
		long eventId, OrderByComparator<Event> orderByComparator) {

		int count = countByEventId(eventId);

		if (count == 0) {
			return null;
		}

		List<Event> list = findByEventId(
			eventId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Removes all the events where eventId = &#63; from the database.
	 *
	 * @param eventId the event ID
	 */
	@Override
	public void removeByEventId(long eventId) {
		for (Event event :
				findByEventId(
					eventId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(event);
		}
	}

	/**
	 * Returns the number of events where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the number of matching events
	 */
	@Override
	public int countByEventId(long eventId) {
		FinderPath finderPath = _finderPathCountByEventId;

		Object[] finderArgs = new Object[] {eventId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EVENT_WHERE);

			query.append(_FINDER_COLUMN_EVENTID_EVENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(eventId);

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

	private static final String _FINDER_COLUMN_EVENTID_EVENTID_2 =
		"event.eventId = ?";

	public EventPersistenceImpl() {
		setModelClass(Event.class);

		setModelImplClass(EventImpl.class);
		setModelPKClass(long.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("date", "date_");

		setDBColumnNames(dbColumnNames);
	}

	/**
	 * Caches the event in the entity cache if it is enabled.
	 *
	 * @param event the event
	 */
	@Override
	public void cacheResult(Event event) {
		entityCache.putResult(
			entityCacheEnabled, EventImpl.class, event.getPrimaryKey(), event);

		event.resetOriginalValues();
	}

	/**
	 * Caches the events in the entity cache if it is enabled.
	 *
	 * @param events the events
	 */
	@Override
	public void cacheResult(List<Event> events) {
		for (Event event : events) {
			if (entityCache.getResult(
					entityCacheEnabled, EventImpl.class,
					event.getPrimaryKey()) == null) {

				cacheResult(event);
			}
			else {
				event.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all events.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EventImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the event.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Event event) {
		entityCache.removeResult(
			entityCacheEnabled, EventImpl.class, event.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Event> events) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Event event : events) {
			entityCache.removeResult(
				entityCacheEnabled, EventImpl.class, event.getPrimaryKey());
		}
	}

	/**
	 * Creates a new event with the primary key. Does not add the event to the database.
	 *
	 * @param eventId the primary key for the new event
	 * @return the new event
	 */
	@Override
	public Event create(long eventId) {
		Event event = new EventImpl();

		event.setNew(true);
		event.setPrimaryKey(eventId);

		return event;
	}

	/**
	 * Removes the event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventId the primary key of the event
	 * @return the event that was removed
	 * @throws NoSuchEventException if a event with the primary key could not be found
	 */
	@Override
	public Event remove(long eventId) throws NoSuchEventException {
		return remove((Serializable)eventId);
	}

	/**
	 * Removes the event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the event
	 * @return the event that was removed
	 * @throws NoSuchEventException if a event with the primary key could not be found
	 */
	@Override
	public Event remove(Serializable primaryKey) throws NoSuchEventException {
		Session session = null;

		try {
			session = openSession();

			Event event = (Event)session.get(EventImpl.class, primaryKey);

			if (event == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEventException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(event);
		}
		catch (NoSuchEventException nsee) {
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
	protected Event removeImpl(Event event) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(event)) {
				event = (Event)session.get(
					EventImpl.class, event.getPrimaryKeyObj());
			}

			if (event != null) {
				session.delete(event);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (event != null) {
			clearCache(event);
		}

		return event;
	}

	@Override
	public Event updateImpl(Event event) {
		boolean isNew = event.isNew();

		if (!(event instanceof EventModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(event.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(event);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in event proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Event implementation " +
					event.getClass());
		}

		EventModelImpl eventModelImpl = (EventModelImpl)event;

		Session session = null;

		try {
			session = openSession();

			if (event.isNew()) {
				session.save(event);

				event.setNew(false);
			}
			else {
				event = (Event)session.merge(event);
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
			Object[] args = new Object[] {eventModelImpl.getEventId()};

			finderCache.removeResult(_finderPathCountByEventId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByEventId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((eventModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByEventId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					eventModelImpl.getOriginalEventId()
				};

				finderCache.removeResult(_finderPathCountByEventId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByEventId, args);

				args = new Object[] {eventModelImpl.getEventId()};

				finderCache.removeResult(_finderPathCountByEventId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByEventId, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, EventImpl.class, event.getPrimaryKey(), event,
			false);

		event.resetOriginalValues();

		return event;
	}

	/**
	 * Returns the event with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the event
	 * @return the event
	 * @throws NoSuchEventException if a event with the primary key could not be found
	 */
	@Override
	public Event findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEventException {

		Event event = fetchByPrimaryKey(primaryKey);

		if (event == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEventException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return event;
	}

	/**
	 * Returns the event with the primary key or throws a <code>NoSuchEventException</code> if it could not be found.
	 *
	 * @param eventId the primary key of the event
	 * @return the event
	 * @throws NoSuchEventException if a event with the primary key could not be found
	 */
	@Override
	public Event findByPrimaryKey(long eventId) throws NoSuchEventException {
		return findByPrimaryKey((Serializable)eventId);
	}

	/**
	 * Returns the event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventId the primary key of the event
	 * @return the event, or <code>null</code> if a event with the primary key could not be found
	 */
	@Override
	public Event fetchByPrimaryKey(long eventId) {
		return fetchByPrimaryKey((Serializable)eventId);
	}

	/**
	 * Returns all the events.
	 *
	 * @return the events
	 */
	@Override
	public List<Event> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EventModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @return the range of events
	 */
	@Override
	public List<Event> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EventModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findAll(int, int, OrderByComparator)}
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of events
	 */
	@Deprecated
	@Override
	public List<Event> findAll(
		int start, int end, OrderByComparator<Event> orderByComparator,
		boolean useFinderCache) {

		return findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>EventModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of events
	 */
	@Override
	public List<Event> findAll(
		int start, int end, OrderByComparator<Event> orderByComparator) {

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

		List<Event> list = (List<Event>)finderCache.getResult(
			finderPath, finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_EVENT);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EVENT;

				if (pagination) {
					sql = sql.concat(EventModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Event>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Event>)QueryUtil.list(
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
	 * Removes all the events from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Event event : findAll()) {
			remove(event);
		}
	}

	/**
	 * Returns the number of events.
	 *
	 * @return the number of events
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EVENT);

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
		return "eventId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_EVENT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EventModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the event persistence.
	 */
	@Activate
	public void activate() {
		EventModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		EventModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByEventId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEventId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByEventId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEventId",
			new String[] {Long.class.getName()},
			EventModelImpl.EVENTID_COLUMN_BITMASK |
			EventModelImpl.DATE_COLUMN_BITMASK);

		_finderPathCountByEventId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEventId",
			new String[] {Long.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(EventImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = MonitorPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.liferay.amf.monitor.model.Event"),
			true);
	}

	@Override
	@Reference(
		target = MonitorPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = MonitorPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_EVENT =
		"SELECT event FROM Event event";

	private static final String _SQL_SELECT_EVENT_WHERE =
		"SELECT event FROM Event event WHERE ";

	private static final String _SQL_COUNT_EVENT =
		"SELECT COUNT(event) FROM Event event";

	private static final String _SQL_COUNT_EVENT_WHERE =
		"SELECT COUNT(event) FROM Event event WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "event.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Event exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Event exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		EventPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"date"});

	static {
		try {
			Class.forName(MonitorPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException cnfe) {
			throw new ExceptionInInitializerError(cnfe);
		}
	}

}
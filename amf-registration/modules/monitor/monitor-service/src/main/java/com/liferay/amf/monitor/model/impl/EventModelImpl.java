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

package com.liferay.amf.monitor.model.impl;

import com.liferay.amf.monitor.model.Event;
import com.liferay.amf.monitor.model.EventModel;
import com.liferay.amf.monitor.model.EventSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the Event service. Represents a row in the &quot;Monitor_Event&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>EventModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link EventImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class EventModelImpl extends BaseModelImpl<Event> implements EventModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a event model instance should use the <code>Event</code> interface instead.
	 */
	public static final String TABLE_NAME = "Monitor_Event";

	public static final Object[][] TABLE_COLUMNS = {
		{"eventId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"screenName", Types.VARCHAR}, {"date_", Types.TIMESTAMP},
		{"ipAddress", Types.VARCHAR}, {"eventType", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("eventId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("screenName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("date_", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("ipAddress", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("eventType", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Monitor_Event (eventId LONG not null primary key,userId LONG,screenName VARCHAR(75) null,date_ DATE null,ipAddress VARCHAR(75) null,eventType VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table Monitor_Event";

	public static final String ORDER_BY_JPQL = " ORDER BY event.date DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Monitor_Event.date_ DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long EVENTID_COLUMN_BITMASK = 1L;

	public static final long DATE_COLUMN_BITMASK = 2L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Event toModel(EventSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Event model = new EventImpl();

		model.setEventId(soapModel.getEventId());
		model.setUserId(soapModel.getUserId());
		model.setScreenName(soapModel.getScreenName());
		model.setDate(soapModel.getDate());
		model.setIpAddress(soapModel.getIpAddress());
		model.setEventType(soapModel.getEventType());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Event> toModels(EventSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Event> models = new ArrayList<Event>(soapModels.length);

		for (EventSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public EventModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _eventId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setEventId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _eventId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Event.class;
	}

	@Override
	public String getModelClassName() {
		return Event.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Event, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Event, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Event, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Event)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Event, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Event, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((Event)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Event, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Event, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Event>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Event.class.getClassLoader(), Event.class, ModelWrapper.class);

		try {
			Constructor<Event> constructor =
				(Constructor<Event>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<Event, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Event, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Event, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Event, Object>>();
		Map<String, BiConsumer<Event, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Event, ?>>();

		attributeGetterFunctions.put("eventId", Event::getEventId);
		attributeSetterBiConsumers.put(
			"eventId", (BiConsumer<Event, Long>)Event::setEventId);
		attributeGetterFunctions.put("userId", Event::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Event, Long>)Event::setUserId);
		attributeGetterFunctions.put("screenName", Event::getScreenName);
		attributeSetterBiConsumers.put(
			"screenName", (BiConsumer<Event, String>)Event::setScreenName);
		attributeGetterFunctions.put("date", Event::getDate);
		attributeSetterBiConsumers.put(
			"date", (BiConsumer<Event, Date>)Event::setDate);
		attributeGetterFunctions.put("ipAddress", Event::getIpAddress);
		attributeSetterBiConsumers.put(
			"ipAddress", (BiConsumer<Event, String>)Event::setIpAddress);
		attributeGetterFunctions.put("eventType", Event::getEventType);
		attributeSetterBiConsumers.put(
			"eventType", (BiConsumer<Event, String>)Event::setEventType);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getEventId() {
		return _eventId;
	}

	@Override
	public void setEventId(long eventId) {
		_columnBitmask |= EVENTID_COLUMN_BITMASK;

		if (!_setOriginalEventId) {
			_setOriginalEventId = true;

			_originalEventId = _eventId;
		}

		_eventId = eventId;
	}

	public long getOriginalEventId() {
		return _originalEventId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getScreenName() {
		if (_screenName == null) {
			return "";
		}
		else {
			return _screenName;
		}
	}

	@Override
	public void setScreenName(String screenName) {
		_screenName = screenName;
	}

	@JSON
	@Override
	public Date getDate() {
		return _date;
	}

	@Override
	public void setDate(Date date) {
		_columnBitmask = -1L;

		_date = date;
	}

	@JSON
	@Override
	public String getIpAddress() {
		if (_ipAddress == null) {
			return "";
		}
		else {
			return _ipAddress;
		}
	}

	@Override
	public void setIpAddress(String ipAddress) {
		_ipAddress = ipAddress;
	}

	@JSON
	@Override
	public String getEventType() {
		if (_eventType == null) {
			return "";
		}
		else {
			return _eventType;
		}
	}

	@Override
	public void setEventType(String eventType) {
		_eventType = eventType;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, Event.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Event toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Event>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		EventImpl eventImpl = new EventImpl();

		eventImpl.setEventId(getEventId());
		eventImpl.setUserId(getUserId());
		eventImpl.setScreenName(getScreenName());
		eventImpl.setDate(getDate());
		eventImpl.setIpAddress(getIpAddress());
		eventImpl.setEventType(getEventType());

		eventImpl.resetOriginalValues();

		return eventImpl;
	}

	@Override
	public int compareTo(Event event) {
		int value = 0;

		value = DateUtil.compareTo(getDate(), event.getDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Event)) {
			return false;
		}

		Event event = (Event)obj;

		long primaryKey = event.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		EventModelImpl eventModelImpl = this;

		eventModelImpl._originalEventId = eventModelImpl._eventId;

		eventModelImpl._setOriginalEventId = false;

		eventModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Event> toCacheModel() {
		EventCacheModel eventCacheModel = new EventCacheModel();

		eventCacheModel.eventId = getEventId();

		eventCacheModel.userId = getUserId();

		eventCacheModel.screenName = getScreenName();

		String screenName = eventCacheModel.screenName;

		if ((screenName != null) && (screenName.length() == 0)) {
			eventCacheModel.screenName = null;
		}

		Date date = getDate();

		if (date != null) {
			eventCacheModel.date = date.getTime();
		}
		else {
			eventCacheModel.date = Long.MIN_VALUE;
		}

		eventCacheModel.ipAddress = getIpAddress();

		String ipAddress = eventCacheModel.ipAddress;

		if ((ipAddress != null) && (ipAddress.length() == 0)) {
			eventCacheModel.ipAddress = null;
		}

		eventCacheModel.eventType = getEventType();

		String eventType = eventCacheModel.eventType;

		if ((eventType != null) && (eventType.length() == 0)) {
			eventCacheModel.eventType = null;
		}

		return eventCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Event, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Event, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Event, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Event)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<Event, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Event, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Event, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Event)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Event>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _eventId;
	private long _originalEventId;
	private boolean _setOriginalEventId;
	private long _userId;
	private String _screenName;
	private Date _date;
	private String _ipAddress;
	private String _eventType;
	private long _columnBitmask;
	private Event _escapedModel;

}
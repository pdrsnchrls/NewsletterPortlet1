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

package com.liferay.amf.monitor.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Event service. Represents a row in the &quot;Monitor_Event&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.amf.monitor.model.impl.EventModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.amf.monitor.model.impl.EventImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Event
 * @generated
 */
@ProviderType
public interface EventModel extends BaseModel<Event> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a event model instance should use the {@link Event} interface instead.
	 */

	/**
	 * Returns the primary key of this event.
	 *
	 * @return the primary key of this event
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this event.
	 *
	 * @param primaryKey the primary key of this event
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the event ID of this event.
	 *
	 * @return the event ID of this event
	 */
	public long getEventId();

	/**
	 * Sets the event ID of this event.
	 *
	 * @param eventId the event ID of this event
	 */
	public void setEventId(long eventId);

	/**
	 * Returns the user ID of this event.
	 *
	 * @return the user ID of this event
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this event.
	 *
	 * @param userId the user ID of this event
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this event.
	 *
	 * @return the user uuid of this event
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this event.
	 *
	 * @param userUuid the user uuid of this event
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the screen name of this event.
	 *
	 * @return the screen name of this event
	 */
	@AutoEscape
	public String getScreenName();

	/**
	 * Sets the screen name of this event.
	 *
	 * @param screenName the screen name of this event
	 */
	public void setScreenName(String screenName);

	/**
	 * Returns the date of this event.
	 *
	 * @return the date of this event
	 */
	public Date getDate();

	/**
	 * Sets the date of this event.
	 *
	 * @param date the date of this event
	 */
	public void setDate(Date date);

	/**
	 * Returns the ip address of this event.
	 *
	 * @return the ip address of this event
	 */
	@AutoEscape
	public String getIpAddress();

	/**
	 * Sets the ip address of this event.
	 *
	 * @param ipAddress the ip address of this event
	 */
	public void setIpAddress(String ipAddress);

	/**
	 * Returns the event type of this event.
	 *
	 * @return the event type of this event
	 */
	@AutoEscape
	public String getEventType();

	/**
	 * Sets the event type of this event.
	 *
	 * @param eventType the event type of this event
	 */
	public void setEventType(String eventType);

}
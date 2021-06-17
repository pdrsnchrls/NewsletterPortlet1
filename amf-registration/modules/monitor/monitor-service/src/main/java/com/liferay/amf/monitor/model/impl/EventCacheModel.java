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
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing Event in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class EventCacheModel implements CacheModel<Event>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventCacheModel)) {
			return false;
		}

		EventCacheModel eventCacheModel = (EventCacheModel)obj;

		if (eventId == eventCacheModel.eventId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, eventId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{eventId=");
		sb.append(eventId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", screenName=");
		sb.append(screenName);
		sb.append(", date=");
		sb.append(date);
		sb.append(", ipAddress=");
		sb.append(ipAddress);
		sb.append(", eventType=");
		sb.append(eventType);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Event toEntityModel() {
		EventImpl eventImpl = new EventImpl();

		eventImpl.setEventId(eventId);
		eventImpl.setUserId(userId);

		if (screenName == null) {
			eventImpl.setScreenName("");
		}
		else {
			eventImpl.setScreenName(screenName);
		}

		if (date == Long.MIN_VALUE) {
			eventImpl.setDate(null);
		}
		else {
			eventImpl.setDate(new Date(date));
		}

		if (ipAddress == null) {
			eventImpl.setIpAddress("");
		}
		else {
			eventImpl.setIpAddress(ipAddress);
		}

		if (eventType == null) {
			eventImpl.setEventType("");
		}
		else {
			eventImpl.setEventType(eventType);
		}

		eventImpl.resetOriginalValues();

		return eventImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		eventId = objectInput.readLong();

		userId = objectInput.readLong();
		screenName = objectInput.readUTF();
		date = objectInput.readLong();
		ipAddress = objectInput.readUTF();
		eventType = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(eventId);

		objectOutput.writeLong(userId);

		if (screenName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(screenName);
		}

		objectOutput.writeLong(date);

		if (ipAddress == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(ipAddress);
		}

		if (eventType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(eventType);
		}
	}

	public long eventId;
	public long userId;
	public String screenName;
	public long date;
	public String ipAddress;
	public String eventType;

}
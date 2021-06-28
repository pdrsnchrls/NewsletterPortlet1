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

package com.liferay.amf.newsletter.model.impl;

import com.liferay.amf.newsletter.model.Issue;
import com.liferay.amf.newsletter.model.IssueModel;
import com.liferay.amf.newsletter.model.IssueSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
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
 * The base model implementation for the Issue service. Represents a row in the &quot;Newsletter_Issue&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>IssueModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link IssueImpl}.
 * </p>
 *
 * @author Charles Pederson
 * @see IssueImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class IssueModelImpl extends BaseModelImpl<Issue> implements IssueModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a issue model instance should use the <code>Issue</code> interface instead.
	 */
	public static final String TABLE_NAME = "Newsletter_Issue";

	public static final Object[][] TABLE_COLUMNS = {
		{"issueId", Types.BIGINT}, {"issueNumber", Types.INTEGER},
		{"title", Types.VARCHAR}, {"description", Types.VARCHAR},
		{"issueDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("issueId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("issueNumber", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("issueDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Newsletter_Issue (issueId LONG not null primary key,issueNumber INTEGER,title VARCHAR(75) null,description VARCHAR(75) null,issueDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table Newsletter_Issue";

	public static final String ORDER_BY_JPQL = " ORDER BY issue.issueDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Newsletter_Issue.issueDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

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
	public static Issue toModel(IssueSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Issue model = new IssueImpl();

		model.setIssueId(soapModel.getIssueId());
		model.setIssueNumber(soapModel.getIssueNumber());
		model.setTitle(soapModel.getTitle());
		model.setDescription(soapModel.getDescription());
		model.setIssueDate(soapModel.getIssueDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Issue> toModels(IssueSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Issue> models = new ArrayList<Issue>(soapModels.length);

		for (IssueSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public IssueModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _issueId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setIssueId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _issueId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Issue.class;
	}

	@Override
	public String getModelClassName() {
		return Issue.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Issue, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Issue, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Issue, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Issue)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Issue, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Issue, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((Issue)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Issue, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Issue, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Issue>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Issue.class.getClassLoader(), Issue.class, ModelWrapper.class);

		try {
			Constructor<Issue> constructor =
				(Constructor<Issue>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Issue, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Issue, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Issue, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Issue, Object>>();
		Map<String, BiConsumer<Issue, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Issue, ?>>();

		attributeGetterFunctions.put("issueId", Issue::getIssueId);
		attributeSetterBiConsumers.put(
			"issueId", (BiConsumer<Issue, Long>)Issue::setIssueId);
		attributeGetterFunctions.put("issueNumber", Issue::getIssueNumber);
		attributeSetterBiConsumers.put(
			"issueNumber", (BiConsumer<Issue, Integer>)Issue::setIssueNumber);
		attributeGetterFunctions.put("title", Issue::getTitle);
		attributeSetterBiConsumers.put(
			"title", (BiConsumer<Issue, String>)Issue::setTitle);
		attributeGetterFunctions.put("description", Issue::getDescription);
		attributeSetterBiConsumers.put(
			"description", (BiConsumer<Issue, String>)Issue::setDescription);
		attributeGetterFunctions.put("issueDate", Issue::getIssueDate);
		attributeSetterBiConsumers.put(
			"issueDate", (BiConsumer<Issue, Date>)Issue::setIssueDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getIssueId() {
		return _issueId;
	}

	@Override
	public void setIssueId(long issueId) {
		_issueId = issueId;
	}

	@JSON
	@Override
	public int getIssueNumber() {
		return _issueNumber;
	}

	@Override
	public void setIssueNumber(int issueNumber) {
		_issueNumber = issueNumber;
	}

	@JSON
	@Override
	public String getTitle() {
		if (_title == null) {
			return "";
		}
		else {
			return _title;
		}
	}

	@Override
	public void setTitle(String title) {
		_title = title;
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public Date getIssueDate() {
		return _issueDate;
	}

	@Override
	public void setIssueDate(Date issueDate) {
		_issueDate = issueDate;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, Issue.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Issue toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Issue>
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
		IssueImpl issueImpl = new IssueImpl();

		issueImpl.setIssueId(getIssueId());
		issueImpl.setIssueNumber(getIssueNumber());
		issueImpl.setTitle(getTitle());
		issueImpl.setDescription(getDescription());
		issueImpl.setIssueDate(getIssueDate());

		issueImpl.resetOriginalValues();

		return issueImpl;
	}

	@Override
	public int compareTo(Issue issue) {
		int value = 0;

		value = DateUtil.compareTo(getIssueDate(), issue.getIssueDate());

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

		if (!(obj instanceof Issue)) {
			return false;
		}

		Issue issue = (Issue)obj;

		long primaryKey = issue.getPrimaryKey();

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
	}

	@Override
	public CacheModel<Issue> toCacheModel() {
		IssueCacheModel issueCacheModel = new IssueCacheModel();

		issueCacheModel.issueId = getIssueId();

		issueCacheModel.issueNumber = getIssueNumber();

		issueCacheModel.title = getTitle();

		String title = issueCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			issueCacheModel.title = null;
		}

		issueCacheModel.description = getDescription();

		String description = issueCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			issueCacheModel.description = null;
		}

		Date issueDate = getIssueDate();

		if (issueDate != null) {
			issueCacheModel.issueDate = issueDate.getTime();
		}
		else {
			issueCacheModel.issueDate = Long.MIN_VALUE;
		}

		return issueCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Issue, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Issue, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Issue, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Issue)this));
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
		Map<String, Function<Issue, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Issue, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Issue, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Issue)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Issue>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _issueId;
	private int _issueNumber;
	private String _title;
	private String _description;
	private Date _issueDate;
	private Issue _escapedModel;

}
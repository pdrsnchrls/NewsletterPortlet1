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

import com.liferay.amf.newsletter.model.Newsletter;
import com.liferay.amf.newsletter.model.NewsletterModel;
import com.liferay.amf.newsletter.model.NewsletterSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the Newsletter service. Represents a row in the &quot;Newsletter_Newsletter&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>NewsletterModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NewsletterImpl}.
 * </p>
 *
 * @author Charles Pederson
 * @see NewsletterImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class NewsletterModelImpl
	extends BaseModelImpl<Newsletter> implements NewsletterModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a newsletter model instance should use the <code>Newsletter</code> interface instead.
	 */
	public static final String TABLE_NAME = "Newsletter_Newsletter";

	public static final Object[][] TABLE_COLUMNS = {
		{"newsletterId", Types.BIGINT}, {"issueNumber", Types.BIGINT},
		{"title", Types.VARCHAR}, {"author", Types.VARCHAR},
		{"order_", Types.INTEGER}, {"content", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("newsletterId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("issueNumber", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("author", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("order_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("content", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Newsletter_Newsletter (newsletterId LONG not null primary key,issueNumber LONG,title VARCHAR(75) null,author VARCHAR(75) null,order_ INTEGER,content VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP =
		"drop table Newsletter_Newsletter";

	public static final String ORDER_BY_JPQL =
		" ORDER BY newsletter.order DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Newsletter_Newsletter.order_ DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long ISSUENUMBER_COLUMN_BITMASK = 1L;

	public static final long ORDER_COLUMN_BITMASK = 2L;

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
	public static Newsletter toModel(NewsletterSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Newsletter model = new NewsletterImpl();

		model.setNewsletterId(soapModel.getNewsletterId());
		model.setIssueNumber(soapModel.getIssueNumber());
		model.setTitle(soapModel.getTitle());
		model.setAuthor(soapModel.getAuthor());
		model.setOrder(soapModel.getOrder());
		model.setContent(soapModel.getContent());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Newsletter> toModels(NewsletterSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Newsletter> models = new ArrayList<Newsletter>(soapModels.length);

		for (NewsletterSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public NewsletterModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _newsletterId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setNewsletterId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _newsletterId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Newsletter.class;
	}

	@Override
	public String getModelClassName() {
		return Newsletter.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Newsletter, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Newsletter, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Newsletter, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Newsletter)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Newsletter, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Newsletter, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Newsletter)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Newsletter, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Newsletter, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Newsletter>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Newsletter.class.getClassLoader(), Newsletter.class,
			ModelWrapper.class);

		try {
			Constructor<Newsletter> constructor =
				(Constructor<Newsletter>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Newsletter, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Newsletter, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Newsletter, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Newsletter, Object>>();
		Map<String, BiConsumer<Newsletter, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Newsletter, ?>>();

		attributeGetterFunctions.put(
			"newsletterId", Newsletter::getNewsletterId);
		attributeSetterBiConsumers.put(
			"newsletterId",
			(BiConsumer<Newsletter, Long>)Newsletter::setNewsletterId);
		attributeGetterFunctions.put("issueNumber", Newsletter::getIssueNumber);
		attributeSetterBiConsumers.put(
			"issueNumber",
			(BiConsumer<Newsletter, Long>)Newsletter::setIssueNumber);
		attributeGetterFunctions.put("title", Newsletter::getTitle);
		attributeSetterBiConsumers.put(
			"title", (BiConsumer<Newsletter, String>)Newsletter::setTitle);
		attributeGetterFunctions.put("author", Newsletter::getAuthor);
		attributeSetterBiConsumers.put(
			"author", (BiConsumer<Newsletter, String>)Newsletter::setAuthor);
		attributeGetterFunctions.put("order", Newsletter::getOrder);
		attributeSetterBiConsumers.put(
			"order", (BiConsumer<Newsletter, Integer>)Newsletter::setOrder);
		attributeGetterFunctions.put("content", Newsletter::getContent);
		attributeSetterBiConsumers.put(
			"content", (BiConsumer<Newsletter, String>)Newsletter::setContent);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getNewsletterId() {
		return _newsletterId;
	}

	@Override
	public void setNewsletterId(long newsletterId) {
		_newsletterId = newsletterId;
	}

	@JSON
	@Override
	public long getIssueNumber() {
		return _issueNumber;
	}

	@Override
	public void setIssueNumber(long issueNumber) {
		_columnBitmask |= ISSUENUMBER_COLUMN_BITMASK;

		if (!_setOriginalIssueNumber) {
			_setOriginalIssueNumber = true;

			_originalIssueNumber = _issueNumber;
		}

		_issueNumber = issueNumber;
	}

	public long getOriginalIssueNumber() {
		return _originalIssueNumber;
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
	public String getAuthor() {
		if (_author == null) {
			return "";
		}
		else {
			return _author;
		}
	}

	@Override
	public void setAuthor(String author) {
		_author = author;
	}

	@JSON
	@Override
	public int getOrder() {
		return _order;
	}

	@Override
	public void setOrder(int order) {
		_columnBitmask = -1L;

		_order = order;
	}

	@JSON
	@Override
	public String getContent() {
		if (_content == null) {
			return "";
		}
		else {
			return _content;
		}
	}

	@Override
	public void setContent(String content) {
		_content = content;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, Newsletter.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Newsletter toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Newsletter>
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
		NewsletterImpl newsletterImpl = new NewsletterImpl();

		newsletterImpl.setNewsletterId(getNewsletterId());
		newsletterImpl.setIssueNumber(getIssueNumber());
		newsletterImpl.setTitle(getTitle());
		newsletterImpl.setAuthor(getAuthor());
		newsletterImpl.setOrder(getOrder());
		newsletterImpl.setContent(getContent());

		newsletterImpl.resetOriginalValues();

		return newsletterImpl;
	}

	@Override
	public int compareTo(Newsletter newsletter) {
		int value = 0;

		if (getOrder() < newsletter.getOrder()) {
			value = -1;
		}
		else if (getOrder() > newsletter.getOrder()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(obj instanceof Newsletter)) {
			return false;
		}

		Newsletter newsletter = (Newsletter)obj;

		long primaryKey = newsletter.getPrimaryKey();

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
		NewsletterModelImpl newsletterModelImpl = this;

		newsletterModelImpl._originalIssueNumber =
			newsletterModelImpl._issueNumber;

		newsletterModelImpl._setOriginalIssueNumber = false;

		newsletterModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Newsletter> toCacheModel() {
		NewsletterCacheModel newsletterCacheModel = new NewsletterCacheModel();

		newsletterCacheModel.newsletterId = getNewsletterId();

		newsletterCacheModel.issueNumber = getIssueNumber();

		newsletterCacheModel.title = getTitle();

		String title = newsletterCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			newsletterCacheModel.title = null;
		}

		newsletterCacheModel.author = getAuthor();

		String author = newsletterCacheModel.author;

		if ((author != null) && (author.length() == 0)) {
			newsletterCacheModel.author = null;
		}

		newsletterCacheModel.order = getOrder();

		newsletterCacheModel.content = getContent();

		String content = newsletterCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			newsletterCacheModel.content = null;
		}

		return newsletterCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Newsletter, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Newsletter, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Newsletter, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Newsletter)this));
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
		Map<String, Function<Newsletter, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Newsletter, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Newsletter, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Newsletter)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Newsletter>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _newsletterId;
	private long _issueNumber;
	private long _originalIssueNumber;
	private boolean _setOriginalIssueNumber;
	private String _title;
	private String _author;
	private int _order;
	private String _content;
	private long _columnBitmask;
	private Newsletter _escapedModel;

}
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

package com.liferay.amf.newsletter.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.amf.newsletter.service.http.NewsletterServiceSoap}.
 *
 * @author Charles Pederson
 * @generated
 */
@ProviderType
public class NewsletterSoap implements Serializable {

	public static NewsletterSoap toSoapModel(Newsletter model) {
		NewsletterSoap soapModel = new NewsletterSoap();

		soapModel.setNewsletterId(model.getNewsletterId());
		soapModel.setIssueId(model.getIssueId());
		soapModel.setTitle(model.getTitle());
		soapModel.setAuthor(model.getAuthor());
		soapModel.setOrder(model.getOrder());
		soapModel.setContent(model.getContent());

		return soapModel;
	}

	public static NewsletterSoap[] toSoapModels(Newsletter[] models) {
		NewsletterSoap[] soapModels = new NewsletterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NewsletterSoap[][] toSoapModels(Newsletter[][] models) {
		NewsletterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NewsletterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NewsletterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NewsletterSoap[] toSoapModels(List<Newsletter> models) {
		List<NewsletterSoap> soapModels = new ArrayList<NewsletterSoap>(
			models.size());

		for (Newsletter model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NewsletterSoap[soapModels.size()]);
	}

	public NewsletterSoap() {
	}

	public long getPrimaryKey() {
		return _newsletterId;
	}

	public void setPrimaryKey(long pk) {
		setNewsletterId(pk);
	}

	public long getNewsletterId() {
		return _newsletterId;
	}

	public void setNewsletterId(long newsletterId) {
		_newsletterId = newsletterId;
	}

	public long getIssueId() {
		return _issueId;
	}

	public void setIssueId(long issueId) {
		_issueId = issueId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getAuthor() {
		return _author;
	}

	public void setAuthor(String author) {
		_author = author;
	}

	public int getOrder() {
		return _order;
	}

	public void setOrder(int order) {
		_order = order;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	private long _newsletterId;
	private long _issueId;
	private String _title;
	private String _author;
	private int _order;
	private String _content;

}
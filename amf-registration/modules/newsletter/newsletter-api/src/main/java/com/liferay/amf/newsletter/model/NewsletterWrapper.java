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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <p>
 * This class is a wrapper for {@link Newsletter}.
 * </p>
 *
 * @author Charles Pederson
 * @see Newsletter
 * @generated
 */
@ProviderType
public class NewsletterWrapper
	extends BaseModelWrapper<Newsletter>
	implements Newsletter, ModelWrapper<Newsletter> {

	public NewsletterWrapper(Newsletter newsletter) {
		super(newsletter);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("newsletterId", getNewsletterId());
		attributes.put("issueId", getIssueId());
		attributes.put("title", getTitle());
		attributes.put("author", getAuthor());
		attributes.put("order", getOrder());
		attributes.put("content", getContent());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long newsletterId = (Long)attributes.get("newsletterId");

		if (newsletterId != null) {
			setNewsletterId(newsletterId);
		}

		Long issueId = (Long)attributes.get("issueId");

		if (issueId != null) {
			setIssueId(issueId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String author = (String)attributes.get("author");

		if (author != null) {
			setAuthor(author);
		}

		Integer order = (Integer)attributes.get("order");

		if (order != null) {
			setOrder(order);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}
	}

	/**
	 * Returns the author of this newsletter.
	 *
	 * @return the author of this newsletter
	 */
	@Override
	public String getAuthor() {
		return model.getAuthor();
	}

	/**
	 * Returns the content of this newsletter.
	 *
	 * @return the content of this newsletter
	 */
	@Override
	public String getContent() {
		return model.getContent();
	}

	/**
	 * Returns the issue ID of this newsletter.
	 *
	 * @return the issue ID of this newsletter
	 */
	@Override
	public long getIssueId() {
		return model.getIssueId();
	}

	/**
	 * Returns the newsletter ID of this newsletter.
	 *
	 * @return the newsletter ID of this newsletter
	 */
	@Override
	public long getNewsletterId() {
		return model.getNewsletterId();
	}

	/**
	 * Returns the order of this newsletter.
	 *
	 * @return the order of this newsletter
	 */
	@Override
	public int getOrder() {
		return model.getOrder();
	}

	/**
	 * Returns the primary key of this newsletter.
	 *
	 * @return the primary key of this newsletter
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the title of this newsletter.
	 *
	 * @return the title of this newsletter
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the author of this newsletter.
	 *
	 * @param author the author of this newsletter
	 */
	@Override
	public void setAuthor(String author) {
		model.setAuthor(author);
	}

	/**
	 * Sets the content of this newsletter.
	 *
	 * @param content the content of this newsletter
	 */
	@Override
	public void setContent(String content) {
		model.setContent(content);
	}

	/**
	 * Sets the issue ID of this newsletter.
	 *
	 * @param issueId the issue ID of this newsletter
	 */
	@Override
	public void setIssueId(long issueId) {
		model.setIssueId(issueId);
	}

	/**
	 * Sets the newsletter ID of this newsletter.
	 *
	 * @param newsletterId the newsletter ID of this newsletter
	 */
	@Override
	public void setNewsletterId(long newsletterId) {
		model.setNewsletterId(newsletterId);
	}

	/**
	 * Sets the order of this newsletter.
	 *
	 * @param order the order of this newsletter
	 */
	@Override
	public void setOrder(int order) {
		model.setOrder(order);
	}

	/**
	 * Sets the primary key of this newsletter.
	 *
	 * @param primaryKey the primary key of this newsletter
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the title of this newsletter.
	 *
	 * @param title the title of this newsletter
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	@Override
	protected NewsletterWrapper wrap(Newsletter newsletter) {
		return new NewsletterWrapper(newsletter);
	}

}
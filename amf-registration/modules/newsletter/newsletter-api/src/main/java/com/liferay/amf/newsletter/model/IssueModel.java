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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Issue service. Represents a row in the &quot;Newsletter_Issue&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.amf.newsletter.model.impl.IssueModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.amf.newsletter.model.impl.IssueImpl</code>.
 * </p>
 *
 * @author Charles Pederson
 * @see Issue
 * @generated
 */
@ProviderType
public interface IssueModel extends BaseModel<Issue> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a issue model instance should use the {@link Issue} interface instead.
	 */

	/**
	 * Returns the primary key of this issue.
	 *
	 * @return the primary key of this issue
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this issue.
	 *
	 * @param primaryKey the primary key of this issue
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the issue ID of this issue.
	 *
	 * @return the issue ID of this issue
	 */
	public long getIssueId();

	/**
	 * Sets the issue ID of this issue.
	 *
	 * @param issueId the issue ID of this issue
	 */
	public void setIssueId(long issueId);

	/**
	 * Returns the issue number of this issue.
	 *
	 * @return the issue number of this issue
	 */
	public int getIssueNumber();

	/**
	 * Sets the issue number of this issue.
	 *
	 * @param issueNumber the issue number of this issue
	 */
	public void setIssueNumber(int issueNumber);

	/**
	 * Returns the title of this issue.
	 *
	 * @return the title of this issue
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this issue.
	 *
	 * @param title the title of this issue
	 */
	public void setTitle(String title);

	/**
	 * Returns the description of this issue.
	 *
	 * @return the description of this issue
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this issue.
	 *
	 * @param description the description of this issue
	 */
	public void setDescription(String description);

	/**
	 * Returns the issue date of this issue.
	 *
	 * @return the issue date of this issue
	 */
	public Date getIssueDate();

	/**
	 * Sets the issue date of this issue.
	 *
	 * @param issueDate the issue date of this issue
	 */
	public void setIssueDate(Date issueDate);

}
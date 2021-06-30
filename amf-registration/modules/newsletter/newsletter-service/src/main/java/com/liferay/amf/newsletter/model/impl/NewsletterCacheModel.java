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
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing Newsletter in entity cache.
 *
 * @author Charles Pederson
 * @generated
 */
@ProviderType
public class NewsletterCacheModel
	implements CacheModel<Newsletter>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NewsletterCacheModel)) {
			return false;
		}

		NewsletterCacheModel newsletterCacheModel = (NewsletterCacheModel)obj;

		if (newsletterId == newsletterCacheModel.newsletterId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, newsletterId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{newsletterId=");
		sb.append(newsletterId);
		sb.append(", issueNumber=");
		sb.append(issueNumber);
		sb.append(", title=");
		sb.append(title);
		sb.append(", author=");
		sb.append(author);
		sb.append(", order=");
		sb.append(order);
		sb.append(", content=");
		sb.append(content);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Newsletter toEntityModel() {
		NewsletterImpl newsletterImpl = new NewsletterImpl();

		newsletterImpl.setNewsletterId(newsletterId);
		newsletterImpl.setIssueNumber(issueNumber);

		if (title == null) {
			newsletterImpl.setTitle("");
		}
		else {
			newsletterImpl.setTitle(title);
		}

		if (author == null) {
			newsletterImpl.setAuthor("");
		}
		else {
			newsletterImpl.setAuthor(author);
		}

		newsletterImpl.setOrder(order);

		if (content == null) {
			newsletterImpl.setContent("");
		}
		else {
			newsletterImpl.setContent(content);
		}

		newsletterImpl.resetOriginalValues();

		return newsletterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		newsletterId = objectInput.readLong();

		issueNumber = objectInput.readLong();
		title = objectInput.readUTF();
		author = objectInput.readUTF();

		order = objectInput.readInt();
		content = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(newsletterId);

		objectOutput.writeLong(issueNumber);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (author == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(author);
		}

		objectOutput.writeInt(order);

		if (content == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(content);
		}
	}

	public long newsletterId;
	public long issueNumber;
	public String title;
	public String author;
	public int order;
	public String content;

}
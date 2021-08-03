package com.liferay.amf.newsletter.search;

public interface NewsletterBatchReindexer {

    public void reindex(long guestbookId, long companyId);
}

<%@ include file="/init.jsp" %>

<portlet:renderURL var="searchURL">
	<portlet:param name="mvcRenderCommandName" value="/search/view" />
</portlet:renderURL>

<portlet:renderURL var="viewURL">
	<portlet:param
		name="mvcRenderCommandName"
		value="/issue-list/view"
	/>
</portlet:renderURL>

<aui:form action="${searchURL}" name="fm">
	<liferay-ui:header backURL="${viewURL}" title="back" />

	<div class="row">
		<div class="col-md-8">
			<aui:input inlineLabel="left" label="" name="keywords" placeholder="search-newsletters" size="256" />
		</div>

		<div class="col-md-4">
			<aui:button type="submit" value="search" />
		</div>
	</div>
</aui:form>

<h2>Search Results for ${keywords} </h2>
<liferay-ui:search-container delta="5" emptyResultsMessage="no-results-found-please-try-searching-with-other-keywords" total="${newslettersSize }">
	<liferay-ui:search-container-results
		results="${newsletters }"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.amf.newsletter.model.Newsletter"
		escapedModel="${true }"
		keyProperty="newsletterId"
		modelVar="newsletter"
	>
		<liferay-ui:search-container-column-text
			href="/newsletter/-/article-issue/${newsletter.newsletterId}/issue/${issueLocalService.getIssueByIssueNumber(newsletter.issueNumber).issueId}"
		value="${newsletter.title } by ${newsletter.author }" />

	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>
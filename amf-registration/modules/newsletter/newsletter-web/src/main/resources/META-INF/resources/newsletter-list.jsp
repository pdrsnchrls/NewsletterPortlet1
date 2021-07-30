<%@ include file="/init.jsp" %>

<!-- Create a searchContainer variable for each issue.issueNumber... -->
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-ui:search-container delta="4" emptyResultsMessage="no-newsletters-to-display" var="searchContainer">
	<liferay-ui:search-container-results results="${newsletterLocalService.findByIssueNumber(issue.issueNumber) }" />

	<liferay-ui:search-container-row className="com.liferay.amf.newsletter.model.Newsletter" keyProperty="newsletterId" modelVar="newsletter">
		<!--renderURL for each individual article-->
		<portlet:renderURL var="viewArticleURL">
			<portlet:param name="mvcRenderCommandName" value="/article/view" />
			<portlet:param name="newsletterId" value="${newsletter.newsletterId }" />
			<portlet:param name="issueId" value="${issue.issueId}" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text href="<%= viewArticleURL.toString() %>" value="${newsletter.title } by ${newsletter.author }" /> <!--href=""-->
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>
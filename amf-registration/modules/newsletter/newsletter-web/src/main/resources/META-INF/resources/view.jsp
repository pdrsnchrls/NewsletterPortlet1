<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/init.jsp" %>

<!-- Make a jsp page to display the values of the iSsUe using the newsletter persistence -->

<liferay-ui:tabs
	names="${years }"
	tabsValues="${years }"
	param="tab"
	url="${portletURL.toString() }"
	type="tabs nav-tabs-default"
>
	<liferay-ui:section>
		<c:forEach items="${issuesBySelectedYear}" var="issue">
			<p><small>Issue #${issue.issueNumber}, ${IssueLocalService.formatIssueDate(issue.issueDate) }</small></p>
			<!--renderURL for each individual issue-->
			<portlet:renderURL var="viewFullIssueURL">
				<portlet:param name="mvcRenderCommandName" value="/issue/view" />
				<portlet:param name="issueId" value="${issue.issueId }" />
				<portlet:param name="issueNumber" value="${issue.issueNumber }" />
			</portlet:renderURL>

			<a href="<%= viewFullIssueURL.toString() %>"><h2>${issue.title }</h2></a>
				<%@ include file="newsletter-list.jsp" %>
			<br />
		</c:forEach>
	</liferay-ui:section>

</liferay-ui:tabs>
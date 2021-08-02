<%@ include file="/init.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<liferay-ui:tabs
	names="${years }"
	param="tab"
	type="tabs"
	url="${portletURL.toString() }"
>
	<liferay-ui:section>
		<c:forEach items="${monthsBySelectedYear}" var="month">
			<h2>${issueLocalService.getMonthForInt(month-1) } Issues</h2>

			<hr />

			<c:forEach items="${issueLocalService.getIssuesByYearAndMonth(year, month)}" var="issue">
				<portlet:renderURL var="viewFullIssueURL">
					<portlet:param name="mvcRenderCommandName" value="/issue/view" />
					<portlet:param name="issueId" value="${issue.issueId }" />
					<portlet:param name="issueNumber" value="${issue.issueNumber }" />
				</portlet:renderURL>

				<p><small>Issue #${issue.issueNumber} - ${issueLocalService.formatIssueDate(issue.issueDate) }</small></p>

				<a href="/newsletter/-/article-issue/${issue.issueNumber}/${issue.issueId}"><h2>${issue.title }</h2></a>

				<%@ include file="newsletter-list.jsp" %>
				<br /> <br />
			</c:forEach>
		</c:forEach>
	</liferay-ui:section>
</liferay-ui:tabs>
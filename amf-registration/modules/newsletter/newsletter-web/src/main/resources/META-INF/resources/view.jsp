<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/init.jsp" %>

<liferay-ui:tabs
	names="${years }"
	param="tab"
	url="${portletURL.toString() }"
	type="tabs"
	value="${defaultTab }"
>
	<liferay-ui:section>

		<c:forEach items="${monthsBySelectedYear}" var="month">
			<p>Month: ${month} Year: ${year}</p>

			<c:forEach items="${issueLocalService.getIssuesByYearAndMonth(year, month)}" var="issue">
				<p><small>Issue #${issue.issueNumber} - ${issueLocalService.formatIssueDate(issue.issueDate) }</small></p>
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
		</c:forEach>


		<p> HEY THERE THIS IS A BREAK </p>

	</liferay-ui:section>

</liferay-ui:tabs>
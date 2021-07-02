<%@ include file="/init.jsp" %>

<!-- 
Grab issue details - number, date, title.
Grab a list of newsletters with the same issueNumber 
Separate by Issue number in search container
-->
<portlet:renderURL var="viewListURL">
	<portlet:param name="mvcRenderCommandName" value="/newsletter-list/view" />
</portlet:renderURL>
		
<!-- Make a jsp page to display the values of the iSsUe using the newsletter persistence -->
<c:forEach items="${issuesList }" var="issue">
	<p>Issue #${issue.issueNumber} ${issue.issueDate }</p>
	<%@ include file="/newsletter-list.jspf" %>
	<br />
</c:forEach>
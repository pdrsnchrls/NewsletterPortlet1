<%@ include file="/init.jsp" %>
<portlet:renderURL var="viewIssueListURL">
    <portlet:param name="mvcRenderCommandName" value="/issue-list/view" />
</portlet:renderURL>

<p><small>Issue #${issueNumber} - ${issueDate }</small></p>
<h2>${issueTitle}</h2>
<h4>${authorByline}</h4>
<p>${issueDescription}</p>
<br />

<c:forEach items="${newsletterList }" var="newsletter">
    <h3>&ensp;${newsletter.title }</h3>
    <p>&emsp;${newsletter.content}</p>
    <br />
</c:forEach>

<aui:button-row>
    <aui:button onClick="<%= viewIssueListURL.toString() %>" value="Back" />
</aui:button-row>
<%@ include file="/init.jsp" %>

<!--To return to view.jsp-->
<portlet:renderURL var="viewIssueListURL">
    <portlet:param name="mvcRenderCommandName" value="/issue-list/view" />
</portlet:renderURL>

<p><small>Issue: #${issueNumber } - ${issueDate }</small></p>
<h2>${newsletterTitle }</h2>
<h4>${newsletterAuthor }</h4>
<br />
<p>${newsletterContent }</p>

<br /><br />

<aui:button-row>
    <aui:button onClick="<%= viewIssueListURL.toString() %>" value="Back" />
</aui:button-row>
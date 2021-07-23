<%@ include file="/init.jsp" %>

<p><small>Issue #${issueNumber}, ${issueDate }</small></p>
<h2>${issueTitle}</h2>
<h4>${authorByline}</h4>
<br />

<c:forEach items="${newsletterList }" var="newsletter">
    <h3>&ensp;${newsletter.title }</h3>
    <p>&emsp;${newsletter.content}</p>
    <br />
</c:forEach>
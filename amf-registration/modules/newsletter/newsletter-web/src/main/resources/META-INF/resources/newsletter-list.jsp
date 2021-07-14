<!-- Create a searchContainer variable for each issue.issueNumber... -->
<%@ include file="/init.jsp" %>


<!--onClick could be used to call a function to get issues by years 0.o-->
<!-- a for each that displays tabs for the months present for each year, where there is a published newsletter -->
<!-- this would likely have to call a method to get the months present for the issue year... (i.e. getMonths(year) -->
<!-- also would likely have to have something similar like get issues by Month/Year-->
<c:forEach items="${issuesList }" var="issue">
 <p>Issue #${issue.issueNumber} ${issue.issueDate }</p>

</c:forEach>

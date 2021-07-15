<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<!-- Create a searchContainer variable for each issue.issueNumber... -->
<%@ include file="/init.jsp" %>

<c:forEach items="${issuesBySelectedYear}" var="issue">
 <p><small>Issue #${issue.issueNumber}, ${issue.issueDate }</small></p>
 <h2>${issue.title }</h2>
 <liferay-ui:search-container var="searchContainer" delta="4" emptyResultsMessage="no-newsletters-to-display">
   <liferay-ui:search-container-results results="${newsletterLocalService.findByIssueNumber(issue.issueNumber) }" />
   <liferay-ui:search-container-row className="com.liferay.amf.newsletter.model.Newsletter" modelVar="newsletter" keyProperty="newsletterId" >
    <liferay-ui:search-container-column-text value="${newsletter.title } by ${newsletter.author }"/> <!--href=""-->
   </liferay-ui:search-container-row>
  <liferay-ui:search-iterator />
 </liferay-ui:search-container>
    <br />
</c:forEach>

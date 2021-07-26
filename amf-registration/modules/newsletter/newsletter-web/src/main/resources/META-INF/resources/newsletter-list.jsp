<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<!-- Create a searchContainer variable for each issue.issueNumber... -->
<%@ include file="/init.jsp" %>

<!-- Render command for article -->


<c:forEach items="${issuesBySelectedYear}" var="issue">
 <p><small>Issue #${issue.issueNumber}, ${issue.issueDate }</small></p>
    <!--renderURL for each individual issue-->
    <portlet:renderURL var="viewFullIssueURL">
        <portlet:param name="mvcRenderCommandName" value="/issue/view" />
        <portlet:param name="issueId" value="${issue.issueId }" />
        <portlet:param name="issueNumber" value="${issue.issueNumber }" />
    </portlet:renderURL>
 <a href="<%= viewFullIssueURL.toString() %>"><h2>${issue.title }</h2></a>

 <liferay-ui:search-container var="searchContainer" delta="4" emptyResultsMessage="no-newsletters-to-display">
   <liferay-ui:search-container-results results="${newsletterLocalService.findByIssueNumber(issue.issueNumber) }" />
   <liferay-ui:search-container-row className="com.liferay.amf.newsletter.model.Newsletter" modelVar="newsletter" keyProperty="newsletterId" >
       <!--renderURL for each individual article-->
       <portlet:renderURL var="viewArticleURL">
           <portlet:param name="mvcRenderCommandName" value="/article/view" />
           <portlet:param name="newsletterId" value="${newsletter.newsletterId }" />
           <portlet:param name="issueId" value="${issue.issueId}" />
       </portlet:renderURL>
       <liferay-ui:search-container-column-text value="${newsletter.title } by ${newsletter.author }" href="<%= viewArticleURL.toString() %>"/> <!--href=""-->
   </liferay-ui:search-container-row>
  <liferay-ui:search-iterator />
 </liferay-ui:search-container>
    <br />
</c:forEach>

<%@ include file="/init.jsp" %>

<p> Here is a list of the newsletters sorted by IssueNumber </p>
<!-- 
Grab issue details - number, date, title.
Grab a list of newsletters with the same issueNumber 
Separate by Issue number in search container
-->
<portlet:renderURL var="viewListURL">
	<portlet:param name="mvcRenderCommandName" value="/newsletter-list/view" />
</portlet:renderURL>

<p>Issue Test</p>

<liferay-ui:search-container searchContainer="${searchContainer }" >
  
	<liferay-ui:search-container-results results="${ searchContainer.getResults() }"/>
		
		<liferay-ui:search-container-row className="com.liferay.amf.newsletter.model.Issue" modelVar="issue" keyProperty="issueId" >
			<liferay-ui:search-container-column-text name="title" value="${issue.issueTitle}" />
			<liferay-ui:search-container-column-text name="description" value="${issue.description }" />
			<liferay-ui:search-container-column-text name="date" value="${issue.issueDate }" />
		</liferay-ui:search-container-row>
		
	<liferay-ui:search-iterator searchContainer="${searchContainer }" />

</liferay-ui:search-container>
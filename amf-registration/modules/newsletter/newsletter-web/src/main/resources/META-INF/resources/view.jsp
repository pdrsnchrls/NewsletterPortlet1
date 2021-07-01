<%@ include file="/init.jsp" %>

<!-- 
Grab issue details - number, date, title.
Grab a list of newsletters with the same issueNumber 
Separate by Issue number in search container
-->
<portlet:renderURL var="viewListURL">
	<portlet:param name="mvcRenderCommandName" value="/newsletter-list/view" />
</portlet:renderURL>
		
		<c:forEach items="${issuesList }" var="issue">
			<br /><p>Issue #${issue.issueNumber} ${issue.issueDate }</p>
			
			<liferay-ui:search-container searchContainer="${newsletterSearchContainer }">
  
				<liferay-ui:search-container-results results="${ newsletterSearchContainer.getResults() }"/>	
					<liferay-ui:search-container-row className="com.liferay.amf.newsletter.model.Newsletter" modelVar="newsletter" keyProperty="newsletterId">
						<liferay-ui:search-container-column-text name="title" value="${newsletter.title}" />
						<liferay-ui:search-container-column-text name="author" value="${newsletter.author}" />
					</liferay-ui:search-container-row>
				<liferay-ui:search-iterator searchContainer="${newsletterSearchContainer }" />

			</liferay-ui:search-container>
		</c:forEach>
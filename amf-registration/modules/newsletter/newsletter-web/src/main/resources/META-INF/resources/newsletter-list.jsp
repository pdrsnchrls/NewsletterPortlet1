<!-- Create a searchContainer variable for each issue.issueNumber... -->
<%@ include file="/init.jsp" %>


<portlet:renderURL var="viewNewslettersURL">
	<portlet:param name="mvcRenderCommandName" value="/newsletter-list/view" />
</portlet:renderURL>

<p> Sup baby </p>

<!-- <liferay-ui:search-container searchContainer="${newsletterSearchContainer }">
  
	<liferay-ui:search-container-results results="${ newsletterSearchContainer.getResults() }"/>	
		<liferay-ui:search-container-row className="com.liferay.amf.newsletter.model.Newsletter" modelVar="newsletter" keyProperty="newsletterId">
			<liferay-ui:search-container-column-text name="title" value="${newsletter.title}" />
			<liferay-ui:search-container-column-text name="author" value="${newsletter.author}" />
		</liferay-ui:search-container-row>
	<liferay-ui:search-iterator searchContainer="${newsletterSearchContainer }" />

</liferay-ui:search-container> --> 
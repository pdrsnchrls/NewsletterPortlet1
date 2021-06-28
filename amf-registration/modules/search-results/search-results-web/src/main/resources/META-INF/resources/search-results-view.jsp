<%@ include file="/init.jsp" %>

<liferay-ui:error key="systemFailure" message="system-failure" />

<p> Search Results 
	<c:if test = "${not empty zip }" >
		for ${ zip }
	</c:if>
</p>
 
<!-- <liferay-portlet:renderURL varImpl="iteratorURL" >
	<portlet:param name="mvcPath" value="search-results-view.jsp" />
</liferay-portlet:renderURL>
 iteratorURL="${iteratorURL }"
<!--
iteratorURL="<=iteratorURL %>" scriplets

 -->


<liferay-ui:search-container searchContainer="${searchContainer }" >
  
	<liferay-ui:search-container-results results="${ searchContainer.getResults() }"/>
		
		<liferay-ui:search-container-row className="com.liferay.portal.kernel.model.User" modelVar="user" keyProperty="userId" >
			<liferay-ui:search-container-column-text name="name" value="${user.firstName} ${user.lastName.charAt(0)}." />
			<liferay-ui:search-container-column-text name="screen-name" value="${user.screenName }" />
			<liferay-ui:search-container-column-text name="email" value="${user.emailAddress }" />
		</liferay-ui:search-container-row>
		
	<liferay-ui:search-iterator searchContainer="${searchContainer }" />

</liferay-ui:search-container>
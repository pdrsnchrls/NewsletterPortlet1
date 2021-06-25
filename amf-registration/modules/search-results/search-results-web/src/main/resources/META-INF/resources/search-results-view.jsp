<%@ include file="/init.jsp" %>

<liferay-ui:error key="systemFailure" message="system-failure" />

<p> Search Results for ${ zip } </p>

<liferay-ui:search-container searchContainer="${model.searchContainer}" total="${usersSize}" delta="${model.searchContainer.delta}" deltaConfigurable="false" 
  emptyResultsMessage="no-results-found-please-try-a-different-search-criteria">
  
	<liferay-ui:search-container-results results="${model.searchContainer.results() }"/>
		
		<liferay-ui:search-container-row className="com.liferay.portal.kernel.model.User" modelVar="user" keyProperty="userId" >
			<liferay-ui:search-container-column-text name="name" value="${user.firstName} ${user.lastName.charAt(0)}." />
			<liferay-ui:search-container-column-text name="screen-name" value="${user.screenName }" />
			<liferay-ui:search-container-column-text name="email" value="${user.emailAddress }" />
		</liferay-ui:search-container-row>
		
	<liferay-ui:search-iterator />

</liferay-ui:search-container>
<%@ include file="/init.jsp" %>

<liferay-ui:error key="systemFailure" message="system-failure" />

<p> Search Results 
	<c:if test = "${not empty zip }" >
		for ${ zip }
	</c:if>
</p>

<liferay-ui:search-container searchContainer="${searchContainer }" total="${usersSize}" delta="5" deltaConfigurable="false" 
  emptyResultsMessage="no-results-found-please-try-a-different-search-criteria">
  
	<liferay-ui:search-container-results results="${ users }"/>
		
		<liferay-ui:search-container-row className="com.liferay.portal.kernel.model.User" modelVar="user" keyProperty="userId" >
			<liferay-ui:search-container-column-text name="name" value="${user.firstName} ${user.lastName.charAt(0)}." />
			<liferay-ui:search-container-column-text name="screen-name" value="${user.screenName }" />
			<liferay-ui:search-container-column-text name="email" value="${user.emailAddress }" />
		</liferay-ui:search-container-row>
		
	<liferay-ui:search-iterator />

</liferay-ui:search-container>
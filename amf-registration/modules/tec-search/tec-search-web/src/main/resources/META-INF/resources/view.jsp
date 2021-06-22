<%@ include file="/init.jsp" %>

<liferay-ui:error key="serviceErrorDetails">
	<liferay-ui:message arguments='<%= SessionErrors.get(liferayPortletRequest, "serviceErrorDetails") %>' key="error.assignment-service-error" />
</liferay-ui:error>
<liferay-ui:error key="zipEmpty" message="error.zip-empty" />
<liferay-ui:error key="zipLengthError" message="error.zip-length" />
<liferay-ui:error key="zipNonNumeric" message="error.zip-non-numeric" />

<portlet:actionURL name="search" var="searchURL" />

<aui:form action="<%= searchURL %>" name="<portlet:namespace />fm">
	<aui:input type="text" name="enter-us-zip"></aui:input>
	<aui:button type="submit" value="search"></aui:button>
	
</aui:form>
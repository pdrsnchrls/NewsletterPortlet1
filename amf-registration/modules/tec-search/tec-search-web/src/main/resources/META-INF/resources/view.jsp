<%@ include file="/init.jsp" %>

<liferay-ui:error key="serviceErrorDetails">
	<liferay-ui:message arguments='<%= SessionErrors.get(liferayPortletRequest, "serviceErrorDetails") %>' key="error.assignment-service-error" />
</liferay-ui:error>
<liferay-ui:error key="zipEmpty" message="zip-required" />
<liferay-ui:error key="zipLengthError" message="zip-must-be-5-digits" />
<liferay-ui:error key="zipNonNumeric" message="zip-must-be-numeric" />

<portlet:actionURL name="search" var="searchURL" />

<aui:form action="<%= searchURL %>" name="<portlet:namespace />fm">
	<aui:input type="text" name="zip"></aui:input>
	<aui:button type="submit" value="search"></aui:button>
	
</aui:form>
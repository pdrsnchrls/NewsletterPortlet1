<!--Render URL for Search Bar-->
<liferay-portlet:actionURL name="/search/view" var="searchResultsURL">
    <portlet:param name="mvcActionCommand" value="/search/view" />
</liferay-portlet:actionURL>

<liferay-portlet:actionURL name="/search/view" var="searchResultsURL" />

<aui:form action="<%= searchResultsURL %>" method="post" name="fm">
    <aui:input inlineField="${true }" label=""
               name="keywords" size="30" title="search-entries" type="text"
    />
    <aui:button type="submit" value="search" />
</aui:form>
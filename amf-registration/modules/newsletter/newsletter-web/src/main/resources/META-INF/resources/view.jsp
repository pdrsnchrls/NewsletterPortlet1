<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/init.jsp" %>

<!-- 
Grab issue details - number, date, title.
Grab a list of newsletters with the same issueNumber 
Separate by Issue number in search container
-->
<portlet:renderURL var="viewListURL">
	<portlet:param name="mvcRenderCommandName" value="/issue-list/view" />
</portlet:renderURL>

<!-- Make a jsp page to display the values of the iSsUe using the newsletter persistence -->

<liferay-ui:tabs
	names="${years }"
	param="tab"
	url="${portletURL.toString() }"
	type="tabs nav-tabs-default"
>
	<liferay-ui:section>
		<p>The year is ${year }</p>
		<%@ include file="newsletter-list.jsp" %>
	</liferay-ui:section>

</liferay-ui:tabs>
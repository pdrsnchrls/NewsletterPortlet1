<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/init.jsp" %>

<!-- Make a jsp page to display the values of the iSsUe using the newsletter persistence -->

<liferay-ui:tabs
	names="${years }"
	tabsValues="${years }"
	param="tab"
	url="${portletURL.toString() }"
	type="tabs nav-tabs-default"
>
	<liferay-ui:section>
		<%@ include file="newsletter-list.jsp" %>
	</liferay-ui:section>

</liferay-ui:tabs>
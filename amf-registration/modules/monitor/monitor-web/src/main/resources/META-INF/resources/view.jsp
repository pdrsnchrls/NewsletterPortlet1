<%@ include file="/init.jsp"%>

<liferay-ui:tabs names='<%= "all-tab,registration-tab,login-tab" %>'
	param="tabs"
	refresh="<%= false %>"	
	type="tabs nav-tabs-default"
>

	<liferay-ui:section>
		<%@ include file="/configuration/all_events.jspf" %>
	</liferay-ui:section>

	<liferay-ui:section>
		<%@ include file="/configuration/registration_events.jspf" %>
	</liferay-ui:section>

	<liferay-ui:section>
		<%@ include file="/configuration/login_events.jspf" %>
	</liferay-ui:section>
	

</liferay-ui:tabs>

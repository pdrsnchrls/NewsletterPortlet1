<%@ include file="/init.jsp" %>

<liferay-ui:tabs names="2018,2019,2020" param="tabs" type="tabs nav-tabs-default" refresh="false">
	<c:forEach var="i" begin="0" end="3">
		<liferay-ui:section>
			<%@ include file="/years.jspf" %>
		</liferay-ui:section>
	</c:forEach>
</liferay-ui:tabs>
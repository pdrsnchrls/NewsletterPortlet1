<%@ include file="/init.jsp" %>

<portlet:renderURL var="searchURL">
    <portlet:param name="mvcRenderCommandName" value="/search/view" />
</portlet:renderURL>

<portlet:renderURL var="viewURL">
    <portlet:param
            name="mvcPath"
            value="/view.jsp"
    />
</portlet:renderURL>

<aui:form action="${searchURL}" name="fm">

    <liferay-ui:header backURL="${viewURL}" title="back" />

    <div class="row">
        <div class="col-md-8">
            <aui:input inlineLabel="left" label="" name="keywords" placeholder="search-newsletters" size="256" />
        </div>

        <div class="col-md-4">
            <aui:button type="submit" value="search" />
        </div>
    </div>
</aui:form>

<liferay-ui:search-container delta="5" emptyResultsMessage="no-newsletters-were-found" total="${newslettersSize }">
    <liferay-ui:search-container-results
            results="${newsletters }" />
    <liferay-ui:search-container-row
            className="com.liferay.amf.newsletter.model.Newsletter"
            keyProperty="newsletterId" modelVar="newsletter" escapedModel="${true }">
        <liferay-ui:search-container-column-text href="/newsletter/-/article-issue/${newsletter.newsletterId}/issue/${issue.issueId}"
                                                 value="${newsletter.title } by ${newsletter.author }" />


    </liferay-ui:search-container-row>
    <liferay-ui:search-iterator />
</liferay-ui:search-container>
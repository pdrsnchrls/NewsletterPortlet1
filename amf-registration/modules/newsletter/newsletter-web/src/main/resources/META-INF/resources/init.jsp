<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
<%@ taglib prefix="clay" uri="http://liferay.com/tld/clay" %>
<%@ taglib prefix="liferay-item-selector" uri="http://liferay.com/tld/item-selector" %>
<%@ taglib prefix="liferay-frontend" uri="http://liferay.com/tld/frontend" %>
<%@ taglib prefix="liferay-portlet" uri="http://liferay.com/tld/portlet" %>
<%@ taglib prefix="liferay-theme" uri="http://liferay.com/tld/theme" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix="liferay-security" uri="http://liferay.com/tld/security" %>

<%@ page import="com.liferay.amf.newsletter.model.Newsletter" %>
<%@ page import="java.util.List"%>
<%@ page import="com.liferay.amf.newsletter.service.NewsletterLocalServiceUtil"%>
<%@ page import="com.liferay.amf.newsletter.service.NewsletterLocalService"%>
<%@ page import="com.liferay.amf.newsletter.service.IssueLocalService"%>
<%@ page import="com.liferay.amf.newsletter.service.IssueLocalServiceUtil" %>

<%@ page import="com.liferay.portal.kernel.util.ListUtil"%>


<liferay-theme:defineObjects />

<portlet:defineObjects />
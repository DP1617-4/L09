<%--
 * edit.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form method="post" action="systemConfiguration/administrator/setBanner.do">
	<label><spring:message code="systemConfiguration.banner" /></label>
	<input type="text" name="banner" id="banner"><br />
	<input type="submit" name="changeBanner"
		value="<spring:message code="systemConfiguration.change.banner" />" /><br />
</form>
<br />

<jstl:if test="${admin.id == 0}">
	<input type="button" name="cancel"
		value="<spring:message code="systemConfiguration.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />
</jstl:if>
<br />

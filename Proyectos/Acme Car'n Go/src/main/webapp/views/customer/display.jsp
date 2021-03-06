<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<security:authentication property="principal" var ="loggedactor"/>
<jstl:set var="customer" value="${customer}"/> 

<h2><spring:message code="customer" /></h2>
<p><spring:message code="customer.name"/>: <jstl:out value="${customer.name}" /></p> 
<p><spring:message code="customer.surname"/>: <jstl:out value="${customer.surname}" /></p> 
<p><spring:message code="customer.email"/>: <jstl:out value="${customer.email}" /></p> 
<p><spring:message code="customer.phone"/>: <jstl:out value="${customer.phoneNumber}" /></p> 

<br/>


<b><spring:message code="customer.comments"/></b><br/>

<security:authorize access="hasRole('CUSTOMER')">


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="customerComments" requestURI="${requestURI }" id="row">

	<!--Attributes -->
	<spring:message code="comment.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />

	<spring:message code="comment.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" sortable="true" />
	
	<spring:message code="comment.stars" var="starsHeader" />
	<display:column property="stars" title="${starsHeader}" sortable="true" />

	<spring:message code="comment.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}"  format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	<spring:message code="comment.actor" var="authorHeader"/>
	<display:column title="${authorHeader}">
		<a href="actor/actor/display.do?actorId=${row.actor.id}"> ${row.actor.name} ${row.actor.surname}</a>
	</display:column>
	
</display:table>

	
</security:authorize>

<security:authorize access="hasRole('ADMIN')">

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="adminComments" requestURI="${requestURI }" id="row">

	<!--Attributes -->
	<spring:message code="comment.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />

	<spring:message code="comment.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" sortable="true" />
	
	<spring:message code="comment.stars" var="starsHeader" />
	<display:column property="stars" title="${starsHeader}" sortable="true" />

	<spring:message code="comment.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}"  format="{0,date,dd/MM/yyyy HH:mm}"/>
	
	<spring:message code="comment.actor" var="authorHeader"/>
	<display:column title="${authorHeader}">
		<a href="actor/actor/display.do?actorId=${row.actor.id}"> ${row.actor.name} ${row.actor.surname}</a>
	</display:column>
	
	<spring:message code="comment.ban" var="banHeader"/>
	<display:column title="${banHeader}">
	<jstl:if test="${row.banned eq false || row.banned == null}">
		<a href="comment/administrator/ban.do?commentId=${row.id}"> ban</a>
	</jstl:if>
	</display:column>

	
</display:table>
	
</security:authorize>
<br/>

<security:authorize access="hasRole('CUSTOMER')">
<a href="comment/actor/create.do?commentableId=${customer.id}"><spring:message code="comment.new" /></a>
</security:authorize>


                               <!-- AQUI DEBERIA MOSTRARSE LOS POSTS Y LAS APPLICATIONS! -->
<%-- <b><spring:message code="tenant.socials"/></b><br/>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="socialIdentities" requestURI="tenant/display.do" id="row">

	<!-- Attributes -->
	
	<spring:message code="socialidentity.nick" var="nickHeader" />
	<display:column property="nick" title="${nickHeader}" sortable="true" />

	<spring:message code="socialidentity.network.link" var="linkHeader" />
	<display:column title="${linkHeader}" sortable="false" >
		<a href="${row.socialNetworkLink}"><spring:message code="socialidentity.network.link"/></a>
	</display:column>
	
	<spring:message code="socialidentity.network.name" var="nameHeader" />
	<display:column property="socialNetworkName" title="${nameHeader}" sortable="false" />
	
</display:table> --%>

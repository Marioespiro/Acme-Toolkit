<%--
- list.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.item.list.label.name" path="name" width="20%"/>
	<acme:list-column code="inventor.item.list.label.code" path="code" width="10%"/>
	<acme:list-column code="inventor.item.list.label.technology" path="technology" width="50%"/>
	<acme:list-column code="inventor.item.list.label.price" path="retailPrice" width="20%"/>
</acme:list>

<jstl:if test="${acme:anyOf(command, 'list-component')}">
	<acme:button code="inventor.component.list.button.create" action="/inventor/item/create-component"/>
</jstl:if>	
<jstl:if test="${acme:anyOf(command, 'list-tool')}">
	<acme:button code="inventor.tool.list.button.create" action="/inventor/item/create-tool"/>
</jstl:if>	



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
	<acme:list-column code="authenticated.inventor.toolkit.list.code" path="code" width="10%"/>
	<acme:list-column code="authenticated.inventor.toolkit.list.title" path="title" width="10%"/>
	<acme:list-column code="authenticated.inventor.toolkit.list.description" path="description" width="20%"/>
	<acme:list-column code="authenticated.inventor.toolkit.list.assemblyNotes" path="assemblyNotes" width="20%"/>
	<acme:list-column code="authenticated.inventor.toolkit.list.link" path="link" width="20%"/>
	<acme:list-column code="authenticated.inventor.toolkit.list.isPublished" path="isPublished" width="10%"/>
</acme:list>
<acme:button code="authenticated.inventor.toolkit.list.button.create" action="/inventor/toolkit/create"/>
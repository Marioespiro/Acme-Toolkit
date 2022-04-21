<%--
- form.jsp
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

<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<acme:form readonly="true">
	<acme:input-textbox code="authenticated.item.form.label.title" path="title"/>	
	<acme:input-textbox code="authenticated.item.form.label.code" path="code"/>	
	<acme:input-textarea code="authenticated.item.form.label.description" path="description"/>
	<acme:input-textarea code="authenticated.item.form.label.assemblyNotes" path="assemblyNotes"/>
	<acme:input-textbox code="authenticated.item.form.label.link" path="link"/>
	<acme:input-textbox code="authenticated.item.form.label.retailPrice" path="retailPrice"/>
	<acme:button code="authenticated.item.form.button.components" action="/any/item/list-component?toolkitId=${id}"/>	
	<acme:button code="authenticated.item.form.button.tools" action="/any/item/list-tool?toolkitId=${id}"/>	
</acme:form>



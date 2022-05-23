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

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:input-textbox code="authenticated.item.form.label.name" path="name"/>	
	<acme:input-textbox code="authenticated.item.form.label.code" path="code"/>	
	<acme:input-textbox code="authenticated.item.form.label.technology" path="technology"/>
	<acme:input-textarea code="authenticated.item.form.label.description" path="description"/>
	<acme:input-textbox code="authenticated.item.form.label.retailPrice" path="retailPrice"/>
	<acme:input-textbox code="authenticated.item.form.label.link" path="link"/>
	<acme:button code="authenticated.item.form.button.findBy" action="/any/toolkit/list-with-item?id=${id}"/>
</acme:form>

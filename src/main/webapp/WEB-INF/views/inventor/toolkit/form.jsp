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
    <acme:input-textbox code="authenticated.inventor.toolkit.form.label.code" path="code"/>	
	<acme:input-textbox code="authenticated.inventor.toolkit.form.label.title" path="title"/>	
	<acme:input-textarea code="authenticated.inventor.toolkit.form.label.description" path="description"/>	
	<acme:input-textarea code="authenticated.inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>	
	<acme:input-textbox code="authenticated.inventor.toolkit.form.label.link" path="link"/>
	<acme:input-textbox code="authenticated.inventor.toolkit.form.label.retailPrice" path="retailPrice"/>
	<acme:button code="authenticated.inventor.toolkit.form.button.component" action="/inventor/item/list-component?toolkitId=${id}"/>	
	<acme:button code="authenticated.inventor.toolkit.form.button.tools" action="/inventor/item/list-tool?toolkitId=${id}"/>	
</acme:form>
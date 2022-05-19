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

<acme:form readonly="false">
    <acme:input-textbox code="authenticated.inventor.toolkit.form.label.code" path="code"/>	
	<acme:input-textbox code="authenticated.inventor.toolkit.form.label.title" path="title"/>	
	<acme:input-textarea code="authenticated.inventor.toolkit.form.label.description" path="description"/>	
	<acme:input-textarea code="authenticated.inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>	
	<acme:input-textbox code="authenticated.inventor.toolkit.form.label.link" path="link"/>
	<jstl:if test="${acme:anyOf(command, 'show') && isPublished == true}">
	<acme:input-textbox code="authenticated.inventor.toolkit.form.label.retailPrice" path="retailPrice"/>	 
	</jstl:if>
		<jstl:if test="${acme:anyOf(command, 'show, update, delete, publish') && isPublished == false}">
			<acme:button code="authenticated.inventor.toolkit.form.button.quantity" action="/inventor/quantity/list?toolkitId=${id}"/>
			<acme:submit code="authenticated.inventor.toolkit.form.button.update" action="/inventor/toolkit/update"/>
			<acme:submit code="authenticated.inventor.toolkit.form.button.delete" action="/inventor/toolkit/delete?id=${id}"/>
			<acme:submit code="authenticated.inventor.toolkit.form.button.publish" action="/inventor/toolkit/publish?id=${id}"/>
		</jstl:if>
	<jstl:if test="${acme:anyOf(command, 'create')}">
	<acme:submit code="authenticated.inventor.toolkit.form.button.create" action="/inventor/toolkit/create"/>
	</jstl:if>
	
</acme:form>
<jstl:if test="${acme:anyOf(command, 'show')}">
	<acme:button code="authenticated.inventor.toolkit.form.button.component" action="/inventor/item/list-component?toolkitId=${id}"/>	
	<acme:button code="authenticated.inventor.toolkit.form.button.tools" action="/inventor/item/list-tool?toolkitId=${id}"/>
</jstl:if>	
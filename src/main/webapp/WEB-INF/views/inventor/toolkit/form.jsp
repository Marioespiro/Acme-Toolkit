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

<acme:form>
    <acme:input-textbox code="authenticated.inventor.toolkit.form.label.code" path="code"/>	
	<acme:input-textbox code="authenticated.inventor.toolkit.form.label.title" path="title"/>	
	<acme:input-textarea code="authenticated.inventor.toolkit.form.label.description" path="description"/>	
	<acme:input-textarea code="authenticated.inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>	
	<acme:input-textbox code="authenticated.inventor.toolkit.form.label.link" path="link"/>
	<acme:input-textbox code="authenticated.inventor.toolkit.form.label.retailPrice" path="retailPrice"/>
<%-- 	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && isPublished == false}">
			<acme:submit code="authenticated.inventor.toolkit.form.button.update" action="/inventor/toolkit/update"/>
			<acme:submit code="authenticated.inventor.toolkit.form.button.delete" action="/inventor/toolkit/delete?id=${id}"/>
			<acme:submit code="authenticated.inventor.toolkit.form.button.publish" action="/inventor/toolkit/publish?id=${id}"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'create')}">
		<jstl:set var = "toolsIndex" value = "1"/>
		<jstl:set var = "amountIndex" value = "101"/>
			<acme:input-select code="inventor.toolkit.form.label.select.artifacts" path="${toolsIndex}">	
			<jstl:forEach items="${tools}" var="optionTools">
				<acme:input-option code="${optionTools.name}" value="${optionTools.name}"/>			
			</jstl:forEach> 
			</acme:input-select>
			<acme:input-textbox code="inventor.toolkit.form.label.artifact.amount" path="${amountIndex}" placeholder="0"/>
			<jstl:set var = "toolsIndex" value = "${toolsIndex + 1}"/>
			<jstl:set var = "amountIndex" value = "${amountIndex + 1}"/>
			<acme:submit code="authenticated.inventor.toolkit.form.button.create" action="/inventor/toolkit/create"/>
		</jstl:when>		
	</jstl:choose> --%>
</acme:form>

	<acme:button code="authenticated.inventor.toolkit.form.button.component" action="/inventor/item/list-component?toolkitId=${id}"/>	
	<acme:button code="authenticated.inventor.toolkit.form.button.tools" action="/inventor/item/list-tool?toolkitId=${id}"/>	
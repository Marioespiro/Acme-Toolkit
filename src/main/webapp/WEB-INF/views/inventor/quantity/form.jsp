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
<jstl:if test="${acme:anyOf(command, 'show')}">
	<acme:input-textbox code="inventor.item.form.label.name" path="itemName"/>	
	<acme:input-textbox code="inventor.item.form.label.code" path="itemCode"/>	
	<acme:input-textbox code="inventor.item.form.label.technology" path="itemTechnology"/>
	<acme:input-textarea code="inventor.item.form.label.description" path="itemDescription"/>
	<acme:input-money code="inventor.item.form.label.retailPrice" path="itemRetailPrice"/>
	<acme:input-textbox code="inventor.item.form.label.link" path="itemLink"/>
	<acme:input-textbox code="authenticated.inventor.quantity.list.label.amount" path="amount"/>
</jstl:if>
<jstl:if test="${acme:anyOf(command, 'show') && isPublished == false}">
<acme:submit code="authenticated.inventor.toolkit.form.button.delete" action="/inventor/quantity/delete?id=${id}"/>
</jstl:if>
<jstl:if test="${acme:anyOf(command, 'create')}">
	<acme:input-integer code="authenticated.inventor.quantity.form.label.select.amount" path="amount"/>
	<acme:input-select code="authenticated.inventor.quantity.form.label.select.item" path="itemName">
			<jstl:forEach items="${items}" var="optionItem">
				<acme:input-option code="${optionItem.name}" value="${optionItem.name}"/>
			</jstl:forEach>
	</acme:input-select>
		<acme:submit code="authenticated.inventor.quantity.form.button.create" action="/inventor/quantity/create?toolkitId=${toolkitId}"/>		
	</jstl:if>
</acme:form>
	
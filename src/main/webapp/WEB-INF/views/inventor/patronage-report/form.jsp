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
    <acme:input-textbox code="inventor.patronageReport.list.label.automaticSequenceNumber" path="automaticSequenceNumber"/>	
	<acme:input-textbox code="inventor.patronageReport.list.label.creationMoment" path="creationMoment"/>	
</jstl:if>
	<acme:input-textbox code="inventor.patronageReport.list.label.memorandum" path="memorandum"/>	
	<acme:input-textbox code="inventor.patronageReport.list.label.link" path="link"/>

	<jstl:if test="${command == 'create'}">
		<acme:input-checkbox code="inventor.patronageReport.form.checkbox.confirm" path="confirm"/>
		<acme:submit code="inventor.patronageReport.form.button.create" action="/inventor/patronage-report/create?masterId=${masterId}"/>
	</jstl:if>
		
</acme:form>
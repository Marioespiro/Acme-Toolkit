<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
 
<acme:form>
	<acme:input-textbox code="administrator.announcement.form.label.title" path="title"/>
	<acme:input-textarea code="administrator.announcement.form.label.body" path="body"/>
	<acme:input-url code="administrator.announcement.form.label.url" path="url"/>
	<acme:input-checkbox code="administrator.announcement.form.label.critical" path="critical"/>
	
	<acme:submit test="${command == 'create'}" code="administrator.announcement.form.button.create" action="/administrator/announcement/create"/>
</acme:form>

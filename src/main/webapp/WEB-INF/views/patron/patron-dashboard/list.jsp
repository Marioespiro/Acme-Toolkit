<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="patron.dashboard.list.title"/>
</h2>
  <acme:list>
	<acme:list-column code="patron.dashboard.list.label.total" path="totalNumberOfPatronage" width="20%"/>
	<acme:list-column code="patron.dashboard.list.label.total" path="totalNumberOfPatronage" width="10%"/>
	<acme:list-column code="patron.dashboard.list.label.total" path="totalNumberOfPatronage" width="50%"/>
	<acme:list-column code="patron.dashboard.list.label.total" path="totalNumberOfPatronage" width="20%"/>
</acme:list> 
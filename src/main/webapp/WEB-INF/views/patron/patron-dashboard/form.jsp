<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="patron.dashboard.list.title"/>
</h2>
<acme:form readonly="true">
	<acme:input-textbox code="patron.dashboard.list.label.totalProposed" path="totalNumberOfProposedPatronage"/>
	<acme:input-textbox code="patron.dashboard.list.label.totalAccepted" path="totalNumberOfAcceptedPatronage"/>
	<acme:input-textbox code="patron.dashboard.list.label.totalDenied" path="totalNumberOfDeniedPatronage"/>
	<acme:input-textbox code="patron.dashboard.list.label.averageDenied" path="averageDeniedPatronages"/>
	<acme:input-textbox code="patron.dashboard.list.label.averageAccepted" path="averageAcceptedPatronages"/>
	<acme:input-textbox code="patron.dashboard.list.label.averageProposed" path="averageProposedPatronages"/>
	<acme:input-textbox code="patron.dashboard.list.label.deviationDenied" path="deviationDeniedPatronages"/>
	<acme:input-textbox code="patron.dashboard.list.label.deviationAccepted" path="deviationAcceptedPatronages"/>
	<acme:input-textbox code="patron.dashboard.list.label.deviationProposed" path="deviationProposedPatronages"/>
	<acme:input-textbox code="patron.dashboard.list.label.minDenied" path="minDeniedPatronages"/>
	<acme:input-textbox code="patron.dashboard.list.label.minAccepted" path="minAcceptedPatronages"/>
	<acme:input-textbox code="patron.dashboard.list.label.minProposed" path="minProposedPatronages"/>
	<acme:input-textbox code="patron.dashboard.list.label.maxDenied" path="maxDeniedPatronages"/>
	<acme:input-textbox code="patron.dashboard.list.label.maxAccepted" path="maxAcceptedPatronages"/>
	<acme:input-textbox code="patron.dashboard.list.label.maxProposed" path="maxProposedPatronages"/>				

</acme:form>

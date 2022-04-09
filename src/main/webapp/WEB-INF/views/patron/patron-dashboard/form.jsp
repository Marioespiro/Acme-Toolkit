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
		<acme:input-textbox code="patron.dashboard.list.label.totalDenied" path="averageDeniedPatronages"/>
					
<table class="table table-sm">
<acme:message code="patron.dashboard.list.label.averageDenied"/>
<jstl:forEach items="${averageDeniedPatronages}" var="denied">	
			<tr>
				<td>
					<acme:print value="${denied}"/>	
				</td>	
			</tr>
		</jstl:forEach>

</table>
<table class="table table-sm">
<acme:message code="patron.dashboard.list.label.averageProposed"/>

<jstl:forEach items="${averageProposedPatronages}" var="proposed">	
			<tr>
				<td>
					<acme:print value="${proposed}"/>	
				</td>	
			</tr>
		</jstl:forEach>

</table>
<table class="table table-sm">
	<acme:message code="patron.dashboard.list.label.averageAccepted"/>
<jstl:forEach items="${averageAcceptedPatronages}" var="accepted">	
			<tr>
				<td>
					<acme:print value="${accepted}"/>	
				</td>	
			</tr>
		</jstl:forEach>

</table>
</acme:form>

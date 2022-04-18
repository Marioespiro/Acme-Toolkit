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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>
	<acme:message code="patron.dashboard.form.title"/>
</h2>
<table class="table table-sm">
		<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.total"/>
		</th>
		<td>
			<c:forEach items="${totalNumberOfProposedPatronagesByStatus}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.average"/>
		</th>
		<td>
			<c:forEach items="${averageBudgetOfPatronagesStatusByCurrency}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.deviation"/>
		</th>
		<td>
			<c:forEach items="${deviationBudgetOfPatronagesStatusByCurrency}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.max"/>
		</th>
		<td>
			<c:forEach items="${maximumBudgetOfPatronagesStatusByCurrency}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.min"/>
		</th>
		<td>
			<c:forEach items="${minimumBudgetOfPatronagesStatusByCurrency}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
		</td>
	</tr>
</table>





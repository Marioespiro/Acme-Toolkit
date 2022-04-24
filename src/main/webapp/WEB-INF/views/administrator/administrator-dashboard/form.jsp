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
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<h2>
	<acme:message code="administrator.dashboard.form.title"/>
</h2>
<table class="table table-sm">
	<caption><acme:message code="administrator.dashboard.form.description-table"/></caption>
		<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-tools"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfTools}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-retail-price-items"/>
		</th>
		
		<td>
			<c:forEach items="${averageRetailPriceOfToolsByCurrency}" var="entry">
				${entry.key} = <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${entry.value}"/><br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-retail-price-items"/>
		</th>
		<td>
			<c:forEach items="${deviationRetailPriceOfToolsByCurrency}" var="entry">
				${entry.key} = <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${entry.value}"/><br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.min-retail-price-items"/>
		</th>
		<td>
			<c:forEach items="${minimumRetailPriceOfToolsByCurrency}" var="entry">
				${entry.key} = <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${entry.value}"/><br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.max-retail-price-items"/>
		</th>
		<td>
			<c:forEach items="${maximumRetailPriceOfToolsByCurrency}" var="entry">
				${entry.key} = <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${entry.value}"/><br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-components"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfComponents}"/>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-retail-price"/>
		</th>
		<td>
			<c:forEach items="${averageRetailPriceOfComponentsByTechnologyAndCurrency}" var="entry">
				${entry.key} = <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${entry.value}"/><br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-retail-price"/>
		</th>
		<td>
			<c:forEach items="${deviationRetailPriceOfComponentsByTechnologyAndCurrency}" var="entry">
				${entry.key} = <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${entry.value}"/><br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-retail-price"/>
		</th>
		<td>
			<c:forEach items="${minimumRetailPriceOfComponentsByTechnologyAndCurrency}" var="entry">
				${entry.key} = <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${entry.value}"/><br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-retail-price"/>
		</th>
		<td>
			<c:forEach items="${maximumRetailPriceOfComponentsByTechnologyAndCurrency}" var="entry">
				${entry.key} = <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${entry.value}"/><br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.number.patronages"/>
		</th>
		<td>
			<c:forEach items="${totalNumberOfPatronagesByStatus}" var="entry">
	    		${entry.key} = ${entry.value}<br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-retail-price"/>
		</th>
		<td>
			<c:forEach items="${averagePatronagesBudgetByStats}" var="entry">
				${entry.key} = <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${entry.value}"/><br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-retail-price"/>
		</th>
		<td>
			<c:forEach items="${deviationPatronagesBudgetByStats}" var="entry">
				${entry.key} = <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${entry.value}"/><br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-retail-price"/>
		</th>
		<td>
			<c:forEach items="${minimumPatronagesBudgetByStats}" var="entry">
				${entry.key} = <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${entry.value}"/><br>
			</c:forEach>
		</td>
	</tr><tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-retail-price"/>
		</th>
		<td>
			<c:forEach items="${maximumPatronagesBudgetByStats}" var="entry">
				${entry.key} = <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${entry.value}"/><br>
			</c:forEach>
		</td>
	</tr>

</table>




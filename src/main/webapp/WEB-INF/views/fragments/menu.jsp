<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.roles.Inventor,acme.roles.Patron"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.any.list-tools" action="/any/item/list-tool"/>
			<acme:menu-suboption code="master.menu.any.list-components" action="/any/item/list-component"/>
			<acme:menu-suboption code="master.menu.any.list-userAccounts" action="/any/user-account/list"/>
			<acme:menu-suboption code="master.menu.any.list-chirps" action="/any/chirp/list-recent"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-mario" action="https://www.youtube.com/watch?v=VKMw2it8dQY"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-Javi" action="https://www.youtube.com"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-manu" action="https://www.reddit.com/r/Eldenring/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-Jose" action="https://www.estadiodeportivo.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-andrea" action="https://www.instagram.com/p/CaZ_40ds93W/?utm_medium=copy_link"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-ezequiel" action="https://www.twitch.tv/"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.system-configuration" action="/administrator/system-configuration/show"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/administrator-dashboard/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
			
		</acme:menu-option>
		

		<acme:menu-option code="master.menu.authenticated" access="hasRole('Authenticated')">
			<acme:menu-suboption code="master.menu.authenticated.list-currencies" action="/authenticated/system-configuration/show"/>
			<acme:menu-suboption code="master.menu.authenticated.list-announcements" action="/authenticated/announcement/list-recent"/>
			<acme:menu-suboption code="master.menu.any.list-tools" action="/any/item/list-tool"/>
			<acme:menu-suboption code="master.menu.any.list-components" action="/any/item/list-component"/>
			<acme:menu-suboption code="master.menu.any.list-userAccounts" action="/any/user-account/list"/>
			<acme:menu-suboption code="master.menu.any.list-chirps" action="/any/chirp/list-recent"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.inventor" access="hasRole('Inventor')">
			<acme:menu-suboption code="master.menu.inventor.list-patronages" action="/inventor/patronage/list"/>
			<acme:menu-suboption code="master.menu.inventor.list-patronage-reports" action="/inventor/patronage-report/list"/>
			<acme:menu-suboption code="master.menu.inventor.list-toolkits" action="/inventor/toolkit/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.patron.dashboard" action="/patron/patron-dashboard/show"/>
			<acme:menu-suboption code="master.menu.patron.list-patronage-reports" action="/patron/patronage-report/list"/>
		</acme:menu-option>
		
		
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>


/*
 * AuthenticatedAnnouncementRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.system_configuration;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.system_configurations.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSystemConfigurationRepository extends AbstractRepository {

	@Query("select sc from SystemConfiguration sc")
	Collection<SystemConfiguration> findAllConfigurations();

	@Query("select sc from SystemConfiguration sc where sc.id = :id ")
	SystemConfiguration findSystemConfigurationById(int id);
	
}

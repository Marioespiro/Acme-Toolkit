package acme.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.system_configurations.SystemConfiguration;
import spam_filter.SpamFilter;

@Service
public class SpamFilterService {

	@Autowired
	protected static SpamFilterRepository repository;
	
	public static void initFilter() {
		
		final List<SystemConfiguration> allConfigurations = new ArrayList<>(SpamFilterService.repository.findAllConfigurations());
		final SystemConfiguration sysCon = allConfigurations.get(0);
		
		SpamFilter.initFilter(sysCon.getStrongSpamTerms(), sysCon.getWeakSpamTerms(), sysCon.getStrongSpamThreshold(), sysCon.getWeakSpamThreshold());
	}
}

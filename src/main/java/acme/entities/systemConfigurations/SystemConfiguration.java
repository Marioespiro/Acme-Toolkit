package acme.entities.systemConfigurations;


import javax.persistence.Entity;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity {
	
	// Serialisation identifier

	private static final long	serialVersionUID	= 1L;

	// Attributes
	
	protected String systemCurrency;
	
	protected String acceptedCurrencies;
	
	protected String strongSpamTerms;
	
	protected Integer strongSpamThreshold;
	
	protected String weakSpamTerms;
	
	protected Integer weakSpamThreshold;


}

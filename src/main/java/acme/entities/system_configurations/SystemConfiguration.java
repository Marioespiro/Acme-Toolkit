package acme.entities.system_configurations;


import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
	
	@NotBlank
	protected String systemCurrency;
	
	@NotBlank
	protected String acceptedCurrencies;
	
	@NotBlank
	protected String strongSpamTerms;
	
	@Min(0)
	@Max(100)
	protected double strongSpamThreshold;
	
	@NotBlank
	protected String weakSpamTerms;
	
	@Min(0)
	@Max(100)
	protected double weakSpamThreshold;


}

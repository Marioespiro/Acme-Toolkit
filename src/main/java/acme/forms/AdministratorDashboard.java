
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {

	// Serialisation Identifier 
	private static final long	serialVersionUID	= 1L;

	// Attributes
	
	Integer						totalItems;

	Double						averageRetailPriceItems;
	Double						deviationRetailPriceItems;
	Integer						minRetailPriceItems;
	Integer						maxRetailPriceItems;

	Double						averageBudgetPatronages;
	Double						deviationBudgetPatronages;
	Integer						minBudgetPatronages;
	Integer						maxBudgetPatronages;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	



}

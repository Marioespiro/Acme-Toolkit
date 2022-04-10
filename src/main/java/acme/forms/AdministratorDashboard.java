
package acme.forms;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.patronages.PatronageStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {

	// Serialisation Identifier 
	private static final long	serialVersionUID	= 1L;

	// Attributes
	
	Integer								totalNumberOfComponents;
	Map<Pair<String, String>, Double>	averageRetailPriceOfComponentsByTechnologyAndCurrency;
	Map<Pair<String, String>, Double>	deviationRetailPriceOfComponentsByTechnologyAndCurrency;
	Map<Pair<String, String>, Double>	minimumRetailPriceOfComponentsByTechnologyAndCurrency;
	Map<Pair<String, String>, Double>	maximumRetailPriceOfComponentsByTechnologyAndCurrency;
	Integer								totalNumberOfTools;
	Map<String, Double>					averageRetailPriceOfToolsByCurrency;
	Map<String, Double>					deviationRetailPriceOfToolsByCurrency;
	Map<String, Double>					minimumRetailPriceOfToolsByCurrency;
	Map<String, Double>					maximumRetailPriceOfToolsByCurrency;
	EnumMap<PatronageStatus, Integer>		totalNumberOfPatronagesByStatus;
	EnumMap<PatronageStatus, Double>		averagePatronagesBudgetByStats;
	EnumMap<PatronageStatus, Double>		deviationPatronagesBudgetByStats;
	EnumMap<PatronageStatus, Double>		minimumPatronagesBudgetByStats;
	EnumMap<PatronageStatus, Double>		maximumPatronagesBudgetByStats;
	
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	



}

package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.patronages.PatronageStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	private Map<PatronageStatus, Integer> 					totalNumberOfProposedPatronagesByStatus;
	private transient Map<Pair<String, PatronageStatus>, Double>		averageBudgetOfPatronagesStatusByCurrency;
	private transient Map<Pair<String, PatronageStatus>, Double>		deviationBudgetOfPatronagesStatusByCurrency;
	private transient Map<Pair<String, PatronageStatus>, Double>		maximumBudgetOfPatronagesStatusByCurrency;
	private transient Map<Pair<String, PatronageStatus>, Double>		minimumBudgetOfPatronagesStatusByCurrency;
	
	
	

}

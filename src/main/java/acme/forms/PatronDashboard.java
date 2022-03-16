package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	Integer 					totalNumberOfProposedPatronages;
	Integer						totalNumberOfAcceptedPatronages;
	Integer						totalNumberOfDeniedPatronages;
	Double						averageBugdetProposedPatronage;
	Double						deviationBugdetProposedPatronage;
	Double						minimumBugdetProposedPatronage;
	Double						maximumBugdetProposedPatronage;
	Double						averageBugdetAcceptedPatronage;
	Double						deviationBugdetAcceptedPatronage;
	Double						minimumBugdetAcceptedPatronage;
	Double						maximumBugdetAcceptedPatronage;
	Double						averageBugdetDeniedPatronage;
	Double						deviationBugdetDeniedPatronage;
	Double						minimumBugdetDeniedPatronage;
	Double						maximumBugdetDeniedPatronage;


}

package acme.testing.patron.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageReportListAllTest extends TestHarness { 
	
	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------

		@ParameterizedTest 
		@CsvFileSource(resources = "/patron/patronageReport/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final int recordIndex,final String automaticSequence, final String creationMoment, final String memorandum, final String link) {
			super.signIn("patron1", "patron1");
			super.clickOnMenu("Patron", "List patronage reports");
			super.checkListingExists();
			super.sortListing(0, "asc");

			
			super.checkColumnHasValue(recordIndex, 0, automaticSequence);
			super.checkColumnHasValue(recordIndex, 1, creationMoment);
			super.checkColumnHasValue(recordIndex, 2, memorandum);
			super.checkColumnHasValue(recordIndex, 3, link);
			

			super.signOut();
		}

		// Ancillary methods ------------------------------------------------------

}

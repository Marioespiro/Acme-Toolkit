package acme.testing.patron.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageReportShowTest extends TestHarness {
		@ParameterizedTest
		@CsvFileSource(resources = "/patron/patronageReport/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)	// 	 creationDate;critical; title
		public void positiveTest(final int recordIndex,final String automaticSequence, final String creationMoment, final String memorandum, final String link) {
			
			super.signIn("patron1", "patron1");
			super.clickOnMenu("Patron", "List patronage reports");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
			super.checkColumnHasValue(recordIndex, 0, automaticSequence);
			super.checkColumnHasValue(recordIndex, 1, creationMoment);
			super.checkColumnHasValue(recordIndex, 2, memorandum);
			super.checkColumnHasValue(recordIndex, 3, link);
			super.clickOnListingRecord(recordIndex);

			super.checkFormExists();
			super.checkInputBoxHasValue("automaticSequenceNumber", automaticSequence);
			super.checkInputBoxHasValue("creationMoment", creationMoment);
			super.checkInputBoxHasValue("memorandum", memorandum);
			super.checkInputBoxHasValue("link", link);
			
			super.signOut();
		}

}

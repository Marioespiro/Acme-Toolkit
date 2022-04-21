package acme.testing.authenticated.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedAnnouncementShowTest extends TestHarness {
		@ParameterizedTest
		@CsvFileSource(resources = "/authenticated/announcement/show.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)	// 	 creationDate;critical; title
		public void positiveTest(final int recordIndex, final String creationDate, final String title, final String critical, final String body, final String url) {
			
			super.signIn("patron1", "patron1");
			
			super.clickOnMenu("Authenticated", "List announcements");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
			super.checkColumnHasValue(recordIndex, 0, creationDate);
			super.checkColumnHasValue(recordIndex, 1, critical);
			super.checkColumnHasValue(recordIndex, 2, title);
			super.clickOnListingRecord(recordIndex);

			super.checkFormExists();
			super.checkInputBoxHasValue("creationDate", creationDate);
			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("critical", critical);
			super.checkInputBoxHasValue("body", body);
			super.checkInputBoxHasValue("url", url);
			
			super.signOut();
		}

}

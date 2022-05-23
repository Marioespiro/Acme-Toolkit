package acme.testing.administrator.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorAnnouncementCreateTest extends TestHarness {

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String body, final String url, final String critical) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Create announcement");
		
		super.checkFormExists();
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("url", url);
		super.fillInputBoxIn("critical", critical);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create announcement");
		
		super.checkNotErrorsExist();
		
		super.navigateHome();
		super.clickOnMenu("Authenticated", "List announcements");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 1, critical);
		super.checkColumnHasValue(recordIndex, 2, title);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String title, final String body, final String url, final String critical) {

		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Create announcement");
		
		super.checkFormExists();

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("url", url);
		super.fillInputBoxIn("critical", critical);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create announcement");

		super.checkErrorsExist();

		super.signOut();
	}
	
}

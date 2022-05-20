package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportCreateTest extends TestHarness{
	
		// Lifecycle management --------------------------------------
	
		// Test cases ------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronageReport/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest (final int recordIndex, final String creationMoment, final String memorandum, final String link, final String serialNumber ) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List patronages");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.clickOnButton("Create patronage report");
		super.checkFormExists();
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create");
		
		super.checkNotErrorsExist();
		
		super.navigateHome();
		super.clickOnMenu("Inventor", "List patronage reports");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, serialNumber);
		super.checkColumnHasValue(recordIndex, 2, memorandum);
		super.checkColumnHasValue(recordIndex, 3, link);
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronageReport/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String creationMoment, final String memorandum, final String link, final String serialNumber ) {

		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List patronages");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.clickOnButton("Create patronage report");
		super.checkFormExists();

		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronageReport/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest2(final int recordIndex, final String creationMoment, final String memorandum, final String link, final String serialNumber ) {

		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List patronages");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.clickOnButton("Create patronage report");
		super.checkFormExists();

		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}
}

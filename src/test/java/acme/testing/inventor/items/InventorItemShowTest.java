package acme.testing.inventor.items;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemShowTest extends TestHarness { 
	
	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------

	
	@ParameterizedTest 
	@CsvFileSource(resources = "/inventor/items/show-components.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestComponent(final int recordIndex, final String name, final String code, final String technology, final String description, final String price, final String optionalLink) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List components");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);

		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", price);
		super.checkInputBoxHasValue("link", optionalLink);
		
		super.signOut();
	}

	@ParameterizedTest 
	@CsvFileSource(resources = "/inventor/items/show-tools.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestTool(final int recordIndex, final String name, final String code, final String technology, final String description, final String price, final String optionalLink) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List tools");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);

		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", price);
		super.checkInputBoxHasValue("link", optionalLink);
		
		super.signOut();

	}



		// Ancillary methods ------------------------------------------------------

}

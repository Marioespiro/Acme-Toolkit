package acme.testing.inventor.items;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemListTest extends TestHarness { 
	
	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------

	@ParameterizedTest 
	@CsvFileSource(resources = "/inventor/items/list-components.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestComponent(final int recordIndex, final String name, final String code, final String technology, final String price) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List components");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, technology);
		super.checkColumnHasValue(recordIndex, 3, price);

		super.signOut();
	}
	@ParameterizedTest 
	@CsvFileSource(resources = "/inventor/items/list-tools.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestTool(final int recordIndex, final String name, final String code, final String technology, final String price) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List tools");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, technology);
		super.checkColumnHasValue(recordIndex, 3, price);

		super.signOut();
	}
		// Ancillary methods ------------------------------------------------------

}

package acme.testing.inventor.items;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class InventorItemDeleteTest extends TestHarness { 
	
	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------

	@Test
	@Order(10)
	public void positiveTestComponent() {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List tools");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(1, 0, "Item-03");
		super.clickOnListingRecord(1);

		super.checkFormExists();
		super.clickOnSubmit("Delete item");
		super.clickOnMenu("Inventor", "List tools");
		super.checkColumnHasValue(1, 0, "Item-05");
		
		super.signOut();
	}

		// Ancillary methods ------------------------------------------------------

}

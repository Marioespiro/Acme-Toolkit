package acme.testing.inventor.items;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class InventorItemPublishTest extends TestHarness { 
	
	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------

	@Test
	@Order(10)
	public void positiveTestComponent() {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List components");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(1);

		super.checkFormExists();
		super.clickOnSubmit("Publish item");
		super.clickOnMenu("Inventor", "List components");
		super.clickOnListingRecord(1);
		super.checkNotSubmitExists("Publish item");
		
		super.signOut();
	}

		// Ancillary methods ------------------------------------------------------

}

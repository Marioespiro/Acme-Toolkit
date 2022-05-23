package acme.testing.inventor.component;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorComponentUpdateTest extends TestHarness {
	
	// Lifecycle management --------------------------------------
	
	// Test cases ------------------------------------------------
			
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/component/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest (final int recordIndex, final String name, final String code, final String technology, final String price, final String description, final String url, final String isPublished) {
				
			super.signIn("inventor1", "inventor1");
			super.clickOnMenu("Inventor", "List components");
			super.checkListingExists();
			super.clickOnListingRecord(1);
			//super.clickOnButton("Create component");
			super.checkFormExists();
			super.fillInputBoxIn("name", name);
			super.fillInputBoxIn("code", code);
			super.fillInputBoxIn("technology", technology);
			super.fillInputBoxIn("retailPrice", price);
			super.fillInputBoxIn("description", description);
			super.fillInputBoxIn("link", url);
			super.clickOnSubmit("Update item");
				
			super.checkNotErrorsExist();
				
			super.navigateHome();
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
		@CsvFileSource(resources = "/inventor/component/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void negativeTest (final int recordIndex, final String name, final String code, final String technology, final String price, final String description, final String url, final String isPublished) {
				
			super.signIn("inventor1", "inventor1");
			super.clickOnMenu("Inventor", "List components");
			super.checkListingExists();
			super.clickOnListingRecord(1);
			super.checkFormExists();
			super.fillInputBoxIn("name", name);
			super.fillInputBoxIn("code", code);
			super.fillInputBoxIn("technology", technology);
			super.fillInputBoxIn("retailPrice", price);
			super.fillInputBoxIn("description", description);
			super.fillInputBoxIn("link", url);
			super.clickOnSubmit("Update item");
				
			super.checkErrorsExist();
			
			super.signOut();
		}
			
}
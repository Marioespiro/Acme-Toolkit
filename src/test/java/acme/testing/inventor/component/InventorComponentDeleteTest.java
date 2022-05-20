package acme.testing.inventor.component;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorComponentDeleteTest extends TestHarness {
	
	// Lifecycle management --------------------------------------
	
	// Test cases ------------------------------------------------
				
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/component/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest (final int recordIndex, final String name, final String code, final String technology, final String price, final String description, final String url, final String isPublished) {
					
			super.signIn("inventor1", "inventor1");
			super.clickOnMenu("Inventor", "List components");
			super.checkListingExists();
			super.clickOnListingRecord(1);

			super.checkFormExists();

			super.clickOnSubmit("Delete item");
					
			super.checkNotErrorsExist();
				
			super.signOut();
		}
				
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/component/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void negativeTest(final int recordIndex, final String name, final String code, final String technology, final String price, final String description, final String url, final String isPublished) {
		
			super.signIn("inventor1", "inventor1");
			super.clickOnMenu("Inventor", "List components");
			super.checkListingExists();
			super.clickOnListingRecord(0);
			super.checkFormExists();
		
			super.checkNotButtonExists("Delete item");
		
			super.signOut();
		}
				

}

package acme.testing.inventor.items;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemCreateTest extends TestHarness{
	
		// Lifecycle management --------------------------------------
	
		// Test cases ------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/items/create-tool-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestTool(final int recordIndex, final String name, final String code, final String technology, final String description, final String price, final String optionalLink) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List tools");
		super.checkListingExists();
		super.clickOnButton("Create tool");
		super.checkFormExists();
		
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", price);
		super.fillInputBoxIn("link", optionalLink);
		super.clickOnSubmit("Create tool");
		
		super.checkNotErrorsExist();
		
		super.navigateHome();
		super.clickOnMenu("Inventor", "List tools");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, technology);
		super.checkColumnHasValue(recordIndex, 3, price);
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/items/create-tool-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String price, final String optionalLink) {

		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List tools");
		super.checkListingExists();
		super.clickOnButton("Create tool");
		super.checkFormExists();
		
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", price);
		super.fillInputBoxIn("link", optionalLink);
		super.clickOnSubmit("Create tool");

		super.checkErrorsExist();

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/items/create-component-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestComponent(final int recordIndex, final String name, final String code, final String technology, final String description, final String price, final String optionalLink) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List components");
		super.checkListingExists();
		super.clickOnButton("Create component");
		super.checkFormExists();
		
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", price);
		super.fillInputBoxIn("link", optionalLink);
		super.clickOnSubmit("Create component");
		
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
	@CsvFileSource(resources = "/inventor/items/create-component-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeComponent(final int recordIndex, final String name, final String code, final String technology, final String description, final String price, final String optionalLink) {

		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List components");
		super.checkListingExists();
		super.clickOnButton("Create component");
		super.checkFormExists();
		
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", price);
		super.fillInputBoxIn("link", optionalLink);
		super.clickOnSubmit("Create component");

		super.checkErrorsExist();

		super.signOut();
	}
	
}

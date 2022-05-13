package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyChirpCreateTest extends TestHarness {

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String author, final String body, final String email) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Authenticated", "List chirps");
		super.checkListingExists();
		
		super.clickOnButton("Create chirp");
		super.checkFormExists();
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("email", email);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create chirp");
		
		super.checkNotErrorsExist();
		
		super.navigateHome();
		super.clickOnMenu("Authenticated", "List chirps");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String title, final String author, final String body, final String email) {

		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Authenticated", "List chirps");
		super.checkListingExists();
		
		super.clickOnButton("Create chirp");
		super.checkFormExists();

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("email", email);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create chirp");

		super.checkErrorsExist();

		super.signOut();
	}
	
}

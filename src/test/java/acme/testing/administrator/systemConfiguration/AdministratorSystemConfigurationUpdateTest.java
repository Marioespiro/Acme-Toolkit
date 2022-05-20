package acme.testing.administrator.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationUpdateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfiguration/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String systemCurrency, final String acceptedCurrencies, final String strongSpamTerms,
		final String strongSpamThreshold, final String weakSpamTerms, final String weakSpamThreshold) {
		
		super.signIn("Administrator", "administrator");
		
		super.clickOnMenu("Administrator", "System configuration");
		super.checkFormExists();
		
		super.fillInputBoxIn("systemCurrency", systemCurrency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("strongSpamTerms", strongSpamTerms);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold);
		super.fillInputBoxIn("weakSpamTerms", weakSpamTerms);
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold);
		super.clickOnSubmit("Update system configuration");
		
		super.clickOnMenu("Administrator", "System configuration");
		super.checkFormExists();
		
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("strongSpamTerms", strongSpamTerms);
		super.checkInputBoxHasValue("strongSpamThreshold", strongSpamThreshold);
		super.checkInputBoxHasValue("weakSpamTerms", weakSpamTerms);
		super.checkInputBoxHasValue("weakSpamThreshold", weakSpamThreshold);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfiguration/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String systemCurrency, final String acceptedCurrencies, final String strongSpamTerms,
		final String strongSpamThreshold, final String weakSpamTerms, final String weakSpamThreshold) {
		
		super.signIn("Administrator", "administrator");
		
		super.clickOnMenu("Administrator", "System configuration");
		super.checkFormExists();
		
		super.fillInputBoxIn("systemCurrency", systemCurrency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("strongSpamTerms", strongSpamTerms);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold);
		super.fillInputBoxIn("weakSpamTerms", weakSpamTerms);
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold);
		super.clickOnSubmit("Update system configuration");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Administrator");
		super.navigate("/administrator/system-configuration/show");
		super.checkPanicExists();
		
		super.signIn("inventor1", "inventor1");
		super.checkNotLinkExists("Administrator");
		super.navigate("/administrator/system-configuration/show");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("patron1", "patron1");
		super.checkNotLinkExists("Administrator");
		super.navigate("/administrator/system-configuration/show");
		super.checkPanicExists();
		super.signOut();
	}

}

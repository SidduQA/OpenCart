package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.baseClass;

public class TC001_AccountRegistrationTest extends baseClass {
	AccountRegistrationPage acr;
	HomePage hp;

	@Test(groups = { "sanity", "master" })
	public void verify_ac_registration() {
		hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();

		acr = new AccountRegistrationPage(driver);

		acr.setFirstName(randomString().toUpperCase());
		acr.setLastName(randomString().toUpperCase());
		acr.setEmail(randomString() + "@gmail.com"); // randomly generated mail
		acr.setTelephone(randomNumber());

		String password = randomAlphaNumeric();

		acr.setPassword(password);
		acr.setCnfPassword(password);

		acr.clickPrivacy();
		acr.contniue();

		String cnfmsge = acr.getConfirmationMsge();
		Assert.assertEquals(cnfmsge, "Your Account Has Been Created!");
	}

	@Test(groups = { "sanity", "master" })
	public void nullFname() {
		hp = new HomePage(driver);

		hp.clickMyAccount();
		hp.clickRegister();

		acr = new AccountRegistrationPage(driver);
		acr.setLastName(randomString().toUpperCase());
		acr.setEmail(randomString() + "@gmail.com"); // randomly generated mail
		acr.setTelephone(randomNumber());

		String password = randomAlphaNumeric();

		acr.setPassword(password);
		acr.setCnfPassword(password);

		acr.clickPrivacy();
		acr.contniue();

//		String errormsge = acr.getErrorMsgeField("input-firstname");
//		Assert.assertEquals(errormsge, "First Name must be between 1 and 32 characters!");

		Assert.assertTrue(false);

	}

}

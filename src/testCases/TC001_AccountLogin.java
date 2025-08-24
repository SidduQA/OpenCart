package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.baseClass;

public class TC001_AccountLogin extends baseClass {
	HomePage hp;
	LoginPage lp;

	@Test(groups = { "regression", "master" }, priority = 0)
	public void verifyLogin() {
		hp = new HomePage(driver);

		hp.clickMyAccount();
		hp.clickLogin();

		lp = new LoginPage(driver);

		lp.userEmail(p.getProperty("email"));
		lp.userPass(p.getProperty("password"));
		lp.login();

		String cnflgn = lp.loginMsge();
		Assert.assertEquals(cnflgn, "My Account");
	}

}

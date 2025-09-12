package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.baseClass;

public class TC001_AccountLogin extends baseClass {
	HomePage hp;
	LoginPage lp;
	MyAccountPage mp;

<<<<<<< HEAD
	@Test(groups = { "regression", "master" }, priority = 0)
	public void verifyLogin() throws InterruptedException {
=======
	@Test(groups = { "regression", "master" })
	public void verifyLogin() {
>>>>>>> 83dfa9ebae32d36463a00e3c115d10ead8cb1078
		hp = new HomePage(driver);

		hp.clickMyAccount();
		hp.clickLogin();

		lp = new LoginPage(driver);

		lp.userEmail(p.getProperty("email"));
		lp.userPass(p.getProperty("password"));
		lp.login();

		mp = new MyAccountPage(driver);

		Assert.assertTrue(mp.TargetPage());

	}

}

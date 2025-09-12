package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.baseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends baseClass {
	HomePage hp;
	LoginPage lp;
	MyAccountPage mp;

	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException {
		try {

			hp = new HomePage(driver);

			hp.clickMyAccount();
			hp.clickLogin();

			lp = new LoginPage(driver);

			lp.userEmail(email);
			lp.userPass(pwd);
			lp.login();

			mp = new MyAccountPage(driver);
			boolean isMyaccExist = mp.TargetPage();
			Assert.assertTrue(isMyaccExist);

			/*
			 * Data is valid - login success - test pass - logout login failed - test fail
			 * 
			 * Data is invalid - login success - test fail - logout login failed - test pass
			 */

			if (exp.equalsIgnoreCase("Valid")) {
				if (isMyaccExist == true) {
					mp.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}

			if (exp.equalsIgnoreCase("Invalid")) {
				if (isMyaccExist == true) {
					mp.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}

		} catch (Exception e) {
			Assert.fail();
		}
		Thread.sleep(3000);

	}
}

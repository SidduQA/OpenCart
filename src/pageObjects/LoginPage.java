package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "input-email")
	WebElement email;

	@FindBy(id = "input-password")
	WebElement password;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement submit;

	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	WebElement cnfMsge;

	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logout;

	public void userEmail(String mail) {
		email.sendKeys(mail);
	}

	public void userPass(String pass) {
		password.sendKeys(pass);
	}

	public void login() {
		submit.click();
	}

}

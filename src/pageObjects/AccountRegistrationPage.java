package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement firstName;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement lastName;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement email;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement telephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement password;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement confirmPassword;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPrivacyPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnSubmit;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfiramtion;

	public void setFirstName(String fname) {
		firstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		lastName.sendKeys(lname);
	}

	public void setTelephone(String mobNo) {
		telephone.sendKeys(mobNo);
	}

	public void setEmail(String Uemail) {
		email.sendKeys(Uemail);
	}

	public void setPassword(String pass) {
		password.sendKeys(pass);
	}

	public void setCnfPassword(String pass) {
		confirmPassword.sendKeys(pass);
	}

	public void clickPrivacy() {
		((JavascriptExecutor) driver).executeScript("scroll,(0,120);");
		chkPrivacyPolicy.click();
	}

	public void contniue() {
		btnSubmit.click();
	}

	public String getConfirmationMsge() {
		try {
			return msgConfiramtion.getText();
		} catch (Exception e) {
			return (e.getMessage());
		}

	}

	public String getErrorMsgeField(String Field) {
		WebElement errorMsge = driver
				.findElement(By.xpath("//input[@id='" + Field + "']/following-sibling::div[@class='text-danger']"));
		return errorMsge.getText();

	}

}

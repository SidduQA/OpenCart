package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class baseClass {
	public static WebDriver driver;
	public Properties p;

	@BeforeClass(groups = { "sanity", "regression", "master" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String browser) throws IOException {
//	    Configuring properties file

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\hp\\eclipse-workspace\\OpencartV1.2.1\\src\\config.properties");
		p = new Properties();
		p.load(fis);

		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Please valid browser name...");
			return; // return will exit from remaining execution if br name is invalid
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(p.getProperty("appURL"));
	}

	@AfterClass(groups = { "sanity", "regression", "master" })
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String getRandomString = RandomStringUtils.randomAlphabetic(5);
		return getRandomString;
	}

	public String randomNumber() {
		String getRandomNumber = RandomStringUtils.randomNumeric(10);
		return getRandomNumber;
	}

	public String randomAlphaNumeric() {
		String getRandomString = RandomStringUtils.randomAlphabetic(5);
		String getRandomNumber = RandomStringUtils.randomNumeric(5);
		return (getRandomString + "@" + getRandomNumber);
	}

//	Screenshot method

	public String captureScreen(String tName) throws InterruptedException {

		Thread.sleep(3000);
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

		File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "/screenshot/" + tName + "_" + timeStamp + ".png";

		File targetFile = new File(targetFilePath);
		srcFile.renameTo(targetFile);

		return targetFilePath;

	}

}

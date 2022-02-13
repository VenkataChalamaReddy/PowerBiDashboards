package automation.POCproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;

public class base {
	public WebDriver driver;

	public WebDriver initializeDriver() throws IOException {
		// this invokes the respective browser for all tests
		Properties prop = new Properties(); // properties file is responsible to pull the values from data.properties
											// class but it has no knowledge of the file
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\360131\\eclipse-workspace\\POCproject\\src\\main\\java\\automation\\POCproject\\data.properties");
		prop.load(fis); // this load will take fis as an arguement whic has knowledge of the properties
						// file
		String browserName = prop.getProperty("browser"); // this will go and pull the browser value from
															// data.properties class

		if (browserName.equals("chrome")) {
			// write a code to execute in chrome driver
			System.setProperty("webdriver.chrome.driver",
					"C:\\Automation\\dependencies-jars\\chromedriver_win32 (1)\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName == "firefox") {
			// write a code to execute in firefox(geckodriver)

		} else if (browserName == "IE") {
			// write a code to execute in IE

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		return driver;

	}

	public void getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		Reporter.log(destinationFile);

	}

}

package automation.POCproject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class HighlightOfElement extends Utilities{
	
	@Test(priority = 1)

	public void highlight() throws IOException, InterruptedException {
		// Driver Initialization and navigating to url
		driver = initializeDriver();
		driver.get("https://app.powerbi.com/home?noSignUpCheck=1");

		// Login to Power BI account
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("360131@narayanahealth.org");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//input[@name='passwd']")).sendKeys("Password123");
		driver.findElement(By.xpath("//input[@value='Sign in']")).click();
		driver.findElement(By.xpath("//input[@value='Yes']")).click();
		if (driver.findElement(By.xpath("//button[@class='paneExpanderButton expanderBtn']"))
				.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
			driver.findElement(By.xpath("//button[@class='paneExpanderButton expanderBtn']")).click();
		}
		driver.findElement(By.xpath("//span[text()='Apps']")).click();

		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[contains(text(),'C3 Material Consumption')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[text()='Implant Consumption']")).click();
		Thread.sleep(5000);
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe)[2]")));
		driver.findElement(By.xpath("//span[contains(text(),'Material Revenue')]")).isSelected();
		//boolean CQ=driver.findElement(By.xpath("//span[contains(text(),'Material Revenue')]")).isSelected();
		//if(driver.findElement(By.xpath("//span[contains(text(),'Consumed Quantity')]")).isSelected()) {
		//	System.out.println("Consumed Quantity is diplayed");
		//}
		
		
		
	}


}

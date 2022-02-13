package automation.POCproject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class PowerBIBargraphsMousehover extends Utilities {
	
	@Test

	public void barGraphsAction() throws IOException, InterruptedException {
		// Driver Initialization and navigating to url
		driver = initializeDriver();
		driver.get("https://app.powerbi.com/home?noSignUpCheck=1");

		// Login to Power BI account
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("360131@narayanahealth.org");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//input[@name='passwd']")).sendKeys("Password123");
		driver.findElement(By.xpath("//input[@value='Sign in']")).click();
		driver.findElement(By.xpath("//input[@value='Yes']")).click();

		// Clicking on Left Bar Items after login to user account
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
		clickOnTabItemsWithActions(
				"(//transform[@class='selectThrough'])[3]/descendant::div[contains(@class,'ui-role-button-text')]",
				"HOSPITAL");
		
		clickOnTabItemsWithActions(
				"(//transform[@class='selectThrough'])[9]/descendant::div[contains(@class,'ui-role-button-text')]",
				"SUMMARY");
		Thread.sleep(3000);
		List<WebElement> hospitalNames=driver.findElements(By.xpath("//*[local-name()='svg']//*[name()='g']//*[name()='g' and @class='y axis hideLinesOnAxis setFocusRing']//*[name()='g']"));
		System.out.println("the Count of hospitals:" + hospitalNames.size());
		int i=0;
		for(WebElement item:hospitalNames) {
			System.out.println(item.getText());
			i++;
		}
		
		
		String verticalXpath = "//*[local-name()='svg']//*[name()='g' and @class='axisGraphicsContext columnChart']//*[name()='rect']";
		List<WebElement> barsList = driver.findElements(By.xpath(verticalXpath));
		System.out.println("total bars:" + barsList.size());
	
		//Perform mouse hover action on the bar graphs
		Actions act = new Actions(driver);
		for (WebElement barList : barsList) {
			act.moveToElement(barList).perform();
			Thread.sleep(500);
			
			
		}
		
		
		//driver.quit();
	}
	
}



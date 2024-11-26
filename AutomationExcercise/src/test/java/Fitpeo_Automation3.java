import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Fitpeo_Automation3 {
	@Test
	public void Fitpeo_Automation() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		/** Navigate to the FitPeo Homepage */
		driver.get("https://www.fitpeo.com");
		Reporter.log("Navigate to the FitPeo Homepage");
		driver.manage().window().maximize();
		
		/** Navigate to Revenvue calculator */
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		driver.findElement(By.xpath("//div[text()='Revenue Calculator']")).click();
		Reporter.log("Navigate to Revenvue calculator");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		/** Scroll down the page until the revenue calculator slider is visible */
		WebElement slider = driver.findElement(By.xpath("//span[@class='MuiSlider-root MuiSlider-colorPrimary MuiSlider-sizeMedium css-16i48op']/../descendant::input[@aria-orientation='horizontal']"));
		WebElement textField1 = driver.findElement(By.xpath("//input[@aria-orientation='horizontal']/../../../descendant::input[@type='number']")); // Replace with the actual text field ID
        Reporter.log("Scroll down the page until the revenue calculator slider is visible");
		
        /** Adjust the slider*/		
		int sliderWidth = slider.getSize().getWidth();
		int minValue = Integer.parseInt(slider.getAttribute("min"));
		int maxValue = Integer.parseInt(slider.getAttribute("max"));
		int targetValue = 816;
        Reporter.log("Adjust the slider");
        
		// Calculate the pixel offset for the target value
		int offsetX = (int) (((double) (targetValue - minValue) / (maxValue - minValue)) * sliderWidth);

		// Drag the slider
		Actions actions = new Actions(driver);
		actions.clickAndHold(slider).moveByOffset(93,0).release().perform();
        Reporter.log("Drag the slider");

        /*Update the Text Field:*/		
        String updatedValue = textField1.getAttribute("value");
		Assert.assertEquals(updatedValue, String.valueOf(targetValue), "Text field did not update to the expected value of 820.");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", " ");
		String value = "560";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement textField = driver.findElement(By.cssSelector("[class*='MuiInputBase-input']"));
		textField.click();
		textField.sendKeys(Keys.CONTROL + "a");
		textField.sendKeys(Keys.BACK_SPACE);
		textField.sendKeys(value);
        Reporter.log("updated the text field");
        
		String position = driver.findElement(By.cssSelector("input[data-index]")).getAttribute("aria-valuenow");
		/** Validate Slider Value:*/		
		Assert.assertEquals(position, value);
		Reporter.log("slider value validated");
		
		/** Select CPT Codes:   ,inaddition to that Required CPT-99487! this is also added bcz it is manditory*/		
		
		WebElement c1 = driver.findElement(By.xpath("//p[text()='CPT-99091']/../descendant::input[@type='checkbox']"));
		c1.click();
		String c1Value = c1.getText();
		WebElement c2 = driver.findElement(By.xpath("//p[text()='CPT-99453']/../descendant::input[@type='checkbox']"));
		c2.click();
		String c2Value = c2.getText();
		WebElement c3 = driver.findElement(By.xpath("//p[text()='CPT-99454']/../descendant::input[@type='checkbox']"));
		c3.click();
		String c3Value = c3.getText();
		WebElement c4 = driver.findElement(By.xpath("//p[text()='CPT-99474']/../descendant::input[@type='checkbox']"));
		c4.click();
		String c4Value = c4.getText();
		WebElement c5 = driver.findElement(By.xpath("//p[text()='CPT-99487']/../descendant::input[@type='checkbox']"));
		c5.click();
		Reporter.log("selected the CPT codes");
		
		WebElement slider2 = driver.findElement(By.xpath("//span[contains(@class, 'MuiSlider-thumb')]"));
		/** Validate Total Recurring Reimbursement:*/		
		WebElement totalReimbursementHeader = driver.findElement(By.xpath("//p[text()='Total Recurring Reimbursement for all Patients Per Month']/../descendant::p[text()='$']")); // Adjust with
		Reporter.log("Validated Total Recurring Reimbursement");
	
		String actualReimbursement = totalReimbursementHeader.getText();
		String expectedReimbursement = "$127120";
		
		/** Verify that the header displaying Total Recurring Reimbursement for all* Patients Per Month: shows the value $127120*/		
		Assert.assertTrue(actualReimbursement.contains(expectedReimbursement),
                "Reimbursement mismatch. Expected: " + expectedReimbursement + ", Found: " + actualReimbursement);
        Reporter.log("verified the header displaying total recurring reimbursement for all patients per month");
        
        System.out.println("All validations passed successfully!");
		driver.close();
	}

}

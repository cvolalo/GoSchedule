package appium;
 
import io.appium.java_client.MobileBy;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class TestNG_testcases extends AppiumDriverBase{
	 
    //Test Annotation changes any java function to TestNG test case
    @Test (priority=1)
    public void Login_Complete() {
  	  driver.findElement(By.id("loginEmail")).sendKeys("mary.l.l.dela.torre");
  	  driver.findElement(By.id("loginPassword")).sendKeys("qwerty");
  	  driver.findElement(By.name("Login")).click();
  	  driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
	  System.out.println("--------Login Complete!--------");
  	  //driver.findElement(By.name("We"));
  	  takeScreenShot();
  	 }
    
    @Test (priority=2)
  //Click on File Leave button
  	  public void File_leave_Complete() {  	  	  
  	  driver.findElement(By.name("File Leave")).click();

  	  // Enter Date
  	  driver.findElement(By.id("leaveDate")).click();
  	  //driver.findElement(By.xpath("//android.widget.NumberPicker[@index='0' and @text='Nov']")).sendKeys("Nov");
  	  ((WebElement) driver.findElements(By.xpath("//android.widget.NumberPicker")).get(0)).sendKeys("Dec");
  	  ((WebElement) driver.findElements(By.xpath("//android.widget.NumberPicker")).get(1)).sendKeys("25");
  	  //driver.findElement(By.xpath("//android.widget.NumberPicker[@index='2' and @text='2016']")).sendKeys("2016");
  	  driver.findElement(By.name("Done")).click();
  	  //System.out.println("Date entered...");
  	  
  	  // Enter Leave Type
  	  driver.findElement(By.id("leaveType")).click();
  	  driver.findElement(By.name("Sick Leave")).click();
  	  //System.out.println("Leave Type entered...");

  	  // Enter Backup Resource
  	  driver.findElement(By.name("BACK UP RESOURCE")).sendKeys("test.only");
  	  //System.out.println("Backup Resource entered...");
  	  
  	  //Submit Button
  	  driver.findElement(By.name("Submit")).click();
  	  //System.out.println("Clicked Submit button...");
  	  System.out.println("--------Leave Added!--------");
  	  takeScreenShot();
  	 }
    
    public void takeScreenShot() {
    	  
		  // Set folder name to store screenshots.
		  destDir = "screenshots";
		  // Capture screenshot.
		  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  // Set date format to set It as screenshot file name.
		  dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		  // Create folder under project with name "screenshots" provided to destDir.
		  new File(destDir).mkdirs();
		  // Set file name using current date time.
		  String destFile = dateFormat.format(new Date()) + ".jpeg";

		  try {
		   // Copy paste file at destination folder location
		   FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		 }
}

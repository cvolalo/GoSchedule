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
import org.testng.annotations.Test;

public class TestNG_testcases extends AppiumDriverBase{
	 
    //Test Annotation changes any java function to TestNG test case
    
    //insert login and file_leave here
	
	@Test (priority=1)
    public void Login_Complete() {
  	  driver.findElement(By.id("loginEmail")).sendKeys("mary.l.l.dela.torre");
  	  driver.findElement(By.id("loginPassword")).sendKeys("qwerty");
  	  driver.findElement(By.name("Login")).click();
  	  driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
	  System.out.println("--------Login Complete!--------");
  	  takeScreenShot();
  	 }
    
    @Test (priority=2)
      //Click on File Leave button
  	  public void File_leave_Complete() {  	  	  
  	  driver.findElement(By.name("File Leave")).click();

  	  // Enter Date
  	  driver.findElement(By.id("leaveDate")).click();
  	  ((WebElement) driver.findElements(By.xpath("//android.widget.NumberPicker")).get(0)).sendKeys("Dec");
  	  ((WebElement) driver.findElements(By.xpath("//android.widget.NumberPicker")).get(1)).sendKeys("25");
  	  driver.findElement(By.name("Done")).click();
  	  
  	  // Enter Leave Type
  	  driver.findElement(By.id("leaveType")).click();
  	  driver.findElement(By.name("Sick Leave")).click();

  	  // Enter Backup Resource
  	  driver.findElement(By.name("BACK UP RESOURCE")).sendKeys("backup.resource");
  	  
  	  //Submit Button
  	  driver.findElement(By.name("Submit")).click();
  	  System.out.println("--------Leave Added!--------");
  	  takeScreenShot();
  	 }
    
	
    @Test (priority=3)
  //Click on More Options
	  public void Update_Leave_Complete() {
    	  
  	    //start of update_leave
    	driver.findElement(By.name("More options")).click();
    	driver.findElement(By.name("My Leaves")).click();
    	driver.findElement(By.id("ListMyLeave")).click();
    	driver.findElement(By.id("editleaveDate")).click();
    	((WebElement) driver.findElements(By.xpath("//android.widget.NumberPicker")).get(1)).sendKeys("29");
    	driver.findElement(By.name("Done")).click();
    	// Enter Leave Type
    	driver.findElement(By.id("editleaveType")).click();
    	driver.findElement(By.name("Emergency Leave")).click();
    	  
    	// Enter Backup Resource
      	driver.findElement(By.id("editleaveBackUp")).sendKeys("another.backup.resource");
      	  
      	driver.findElement(By.name("UPDATE")).click();
      	driver.findElement(By.id("myleavestext"));
      	driver.findElement(By.name("BACK")).click();
      	System.out.println("--------Updated Leave!--------");
    	takeScreenShot();
    }
    @Test (priority=4)
    //Click on More Options
  	  public void Approve_Leave_Complete() {
    	driver.findElement(By.name("More options")).click();
    	driver.findElement(By.name("Filed Leaves")).click();
    	driver.findElement(By.id("ListMyLeave")).click();
    	driver.findElement(By.name("Ok")).click();
    	driver.findElement(By.name("APPROVED")).click();
    	driver.findElement(By.id("ListMyLeave"));
    	driver.findElement(By.name("BACK")).click();
    }
	@Test (priority=5)
    //Click on More Options
  	  public void Check_Leave() {
    	driver.findElement(By.name("More options")).click();
    	driver.findElement(By.name("Filed Leaves")).click();
    	driver.findElement(By.id("ListMyLeave")).click();
    	driver.findElement(By.name("Ok")).click();
    	driver.findElement(By.name("APPROVED")).click();
    	driver.findElement(By.id("ListMyLeave"));
    	driver.findElement(By.name("BACK")).click();
    }
	@Test (priority=6)
    //Click on More Options
  	  public void Find_Leave() {
    	driver.findElement(By.name("More options")).click();
    	driver.findElement(By.name("Filed Leaves")).click();
    	driver.findElement(By.id("ListMyLeave")).click();
    	driver.findElement(By.name("Ok")).click();
    	driver.findElement(By.name("APPROVED")).click();
    	driver.findElement(By.id("ListMyLeave"));
    	driver.findElement(By.name("BACK")).click();
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
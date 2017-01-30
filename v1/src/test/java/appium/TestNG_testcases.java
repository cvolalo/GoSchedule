package appium;
 

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
	 
	@Test (priority=1)
    public void Login_Complete() {
  	  driver.findElement(By.id("loginEmail")).sendKeys("mary.l.l.dela.torre");
  	  driver.findElement(By.id("loginPassword")).sendKeys("qwerty");
  	  takeScreenShot();
  	  driver.findElement(By.name("Login")).click();
  	  driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
	  System.out.println("--------Login Complete!--------");
  	  takeScreenShot();
  	 }
    
    @Test (priority=2)
      //Click on File Leave button, 1 screenshot
  	  public void File_leave_Complete() {  	  	  
  	  driver.findElement(By.name("File Leave")).click();

  	  // Enter Date
  	  driver.findElement(By.id("leaveDate")).click();
  	  ((WebElement) driver.findElements(By.xpath("//android.widget.NumberPicker")).get(0)).sendKeys("Feb");
  	  ((WebElement) driver.findElements(By.xpath("//android.widget.NumberPicker")).get(1)).sendKeys("14");
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
  //Click on More Options, 1 screenshot
	  public void Update_Leave_Complete() {
    	  
  	    //start of update_leave
    	driver.findElement(By.name("More options")).click();
    	driver.findElement(By.name("My Leaves")).click();
    	driver.findElement(By.id("ListMyLeave")).click();
    	driver.findElement(By.id("editleaveDate")).click();
    	((WebElement) driver.findElements(By.xpath("//android.widget.NumberPicker")).get(1)).sendKeys("28");
    	driver.findElement(By.name("Done")).click();
    	// Enter Leave Type
    	driver.findElement(By.id("editleaveType")).click();
    	driver.findElement(By.name("Vacation Leave")).click();
    	  
    	// Enter Backup Resource
      	driver.findElement(By.id("editleaveBackUp")).sendKeys("another.backup.resource");
      	  
      	driver.findElement(By.name("UPDATE")).click();
      	driver.findElement(By.id("myleavestext"));
      	driver.findElement(By.name("BACK")).click();
      	System.out.println("--------Updated Leave!--------");
    	takeScreenShot();
    }
    @Test (priority=4)
    //Click on More Options, 1 screenshot
  	  public void Approve_Leave_Complete() {
    	//Start of Approve Leave
    	driver.findElement(By.name("More options")).click();
    	driver.findElement(By.name("Filed Leaves")).click();
    	driver.findElement(By.id("ListMyLeave")).click();
    	driver.findElement(By.name("Ok")).click();
    	driver.findElement(By.name("APPROVED")).click();
    	takeScreenShot();
    	driver.findElement(By.id("ListMyLeave"));
    	System.out.println("--------Approved Leave!--------");
    	driver.findElement(By.name("BACK")).click();
    }
	@Test (priority=5)
    //Click on More Options, 1 screenshot
  	  public void Check_Leave() {
		//Start of Check Leave
		driver.findElement(By.name("29")).click();
	  	takeScreenShot();
	  	driver.findElement(By.name("BACK")).click();
	  	System.out.println("--------Viewed Leave by Day View!--------");
    }
	@Test (priority=6)
  	  public void Search_Leave() {
		//Start of Search Leave, 1 screenshot
    	driver.findElement(By.name("Search")).click();
    	driver.findElement(By.name("Enter EID")).sendKeys("mary.l.l.dela.torre");
    	takeScreenShot();
    	driver.findElement(By.name("Search")).click();
    	takeScreenShot();
    	System.out.println("--------Leave Searched!--------");
    	driver.findElement(By.name("Cancel")).click();
    }
    public void takeScreenShot() {
    	  
		  // Set folder name to store screenshots.
		  destDir = "screenshots";
		  // Capture screenshot.
		  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  
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

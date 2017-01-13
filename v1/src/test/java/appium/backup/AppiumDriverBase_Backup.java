package appium;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppiumDriverBase {

   protected AndroidDriver driver;
   protected WebDriverWait wait;
   Dimension size;
   String destDir;
   DateFormat dateFormat;

   //before Test Annotation makes a java function to run every time before a TestNG test case
   @BeforeTest
   protected void createAppiumDriver() throws MalformedURLException, InterruptedException {

   //relative path to apk file
   //final File classpathRoot = new File(System.getProperty("user.dir"));
   //final File appDir = new File(classpathRoot, "src/test/resources/apps/");
   final File appDir = new File("C:/Users/mary.l.l.dela.torre/.jenkins/workspace/Build Application/build/outputs/apk/");
   //final File appDir = new File("C:/Users/mary.l.l.dela.torre/Desktop/repo/original_repo/goschedule/");
   final File app = new File(appDir, "Build Application-release.apk");
   //final File app = new File(appDir, "goschedule-release.apk");

   //setting up desired capability
   DesiredCapabilities caps = new DesiredCapabilities();
   caps.setCapability("browserName", "Android");
   caps.setCapability("platform", "ANDROID");
   caps.setCapability("platformVersion", "4.4.2");
   caps.setCapability("deviceName", "emulator-5554");
   caps.setCapability("app", app.getAbsolutePath());
   caps.setCapability("appPackage", "com.projectprototype");
   caps.setCapability("appActivity", "com.projectprototype.SplashActivity");

   //initializing driver object
   driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

   //initializing explicit wait object
   wait = new WebDriverWait(driver, 10);
   }
   
   //After Test Annotation makes a java function to run every time after a TestNG test case
   @AfterTest
   public void afterTest(){

   //quit the driver
   driver.quit();
   }

}
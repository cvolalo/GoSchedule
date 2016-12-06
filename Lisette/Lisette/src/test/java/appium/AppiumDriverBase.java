package appium;
 
import io.appium.java_client.android.AndroidDriver;
 
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
 
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
 
public class AppiumDriverBase {
 
    protected AndroidDriver driver;
    protected WebDriverWait wait;
 
    //before Test Annotation makes a java function to run every time before a TestNG test case
    @BeforeTest
    protected void createAppiumDriver() throws MalformedURLException, InterruptedException {
 
    //relative path to apk file
    final File classpathRoot = new File(System.getProperty("user.dir"));
    final File appDir = new File(classpathRoot, "src/test/resources/apps/");
    final File app = new File(appDir, "ApiDemos-debug.apk");
 
    //setting up desired capability
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("browserName", "Android");
    caps.setCapability("platform", "ANDROID");
    caps.setCapability("platformVersion", "6.0.1");
    //caps.setCapability("deviceName", "G7AZCY04C5092LC");
    caps.setCapability("deviceName", "emulator-5554");
    caps.setCapability("app", app.getAbsolutePath());
 
    //initializing driver object
    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
 
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
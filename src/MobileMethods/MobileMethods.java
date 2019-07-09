package MobileMethods;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class MobileMethods
{
    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Untitled";
    protected AndroidDriver<AndroidElement> driver = null;
    AndroidElement ContentElement;

    DesiredCapabilities dc = new DesiredCapabilities();


    @BeforeClass
    public void setUp() throws MalformedURLException
    {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "4a5f52be");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.android.apis");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        ContentElement=driver.findElementByXPath("//*[@text='Content']");
    }
    @Test
    public void Test01_Do() throws InterruptedException, ParserConfigurationException, SAXException, IOException
    {
        System.out.println("Height is :"+ContentElement.getRect().getHeight()+ "Width is: "+ContentElement.getRect().getWidth());
        System.out.println("X :"+ContentElement.getRect().getX()+ "Y: "+ContentElement.getRect().getY());
        System.out.println("App name:"+driver.currentActivity());
        System.out.println("Time/Date: "+driver.getDeviceTime());
        String Orientation=driver.getOrientation().value();
        if (Orientation=="portrait")
        {
            driver.rotate(ScreenOrientation.LANDSCAPE);
            Thread.sleep(5000);
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
        Thread.sleep(5000);
        driver.openNotifications(); //check why not work
        Thread.sleep(2000);
        TakeSS();
        driver.pressKeyCode(AndroidKeyCode.HOME);
        TakeSS();

    }
@Test
public void Test02_verifyAppInstalled()
{
    try
    {
        assertTrue(driver.isAppInstalled("com.experitest.ExperiBank/.LoginActivity"));
    }
    catch (AssertionError e)
    {
        System.out.println("Application Experibank Not Installed");
        fail();
    }
}


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    public String TakeSS() throws IOException, ParserConfigurationException, SAXException
    {
        String filename="Screenshot-"+new SimpleDateFormat(("yyyyMMddHHmmss'.jpg'")).format(new Date());
        String path= "C:/Automation/workspace/AppiumCourse/src/MobileMethods/"+filename;
        File SrcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(SrcFile, new File(path));
        return path;
    }

}


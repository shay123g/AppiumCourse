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
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

public class MobileMethods
{
   // private String reportDirectory = "reports";
   // private String reportFormat = "xml";
   // private String testName = "Untitled";
    protected AndroidDriver<AndroidElement> driver = null;
    AndroidElement ContentElement;
    DesiredCapabilities dc;
    List<AndroidElement> list;


    @BeforeClass
    public void setUp() throws MalformedURLException
    {
        SetupEnv();
    }
    @Test
    public void Test01_Do()
    {
        AppPhoneData();
        System.out.println(driver.getPageSource().indexOf("ListView"));

    }
   @Test
    public void Test02_rotateScreen() throws InterruptedException
    {
        if (GetOrient()=="portrait")
        {
            driver.rotate(ScreenOrientation.LANDSCAPE);
            Thread.sleep(5000);
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
    }
    @Test
    public void Test03_OpenDrawerHome() throws IOException, InterruptedException
    {
        driver.openNotifications();
        Thread.sleep(2000);
        TakeSS();
        driver.pressKeyCode(AndroidKeyCode.HOME);
        Thread.sleep(2000);
        TakeSS();
    }

@Test
public void Test04_verifyAppInstalled()
{
    try
    {
        assertTrue(driver.isAppInstalled("com.experitest.ExperiBank"));
    }
    catch (AssertionError e)
    {
        System.out.println("Application Experibank Not Installed");
        fail("Application Experibank Not Installed");
    }
}
@Test
public void Test05_Verify11()
{
    try
    {
        SwitchtoApp();
        findallElementsinList();
        assertEquals(11, GetListSize());
    }
    catch (AssertionError e)
    {
        System.out.println("Number of elements in list is not 11");
        fail("Number of elements in list is not 11");
    } catch (InterruptedException e)
    {
        e.printStackTrace();
    }
}
@Test
public void Lock() throws InterruptedException
{
    try
    {
        if (!DoesDeviceLocked())
        {
            LockDevice();
            if (DoesDeviceLocked())
            {
                Thread.sleep(3000);
                unLockDevice();
            }
        }
        assertFalse(DoesDeviceLocked());
    }
    catch( AssertionError e)
    {
        System.out.println("Device still locked");
        fail("Device still locked");
    }
}


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    public String TakeSS() throws IOException
    {
        String filename="Screenshot-"+new SimpleDateFormat(("yyyyMMddHHmmss'.jpg'")).format(new Date());
        String path= "C:/Automation/workspace/AppiumCourse/src/MobileMethods/"+filename;
        File SrcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(SrcFile, new File(path));
        return path;
    }

    public void AppPhoneData()
    {
        ContentElement=driver.findElementByXPath("//*[@text='Content']");
        System.out.println("Height is :"+ContentElement.getRect().getHeight()+ " Width is: "+ContentElement.getRect().getWidth());
        System.out.println("X :"+ContentElement.getRect().getX()+ " Y: "+ContentElement.getRect().getY());
        System.out.println("App name:"+driver.currentActivity());
        System.out.println("Time/Date: "+driver.getDeviceTime());
    }

    public String GetOrient()
    {
        return driver.getOrientation().value();
    }

    public void SetupEnv() throws MalformedURLException
    {
        dc = new DesiredCapabilities();
        //dc.setCapability("reportDirectory", reportDirectory);
        //dc.setCapability("reportFormat", reportFormat);
        //dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "ad0117020c03f11a29");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.android.apis");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.startActivity("com.example.android.apis", ".ApiDemos");
    }

    public boolean DoesDeviceLocked()
    {
        return driver.isLocked();
    }

    public void LockDevice()
    {
        driver.lockDevice();
    }
    public void unLockDevice()
    {
        driver.unlockDevice();
    }
    public void findallElementsinList()
    {
        list=driver.findElementsById("text1");
    }

    public int GetListSize()
    {
        return list.size();
    }

    public void SwitchtoApp() throws InterruptedException
    {
        driver.startActivity("com.example.android.apis",".ApiDemos");
        Thread.sleep(6000);
    }

}


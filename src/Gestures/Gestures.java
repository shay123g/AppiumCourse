package Gestures;


import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;


public class Gestures
{

    protected AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities dc;
    TouchAction action;



    @BeforeClass
    public void setUp() throws MalformedURLException, InterruptedException
    {
        SetupEnv();
    }

    @Test
    public void Test01_Swipe() throws InterruptedException
    {
        DoBefore1();
        try
        {
            driver.swipe(find12inclock().getRect().getX(),find12inclock().getRect().getY(),find9inclock().getRect().getX(),find9inclock().getRect().getY(),1000);
            driver.swipe(find15inclock().getRect().getX(),find15inclock().getRect().getY(),find45inclock().getRect().getX(),find45inclock().getRect().getY(),1000);
            assertEquals("9:45", TimeInClock());
        }
        catch (AssertionError e)
        {
            System.out.println("Time is not 9:45");
            fail("Time is not 9:45");
         }
    }


    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }



    public void DoBefore1() throws InterruptedException
    {

            driver.performTouchAction(action.tap(LocateViews()));
            Thread.sleep(2000);
            driver.performTouchAction(action.tap(LocateDate()));
            Thread.sleep(2000);
            driver.performTouchAction(action.tap(LocateInline()));

    }

    public AndroidElement LocateViews()
    {
        return driver.findElement(By.xpath("//*[@text='Views']"));
    }

    public AndroidElement LocateDate()
    {
        return driver.findElement(By.xpath("//*[@text='Date Widgets']"));
    }
    public AndroidElement LocateInline()
    {
        return driver.findElement(By.xpath("//*[@text='2. Inline']"));
    }
    public AndroidElement find9inclock()
    {
        return driver.findElement(By.xpath("//*[@contentDescription='9']"));
    }
    public AndroidElement find12inclock()
    {
        return driver.findElement(By.xpath("//*[@contentDescription='12']"));
    }

    public void SetupEnv() throws MalformedURLException, InterruptedException
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
        Thread.sleep(5000);
        action=new TouchAction(driver);
    }

    public AndroidElement find15inclock()
    {
        return driver.findElement(By.xpath("//*[@contentDescription='15']"));
    }

    public AndroidElement find45inclock()
    {
        return driver.findElement(By.xpath("//*[@contentDescription='45']"));
    }
    public String TimeInClock()
    {
        String hrs=driver.findElement(By.id("hours")).getText();
        String mins=driver.findElement(By.id("minutes")).getText();
        return (hrs+":"+mins);

    }
}

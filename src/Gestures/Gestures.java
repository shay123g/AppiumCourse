package Gestures;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;


public class Gestures
{

    protected AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities dc;
    AndroidElement context_views,context_date,context_inline;


    @BeforeClass
    public void setUp() throws MalformedURLException
    {
        SetupEnv();
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
    @AfterClass
    public void tearDown() {
        driver.quit();
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
    public AndroidElement find15inclock()
    {
        return driver.findElement(By.xpath("//*[@contentDescription='15']"));
    }
    public AndroidElement find45inclock()
    {
        return driver.findElement(By.xpath("//*[@contentDescription='45']"));
    }
}

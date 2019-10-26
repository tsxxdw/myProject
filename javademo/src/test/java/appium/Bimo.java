package appium;


import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Bimo {

    private AppiumDriver driver;

    @Before

    public void setUp()throws Exception{

        DesiredCapabilities capabilities =new DesiredCapabilities();

        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");

        capabilities.setCapability("platformName", "Android");//设备的系统

        capabilities.setCapability("deviceName", "meizu-m5_note-621QEBPQ2F42W");//设备名称

        capabilities.setCapability("Version", "7.0");//系统版本号

        capabilities.setCapability("appPackage", "com.fcmcoin.fcm");//需要启动的包名

        capabilities.setCapability("appActivity", ".ui.activity.MainActivity");//需要启动的Activity

        capabilities.setCapability("unicodeKeyboard", "True");//支持中文输入

        capabilities.setCapability("resetKeyboard", "True");//支持中文输入

     //   driver =new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @Test

    public void HomePage()throws InterruptedException {

        Thread.sleep(3);

    }

    @After

    public void tearDown()throws Exception{

        driver.quit();

    }

}

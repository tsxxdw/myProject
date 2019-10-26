package appium;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppDemo {
    private AppiumDriver driver;
    private final String platformVersion = "5.1.1";
    private final String deviceName = "Android Emulator";
    private final String platformName = "Android";
    private final String noReset = "True";

    private final String meituanAppActivity = "com.sankuai.waimai.business.page.homepage.MainActivity";
    private final String meituanAppPackage = "com.sankuai.meituan.takeoutnew";
    @BeforeClass
    public void setup() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("appActivity", meituanAppActivity);
        capabilities.setCapability("appPackage", meituanAppPackage);
        capabilities.setCapability("noReset", noReset);

//        //A new session could not be created的解决方法
//     cap.setCapability("appWaitActivity", "com.meizu.flyme.calculator.Calculator");
//        //每次启动时覆盖session，否则第二次后运行会报错不能新建session
//        cap.setCapability("sessionOverride", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void plus() throws Exception {
        Thread.sleep(3000);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        int x0 = (int)(width * 0.8);  // 起始x坐标
        int x1 = (int)(height * 0.2);  // 终止x坐标
        int y = (int)(height * 0.5);  // y坐标
        for (int i=0; i<5; i++) {
            //driver.swipe(x0, y, x1, y, 500);
            Thread.sleep(1000);
        }

        driver.findElementById("com.youdao.calculator:id/guide_button").click();
        for (int i=0; i<6; i++) {
            driver.findElementByXPath("//android.webkit.WebView[@text='Mathbot Editor']").click();
            Thread.sleep(1000);
        }

        String btn_xpath = "//*[@resource-id='com.youdao.calculator:id/view_pager_keyboard']/android.widget.GridView/android.widget.FrameLayout[%d]/android.widget.FrameLayout";
        driver.findElementByXPath(String.format(btn_xpath, 7)).click();
        driver.findElementByXPath(String.format(btn_xpath, 10)).click();
        driver.findElementByXPath(String.format(btn_xpath, 8)).click();
        Thread.sleep(3000);


    }

    @AfterClass
    public void tearDown() throws Exception {

        driver.quit();

    }
}

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeforeTest {

//    private static final String APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
    private static final String APP = "P:/Projekty/Mobile/Appium/TheApp-v1.9.0.apk";
    private static final String APPIUM = "http://0.0.0.0:4723/wd/hub";

//    private RemoteWebDriver driver;

    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("browserName", "Chrome");
//        caps.setCapability("chromedriver", "P:/Projekty/Mobile/Appium/Chrome/chromedriver");
        caps.setCapability("app", APP);
        driver = new AndroidDriver(new URL(APPIUM), caps);
//        driver = new RemoteWebDriver(new URL(APPIUM), caps);
        // dla webmobile dodajemy przeglądarkę


    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        WebDriverWait wait = new WebDriverWait(driver, 10, 100);
        WebElement screen = wait.until(ExpectedConditions.presenceOfElementLocated
                (MobileBy.AccessibilityId("Login Screen")));
        screen.click();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(driver.getPageSource()); // pobieranie informacji o elementach z strony

        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("username")));
        username.sendKeys("alice");
        WebElement password = driver.findElement(MobileBy.AccessibilityId("password"));
        password.sendKeys("mypassword");
        WebElement loginBtn = driver.findElement(MobileBy.AccessibilityId("loginBtn"));
        loginBtn.click();

        WebElement loginText = wait.until(ExpectedConditions.presenceOfElementLocated
                (MobileBy.xpath("//android.widget.TextView[contains(@text, 'You are logged in')]")));
        assert(loginText.getText().contains("alice"));

    }

    @Test
    public void helloWorldTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10, 100);
        WebElement echoBoxScreen = wait.until(ExpectedConditions.presenceOfElementLocated
                (MobileBy.AccessibilityId("Echo Box")));
        echoBoxScreen.click();

        WebElement saySomethingField = wait.until(ExpectedConditions.presenceOfElementLocated
                (MobileBy.AccessibilityId("messageInput")));
        saySomethingField.sendKeys("Hello World");
        WebElement saveBtn = driver.findElement(MobileBy
                .AccessibilityId("messageSaveBtn"));
        saveBtn.click();

        WebElement helloText = wait.until(ExpectedConditions.presenceOfElementLocated
                (MobileBy.AccessibilityId("Hello World")));
        Assert.assertEquals("Wrong text", "Hello World", helloText.getText());

    }

    @Test
    public void touchAndSwipeTest() { // przewijanie listy
        WebDriverWait wait = new WebDriverWait(driver, 10, 100);
        WebElement listDemoScreen = wait.until(ExpectedConditions.presenceOfElementLocated
                (MobileBy.AccessibilityId("List Demo")));
        listDemoScreen.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Altocumulus")));

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Interaction moveToStart = finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 520, 1530);
        Interaction pressDown = finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
        Interaction moveToEnd = finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 520, 490);
        Interaction pressUp = finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg());

        Sequence swipe = new Sequence(finger, 0);
        swipe.addAction(moveToStart);
        swipe.addAction(pressDown);
        swipe.addAction(moveToEnd);
        swipe.addAction(pressUp);

        driver.perform(Arrays.asList(swipe));
        driver.findElement(MobileBy.AccessibilityId("Stratus"));
//        WebElement saySomethingField = wait.until(ExpectedConditions.presenceOfElementLocated
//                (MobileBy.AccessibilityId("messageInput")));
//        saySomethingField.sendKeys("Hello World");
//        WebElement saveBtn = driver.findElement(MobileBy
//                .AccessibilityId("messageSaveBtn"));
//        saveBtn.click();
//
//        WebElement helloText = wait.until(ExpectedConditions.presenceOfElementLocated
//                (MobileBy.AccessibilityId("Hello World")));
//        Assert.assertEquals("Wrong text", "Hello World", helloText.getText());

    }

    @Test
    public void webTest() { // trzeba w ustawieniach zmienić z app na przeglądarke, i drivera na remote, up
        WebDriverWait wait = new WebDriverWait(driver, 10, 100);
//        WebElement echoBoxScreen = wait.until(ExpectedConditions.presenceOfElementLocated
//                (MobileBy.AccessibilityId("Echo Box")));
//        echoBoxScreen.click();
//
//        WebElement saySomethingField = wait.until(ExpectedConditions.presenceOfElementLocated
//                (MobileBy.AccessibilityId("messageInput")));
//        saySomethingField.sendKeys("Hello World");
//        WebElement saveBtn = driver.findElement(MobileBy
//                .AccessibilityId("messageSaveBtn"));
//        saveBtn.click();
//
//        WebElement helloText = wait.until(ExpectedConditions.presenceOfElementLocated
//                (MobileBy.AccessibilityId("Hello World")));
//        Assert.assertEquals("Wrong text", "Hello World", helloText.getText());

    }
}

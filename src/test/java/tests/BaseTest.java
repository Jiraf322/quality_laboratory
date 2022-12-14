package tests;

import org.testng.annotations.AfterMethod;
import support.webdriver.CustomWebDriver;

public abstract class BaseTest {

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        CustomWebDriver.getInstance().quit();
    }
}

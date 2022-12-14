package support.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Slf4j
public final class CustomWebDriver {

    private static long DEFAULT_DURATION = 5;
    private static CustomWebDriver driver;
    private final WebDriver webDriver;

    private CustomWebDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    public static CustomWebDriver getInstance() {
        if (driver == null) {
            driver = new CustomWebDriver();
        }
        return driver;
    }

    public void open(@NonNull String url) {
        log.debug("Opening page by url: {}", url);
        webDriver.get(url);
    }

    public void quit() {
        log.debug("Quitting driver...");
        webDriver.quit();
    }

    public void switchToIframeByXpath(@NonNull String xpath) {
        log.debug("Switching to frame by xpath: {}", xpath);
        webDriver.switchTo().frame(findElementByXpath(xpath));
    }

    public void exitIframe() {
        log.debug("Switching to default page...");
        webDriver.switchTo().defaultContent();
    }

    public WebElement findElementByXpath(@NonNull String xpath) {
        log.debug("Looking for element by xpath: {}", xpath);
        return new WebDriverWait(webDriver, DEFAULT_DURATION).until(visibilityOfElementLocated(By.xpath(xpath)));
    }

    public WebElement shouldBeClickable(@NonNull WebElement webElement) {
        log.debug("Waiting for element to be clickable...");
        return new WebDriverWait(webDriver, DEFAULT_DURATION).until(elementToBeClickable(webElement));
    }
}

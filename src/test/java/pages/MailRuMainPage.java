package pages;

import lombok.NonNull;
import models.Credentials;

public final class MailRuMainPage extends BasePage {

    private static final String MAIN_PAGE_URL = "https://mail.ru/";

    private static final String LOGIN_BUTTON_XPATH = "//button[@data-testId='enter-mail-primary']";

    private static final String SIGN_IN_MODAL_WINDOW_IFRAME = "//iframe[contains(@src, 'login')]";
    private static final String SIGN_IN_MODAL_WINDOWS_XPATH = "//div[@id='login-content']";
    private static final String LOGIN_INPUT_TEXT_XPATH = SIGN_IN_MODAL_WINDOWS_XPATH + "//input[@name='username']";
    private static final String SIGN_IN_NEXT_BUTTON_XPATH = SIGN_IN_MODAL_WINDOWS_XPATH + "//button[@data-test-id='next-button']";
    private static final String PASSWORD_INPUT_TEXT_PATH = SIGN_IN_MODAL_WINDOWS_XPATH + "//input[@name='password']";
    private static final String SIGN_IN_BUTTON_XPATH = SIGN_IN_MODAL_WINDOWS_XPATH + "//button[@data-test-id='submit-button']";
    private static final String CREATE_LINK_BUTTON_XPATH = "//a[@data-testid= 'mailbox-create-link']";

    public void open() {
        driver.open(MAIN_PAGE_URL);
    }

    public boolean isPageOpen() {
        return driver.findElementByXpath(CREATE_LINK_BUTTON_XPATH).isDisplayed();
    }

    public void openLoginModalWindow() {
        driver.findElementByXpath(LOGIN_BUTTON_XPATH).click();
    }

    public boolean isLoginModalWindowOpened() {
        return driver.findElementByXpath(LOGIN_BUTTON_XPATH).isDisplayed();
    }

    public void switchToSignInModalWindowIframe() {
        driver.switchToIframeByXpath(SIGN_IN_MODAL_WINDOW_IFRAME);
    }

    public void switchToMainPage() {
        driver.exitIframe();
    }

    public void enterPassword(@NonNull Credentials credentials) {
        driver.findElementByXpath(PASSWORD_INPUT_TEXT_PATH).sendKeys(credentials.getPassword());
        driver.shouldBeClickable(driver.findElementByXpath(SIGN_IN_BUTTON_XPATH)).click();
    }

    public void enterLogin(@NonNull Credentials credentials) {
        driver.findElementByXpath(LOGIN_INPUT_TEXT_XPATH).sendKeys(credentials.getLogin());
        driver.shouldBeClickable(driver.findElementByXpath(SIGN_IN_NEXT_BUTTON_XPATH)).click();
    }
}

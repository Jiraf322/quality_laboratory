package pages;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import static support.utils.CredentialsProvider.getDefaultCredentials;

@Slf4j
public final class MailRuInboxPage extends BasePage{

    private static final String USER_INBOX_XPATH = "//div[@data-testid = 'whiteline-account' and contains(@aria-label, '%s')]";
    private static final String COMPOSE_BUTTON_XPATH = "//span[contains(@class, 'compose-button') and contains(@class, 'txt')]";
    private static final String RECIPIENT_TEXT_BOX_XPATH = "//div[contains(@class, 'contacts')]//input";
    private static final String SUBJECT_TEXT_BOX_XPATH = "//div[contains(@class, 'subject')]//input";
    private static final String BODY_TEXT_BOX_XPATH = "//div[@role = 'textbox']";
    private static final String BUTTON_COLLAPSE_COMPOSE_MODAL_WINDOW = "//button[@data-promo-id='collapse']";
    private static final String BUTTON_SEND_MAIL = "//button[@data-test-id='send']";
    private static final String WINDOW_SENT_PAGE = "//div[@class='layer-sent-page']";
    private static final String BUTTON_CLOSE_PROMO_MODAL_WINDOW = "//div[@data-click-counter]//div[contains(@class, 'promo')]";

    public boolean isUserAuthorized() {
        return driver.findElementByXpath(String.format(USER_INBOX_XPATH, getDefaultCredentials().getLogin())).isDisplayed();
    }

    public void openComposeModalWindow() {
        driver.findElementByXpath(COMPOSE_BUTTON_XPATH).click();
    }

    public boolean isComposeModalWindowOpened() {
        return driver.findElementByXpath(BUTTON_COLLAPSE_COMPOSE_MODAL_WINDOW).isDisplayed();
    }

    public void fillMailRecipientField(@NonNull String sendTo) {
        driver.findElementByXpath(RECIPIENT_TEXT_BOX_XPATH).sendKeys(sendTo);
    }

    public void fillSubjectField(@NonNull String subject) {
        driver.findElementByXpath(SUBJECT_TEXT_BOX_XPATH).sendKeys(subject);
    }

    public void fillBody(@NonNull String bodyText) {
        driver.findElementByXpath(BODY_TEXT_BOX_XPATH).sendKeys(bodyText);
    }

    public void sendMail() {
        driver.findElementByXpath(BUTTON_SEND_MAIL).click();
    }

    public boolean isMessageSent() {
        return driver.findElementByXpath(WINDOW_SENT_PAGE).isDisplayed();
    }

    public void closePromoModalWindow() {
        try {
            driver.findElementByXpath(BUTTON_CLOSE_PROMO_MODAL_WINDOW).click();
        } catch (Exception e) {
            log.info("Promo didn't appear");
        }
    }
}

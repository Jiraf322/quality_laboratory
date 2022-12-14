package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MailRuInboxPage;
import pages.MailRuMainPage;

import static support.utils.CredentialsProvider.getDefaultCredentials;

public final class MailRuTest extends BaseTest {

    @Test
    public void testEmailIsSent() {
        MailRuMainPage mailRuMainPage = new MailRuMainPage();

        mailRuMainPage.open();
        Assert.assertTrue(mailRuMainPage.isPageOpen(), "MailRu main page is not opened");

        mailRuMainPage.openLoginModalWindow();
        Assert.assertTrue(mailRuMainPage.isLoginModalWindowOpened(), "Login modal window is not opened");

        mailRuMainPage.switchToSignInModalWindowIframe();
        mailRuMainPage.enterLogin(getDefaultCredentials());
        mailRuMainPage.enterPassword(getDefaultCredentials());
        mailRuMainPage.switchToMainPage();

        MailRuInboxPage mailRuInboxPage = new MailRuInboxPage();

        Assert.assertTrue(mailRuInboxPage.isUserAuthorized(), "Expected user is not authorized");

        mailRuInboxPage.closePromoModalWindow();
        mailRuInboxPage.openComposeModalWindow();
        Assert.assertTrue(mailRuInboxPage.isComposeModalWindowOpened(), "Compose window is not opened");

        mailRuInboxPage.fillMailRecipientField(getDefaultCredentials().getLogin() + "@mail.ru");
        mailRuInboxPage.fillSubjectField("Some subject");
        mailRuInboxPage.fillBody("Some text");
        mailRuInboxPage.sendMail();
        Assert.assertTrue(mailRuInboxPage.isMessageSent(), "The mail was not sent");
    }
}

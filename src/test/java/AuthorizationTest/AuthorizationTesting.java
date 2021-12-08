package AuthorizationTest;

import org.testng.annotations.Test;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.codeborne.selenide.Selenide.open;

public class AuthorizationTesting implements IRetryAnalyzer {
    private int retryCount = 0;
    private final int maxRetryCount = 1;

    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }

    @Test(testName = "UserAuthorization", retryAnalyzer = AuthorizationTesting.class)
    public void userAuth() {
        TestConfig user = new TestConfig();
        user.getEntryFieldEmail().setValue(user.getEmail());
        user.getEntryFieldPassword().setValue(user.getPassword());
        user.getCreateUserButton().click();
        user.checkSuccessAuthorization();
    }

    @Test(testName = "Empty Fields Authorization", retryAnalyzer = AuthorizationTesting.class)
    public void emptyFieldsTest() {
        TestConfig user = new TestConfig();
        user.getCreateUserButton().click();
        user.checkErrorAuthorization();
    }

    @Test(testName = "Empty Password Authorization", retryAnalyzer = AuthorizationTesting.class)
    public void emptyPasswordTest() {
        TestConfig user = new TestConfig();
        user.getEntryFieldEmail().setValue(user.getEmail());
        user.getCreateUserButton().click();
        user.checkErrorAuthorization();
    }

    @Test(testName = "Empty E-mail Authorization", retryAnalyzer = AuthorizationTesting.class)
    public void emptyEmailTest() {
        TestConfig user = new TestConfig();
        user.getEntryFieldPassword().setValue(user.getPassword());
        user.getCreateUserButton().click();
        user.checkErrorAuthorization();
    }

    @Test(testName = "Checkbox \"Remember me\" test", retryAnalyzer = AuthorizationTesting.class)
    public void checkboxFormTest() {
        TestConfig user = new TestConfig();
        user.checkboxTest();
    }

    @Test(testName = "Create account link test", retryAnalyzer = AuthorizationTesting.class)
    public void createAccountLinkTest() {
        TestConfig user = new TestConfig();
        user.linkTest();
    }

    @Test(testName = "Restore Password", retryAnalyzer = AuthorizationTesting.class)
    public void restorePasswordTest() {
        TestConfig user = new TestConfig();
        user.restorePasswordTest(user.getEmail(), true);
        open(user.getURL());
        user.getLoginButton().click();
        user.restorePasswordTest(null, false);
        open(user.getURL());
        user.getLoginButton().click();
        user.restorePasswordTest(InvalidEmailList.incorrectEmails[0], false);
    }

    @Test(testName = "Invalid Email login", retryAnalyzer = AuthorizationTesting.class)
    public void loginInvalidEmail() {
        String[] incorrectEmails = InvalidEmailList.incorrectEmails;
        TestConfig user = new TestConfig();
        for (String incorrectEmail : incorrectEmails) {
            user.getEntryFieldEmail().setValue(incorrectEmail);
            user.getEntryFieldPassword().setValue(user.getPassword());
            user.getCreateUserButton().click();
            user.checkErrorAuthorization();
        }
    }
}
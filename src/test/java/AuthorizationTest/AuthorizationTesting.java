package AuthorizationTest;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class AuthorizationTesting {
    @Test(testName = "UserAuthorization")
    public void userAuth() {
        TestConfig user = new TestConfig();
        user.getEntryFieldEmail().setValue(user.getEmail());
        user.getEntryFieldPassword().setValue(user.getPassword());
        user.getCreateUserButton().click();
        user.checkSuccessAuthorization();
    }

    @Test(testName = "Empty Fields Authorization")
    public void emptyFieldsTest() {
        TestConfig user = new TestConfig();
        user.getCreateUserButton().click();
        user.checkErrorAuthorization();
    }

    @Test(testName = "Empty Password Authorization")
    public void emptyPasswordTest() {
        TestConfig user = new TestConfig();
        user.getEntryFieldEmail().setValue(user.getEmail());
        user.getCreateUserButton().click();
        user.checkErrorAuthorization();
    }

    @Test(testName = "Empty E-mail Authorization")
    public void emptyEmailTest() {
        TestConfig user = new TestConfig();
        user.getEntryFieldPassword().setValue(user.getPassword());
        user.getCreateUserButton().click();
        user.checkErrorAuthorization();
    }

    @Test(testName = "Checkbox \"Remember me\" test")
    public void checkboxFormTest() {
        TestConfig user = new TestConfig();
        user.checkboxTest();
    }

    @Test(testName = "Create account link test")
    public void createAccountLinkTest() {
        TestConfig user = new TestConfig();
        user.linkTest();
    }

    @Test(testName = "Restore Password")
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

    @Test(testName = "Invalid Email login")
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

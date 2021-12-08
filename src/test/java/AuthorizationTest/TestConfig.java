package AuthorizationTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.*;
import static com.codeborne.selenide.Condition.*;

public class TestConfig {
    private final int TIMEOUT = 10000;
    private final String URL = "https://openweathermap.org/";
    private final String USER_EMAIL = "poceteh301@simdpi.com";
    private final String USER_PASSWORD = "testUser123";
    private boolean firstRestore = false;

    TestConfig() {
        Configuration.timeout = TIMEOUT;
        open(getURL());
        getLoginButton().click();
    }

    String getURL() {
        return URL;
    }

    String getEmail() {
        return USER_EMAIL;
    }

    String getPassword() {
        return USER_PASSWORD;
    }

    SelenideElement getLoginButton() {
        return $x("//li[@class=\"user-li\"]/a");
    }

    SelenideElement getEntryFieldEmail() {
        return $("#user_email");
    }

    SelenideElement getEntryFieldPassword() {
        return $("#user_password");
    }

    SelenideElement getCreateUserButton() {
        return $x("//form/input[@data-disable-with='Create User']");
    }

    void checkSuccessAuthorization() {
        $x("//div[@id=\"user-dropdown\"]").shouldBe(enabled);
    }

    void checkErrorAuthorization() {
        $("div.panel-body").shouldHave(text("Invalid Email or password."));
    }

    void checkboxTest() {
        $("#user_remember_me").click();
        $("#user_remember_me").shouldHave(checked);
        $("#user_remember_me").click();
        $("#user_remember_me").shouldNotHave(checked);
        $x("//label[@for=\"user_remember_me\"]").click();
        $("#user_remember_me").shouldHave(checked);
        $x("//label[@for=\"user_remember_me\"]").click();
        $("#user_remember_me").shouldNotHave(checked);
    }

    void linkTest() {
        $x("//div[@class=\"sign-form\"]/p/a").click();
        webdriver().shouldHave(url("https://home.openweathermap.org/users/sign_up"));
    }

    void restorePasswordTest(String testEmail, boolean correctEmail) {
        $x("//div[@class=\"pwd-lost-q show\"]/a").click();
        $("div.form-group input#user_email").setValue(testEmail);
        $x("//form[@class=\"simple_form form-inline\"]/input[@data-disable-with=\"Create User\"]").click();
        if (correctEmail && firstRestore) {
            $("div.panel-body").shouldHave(text("You will receive an email with instructions on how to reset your password in a few minutes."));
            firstRestore = false;
        } else if (correctEmail) {
            $("div.panel-body").shouldHave(text("You have recently requested password recovery. Please try again later"));
        } else if (testEmail == null) {
            $("div.panel-body").shouldHave(text("Email can't be blank"));
        } else {
            $("div.panel-body").shouldHave(text("Email not found"));
        }
    }
}
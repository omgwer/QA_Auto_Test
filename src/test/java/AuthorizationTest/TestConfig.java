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
        return $("li.user-li a");
    }

    SelenideElement getEntryFieldEmail() {
        return $("#user_email");
    }

    SelenideElement getEntryFieldPassword() {
        return $("#user_password");
    }

    SelenideElement getCreateUserButton() {
        return $("input[data-disable-with=\"Create User\"]");
    }

    void checkSuccessAuthorization() {
        $("div#user-dropdown").shouldBe(enabled);
    }

    void checkErrorAuthorization() {
        $("div.panel-body").shouldHave(text("Invalid Email or password."));
    }

    void checkboxTest() {
        $("#user_remember_me").click();
        $("#user_remember_me").shouldHave(checked);
        $("#user_remember_me").click();
        $("#user_remember_me").shouldNotHave(checked);
        $("label[for=\"user_remember_me\"]").click();
        $("#user_remember_me").shouldHave(checked);
        $("label[for=\"user_remember_me\"]").click();
        $("#user_remember_me").shouldNotHave(checked);
    }

    void linkTest() {
        $("a[href=\"/users/sign_up\"]").click();
        webdriver().shouldHave(url("https://home.openweathermap.org/users/sign_up"));
    }

    void restorePasswordTest(String testEmail, boolean correctEmail) {
        $("div.pwd-lost-q a").click();
        $("div.form-group input#user_email").setValue(testEmail);
        $("form.form-inline input[data-disable-with=\"Create User\"]").click();
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
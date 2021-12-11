package TemperatureGetTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class TestConfig {
    private final int TIMEOUT = 10000;
    private final String URL = "https://openweathermap.org/";
    public final String[] cityList =
            {
                    "Moscow", "London", "Yoshkar-Ola"
            };

    TestConfig() {
        Configuration.timeout = TIMEOUT;
        open(getURL());
    }

    String getURL() {
        return URL;
    }

    void setSearchCityField(String city) {
        $("input[placeholder=\"Search city\"]").setValue(city);
    }

    void searchCity() {
        $("div.search button.button-round").click();
        $("ul.search-dropdown-menu li").click();
        sleep(2000);
    }

    void selectTemperatureType(String type) {
        if (type.equals("Imperial")) {
            $x("//div[text()=\"Imperial: °F, mph\"]").click();
        } else {
            $x("//div[text()=\"Metric: °C, m/s\"]").click();
        }

        sleep(2000);
    }

    void getCurrentTemp(String type, String city) {
        SelenideElement element = $("div.current-temp span.heading");
        if (type.equals("Imperial")) {
            element.shouldHave(text("°F"));
            System.out.println(city + " = " + element.text());
        } else {
            element.shouldHave(text("°C"));
            System.out.println(city + " = " + element.text());
        }
    }
}
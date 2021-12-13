package APITest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APITest {
    private final String currentCity = "Yoshkar-Ola";
    private final String apiKey = "2eeba1c5674d7602588638f7ab8a40ff";
    private final String metricSystem = "metric";

    @Test
    public void getTemperature() {
        String res = given()
                .when().get("https://api.openweathermap.org/data/2.5/weather?q=" + currentCity + "&appid=" + apiKey + "&units=" + metricSystem)
                .then().assertThat()
                .statusCode(200)
                .extract().jsonPath().getString("main.temp");
        System.out.println("Weather in " + currentCity + " = " + res + " °C");
    }
}
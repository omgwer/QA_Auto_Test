package APITest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APITest {

    @Test
    public void getTemperature() {
        String res = given()
                .when().get("https://api.openweathermap.org/data/2.5/weather?q=Yoshkar-Ola&appid=2eeba1c5674d7602588638f7ab8a40ff&units=metric")
                .then().assertThat()
                .statusCode(200)
                .extract().jsonPath().getString("main.temp");
        System.out.println("Weather in Yoshkar Ola = " + res + " °C");
    }
}
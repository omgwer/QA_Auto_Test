package TemperatureGetTest;

import org.testng.annotations.Test;

public class TemperatureGainTest {

    @Test(testName = "Get City Temperature")
    public void getCityTempTest() {
        TestConfig getTemp = new TestConfig();
        String[] cityList = getTemp.cityList;

        for(String city: cityList) {
            getTemp.setSearchCityField(city);
            getTemp.searchCity();
            getTemp.selectTemperatureType("Imperial");
            getTemp.getCurrentTemp("Imperial", city);
            getTemp.selectTemperatureType("Metric");
            getTemp.getCurrentTemp("Metric", city);
        }
    }
}

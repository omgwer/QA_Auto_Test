package TemperatureGetTest;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;
import org.testng.ITestResult;

public class TemperatureGainTest implements IRetryAnalyzer {
    private int retryCount = 0;
    private final int maxRetryCount = 1;

    public boolean retry(ITestResult result) {

        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }

    @Test(testName = "Get City Temperature", retryAnalyzer = TemperatureGainTest.class)
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
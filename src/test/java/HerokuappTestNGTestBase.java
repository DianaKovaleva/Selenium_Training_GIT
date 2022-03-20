import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;



public class HerokuappTestNGTestBase {

    protected WebDriver webdriver;
    protected final String baseUrl = "https://the-internet.herokuapp.com/";


    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        webdriver = new ChromeDriver();
        webdriver.get(baseUrl);
        webdriver.manage().window().maximize();
    }

    @BeforeMethod
    public void browserOpening() {
        webdriver.get(baseUrl);
    }

    @AfterTest
    public void teardown() {
        webdriver.quit();
    }
}

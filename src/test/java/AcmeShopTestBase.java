import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class AcmeShopTestBase {

    protected WebDriver webdriver;
    protected final String baseUrl = "https://litecart.stqa.ru/en/";


    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        webdriver = new ChromeDriver();
        webdriver.get(baseUrl);
        webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webdriver.manage().window().maximize();
    }

    @BeforeMethod
    public void mainPage(){
        webdriver.get(baseUrl);
    }

    @AfterTest
    public void teardown() {
        webdriver.quit();
    }
}

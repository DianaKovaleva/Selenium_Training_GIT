import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BelhardSite {
    WebDriver webdriver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        webdriver = new ChromeDriver();
        webdriver.get("https://belhard.academy/");
        webdriver.manage().window().maximize();
    }

    @Test //Работа с новыми окнами браузера
    public void newWindow() {
        String initialTab = webdriver.getWindowHandle();
        WebElement employmentButton = webdriver.findElement(By.cssSelector("a[href=\"/employment\"]"));
        employmentButton.click();

        WebElement proftestButton = webdriver.findElement(By.cssSelector("a[href=\"https://belhard.academy/proftest\"]"));
        proftestButton.click();

        String newTab = webdriver.getWindowHandles().toArray()[1].toString();
        webdriver.switchTo().window(newTab);

        WebElement allCoursesButton = webdriver.findElement(By.cssSelector(".t142__text"));
        allCoursesButton.click();
        webdriver.close();

        webdriver.switchTo().window(initialTab);

        String initialTabTitle = webdriver.getTitle();
        Assert.assertEquals(initialTabTitle, "Трудоустройство Академии BELHARD");

    }

    @AfterTest
    public void teardown() {
        webdriver.quit();
    }

}

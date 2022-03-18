import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;
import static org.openqa.selenium.Keys.CONTROL;
import static org.openqa.selenium.Keys.DELETE;

public class HerokuappTestNG {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void click8thElement() {

        // Задание - кликнуть на 8ую ссылку
        //List<WebElement> links = driver.findElements(By.tagName("a"));
        //links.get(8).click();

        // Либо так:
         WebElement linkTo8th = driver.findElement(By.linkText("Digest Authentication"));
         linkTo8th.click();
    }

    @Test
    public void jsAlert() {
        WebElement linkToAlerts = driver.findElement(By.cssSelector("a[href=\"/javascript_alerts\"]"));
        linkToAlerts.click();

        WebElement jsAlert = driver.findElement(By.cssSelector("button[onclick^=\"jsAlert\"]"));
        jsAlert.click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "I am a JS Alert");
        alert.accept();
    }

    @Test
    public void confirmAlert() {
        WebElement linkToAlerts = driver.findElement(By.cssSelector("a[href=\"/javascript_alerts\"]"));
        linkToAlerts.click();

        WebElement jsConfirm = driver.findElement(By.cssSelector("button[onclick^=\"jsConfirm\"]"));
        jsConfirm.click();

        Alert alert = driver.switchTo().alert();
        String alertText2 = alert.getText();
        Assert.assertEquals(alertText2, "I am a JS Confirm");
        alert.dismiss();
    }

    @Test
    public void promptAlert() {
        WebElement linkToAlerts = driver.findElement(By.cssSelector("a[href=\"/javascript_alerts\"]"));
        linkToAlerts.click();

        WebElement jsPrompt = driver.findElement(By.cssSelector("button[onclick^=\"jsPrompt\"]"));
        jsPrompt.click();

        Alert alert = driver.switchTo().alert();
        String alertText3 = alert.getText();
        Assert.assertEquals(alertText3, "I am a JS prompt");
        alert.sendKeys("Ok");
        alert.accept();
    }

    @Test
    public void framesTraining() {
        WebElement framesLink = driver.findElement(By.linkText("Frames"));
        framesLink.click();
        WebElement iFrameLink = driver.findElement(By.linkText("iFrame"));
        iFrameLink.click();
        driver.switchTo().frame("mce_0_ifr");
        WebElement frameInput = driver.findElement(By.cssSelector(".mce-content-body"));
        frameInput.sendKeys(Keys.chord(CONTROL, "A"));
        frameInput.sendKeys(Keys.chord(DELETE));
        frameInput.sendKeys("Hello, I'm a text about nothing");
        driver.switchTo().defaultContent();
        WebElement centerText = driver.findElement(By.cssSelector("[title=\"Align center\"]")); // Проверим, что вышли из рамки
        centerText.click();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }


    }



import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

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
    public void clickAndAlertsHomework() {

        // Задание - кликнуть на 8ую ссылку
        List<WebElement> links = driver.findElements(By.tagName("a"));
        links.get(8).click();

        // Либо так:
        // WebElement linkTo8th = driver.findElement(By.linkText("Digest Authentication"));
        // linkTo8th.click();

        //Отработка алерта
        driver.get("https://the-internet.herokuapp.com/");
        WebElement linkToAlerts = driver.findElement(By.linkText("JavaScript Alerts"));
        linkToAlerts.click();

        WebElement jsAlert = driver.findElement(By.cssSelector("button[onclick^=\"jsAlert\"]"));
        jsAlert.click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "I am a JS Alert");
        alert.accept();

        //Отработка алерта типа Confirm
        WebElement jsConfirm = driver.findElement(By.cssSelector("button[onclick^=\"jsConfirm\"]"));
        jsConfirm.click();

        String alertText2 = alert.getText();
        Assert.assertEquals(alertText2, "I am a JS Confirm");
        alert.dismiss();

        //Отработка алерта типа Prompt
        WebElement jsPrompt = driver.findElement(By.cssSelector("button[onclick^=\"jsPrompt\"]"));
        jsPrompt.click();

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
        Actions action = new Actions(driver);
        action.moveToElement(frameInput);
        //action.sendKeys(Keys.CONTROL+"A"); //Не получается почему-то выделить всё и удалить
        //action.sendKeys(Keys.DELETE);
        frameInput.sendKeys("Hello, I'm a text about nothing");
        driver.switchTo().defaultContent();
        WebElement centerText = driver.findElement(By.cssSelector("[title=\"Align center\"]")); // Проверим, что вышли из рамки
        centerText.click();

    }

        @AfterTest
        public void teardown() {
            //driver.quit();
        }


    }



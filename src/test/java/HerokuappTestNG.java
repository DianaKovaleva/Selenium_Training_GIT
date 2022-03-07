import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HerokuappTestNG {

    @Test
    public void TestNG() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\WebDrivers\\chromedriver.exe");
        WebDriver ChromeForHomework1 = new ChromeDriver();
        ChromeForHomework1.get("https://the-internet.herokuapp.com/");
        List<WebElement> links = ChromeForHomework1.findElements(By.tagName("a"));
        links.get(8).click();

        // Либо так:
        // WebElement linkTo8th = ChromeForHomework1.findElement(By.linkText("Digest Authentication"));
        // linkTo8th.click();


        //Отработка алерта
        ChromeForHomework1.get("https://the-internet.herokuapp.com/");
        WebElement linkToAlerts = ChromeForHomework1.findElement(By.linkText("JavaScript Alerts"));
        linkToAlerts.click();

        WebElement jsAlert = ChromeForHomework1.findElement(By.cssSelector("button[onclick^=\"jsAlert\"]"));
        jsAlert.click();

        Alert alert = ChromeForHomework1.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "I am a JS Alert");
        alert.accept();

        //Отработка алерта типа Confirm
        WebElement jsConfirm = ChromeForHomework1.findElement(By.cssSelector("button[onclick^=\"jsConfirm\"]"));
        jsConfirm.click();

        String alertText2 = alert.getText();
        Assert.assertEquals(alertText2, "I am a JS Confirm");
        alert.dismiss();

        //Отработка алерта типа Prompt
        WebElement jsPrompt = ChromeForHomework1.findElement(By.cssSelector("button[onclick^=\"jsPrompt\"]"));
        jsPrompt.click();

        String alertText3 = alert.getText();
        Assert.assertEquals(alertText3, "I am a JS prompt");
        alert.sendKeys("Ok");
        alert.accept();

        ChromeForHomework1.quit();

    }

}

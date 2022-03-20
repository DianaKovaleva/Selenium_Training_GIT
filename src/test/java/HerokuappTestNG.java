import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjectStaticForHerokuapp.AlertsPageHerokuapp;
import pageobjectStaticForHerokuapp.FramesPageHerokuapp;
import pageobjectStaticForHerokuapp.MainPageHerokuapp;

import static org.openqa.selenium.Keys.CONTROL;
import static org.openqa.selenium.Keys.DELETE;

public class HerokuappTestNG extends HerokuappTestNGTestBase{

    @Test
    public void click8thElement() {

        // Задание - кликнуть на 8ую ссылку
        //List<WebElement> links = driver.findElements(By.tagName("a"));
        //links.get(8).click();

        // Либо так:

        MainPageHerokuapp.clickLinkTo8th(webdriver);
    }

    @Test
    public void jsAlert() {
        MainPageHerokuapp.clickLinkToAlerts(webdriver);

        AlertsPageHerokuapp.clickJsAlertLink(webdriver);

        Alert alert = webdriver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "I am a JS Alert");
        alert.accept();
    }

    @Test
    public void confirmAlert() {
        MainPageHerokuapp.clickLinkToAlerts(webdriver);

        AlertsPageHerokuapp.clickConfirmAlertLink(webdriver);

        Alert alert = webdriver.switchTo().alert();
        String alertText2 = alert.getText();
        Assert.assertEquals(alertText2, "I am a JS Confirm");
        alert.dismiss();
    }

    @Test
    public void promptAlert() {
        MainPageHerokuapp.clickLinkToAlerts(webdriver);

        AlertsPageHerokuapp.clickPromptAlertLink(webdriver);

        Alert alert = webdriver.switchTo().alert();
        String alertText3 = alert.getText();
        Assert.assertEquals(alertText3, "I am a JS prompt");
        alert.sendKeys("Ok");
        alert.accept();
    }

    @Test
    public void framesTraining() {
        MainPageHerokuapp.clickLinkToFrames(webdriver);

        FramesPageHerokuapp.clickLinkToiFrame(webdriver);

        webdriver.switchTo().frame("mce_0_ifr");
        WebElement frameInput = webdriver.findElement(By.cssSelector(".mce-content-body"));
        frameInput.sendKeys(Keys.chord(CONTROL, "A"));
        frameInput.sendKeys(Keys.chord(DELETE));
        frameInput.sendKeys("Hello, I'm a text about nothing");
        webdriver.switchTo().defaultContent();
        FramesPageHerokuapp.clickCenterText(webdriver); // Проверим, что вышли из рамки

    }

}



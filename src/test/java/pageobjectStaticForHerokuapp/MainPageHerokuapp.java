package pageobjectStaticForHerokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPageHerokuapp {
    private static By linkTo8thLocator = By.linkText("Digest Authentication");
    private static By linkToAlertsLocator = By.cssSelector("a[href=\"/javascript_alerts\"]");
    public static By linkToFramesLocator = By.linkText("Frames");

    public static void clickLinkTo8th(WebDriver driver){
    driver.findElement(linkTo8thLocator).click();
    }

    public static void clickLinkToAlerts(WebDriver driver){
        driver.findElement(linkToAlertsLocator).click();
    }

    public static void clickLinkToFrames(WebDriver driver){
        driver.findElement(linkToFramesLocator).click();
    }
}
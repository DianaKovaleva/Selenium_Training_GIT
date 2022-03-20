package pageobjectStaticForHerokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FramesPageHerokuapp {
    private static By iFrameLinkLocator = By.linkText("iFrame");
    private static By centerTextLocator = By.cssSelector("[title=\"Align center\"]");

    public static void clickLinkToiFrame(WebDriver driver){
        driver.findElement(iFrameLinkLocator).click();
    }

    public static void clickCenterText(WebDriver driver){
        driver.findElement(centerTextLocator).click();
    }
}

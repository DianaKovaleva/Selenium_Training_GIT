package pageobjectStaticForHerokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertsPageHerokuapp {

    private static By jsAlertLinkLocator = By.cssSelector("button[onclick^=\"jsAlert\"]");
    private static By ConfirmAlertLinkLocator = By.cssSelector("button[onclick^=\"jsConfirm\"]");
    private static By PromptAlertLinkLocator = By.cssSelector("button[onclick^=\"jsPrompt\"]");

    public static void clickJsAlertLink(WebDriver driver){
        driver.findElement(jsAlertLinkLocator).click();
    }

    public static void clickConfirmAlertLink(WebDriver driver){
        driver.findElement(ConfirmAlertLinkLocator).click();
    }

    public static void clickPromptAlertLink(WebDriver driver){
        driver.findElement(PromptAlertLinkLocator).click();
    }


}

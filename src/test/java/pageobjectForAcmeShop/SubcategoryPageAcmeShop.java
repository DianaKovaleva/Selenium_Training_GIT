package pageobjectForAcmeShop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SubcategoryPageAcmeShop {
    private By nameSortingLocator = By.linkText("Name");
    private By priceSortingLocator = By.linkText("Price");
    private By greenDuckNameLocator = By.cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/green-duck-p-2\"] .name");
    private By yellowDuckNameLocator = By.cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1\"] .name");
    private By pinkDuckNameLocator = By.cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/rozovaya-utochka-p-6\"] .name");
    private By greenDuckPriceLocator = By.cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/green-duck-p-2\"] span[class*=price]");
    private By yellowDuckPriceLocator = By.cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1\"] strong[class*=price]");
    private By pinkDuckPriceLocator = By.cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/rozovaya-utochka-p-6\"] span[class*=price]");
    private By greenDuckLabelLocator = By.cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/green-duck-p-2\"] div[class*=\"sticker\"]");
    private By yellowDuckLabelLocator = By.cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1\"] div[class*=\"sticker\"]");
    private By pinkDuckLabelLocator = By.cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/rozovaya-utochka-p-6\"] div[class*=\"sticker\"]");

    private final WebDriver driver;

    public SubcategoryPageAcmeShop(WebDriver driver) {
        this.driver = driver;
    }

    public void clickNameSortingButton(){
        driver.findElement(nameSortingLocator).click();
    }

    public void clickPriceSortingButton(){
        driver.findElement(priceSortingLocator).click();
    }

    public String getTextOfGreenDuckName() {
        return driver.findElement(greenDuckNameLocator).getText();
    }
    public String getTextOfYellowDuckName() {
        return driver.findElement(yellowDuckNameLocator).getText();
    }
    public String getTextOfPinkDuckName() {
        return driver.findElement(pinkDuckNameLocator).getText();
    }

    public String getTextOfGreenDuckPrice() {
        String greenPriceString = driver.findElement(greenDuckPriceLocator).getText();
        String greenPriceTruncated = greenPriceString.substring(0, greenPriceString.length() - 2); // обрезаем пробел и значок валюты
        return greenPriceTruncated;
    }
    public String getTextOfYellowDuckPrice() {
        String yellowPriceString = driver.findElement(yellowDuckPriceLocator).getText();
        String yellowPriceTruncated = yellowPriceString.substring(0, yellowPriceString.length() - 2); // обрезаем пробел и значок валюты
        return yellowPriceTruncated;
    }
    public String getTextOfPinkDuckPrice() {
        String pinkPriceString = driver.findElement(pinkDuckPriceLocator).getText();
        String pinkPriceTruncated = pinkPriceString.substring(0, pinkPriceString.length() - 2); // обрезаем пробел и значок валюты
        return pinkPriceTruncated;
    }

    public String getTextOfGreenDuckLabel() {
        return driver.findElement(greenDuckLabelLocator).getText();
    }
    public String getTextOfYellowDuckLabel() {
        return driver.findElement(yellowDuckLabelLocator).getText();
    }
    public String getTextOfPinkDuckLabel() {
        return driver.findElement(pinkDuckLabelLocator).getText();
    }

}

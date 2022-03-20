package pageobjectForAcmeShop;
import HelperForAcmeShop.Locators;
import org.openqa.selenium.*;
import static org.openqa.selenium.Keys.ENTER;

public class HomePageAcmeShop {
    private By homeHeaderLocator = Locators.getLocator("HomePageAcmeShop.homeHeader");
    private By rubberDucksHeaderLocator = Locators.getLocator("HomePageAcmeShop.rubberDucksHeader");
    private By deliveryInformationHeaderLocator = Locators.getLocator("HomePageAcmeShop.deliveryInformationHeader");
    private By termsConditionsHeaderLocator = Locators.getLocator("HomePageAcmeShop.termsConditionsHeader");
    private By redDuckLocator = Locators.getLocator("HomePageAcmeShop.redDuck");
    private By subcategoryLocator = Locators.getLocator("HomePageAcmeShop.subcategory");
    private By queryLocator = Locators.getLocator("HomePageAcmeShop.query");

    private final WebDriver driver;

    public HomePageAcmeShop(WebDriver driver) throws Exception {
        this.driver = driver;
    }

    public void search(String searchInfo){
        driver.findElement(queryLocator).sendKeys(searchInfo);
        driver.findElement(queryLocator).sendKeys(Keys.chord(ENTER));
    }

    public void clickHomeHeader(){
        driver.findElement(homeHeaderLocator).click();
    }

    public void clickRubberDucksHeader(){
        driver.findElement(rubberDucksHeaderLocator).click();
    }

    public void clickDeliveryInformationHeader(){
        driver.findElement(deliveryInformationHeaderLocator).click();
    }

    public void clickTermsConditions(){
        driver.findElement(termsConditionsHeaderLocator).click();
    }

    public void clickSubcategory(){
        driver.findElement(subcategoryLocator).click();
    }

    public void clickRedDuck(){
        driver.findElement(redDuckLocator).click();
    }

    public String getTitle(){
        return driver.getTitle();
    }




}

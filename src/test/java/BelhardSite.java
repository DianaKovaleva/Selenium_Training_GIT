import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageFactoryForBelhard.EmploymentPageBelhard;
import pageFactoryForBelhard.HomePageBelhard;

public class BelhardSite extends BelhardSiteTestBase {

    @Test //Работа с новыми окнами браузера
    public void newWindow() {
        String initialTab = webdriver.getWindowHandle();
        HomePageBelhard homePage = PageFactory.initElements(webdriver, HomePageBelhard.class);
        EmploymentPageBelhard employmentPage = PageFactory.initElements(webdriver, EmploymentPageBelhard.class);

        homePage.clickEmploymentButton();

        employmentPage.clickTestButton();

        String newTab = webdriver.getWindowHandles().toArray()[1].toString();
        webdriver.switchTo().window(newTab);

        WebElement allCoursesButton = webdriver.findElement(By.cssSelector(".t142__text"));
        allCoursesButton.click();
        webdriver.close();

        webdriver.switchTo().window(initialTab);

        String initialTabTitle = webdriver.getTitle();
        Assert.assertEquals(initialTabTitle, "Трудоустройство Академии BELHARD"); //первая проверка, что попали на первоначальную страницу

        Assert.assertEquals(employmentPage.getTextOfTitle(), "Трудоустройство"); //вторая проверка, что попали на первоначальную страницу

    }

}

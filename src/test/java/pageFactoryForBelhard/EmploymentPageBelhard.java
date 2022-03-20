package pageFactoryForBelhard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmploymentPageBelhard {
    @FindBy(css = ("a[href=\"https://belhard.academy/proftest\"]"))
    WebElement proftestButton;

    @FindBy(css = "h1.tn-atom")
    WebElement titleOnPage;

    public void clickTestButton(){
        proftestButton.click();
    }


    public String getTextOfTitle() {
    return titleOnPage.getText();
    }
}

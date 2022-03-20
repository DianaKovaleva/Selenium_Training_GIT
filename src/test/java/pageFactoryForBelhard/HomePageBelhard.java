package pageFactoryForBelhard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageBelhard {
    @FindBy(css = "a[href=\"/employment\"]")
    private WebElement employmentButton;

public void clickEmploymentButton(){
    employmentButton.click();
}
}

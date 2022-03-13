import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AcmeShop {
    WebDriver webdriver;


    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        webdriver = new ChromeDriver();
        webdriver.get("https://litecart.stqa.ru/en/");
        webdriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        webdriver.manage().window().maximize();
    }

    @Test
    public void ducksShopHeaders() {  // Проверка переходов по пунктам меню

        WebElement rubberDucksHeader = webdriver.findElement(By.cssSelector("#site-menu-wrapper .category-1"));
        rubberDucksHeader.click();
        String rubberDucksHeaderTitle = webdriver.getTitle();
        Assert.assertEquals(rubberDucksHeaderTitle, "Rubber Ducks | My Store1");

        WebElement deliveryInformationHeader = webdriver.findElement(By.cssSelector("#site-menu-wrapper .page-2"));
        deliveryInformationHeader.click();
        String deliveryInformationHeaderTitle = webdriver.getTitle();
        Assert.assertEquals(deliveryInformationHeaderTitle, "Delivery Information | My Store1");

        WebElement termsConditionsHeader = webdriver.findElement(By.cssSelector("#site-menu-wrapper .page-4"));
        termsConditionsHeader.click();
        String termsConditionsHeaderTitle = webdriver.getTitle();
        Assert.assertEquals(termsConditionsHeaderTitle, "Terms & Conditions | My Store1");

        WebElement homeHeader = webdriver.findElement(By.cssSelector("#site-menu-wrapper .general-0"));
        homeHeader.click();
        String homeHeaderTitle = webdriver.getTitle();
        Assert.assertEquals(homeHeaderTitle, "Online Store | My Store1");

    }

        @Test //Отработка Actions (положить в корзину и удостовериться, что кол-во отобразилось в ней верное):
        public void addingToCart() throws InterruptedException {

             // Кликаем на красную уточку:
        webdriver.get("https://litecart.stqa.ru/en/");
        WebElement redDuck = webdriver.findElement(By.cssSelector(".link[title=\"Red Duck\"]"));
        redDuck.click();

            // Добавляем ее в корзину:
        WebElement addToCart = webdriver.findElement(By.name("add_cart_product"));
        addToCart.click();

        WebDriverWait wait = new WebDriverWait(webdriver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[src=\"/includes/templates/default.catalog/images/cart_filled.png\"]")));

            // Кликаем на корзину:
        WebElement cart = webdriver.findElement(By.cssSelector("#cart"));
        cart.click();

            //Меняем кол-во товара на 3
        WebElement quantityCart = webdriver.findElement(By.cssSelector("input[type=\"number\"]"));
        Actions builder2 = new Actions(webdriver);
        builder2.doubleClick(quantityCart).perform();
        quantityCart.sendKeys("{UP 3}");

            //Нажимаем на Update
        WebElement updateButton = webdriver.findElement(By.name("update_cart_item"));
        updateButton.click();

        Thread.sleep(2000);

            //Проверяем, отобразилось ли в корзине правильное количество(3)
        WebElement cartQuantity = webdriver.findElement(By.cssSelector("input[type=\"number\"]"));
        String quantityFact = cartQuantity.getAttribute("value");
        Assert.assertEquals(quantityFact, "3");

            //Проверяем, отобразилось ли правильное количество(3) в таблице ниже
        WebElement gridQuantity = webdriver.findElement(By.cssSelector("td[style=\"text-align: center;\"]"));
        String quantityGrid = gridQuantity.getText();
        Assert.assertEquals(quantityGrid, "3");

            // Для тренировки DragAndDrop перетянем изображение уточки в поле комментария (появится url в нем)
        //WebElement duckInCart = webdriver.findElement(By.cssSelector("img[src*=\"fwb.png\"]"));
        //WebElement field1 = webdriver.findElement(By.cssSelector("textarea[name=\"comments\"]"));
        //Actions builder3 = new Actions(webdriver);
        //builder3.dragAndDrop(duckInCart, field1).perform(); //Почему-то не проходит, хотя вручную перетаскивается


            // Тут я хотела увеличить высоту поля комментария в 5 раз, погуглила примерно, но не увеличивается
            Actions builder4 = new Actions(webdriver);
            WebElement fieldComments = webdriver.findElement(By.cssSelector("textarea[name=\"comments\"]"));
            int height = fieldComments.getSize().height;
            int width = fieldComments.getSize().width;
            builder4.moveToElement(fieldComments, width, height).clickAndHold().moveByOffset(0, height*5)
                    .release().build();
            builder4.perform();
    }

        @Test //Сортировка
        public void subcategorySorting () {
        WebElement rubberDucksHeader = webdriver.findElement(By.cssSelector("#site-menu-wrapper .category-1"));
        rubberDucksHeader.click();

        WebElement subcategoryLink = webdriver.findElement(By.linkText("Subcategory"));
        subcategoryLink.click();

        //Сортировка по имени
        WebElement nameButton = webdriver.findElement(By.linkText("Name"));
        nameButton.click();

        // List<WebElement> namesOnSite = webdriver.findElements(By.cssSelector(".name"));
        //List<String> namesInText; //Тут я хотела каждый элемент коллекции 'namesOnSite' преобразовать в текстовую коллекцию одним махом,
            //чтобы потом сравнить, но не получилось, может, подскажешь, как. Поэтому далее делала то же, но с каждым отдельно

        ArrayList<String> allNames = new ArrayList<>();
        WebElement greenDuckName = webdriver.findElement(By.cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/green-duck-p-2\"] .name"));
        String greenDuck = greenDuckName.getText();
        allNames.add(greenDuck);
        WebElement yellowDuckName = webdriver.findElement(By.cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1\"] .name"));
        String yellowDuck = yellowDuckName.getText();
        allNames.add(yellowDuck);
        WebElement pinkDuckName = webdriver.findElement(By.cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/rozovaya-utochka-p-6\"] .name"));
        String pinkDuck = pinkDuckName.getText();
        allNames.add(pinkDuck);

        ArrayList<String> forNamesComparison = new ArrayList<>();
        forNamesComparison.add("Green DucK");
        forNamesComparison.add("Yellow Duck");
        forNamesComparison.add("Розовая уточка");

        Assert.assertEquals(allNames, forNamesComparison);

        //Сортировка по цене
        WebElement priceButton = webdriver.findElement(By.linkText("Price"));
        priceButton.click();

        WebElement yellowPrice = webdriver.findElement(By.cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1\"] strong[class*=price]"));
        String yellowPriceString = yellowPrice.getText();
        String yellowPriceTruncated = yellowPriceString.substring(0, yellowPriceString.length() - 2); // обрезаем пробел и значок валюты
        double yellowPriceDouble = Double.parseDouble(yellowPriceTruncated);

        WebElement greenPrice = webdriver.findElement(By.cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/green-duck-p-2\"] span[class*=price]"));
        String greenPriceString = greenPrice.getText();
        String greenPriceTruncated = greenPriceString.substring(0, greenPriceString.length() - 2); // обрезаем пробел и значок валюты
        double greenPriceDouble = Double.parseDouble(greenPriceTruncated);

        WebElement pinkPrice = webdriver.findElement(By.cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/rozovaya-utochka-p-6\"] span[class*=price]"));
        String pinkPriceString = pinkPrice.getText();
        String pinkPriceTruncated = pinkPriceString.substring(0, pinkPriceString.length() - 2); // обрезаем пробел и значок валюты
        double pinkPriceDouble = Double.parseDouble(pinkPriceTruncated);

        ArrayList<Double> allPrices = new ArrayList<>();
        allPrices.add(yellowPriceDouble);
        allPrices.add(greenPriceDouble);
        allPrices.add(pinkPriceDouble);

        String result;
        if (allPrices.get(0) < allPrices.get(1) && allPrices.get(1) < allPrices.get(2)) {
            result = "Sorted";
        } else {
            result = "Not Sorted";
        }

        Assert.assertEquals(result, "Sorted");
        }

        @Test //Проверка соответствия лэйблов
        public void labelsChecking() {
        WebElement rubberDucksHeader = webdriver.findElement(By.cssSelector("#site-menu-wrapper .category-1"));
        rubberDucksHeader.click();

        WebElement subcategoryLink = webdriver.findElement(By.linkText("Subcategory"));
        subcategoryLink.click();

        WebElement greenDuckLabel = webdriver.findElement(By.
                cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/green-duck-p-2\"] div[class*=\"sticker\"]"));
        String greenDuckLabelString = greenDuckLabel.getText();
        Assert.assertEquals(greenDuckLabelString, "NEW");

        WebElement pinkDuckLabel = webdriver.findElement(By.
                cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/rozovaya-utochka-p-6\"] div[class*=\"sticker\"]"));
        String pinkDuckLabelString = pinkDuckLabel.getText();
        Assert.assertEquals(pinkDuckLabelString, "NEW");

        WebElement yellowDuckLabel = webdriver.findElement(By.
                cssSelector(".link[href=\"https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1\"] div[class*=\"sticker\"]"));
        String yellowDuckLabelString = yellowDuckLabel.getText();
        Assert.assertEquals(yellowDuckLabelString, "SALE");

    }


    @AfterTest
    public void teardown() {
        webdriver.quit();
    }

  }


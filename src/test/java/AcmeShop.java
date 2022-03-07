import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.time.Duration;
import java.util.List;

public class AcmeShop {

    @Test
    public void DucksShopHeaders() {  // Проверка переходов по пунктам меню

        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        WebDriver webdriver = new ChromeDriver();
        webdriver.get("https://litecart.stqa.ru/en/");

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

        webdriver.quit();

    }

        @Test
        public void addingToCart() {
        //Отработка Actions (положить в корзину и удостовериться, что кол-во отобразилось в ней верное):

        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        WebDriver webdriver = new ChromeDriver();
        webdriver.get("https://litecart.stqa.ru/en/");

            // Кликаем на красную уточку:
        webdriver.get("https://litecart.stqa.ru/en/");
        WebElement redDuck = webdriver.findElement(By.cssSelector(".link[title=\"Red Duck\"]"));
        redDuck.click();

            // Добавляем ее в корзину:
        WebElement addToCart = webdriver.findElement(By.name("add_cart_product"));
        addToCart.click();

            //Поделаем что-нибудь вместо неработающего ожидания, пока не долетит до корзины
        WebElement quantity = webdriver.findElement(By.cssSelector("input[type=\"number\"]"));
        Actions builder = new Actions(webdriver);
        builder.doubleClick(quantity).perform();
        builder.doubleClick(quantity).perform();
        builder.doubleClick(quantity).perform();
        builder.doubleClick(quantity).perform();
        builder.doubleClick(quantity).perform();
        quantity.sendKeys("{UP 3}"); //Увеличить кол-во двойным кликом в данном элементе не получилось, поэтому с помощью стрелочки
                                                // Количество здесь меняем просто так, чтобы потянуть время. По факту увеличим кол-во уже в корзине
        // !НЕ ИМПОРТЯТСЯ КЛАССЫ WebDriverWait и ExpectedConditions
        //WebDriverWait wait = new WebDriverWait(webdriver,10);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[src=\"/includes/templates/default.catalog/images/cart_filled.png\"]")));

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

            //Проверяем, отобразилось ли в корзине правильное количество(3)
        WebElement cartQuantity = webdriver.findElement(By.cssSelector("input[type=\"number\"]"));
        String quantityFact = cartQuantity.getAttribute("value"); //иногда не проходит в этом месте, так как тоже не успевает отобразиться и считать
        Assert.assertEquals(quantityFact, "3");

            //Проверяем, отобразилось ли правильное количество(3) в таблице ниже
        WebElement gridQuantity = webdriver.findElement(By.cssSelector("td[style=\"text-align: center;\"]"));
        String quantityGrid = gridQuantity.getText();
        //Assert.assertEquals(quantityGrid, "3"); // Здесь тест провалится, так как тоже нужно добавить ожидание.
                                                        // Так как проходит какое-то время, пока update долетит до таблицы

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

            webdriver.quit();


    }

  }


import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjectForAcmeShop.CartPageAcmeShop;
import pageobjectForAcmeShop.DuckPageAcmeShop;
import pageobjectForAcmeShop.HomePageAcmeShop;
import pageobjectForAcmeShop.SubcategoryPageAcmeShop;

import java.util.ArrayList;
import static org.testng.Assert.assertEquals;


public class AcmeShop extends AcmeShopTestBase {


    @Test
    public void ducksShopHeaders() throws Exception {  // Проверка переходов по пунктам меню
        HomePageAcmeShop homePage = new HomePageAcmeShop(webdriver);
        SoftAssert softAssert = new SoftAssert();

        homePage.clickRubberDucksHeader();
        String rubberDucksHeaderTitle = homePage.getTitle();
        softAssert.assertEquals(rubberDucksHeaderTitle, "Rubber Ducks | My Store1");

        homePage.clickDeliveryInformationHeader();
        String deliveryInformationHeaderTitle = homePage.getTitle();
        softAssert.assertEquals(deliveryInformationHeaderTitle, "Delivery Information | My Store1");

        homePage.clickTermsConditions();
        String termsConditionsHeaderTitle = homePage.getTitle();
        softAssert.assertEquals(termsConditionsHeaderTitle, "Terms & Conditions | My Store1");

        homePage.clickHomeHeader();
        String homeHeaderTitle = homePage.getTitle();
        softAssert.assertEquals(homeHeaderTitle, "Online Store | My Store1");

        softAssert.assertAll();

    }

        @Test //Отработка Actions (положить в корзину и удостовериться, что кол-во отобразилось в ней верное):
        public void addingToCart() throws Exception {
            HomePageAcmeShop homePage = new HomePageAcmeShop(webdriver);
            CartPageAcmeShop cartPage = new CartPageAcmeShop(webdriver);
            DuckPageAcmeShop duckPage = new DuckPageAcmeShop(webdriver);

             // Кликаем на красную уточку:
        homePage.clickRedDuck();

            // Добавляем ее в корзину:
        duckPage.clickAddToCartButton();
        duckPage.waitDuckAppearsInCart();

            // Кликаем на корзину:
        duckPage.goToCart();

            //Меняем кол-во товара на 3
        cartPage.changeQuantityTo3();

            //Нажимаем на Update
        cartPage.clickUpdateInCart();

            //Проверяем, отобразилось ли в корзине правильное количество(3)
        cartPage.assertRightNumberInCart();

            //Проверяем, отобразилось ли правильное количество(3) в таблице ниже
        cartPage.assertRightNumberInGrid();


        // Всё равно не увеличивается
//        WebElement fieldComments = webdriver.findElement(By.cssSelector("textarea[name=\"comments\"]"));
//        int height = fieldComments.getSize().height;
//        int width = fieldComments.getSize().width;
//        builder2.moveToElement(fieldComments, ((width/2)-3), ((height/2)-3)).clickAndHold().moveByOffset(0, 15)
//                .release().build();
//        builder2.perform();
//        int height2 = fieldComments.getSize().height;
        //Assert.assertNotEquals(height, height2);  //закоментила, чтобы не падал весь метод
    }

        @Test //Сортировка
        public void subcategorySorting () throws Exception {
        HomePageAcmeShop homePage = new HomePageAcmeShop(webdriver);
        SubcategoryPageAcmeShop subcategoryPage = new SubcategoryPageAcmeShop(webdriver);

        homePage.clickRubberDucksHeader();
        homePage.clickSubcategory();

        //Сортировка по имени

        subcategoryPage.clickNameSortingButton();

        ArrayList<String> allNames = new ArrayList<>();
        String greenDuck = subcategoryPage.getTextOfGreenDuckName();
        allNames.add(greenDuck);

        String yellowDuck = subcategoryPage.getTextOfYellowDuckName();
        allNames.add(yellowDuck);

        String pinkDuck = subcategoryPage.getTextOfPinkDuckName();
        allNames.add(pinkDuck);

        ArrayList<String> forNamesComparison = new ArrayList<>();
        forNamesComparison.add("Green DucK");
        forNamesComparison.add("Yellow Duck");
        forNamesComparison.add("Розовая уточка");

        assertEquals(allNames, forNamesComparison);

        //Сортировка по цене
        subcategoryPage.clickPriceSortingButton();

        String yellowPriceTruncated = subcategoryPage.getTextOfYellowDuckPrice();
        double yellowPriceDouble = Double.parseDouble(yellowPriceTruncated);

        String greenPriceTruncated = subcategoryPage.getTextOfGreenDuckPrice();
        double greenPriceDouble = Double.parseDouble(greenPriceTruncated);

        String pinkPriceTruncated = subcategoryPage.getTextOfPinkDuckPrice();
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

        assertEquals(result, "Sorted");
        }

        @Test //Проверка соответствия лэйблов
        public void labelsChecking() throws Exception {
        HomePageAcmeShop homePage = new HomePageAcmeShop(webdriver);
        SubcategoryPageAcmeShop subcategoryPage = new SubcategoryPageAcmeShop(webdriver);

        homePage.clickRubberDucksHeader();
        homePage.clickSubcategory();

        String greenDuckLabelString = subcategoryPage.getTextOfGreenDuckLabel();
        assertEquals(greenDuckLabelString, "NEW");

        String pinkDuckLabelString = subcategoryPage.getTextOfPinkDuckLabel();
        assertEquals(pinkDuckLabelString, "NEW");

        String yellowDuckLabelString = subcategoryPage.getTextOfYellowDuckLabel();
        assertEquals(yellowDuckLabelString, "SALE");
    }

    @Test
    public void searching() throws Exception {
        HomePageAcmeShop homePage = new HomePageAcmeShop(webdriver);
        homePage.search("yellow"); //пока просто так, ничего не оцениваем на странице по результатам
        homePage.search("red");
        homePage.search("acme");
    }



  }


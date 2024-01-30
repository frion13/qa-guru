package junit.homework1.annotation;


import junit.homework1.annotation.data.Products;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Параметризованный тест c Enum Классом")
public class ShoppingCartWithEnumTest {


    @BeforeEach
    void setup() {
        open("https://www.saucedemo.com/inventory.html");
        $("#user-name").setValue("standard_user");
        $("#password").setValue("secret_sauce").pressEnter().pressEnter();
    }

    @AfterEach
    void close() {
        closeWindow();
    }


    @ParameterizedTest
    @EnumSource(Products.class)
    void cartShouldHaveExpectedInfo(Products products) {
        $(byText(products.title)).click();
        $(".btn_inventory").click();
        $(".shopping_cart_link").click();
        $(".cart_list").shouldHave(text(products.title))
                .shouldHave(text(products.price))
                .shouldHave(text(products.description));

    }
}

package junit.homework1.annotation;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Параметризованный тест для домашнего задания")
public class ShoppingCartWithValueSoursTest {


    @BeforeEach
    void setup(){
        open("https://www.saucedemo.com/inventory.html");
        $("#user-name").setValue("standard_user");
        $("#password").setValue("secret_sauce").pressEnter().pressEnter();
    }

    @AfterEach
    void close(){
        closeWindow();
    }

    @ValueSource(strings = {
            "Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt"
    })
    @ParameterizedTest(name = "Поиск товара {0}")
    void buttonShouldHaveTextRemove(String value) {
        $(byText(value)).click();
        $(".btn_inventory").click();
        $(".btn_inventory").shouldHave(text("Remove"));

    }
}

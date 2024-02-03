package junit.homework1.annotation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Параметризованный тест c CSV файлом")
public class ShoppingCartWithCsvFileTest {


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
    @CsvFileSource(resources = "/testdata/cartShouldHaveExpectedInfo.csv", delimiter = '|')
    void cartShouldHaveExpectedInfo(String title, String price, String description) {
        $(byText(title)).click();
        $(".btn_inventory").click();
        $(".shopping_cart_link").click();
        $(".cart_list").shouldHave(text(title)).shouldHave(text(price)).shouldHave(text(description));

    }
}

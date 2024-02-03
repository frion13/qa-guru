package junit.lesson1.annotation;


import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WebTest {

    @BeforeEach
    void setup(){
        open("https://ya.ru/");
    }

    @ValueSource(strings = {
            "Selenide", "JUnit 5"
    }) //записываются передаваемые значения (может быть только один параметр)
    @ParameterizedTest(name = "Возвращается не пустой список карточек на запрос {0}") //параметризированный тест, передаются параметры
    @Tag("Пишется приоритет теста Blocker")
    void serchResultShouldNotBeEmpty(String searchQuery){
     $("input#text").setValue(searchQuery).pressEnter();
     $$("li.serp-item").shouldBe(CollectionCondition.sizeGreaterThan(0));
    }

    @CsvSource(value = {
            "Selenide | https://ru.selenide.org/",
            "Junit 5 | https://junit.org/junit5/docs/current/user-guide/"
    }, delimiter = '|') //несколько параметров с разделителем | или просто запятая вместо символа
    @CsvFileSource(resources = "/testdata/serchResultShouldContainExpectedUrl.csv") //подключить файл
    @ParameterizedTest(name = "В первой карточке должна быть ссылка {1} для поискового запроса {0}") //параметризированный тест, передаются параметры
    @Tag("Пишется приоритет теста Blocker")
    void serchResultShouldContainExpectedUrl(String searchQuery, String expectedLink){
        $("input#text").setValue(searchQuery).pressEnter();
        $("li.serp-item").shouldHave(text(expectedLink));
    }



//    public static void main(String[] args) {
//        System.out.println(Languege.RU.description);
//        System.out.println(Languege.EN.description);
//    }




}

package junit.lesson1.annotation;


import junit.lesson1.annotation.data.Languege;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SelenideWebTest {
    @BeforeEach
    void setup(){
        open("https://ru.selenide.org/");
    }


    @ParameterizedTest
    @EnumSource(Languege.class)
    void selenideSiteShouldDisplayCorrectText(Languege languege){
        $$("#languages a").find(text(languege.name())).click();
        $("h3").shouldHave(text(languege.description));

    }

    static Stream<Arguments> selenideSiteShouldDisplayCorrectButtons(){
        return Stream.of(
                Arguments.of(Languege.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")),
                Arguments.of(Languege.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы")));
    }
    @ParameterizedTest
    @MethodSource
    void selenideSiteShouldDisplayCorrectButtons(Languege languege, List<String> expectedButtons) {
        $$("#languages a").find(text(languege.name())).click();
        $$(".main-menu-pages a").filter(visible)
                .shouldHave(texts(expectedButtons));

    }


}

package testGithubSelenide;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class SelenideReposytorySearch {
    @Test
    void shouldFindSelenideRepositoryAtTheTop(){
        // открыть github
        open("https://github.com/");
        //ввести в поле поиска selenide и нажать enter
        $("[data-target='qbsearch-input.inputButton']").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        //кликнуть на первый репозиторий из списка найденных
        $$("[data-testid='results-list']").first().$("a").click();
        //проыерка : заголовок selenide/selenide
        $("#repository-container-header").shouldHave(text("selenide / selenide"));



        //ARRANGE - подготовить
        //ACT - действие
        //ASSERT -проверить

    }
}

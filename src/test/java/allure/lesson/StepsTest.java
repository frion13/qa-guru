package allure.lesson;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {
    private static final String REPOSYTORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;
    @Test
    public void testLambdaStep(){
        //лямбда функция для класса в котором только 1 метод
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
                    open("https://github.com");
                });
        step("Ищем репозиторий" + REPOSYTORY, () -> {
            $(".search-input-container").click();
            $("#query-builder-test").sendKeys(REPOSYTORY);
            $("#query-builder-test").submit();
        });
        step("Кликаем по ссылке репозитория"+ REPOSYTORY, () ->{
            $(linkText(REPOSYTORY)).click();
        });
        step("Открываем таб Issue", () -> {
            $("#issues-tab").click();
        });
        step("проверяем наличие Issue с номером"+ ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedStep(){
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSYTORY);
        steps.clickOnRepositoryLink(REPOSYTORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(80);

    }
}


//запуск отчета в gradle --Tasks -- verification --allureServe

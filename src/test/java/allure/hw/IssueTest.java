package allure.hw;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.WebStepsPage;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@DisplayName("Проверка корректности имени Issue")
public class IssueTest {
    private static final String REPOSYTORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;
    @Test
    @Owner("tl")
    @Feature("Issue page")
    @Story("Issues")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка корректности имени Issue на странице Issue с помощью чистого Selenide")
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");
        $(".search-input-container").click();
        $("#query-builder-test").sendKeys(REPOSYTORY);
        $("#query-builder-test").submit();
        $(linkText(REPOSYTORY)).click();
        $("#issues-tab").click();
        $(withText("#" + ISSUE)).should(Condition.exist);
    }


    @Test
    @Owner("tarabne")
    @Feature("Issue page")
    @Story("Issues")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка корректности имени Issue на странице Issue с помощью lambda-функций")
    public void testLambdaStep(){
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
    @Owner("tarabne")
    @Feature("Issue page")
    @Story("Issues")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка корректности имени Issue на странице Issue с помощью аннотации @Step")
    public void testAnnotatedStep(){
        WebStepsPage steps = new WebStepsPage();
        steps.openMainPage();
        steps.searchForRepository(REPOSYTORY);
        steps.clickOnRepositoryLink(REPOSYTORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(80);

    }
}


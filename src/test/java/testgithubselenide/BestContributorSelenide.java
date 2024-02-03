package testgithubselenide;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BestContributorSelenide {
    @Test
    void firstConrtibutor(){
        open("https://github.com/selenide/selenide");
         $("div.Layout-sidebar").$(byText("Contributors"))
                 .closest("h2").sibling(0).$$("li").first().hover();
         $$(".Popover").findBy(visible).shouldHave(text("Andrei Solntsev"));
         sleep(5000);
    }
}


//setTimeout(function() {debugger}, 4000) функция для остановки браузера, чтобы смотреть попап окно
package testGithubSelenide;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Homework {
    @Test
    void testSoftAssertions(){
        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $(".markdown-body").shouldHave(text("Soft assertions"));
        sleep(5000);

    }

    @Test
    void testJunitExampleCode(){
        open("https://github.com/selenide/selenide/wiki/SoftAssertions");
        $("body").shouldHave(text("Using JUnit5 extend test class:"));
    }

}

package testGithubSelenide;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Homework {
    @Test
    void testSoftAssertions(){
        open("https://github.com/selenide/selenide/");
        $("#wiki-tab").click();
        $("button.Link--muted.js-wiki-more-pages-link").click();
        $(" .wiki-pages-box").shouldHave(text("SoftAssertions")).shouldBe(visible);

    }

    @Test
    void testJunitExampleCode(){
        open("https://github.com/selenide/selenide/wiki/SoftAssertions");
        $("body").shouldHave(text("""
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }"""));
    }

}

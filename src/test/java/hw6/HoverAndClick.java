package hw6;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HoverAndClick {
    @Test
    void HoverAndClickTest(){
        open("https://github.com/");
        $("nav").$(byText("Solutions")).hover();
        $(byTagAndText("a", "Enterprise")).click();
        $("body").shouldHave(text("""
The AI-powered developer platform."""));
    }
}

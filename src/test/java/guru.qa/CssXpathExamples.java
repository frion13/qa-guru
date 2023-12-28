package guru.qa;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CssXpathExamples {
    void CssXpathExamples(){
        $("[data-testId=email]").setValue("Some text");
        $(by("data-testId", "email")).setValue("Some text");
        $x("//*[data-testId='email']").setValue("Some text");
        $(byId("email")).setValue("some text"); //поиск по id (selenide)
        $(By.id("email")).setValue("some text"); //поиск по id (selenium)
        $("#email").setValue("some text"); //поиск по id (selenide)

         //name поиск
        $("[name=email]").setValue("Some text");
        $(by("name", "email")).setValue("Some text");
        $x("//*[name='email']").setValue("Some text");
        $(byName("email")).setValue("some text"); //поиск по id (selenide)
        $(By.name("email")).setValue("some text"); //поиск по id (selenium)

        // поиск  по классу
        $("[class=login_form_input_box]").setValue("Some text");
        $(by("class", "login_form_input_box")).setValue("Some text");
        $x("//*[class='login_form_input_box']").setValue("Some text");
        $(byClassName("login_form_input_box")).setValue("some text"); //поиск по id (selenide)
        $(By.className("login_form_input_box")).setValue("some text"); //поиск по id (selenium)
        $(".login_form_input_box").setValue("some text");


        //конкретизировать
        $("[class=inputText][class=login_form_input_box]").setValue("Some text");
        $("input[class=inputText][class=login_form_input_box]").setValue("Some text");
        $x("input[@class='class=inputText'][@class='login_form_input_box']").setValue("Some text");
        $("input.login_form_input_box").setValue("Some text");
        $("input.inpetText.login_form_input_box").setValue("Some text");


        //<div class ="inputtext">
        //<input type = "email" class = "login_form_input_box"
        //</div>
        $(".login_form_input_box").setValue("some text");
        $("inputtext .login_form_input_box").setValue("some text");
        $("div.inputtext input.login_form_input_box").setValue("some text");
        $(".inputtext").$(".login_form_input_box").setValue("some text");
        $("div.inputtext").$("input.login_form_input_box").setValue("some text");


        //<div>hello qa.guru</div>
        $(byText("hello qa.guru"));
        $(withText("qa.guru"));
        $x("//*[contains(text(),'hello qa.guru'))]");













    }
}

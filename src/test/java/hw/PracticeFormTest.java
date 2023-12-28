package hw;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class PracticeFormTest {
    @BeforeAll
    static void setup(){
        open("https://demoqa.com/automation-practice-form");
        Configuration.holdBrowserOpen = false;
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }


    @Test
    void practiceFormTest(){
        SelenideElement month = $(".react-datepicker__month-select");
        SelenideElement year = $(".react-datepicker__year-select");

        $("#firstName").setValue("Иван");
        $("#lastName").setValue("Иванов");
        $("#userEmail").setValue("ivan@ivanov.ru");
        $(".custom-control-label"). shouldHave(text("Male")).click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        month.click();
        month.selectOption("April");
        year.click();
        year.selectOption("1990");
        $$(".react-datepicker__day").find(text("16")).click();
        $("#subjectsInput").setValue("math").pressEnter();
        $$(".custom-control-label").findBy(text("Sports")).click();
        $$(".custom-control-label").findBy(text("Reading")).click();
        $$(".custom-control-label").findBy(text("Music")).click();
        $("#uploadPicture").uploadFromClasspath("img.jpeg");
        $("#currentAddress").setValue("Москва, ул Тверская 56");
        $("#react-select-3-input").setValue("ncr").pressEnter();
        $("#react-select-4-input").setValue("Gurgaon").pressEnter();
        $("#userForm").submit();
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $$("tbody tr").shouldHave(
                texts(
                        "Student Name Иван Иванов",
                        "Student Email ivan@ivanov.ru",
                        "Gender Male",
                        "Mobile 1234567890",
                        "Date of Birth 16 April,1990",
                        "Subjects Maths",
                        "Hobbies Sports, Reading, Music",
                        "Picture img.jpeg",
                        "Address Москва, ул Тверская 56",
                        "State and City NCR Gurgaon"
                )
        );
    }
}

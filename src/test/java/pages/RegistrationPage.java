package pages;


import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumber = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            upploadPictureInput = $("#uploadPicture"),
            currentAddress = $("#currentAddress");


    CalendarComponent calendarComponent = new CalendarComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;

    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumber.setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }


    public RegistrationPage setSubject(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage setHobbies(String value1, String value2, String value3) {
        hobbiesInput.$(byText(value1)).click();
        hobbiesInput.$(byText(value2)).click();
        hobbiesInput.$(byText(value3)).click();

        return this;
    }

    public RegistrationPage uploadPicture(String value) {
        upploadPictureInput.uploadFromClasspath(value);

        return this;
    }

    public RegistrationPage setCurrentAdrress(String value) {
        currentAddress.setValue(value);

        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        return this;
    }

    public RegistrationPage submit() {
        $("#userForm").submit();

        return this;
    }


    public RegistrationPage checkResult(String value) {
        $$(".modal-header").shouldHave(texts("Thanks for submitting the form"));
        $$("tbody").shouldHave(texts(value));
        return this;
    }

    public RegistrationPage checkBorderColor() {
        firstNameInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        lastNameInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        return this;
    }


}






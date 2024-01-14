package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BoxPage {
    private final SelenideElement userNameInput = $("#userName"),
            userEmailInput = $("#userEmail"),
            currentAddressInput = $("#currentAddress"),
            permanentAddressInput = $("#permanentAddress"),
            userFormSubmit = $("#submit"),
            nameOutput = $("#name"),
            emailOutput = $("#email"),
            currentAddressOutput = $("p#currentAddress"),
            permanentAddressOutput = $("p#permanentAddress");


    public BoxPage openBoxPage() {
        open("/text-box");
        $(".main-header").shouldHave(text("Text Box"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public BoxPage setUserName(String value) {
        userNameInput.setValue(value);
        return this;
    }

    public BoxPage setUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public BoxPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    public BoxPage setPermanentAddress(String value) {
        permanentAddressInput.setValue(value);
        return this;
    }

    public BoxPage submitForm() {
        userFormSubmit.click();
        return this;
    }

    public BoxPage checkResult(String name, String email, String currentAdress, String permanentAdress) {
        nameOutput.shouldHave(text(name));
        emailOutput.shouldHave(text(email));
        currentAddressOutput.shouldHave(text(currentAdress));
        permanentAddressOutput.shouldHave(text(permanentAdress));
        return this;

    }
}

package rest.api.ui.homework3.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import rest.api.ui.homework3.models.LoginModel;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Data
public class LoginPage {
    LoginModel loginModel = new LoginModel();



    private final SelenideElement userNameValue = $("#userName-value"),
    logoutButton = $(byText("Log out")),
    userFormLogin = $("#userForm"),
    emptyBookData = $(".rt-noData");




    public LoginPage openProfilePage(String text) {
        open("/profile");
        userNameValue.shouldHave(text(text));

        return this;
    }

    public LoginPage logout() {
        logoutButton.click();
        userFormLogin.shouldBe(visible);

        return this;
    }






}

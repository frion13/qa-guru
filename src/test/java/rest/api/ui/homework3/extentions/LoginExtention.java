package rest.api.ui.homework3.extentions;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import rest.api.ui.homework3.config.AuthConfig;
import rest.api.ui.homework3.models.CookiesModel;
import rest.api.ui.homework3.models.LoginModel;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.*;
import static rest.api.ui.homework3.spec.DemoqaSpec.requestSpec;
import static rest.api.ui.homework3.spec.DemoqaSpec.responseSpec;

public class LoginExtention implements BeforeEachCallback {
    AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());

    @Override
    public void beforeEach(ExtensionContext context) {
        baseURI="https://demoqa.com";
        basePath="Account/v1/Login";
        //make request to get token, id..
        //Put data to cookies
        LoginModel login = new LoginModel();
        login.setUserName(config.userName());
        login.setPassword(config.password());
        CookiesModel response = given(requestSpec)
                .body(login)
                .when()
                .post()
                .then()
                .spec(responseSpec)
                .extract().as(CookiesModel.class);
        String userId = response.getUserId(),
                token = response.getToken(),
                expires = response.getExpires();


        open("https://demoqa.com/profile");

        //добавляет куки в браузер
        getWebDriver().manage().addCookie(
                new Cookie("userID", userId));
        getWebDriver().manage().addCookie(
                new Cookie("token", token));
        getWebDriver().manage().addCookie(
                new Cookie("expires", expires));

    }
}

package rest.api.ui.lesson.tests.extentions;

import io.restassured.response.Response;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class LoginExtention implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        //make request to get token, id..
        //Put data to cookies
        String body = " {\"userName\": \"taty\", \"password\": \"Qwerty!1\"} "; //BAD PRACTICE
        Response response = given()
                .log().uri()
                .log().method()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://demoqa.com/Account/v1/Login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200).extract().response();
        String userId = response.path("userId"),
                token = response.path("token"),
                expires = response.path("expires");


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

package rest.api.ui.lesson.tests;

import com.codeborne.selenide.Configuration;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import rest.api.ui.lesson.tests.extentions.WithLogin;

import java.net.URL;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTests {
    @BeforeAll
    static void setup(){

        Configuration.pageLoadStrategy="eager";
    }



    @AfterEach
    void tearDown(){
        closeWebDriver();
    }


    @Test
    void successfulLoginWithUiTest(){
        open("https://demoqa.com/login");
        $("#userName").setValue("taty");
        $("#password").setValue("Qwerty!1").pressEnter();
        $("#userName-value").shouldHave(text("taty"));


    }

    @Test
    void successfulLoginWithApiAndUiTest(){
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
        open("https://demoqa.com/profile");
        $("#userName-value").shouldHave(text("taty"));


    }

    @Test
    @WithLogin
    void successfulLogOutTest(){
        open("https://demoqa.com/profile");
        $("#userName-value").shouldHave(text("taty"));
        $(byText("Log out")).click();
        $("#userForm").shouldBe(visible);
        assertEquals("https://demoqa.com/login", url());
        assertThat(url()).contains("/login");


    }


}

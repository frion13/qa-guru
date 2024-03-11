package rest.api.lesson;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;

public class LoginTests {
    /*
    1. Make request (POST) to https://reqres.in/api/login
        with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
    2. Get response { "token": "QpwL5tke4Pnpja7X4" }
    3. Check token is QpwL5tke4Pnpja7X4
     */

    @Test
    void successfulLoginTest() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }"; //BAD PRACTICE
        given()
                .log().uri()
                .log().method()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath(("schemas/success-login-schema.json")))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void wrongPasswordTest() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"111cityslicka\" }"; //BAD PRACTICE
        given()
                .log().uri()
                .log().method()
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(403)
//                .body(matchesJsonSchemaInClasspath(("schemas/status-response-schema.json")))
                .body("error", is("Wrong password"));
    }


    @Test
    void wrongEmailTest() {
        String body = "{ \"email\": \"everr.holt@reqres.in\", \"password\": \"cityslicka\" }"; //BAD PRACTICE
        given()
                .log().uri()
                .log().method()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
//                .body(matchesJsonSchemaInClasspath(("schemas/status-response-schema.json")))
                .body("error", is("user not found"));
    }

    @Test
    void noContentTypeTest() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }"; //BAD PRACTICE
        given()
                .log().uri()
                .log().method()
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
//                .body(matchesJsonSchemaInClasspath(("schemas/status-response-schema.json")))
                .body("error", is("Missing email or username"));
    }


    @Test
    void emptyBodyTest() {
        String body = ""; //BAD PRACTICE
        given()
                .log().uri()
                .log().method()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
//                .body(matchesJsonSchemaInClasspath(("schemas/status-response-schema.json")))
                .body("error", is("Missing email or username"));
    }


}

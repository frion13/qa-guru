package rest.api.homework1;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.*;

public class CreateTest {

    @BeforeEach
    void setup(){
        baseURI="https://reqres.in";
        basePath="/api/users";
    }
    @Test
    void successCreateUserTest() {
        String body = " { \"name\": \"morpheus\", \"job\": \"leader\" }";
        Response response = given()
                .log().uri()
                .log().method()
                .body(body)
                .contentType(JSON)
                .when()
                .post()
                .then()
                .log().body()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath(("schemas/create-user-schema.json")))
                .extract().response();

        JsonPath responseBody = response.jsonPath();

        assertThat(response.statusCode()).isEqualTo(201);
        assertThat(responseBody.getString("name")).isEqualTo("morpheus");
        assertThat(responseBody.getString("job")).isEqualTo("leader");


    }

    @Test
    void createUserWithEmptyParametersTest() {
        Response response = given()
                .log().uri()
                .log().method()
                .body("")
                .contentType(JSON)
                .when()
                .post()
                .then()
                .log().body()
                .extract().response();

        JsonPath responseBody = response.jsonPath();
        assertThat(responseBody.getMap("")).doesNotContainKey("name");
        assertThat(responseBody.getMap("")).doesNotContainKey("job");





    }

}

package rest.api.homework1;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.*;

public class ListUsersTest {
    private JsonPath responseBody;
    private Response response;

    @BeforeEach
    void setup() {
         response = given()
                .log().uri()
                .log().method()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().body()
                .body(matchesJsonSchemaInClasspath(("schemas/list-users-schema.json")))
                .extract().response();

        responseBody = response.jsonPath();
    }

    @Test
    void checkTotalTest(){
        int total = responseBody.getInt("total");
        assertThat(total).isEqualTo(12);
    }

    @Test
    void checkResponseStatusCodeTest() {
        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    void checkfirstNameByIdTest(){
        String firstName = responseBody.getList("data.findAll { it.id == 7 }.first_name").get(0).toString();
        assertThat(firstName).isEqualTo("Michael");
    }

    @Test
    void checkLastNameByIdTest(){
        String lastName = responseBody.getList("data.findAll { it.id == 11 }.last_name").get(0).toString();
        assertThat(lastName).isEqualTo("Edwards");
    }

    @Test
    void checkEmailIdTest(){
        String email = responseBody.getList("data.findAll { it.id == 8 }.email").get(0).toString();
        assertThat(email).isEqualTo("lindsay.ferguson@reqres.in");
    }

    @Test
    void checkSupportUrlTest(){
        String supportUrl = responseBody.getString("support.url");
        assertThat(supportUrl).isEqualTo("https://reqres.in/#support-heading");
    }

}

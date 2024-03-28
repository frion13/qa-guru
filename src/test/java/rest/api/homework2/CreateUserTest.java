package rest.api.homework2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rest.api.homework2.models.CreateUserModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static rest.api.specs.DemoqaSpec.*;

public class CreateUserTest {

    @BeforeEach
    void setup(){
        baseURI="https://reqres.in";
        basePath="/api/users";
    }

    @Test
    void successCreateUserTest() {
        CreateUserModel request = new CreateUserModel();
        request.setName("morpheus");
        request.setJob("leader");
        CreateUserModel response = step("Make request", () ->
                given(requestSpec)
                        .body(request)
                        .when()
                        .post()
                        .then()
                        .spec(responseSpec)
                        .extract().as(CreateUserModel.class));
        step("Check response user name", () ->
                assertThat(response.getName()).isEqualTo("morpheus"));
        step("Check response user job", () ->
                assertThat(response.getJob()).isEqualTo("leader"));

    }

    @Test
    void createUserWithEmptyParametersTest() {
        CreateUserModel request = new CreateUserModel();
        CreateUserModel response = step("Make request", () ->
                given(requestSpec)
                        .body(request)
                        .when()
                        .post()
                        .then()
                        .spec(responseSpec)
                        .extract().as(CreateUserModel.class));
        step("Check response user name is null", () ->
                assertThat(response.getName()).isEqualTo(null));
        step("Check response user job is null", () ->
                assertThat(response.getJob()).isEqualTo(null));
    }

}

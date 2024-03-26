package rest.api.homework2;

import org.junit.jupiter.api.Test;
import rest.api.homework2.models.CreateUserRequestModel;
import rest.api.homework2.models.CreateUserResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static rest.api.specs.CreateUserSpec.createRequestSpec;
import static rest.api.specs.CreateUserSpec.createResponseSpec;

public class CreateUserTest {
    @Test
    void successCreateUserTest() {
        CreateUserRequestModel request = new CreateUserRequestModel();
        request.setName("morpheus");
        request.setJob("leader");
        CreateUserResponseModel response = step("Make request", () ->
                given(createRequestSpec)
                        .body(request)
                        .when()
                        .post()
                        .then()
                        .spec(createResponseSpec)
                        .extract().as(CreateUserResponseModel.class));
        step("Check response user name", () ->
                assertThat(response.getName()).isEqualTo("morpheus"));
        step("Check response user job", () ->
                assertThat(response.getJob()).isEqualTo("leader"));

    }

    @Test
    void createUserWithEmptyParametersTest() {
        CreateUserRequestModel request = new CreateUserRequestModel();
        CreateUserResponseModel response = step("Make request", () ->
                given(createRequestSpec)
                        .body(request)
                        .when()
                        .post()
                        .then()
                        .spec(createResponseSpec)
                        .extract().as(CreateUserResponseModel.class));
        step("Check response user name is null", () ->
                assertThat(response.getName()).isEqualTo(null));
        step("Check response user job is null", () ->
                assertThat(response.getJob()).isEqualTo(null));
    }

}

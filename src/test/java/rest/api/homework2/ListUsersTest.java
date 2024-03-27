package rest.api.homework2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rest.api.homework2.models.UserListResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;
import static rest.api.specs.DemoqaSpec.requestSpec;
import static rest.api.specs.DemoqaSpec.responseSpec;


public class ListUsersTest {

    @BeforeEach
    void setup() {
        baseURI = "https://reqres.in";
        basePath = "/api/users?page=2";
    }

    @Test
    void getUserLustTest() {

        UserListResponseModel response = step("Make request", () -> given(requestSpec)
                .when()
                .get()
                .then()
                .spec(responseSpec)
                .extract().as(UserListResponseModel.class));



        step("Check total", () ->
                assertThat(response.getTotal()).isEqualTo(12));

        step("Check first name ", () -> {
            String firstName = response.getData().get(0).getFirstName();
            assertThat(firstName).isEqualTo("George");
        });

        step("Check last name ", () -> {
            String lastName = response.getData().get(0).getLastName();
            assertThat(lastName).isEqualTo("Bluth");
        });

        step("Check email ", () -> {
            String email = response.getData().get(0).getEmail();
            assertThat(email).isEqualTo("george.bluth@reqres.in");
        });

        step("Check support url ", () -> {
            String supportUrl = response.getSupport().getUrl();
            assertThat(supportUrl).isEqualTo("https://reqres.in/#support-heading");
        });
    }
}




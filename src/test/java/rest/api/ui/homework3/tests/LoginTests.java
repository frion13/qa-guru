package rest.api.ui.homework3.tests;


import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import rest.api.ui.homework3.config.AuthConfig;
import rest.api.ui.homework3.extentions.WithLogin;
import rest.api.ui.homework3.pages.LoginPage;
import static com.codeborne.selenide.WebDriverRunner.url;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;



public class LoginTests extends TestBase {
    AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());

    String userName = config.userName();
    LoginPage loginPage = new LoginPage();

    @Test
    @Tag("login")
    @WithLogin
    void successfulLoginWithApiAndUiTest() {
        step("Open Profile step", () ->
        loginPage.openProfilePage(userName));
        step("Check user name is displayed", () ->
        assertThat(loginPage.getUserNameValue().getText()).contains(userName));

    }

    @Test
    @Tag("login")
    @WithLogin
    void successfulLogOutTest() {
        step("Open Profile step", () ->
                loginPage.openProfilePage(userName));
        step("Click logout button", () ->
                loginPage.logout());
        step("Check Url", () ->
        assertThat(url()).contains("/login"));
    }


    @Test
    @Tag("login")
    @WithLogin
    void checkEmptyBooksListTest() {
        step("Open Profile step", () ->
                loginPage.openProfilePage(userName));
        step("Check emty list of books in profile", () ->
        assertThat(loginPage.getEmptyBookData()
                .getText()).contains("No rows found"));

    }

}

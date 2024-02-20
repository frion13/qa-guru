package po;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static utils.RandomUtils.*;

@Tag("registrations")
public class PracticeFormPageObjectTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            gender = getRandomGender(),
            phoneNumber = faker.phoneNumber().subscriberNumber(10),
            month = getRandomMonth(),
            year = getRandomYear(),
            subject = getRandomSubject(),
            hobbies = getRandomHobbies(),
            picture = "img.jpeg",
            currentAddress = faker.address().fullAddress(),
            state = randomState,
            city = getRandomCity(),
            checkName = "Student Name",
            name = String.format("%s %s", firstName, lastName),
            checkEmail = "Student Email",
            checkGender = "Gender",
            checkMobile = "Mobile",
            checkDateOfBirth = "Date of Birth",
            birthDay = String.format("%s %s,%s", "15", month, year),
            checkSubjects = "Subjects",
            checkHobbies = "Hobbies",
            checkPicture = "Picture",
            checkAdress = "Address",
            checkStateAndCity = "State and City",
            stateAndCity = String.format("%s %s", state, city);



    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
//        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
//        Configuration.holdBrowserOpen = true;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }


    @BeforeEach
    void openPage() {
        step("Открыть форму", () -> {
            registrationPage.openPage();
            registrationPage.consentForm();
        });

    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }

    @Test
    void practiceFormTest() {
        step("Заполнить форму", () -> {
            registrationPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setUserEmail(userEmail)
                    .setGender(gender)
                    .setUserNumber(phoneNumber)
                    .setDateOfBirth(month, year)
                    .setSubject(subject)
                    .setHobbies(hobbies)
                    .uploadPicture(picture)
                    .setCurrentAdrress(currentAddress)
                    .setStateAndCity(state, city)
                    .submit();
        });

        step("Проверка результатов", () -> {
            registrationPage.checkResult(checkName, name)
                    .checkResult(checkEmail, userEmail)
                    .checkResult(checkGender, gender)
                    .checkResult(checkMobile, phoneNumber)
                    .checkResult(checkDateOfBirth, birthDay)
                    .checkResult(checkSubjects, subject)
                    .checkResult(checkHobbies, hobbies)
                    .checkResult(checkPicture, picture)
                    .checkResult(checkAdress, currentAddress)
                    .checkResult(checkStateAndCity, stateAndCity);
        });

    }

    @Test
    void formWithMinData() {
        step("Заполнить форму", () -> {
            registrationPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setUserEmail(userEmail)
                    .setGender(gender)
                    .setUserNumber(phoneNumber)
                    .submit();
        });
        step("Проверка результатов", () -> {
            registrationPage.checkResult(checkName, name)
                    .checkResult(checkEmail, userEmail)
                    .checkResult(checkGender, gender)
                    .checkResult(checkMobile, phoneNumber);
        });
    }

    @Test
    void negativeTest() {
        step("Заполнить форму", () -> {
            registrationPage.setGender(gender)
                    .setUserNumber(phoneNumber)
                    .submit();
        });
        step("Проверка результатов", () -> {
            registrationPage.checkBorderColor();
        });

    }


}
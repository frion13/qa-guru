package jenkins.hw;

import jenkins.config.RegistrationConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import pages.RegistrationPage;


import static io.qameta.allure.Allure.step;

@Tag("registration")
public class PracticeFormJenkinsPropertiesTest extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();
    private RegistrationConfig registrationConfig;
    TestData testData = new TestData();


    @BeforeEach
    void setTestData() {
        System.setProperty("environment", System.getProperty("environment", "stage"));
        registrationConfig = ConfigFactory.create(RegistrationConfig.class);
        step("Открыть форму", () -> {
            registrationPage.openPage();
            registrationPage.consentForm();
        });
    }


    @Test
    void practiceFormTest() {
        step("Заполнить форму", () -> {
            registrationPage.setFirstName(registrationConfig.firstName())
                    .setLastName(registrationConfig.lastName())
                    .setUserEmail(registrationConfig.userEmail())
                    .setGender(testData.gender)
                    .setUserNumber(registrationConfig.phoneNumber())
                    .setDateOfBirth(testData.month, testData.year)
                    .setSubject(testData.subject)
                    .setHobbies(testData.hobbies)
                    .uploadPicture(testData.picture)
                    .setCurrentAdrress(testData.currentAddress)
                    .setStateAndCity(testData.state, testData.city)
                    .submit();
        });

        step("Проверка результатов", () -> {
            registrationPage.checkResult(testData.checkName,
                            registrationConfig.firstName() + " " + registrationConfig.lastName())
                    .checkResult(testData.checkEmail, registrationConfig.userEmail())
                    .checkResult(testData.checkGender, testData.gender)
                    .checkResult(testData.checkMobile, registrationConfig.phoneNumber())
                    .checkResult(testData.checkDateOfBirth, testData.birthDay)
                    .checkResult(testData.checkSubjects, testData.subject)
                    .checkResult(testData.checkHobbies, testData.hobbies)
                    .checkResult(testData.checkPicture, testData.picture)
                    .checkResult(testData.checkAdress, testData.currentAddress)
                    .checkResult(testData.checkStateAndCity, testData.stateAndCity);
        });

    }

    @Test
    void formWithMinData() {
        step("Заполнить форму", () -> {
            registrationPage.setFirstName(registrationConfig.firstName())
                    .setLastName(registrationConfig.lastName())
                    .setUserEmail(registrationConfig.userEmail())
                    .setGender(testData.gender)
                    .setUserNumber(registrationConfig.phoneNumber())
                    .submit();
        });
        step("Проверка результатов", () -> {
            registrationPage.checkResult(testData.checkName,
                            registrationConfig.firstName() + " " + registrationConfig.lastName())
                    .checkResult(testData.checkEmail, registrationConfig.userEmail())
                    .checkResult(testData.checkGender, testData.gender)
                    .checkResult(testData.checkMobile, registrationConfig.phoneNumber());
        });
    }

    @Test
    void negativeTest() {
        step("Заполнить форму", () -> {
            registrationPage.setGender(testData.gender)
                    .setUserNumber(testData.phoneNumber)
                    .submit();
        });
        step("Проверка результатов", () -> {
            registrationPage.checkBorderColor();
        });

    }


}
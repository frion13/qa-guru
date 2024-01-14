package hw;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static utils.RandomUtils.*;

public class PracticeFormPageObjectTest extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            gender = getRandomGender(),
            phoneNumper = faker.phoneNumber().subscriberNumber(10),
            month = getRandomMonth(),
            year = getRandomYear(),
            subject = getRandomSubject(),
            hobbies = getRandomHobbies(),
            picture = "img.jpeg",
            currentAddress = faker.address().fullAddress(),
            state = "NCR",
            city = "Delhi",
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
            checkStateAndCity = "NCR Delhi",
            stateAndCity = String.format("%s %s", state, city);


    @BeforeEach
    void openPage() {
        registrationPage.openPage();

    }

    @Test
    void practiceFormTest() {
        registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setUserNumber(phoneNumper)
                .setDateOfBirth(month, year)
                .setSubject(subject)
                .setHobbies(hobbies)
                .uploadPicture(picture)
                .setCurrentAdrress(currentAddress)
                .setStateAndCity(state, city)
                .submit()
                .checkResult(checkName, name)
                .checkResult(checkEmail, userEmail)
                .checkResult(checkGender, gender)
                .checkResult(checkMobile, phoneNumper)
                .checkResult(checkDateOfBirth, birthDay)
                .checkResult(checkSubjects, subject)
                .checkResult(checkHobbies, hobbies)
                .checkResult(checkPicture, picture)
                .checkResult(checkAdress, currentAddress)
                .checkResult(checkStateAndCity, stateAndCity);

    }

    @Test
    void formWithMinData() {
        registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setUserNumber(phoneNumper)
                .submit()
                .checkResult(checkName, name)
                .checkResult(checkEmail, userEmail)
                .checkResult(checkGender, gender)
                .checkResult(checkMobile, phoneNumper);
    }

    @Test
    void negativeTest() {
        registrationPage.setGender(gender)
                .setUserNumber(phoneNumper)
                .submit()
                .checkBorderColor();


    }


}
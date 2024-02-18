package jenkins.hw;

import com.github.javafaker.Faker;

import static utils.RandomUtils.*;

public class TestData {
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
}

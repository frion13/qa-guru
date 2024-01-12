package hw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class PracticeFormPageObjectTest extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeEach
    void openPage() {
        registrationPage.openPage();

    }

    @Test
    void practiceFormTest() {
        registrationPage.setFirstName("Иван")
                .setLastName("Иванов")
                .setUserEmail("ivan@ivanov.ru")
                .setGender("Male")
                .setUserNumber("1234567890")
                .setDateOfBirth("30", "April", "1990")
                .setSubject("math")
                .setHobbies("Sports", "Reading", "Music")
                .uploadPicture("img.jpeg")
                .setCurrentAdrress("Москва, ул Тверская 56")
                .setStateAndCity("NCR", "Delhi")
                .submit()
                .checkResult("""
                        Student Name	Иван Иванов
                        Student Email	ivan@ivanov.ru
                        Gender	Male
                        Mobile	1234567890
                        Date of Birth	30 April,1990
                        Subjects	Maths
                        Hobbies	Sports, Reading, Music
                        Picture	img.jpeg
                        Address	Москва, ул Тверская 56
                        State and City	NCR Delhi
                        """);

    }

    @Test
    void formWithMinData() {
        registrationPage.setFirstName("Иван")
                .setLastName("Иванов")
                .setUserEmail("ivan@ivanov.ru")
                .setGender("Male")
                .setUserNumber("1234567890")
                .submit()
                .checkResult("""
                        Student Name	Иван Иванов
                        Student Email	ivan@ivanov.ru
                        Gender	Male
                        Mobile	1234567890
                        Date of Birth	12 January,2024
                        Subjects	
                        Hobbies	
                        Picture	
                        Address	
                        State and City	
                        """);

    }

    @Test
    void negativeTest() {
        registrationPage.setGender("Male")
                .setUserNumber("1234567890")
                .submit()
                .checkBorderColor();


    }


}
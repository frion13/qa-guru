package junit.lesson1.annotation;


import org.junit.jupiter.api.*;

public class EmailTest {
    @Test
    @DisplayName("first test")
    void simpleTest(){
        System.out.println("первый тест");
    }


    @Test
    @DisplayName("емэйл должен быть отправлен новому пользователю")
    void emailShouldBeSentForNewUserTest(){
        System.out.println("первый тест");
    }

    @Test
    @Tags({
            @Tag("Smoke"),
            @Tag("Web")
    })
    @DisplayName("емэйл должен быть отправлен заблокированному пользователю")
    void emailShouldBeSentForBannedUserTest(){
        System.out.println("первый тест");
    }

    @Disabled("Пишем номер бага или таски на исправление теста")
    @Test
    @Tag("Smoke")
    @DisplayName("емэйл должен быть отправлен  пользователю после изменения способа оплаты")
    void emailShouldBeSentAfterChangePatmentTest(){
        System.out.println("первый тест");
    }
}

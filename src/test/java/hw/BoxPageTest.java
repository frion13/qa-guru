package hw;

import org.junit.jupiter.api.Test;
import pages.BoxPage;

public class BoxPageTest extends TestBase{
   BoxPage boxPage = new BoxPage();
    @Test
    void boxPageTest(){
        boxPage.openBoxPage()
                .setUserName("Иванов Иван")
                .setUserEmail("ivanov@ivan.ru")
                .setCurrentAddress("Москва, Красная площадь")
                .setPermanentAdress("Москва, Тверская")
                .submitForm()
                .checkResult("Иванов Иван", "ivanov@ivan.ru",
                        "Москва, Красная площадь",
                        "Москва, Тверская");

    }





}

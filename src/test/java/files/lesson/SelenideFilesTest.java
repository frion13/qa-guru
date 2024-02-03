package files.lesson;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SelenideFilesTest {
    //если нет href
//    static {
//        Configuration.fileDownload = FileDownloadMode.PROXY;
//    }


    @Test
    @DisplayName("скачивание файла с ссылкой href")
    void downloadFileTest() throws Exception {
        //метод работает там, где зашита ссылка href
        open("https://github.com/selenide/selenide/blob/main/README.md");
        File downeloaded = $("a[data-testid='raw-button' ]").download();

        //InputStream для любых файлов
        try (InputStream is = new FileInputStream(downeloaded)) {//ЗАКРЫТЬ ОТКРЫТЫЙ ФАЙЛ

            byte[] content = is.readAllBytes(); //возвращает массив байтов
            String contentAsString = new String(content, StandardCharsets.UTF_8); //преобразование в строку
            assertTrue(contentAsString.contains("Selenide is a framework for writing easy-to-read and easy-to-maintain automated tests in Java."));


        }
    }


    @Test
    @DisplayName("скачивание файла с ссылкой href библиотека apache common io")
    void downloadFileWithApacheTest() throws Exception {
        //метод работает там, где зашита ссылка href
        open("https://github.com/selenide/selenide/blob/main/README.md");
        File downeloaded = $("a[data-testid='raw-button' ]").download();

        //с помощью библиотеки apache common io
        FileUtils.readFileToString(downeloaded, StandardCharsets.UTF_8);

    }


    @Test
    void uploadTest(){
        open("https://fineuploader.com/demos.html");
        $("input[type=file]").uploadFromClasspath("img.jpeg"); //стандартное названия для поля загрузки в браузерах
        $(".qq-file-info").shouldHave(text("img.jpeg"));
    }
}

package files.lesson;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import files.lesson.modal.Human;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class FileParsingTest {

    private final ClassLoader cl = FileParsingTest.class.getClassLoader();
    private final Gson gson = new Gson();

    @Test
    void pdfParsingTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File pdfFile = $("a[href='junit-user-guide-5.10.1.pdf']").download();
        PDF pdf = new PDF(pdfFile);
        assertEquals("JUnit 5 User Guide", pdf.title);
    }


    @Test
    void xlsParsingTest() throws Exception {
        open("https://excelvba.ru/programmes/Teachers");
        File xlsFile = $("a[href='https://ExcelVBA.ru/sites/default/files/teachers.xls']").download();
        XLS xls = new XLS(xlsFile);
        assertEquals("Белый Владимир Михайлович", xls.excel.getSheet("база данных").getRow(4).getCell(1).getStringCellValue());


    }

    @Test
    void csvParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test_data/fileForCsvTest.csv");
             CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
            List<String[]> content = csvReader.readAll();
            assertArrayEquals(
                    new String[]{"name", "lastname"}, content.get(0)
            );

        }
    }

    @Test
    void zipParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test_data/sample.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName());
            }
        }
    }

    @Test
    void jsonParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test_data/human.json");
             Reader reader = new InputStreamReader(is)) {
            JsonObject object = gson.fromJson(reader, JsonObject.class);

            assertEquals("Dima", object.get("name").getAsString());
            assertEquals(34, object.get("age").getAsInt());
            assertArrayEquals(
                    new String[]{"speaking", "teaching"}, object.get("hobby").getAsJsonArray()
                            .asList()
                            .stream()
                            .map(JsonElement::getAsString).toArray());

        }

    }


    @Test
    @DisplayName("создали класс Human и Passport")
    void jsonParsingTestNextLevel() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test_data/human.json");
             Reader reader = new InputStreamReader(is)) {
            Human object = gson.fromJson(reader, Human.class);

            assertEquals("Dima", object.name);
            assertEquals(34, object.age);
            assertArrayEquals(
                    new String[]{"speaking", "teaching"}, object.hobby.toArray());


        }
    }
}




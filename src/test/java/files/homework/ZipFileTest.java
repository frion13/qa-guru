package files.homework;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import files.lesson.FileParsingTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;


public class ZipFileTest {
    private final ClassLoader cl = FileParsingTest.class.getClassLoader();


    @Test
    @DisplayName("Чтение PDF из zip архива")
    void zipParsingPdfTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test_data/ziptest.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    PDF content = new PDF(zis);
                    assertEquals("JUnit 5 User Guide", content.title);
                }
            }
        }
    }

    @Test
    @DisplayName("Чтение XLS из zip архива")
    void zipParsingXlsxTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test_data/ziptest.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx")) {
                    XLS xls = new XLS(zis);
                    assertEquals("Белый Владимир Михайлович", xls.excel.getSheet("база данных").getRow(4).getCell(1).getStringCellValue());
                }
            }

        }
    }

    @Test
    @DisplayName("Чтение CSV из zip архива")
    void zipParsingCsvTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test_data/ziptest.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvReader.readAll();
                    assertArrayEquals(
                            new String[]{"Ivanov", "Ivan"}, content.get(1)
                    );
                }
            }
        }
    }
}


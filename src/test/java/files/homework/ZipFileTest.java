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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class ZipFileTest {
    private final ClassLoader cl = FileParsingTest.class.getClassLoader();
    boolean xlsFound = false;


    @Test
    @DisplayName("Чтение PDF из zip архива")
    void zipParsingPdfTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("testdata/ziptest.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    PDF content = new PDF(zis);
                    assertThat(content.title).isEqualTo("JUnit 5 User Guide");
                }
            }
        }
    }

    @Test
    @DisplayName("Чтение XLS из zip архива")
    void zipParsingXlsxTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("testdata/ziptest.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xls") && !entry.getName().endsWith(".xlsx")) {
                    xlsFound = true;
                    XLS xls = new XLS(zis);
                    assertThat(xls.excel.getSheet("база данных").getRow(4).getCell(1).getStringCellValue())
                            .isEqualTo("Белый Владимир Михайлович");
                }
            }
        }
        if (!xlsFound) {
            fail("Файл .xls не найден в архиве");
        }

    }

    @Test
    @DisplayName("Чтение CSV из zip архива")
    void zipParsingCsvTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("testdata/ziptest.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvReader.readAll();
                    assertThat(content.get(1)).isEqualTo(new String[]{"Ivanov", "Ivan"});
                }
            }
        }
    }
}


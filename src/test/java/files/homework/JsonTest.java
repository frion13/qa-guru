package files.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import files.homework.modal.SuperHero;
import files.lesson.FileParsingTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonTest {
    private final ClassLoader cl = FileParsingTest.class.getClassLoader();

    @Test
    @DisplayName("Парсинг json (Jackson) ")
    void jsonParsingJacksonTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("testdata/example.json");
             Reader reader = new InputStreamReader(is)) {
            ObjectMapper objectMapper = new ObjectMapper();
            SuperHero superHero = objectMapper.readValue(reader, SuperHero.class);
            assertThat(superHero.getHomeTown()).isEqualTo("Metro City");
            assertThat(superHero.getMembers().get(1).getName()).isEqualTo("Madame Uppercut");
            assertThat(superHero.getMembers().get(2).getPowers().toArray())
                    .isEqualTo(new String[]{"Immortality",
                            "Heat Immunity",
                            "Inferno",
                            "Teleportation",
                            "Interdimensional travel"});
        }
    }
}

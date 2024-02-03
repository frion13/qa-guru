package files.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import files.homework.modal.SuperHero;
import files.lesson.FileParsingTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    private final ClassLoader cl = FileParsingTest.class.getClassLoader();

    @Test
    @DisplayName("Парсинг json (Jackson) ")
    void jsonParsingJacksonTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test_data/example.json");
             Reader reader = new InputStreamReader(is)) {
            ObjectMapper objectMapper = new ObjectMapper();
            SuperHero superHero = objectMapper.readValue(reader, SuperHero.class);
            assertEquals("Metro City", superHero.homeTown);
            assertEquals("Madame Uppercut", superHero.members.get(1).name);
            assertArrayEquals(new String[]{"Immortality",
                    "Heat Immunity",
                    "Inferno",
                    "Teleportation",
                    "Interdimensional travel"}, superHero.members.get(2).powers.toArray());

        }
    }


}

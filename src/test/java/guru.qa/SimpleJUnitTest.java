package guru.qa;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleJUnitTest {

    @BeforeAll
    static void befofeAll(){
        System.out.println("@BeforeAll");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("@AfterAll");
    }

    @BeforeEach
    void beforeEasch(){
        System.out.println("@BeforeEach");
    }

    @AfterEach
    void afterEach(){
        System.out.println("@AfterEach");
    }


    @Test
    void firstTest(){
        assertTrue(3>2);
    }

    @Test
    void secondTest(){
        assertFalse(2>2);
    }

}

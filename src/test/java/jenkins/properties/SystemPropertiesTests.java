package jenkins.properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.module.Configuration;

public class SystemPropertiesTests {
    @Test
    void systemPropertyTest() {
        System.setProperty("browser", "chrome");

        System.out.println(System.getProperty("browser"));
    }

    @Test
    void systemProperty1Test() {
        System.setProperty("browser", "mozilla");

        System.out.println(System.getProperty("browser"));
    }

    @Test
    void systemProperty2Test() {
        System.out.println(System.getProperty("browser"));
    }

    @Test
    void systemProperty3Test() {
        System.out.println(System.getProperty("browser", "safari"));
    }

    @Test
    void systemProperty4Test() {
        System.setProperty("browser", "mozilla");

        System.out.println(System.getProperty("browser", "safari"));
    }

    @Test
    @Tag("property1")
    void systemProperty5Test() { // gradle property1_test -Dbrowser=chrome
        System.out.println(System.getProperty("browser", "safari"));
    }


    @Test
    @Tag("property1")
    void systemProperty6Test() {
        String browser = System.getProperty("browser", "safari");
        String browserVersion = System.getProperty("browser_version", "100.0");
        String browserSize = System.getProperty("browser_size", "1920x1080");

        String browserConfig = String.format("Browser: %s, version: %s, window size: %s",
                browser, browserVersion, browserSize);

        //gradle property_test -Dbrowser=mozilla -Dbrowser_version=96.0 -Dbrowser_size=1080x720
        System.out.println(browserConfig);
    }
}
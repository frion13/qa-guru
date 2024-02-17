package jenkins.properties;

import jenkins.config.CredentialConfig;
import jenkins.config.DriverConfig;
import jenkins.config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

public class OwnerTests {

    @Test
    @Tag("owner1")
    void  ownerTest(){
        CredentialConfig credentialConfig = ConfigFactory.create(CredentialConfig.class);
        String user = credentialConfig.user();
        String password = credentialConfig.password();
        System.out.println(format("Login with %s and %s", user, password));
    }


    @Test
    @Tag("owner2")
    void systemProperty6Test() {
        DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);
        String browser = driverConfig.browserName();
        String browserVersion = driverConfig.browserVersion();
        String browserSize = driverConfig.browserSize();

        String browserConfig = format("Browser: %s, version: %s, window size: %s",
                browser, browserVersion, browserSize);

        //gradle property1_test -Dbrowser=mozilla -Dbrowser_version=96.0 -Dbrowser_size=1080x720
        System.out.println(browserConfig);
    }
    @Test
    @Tag("owner3")
    void systemProperty7Test() {
        System.setProperty("environment", System.getProperty("environment", "stage"));
        ProjectConfig projectConfig = ConfigFactory.create(ProjectConfig.class);
        String environment = System.getProperty("environment");
        String webUrl = projectConfig.webUrl();
        String apiUrl = projectConfig.apiUrl();
        String apiPort = projectConfig.apiPort();

        String browserConfig = format("We run test on: %s,\n webUrl: %s,\n apiUrl: %s,\n apiPort: %s",
                environment, webUrl, apiUrl, apiPort);

        System.out.println(browserConfig);
    }

}

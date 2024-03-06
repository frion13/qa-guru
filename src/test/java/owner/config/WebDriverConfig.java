package owner.config;

import org.aeonbits.owner.Config;

import java.net.URL;

public interface WebDriverConfig extends Config {
    @Key("baseUrl")
    @DefaultValue("https://github.com")
    String getBaseUrl();
    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("remoteUrl")
    @DefaultValue("http://localhost:4444")
    URL getRemotrUrl();
}

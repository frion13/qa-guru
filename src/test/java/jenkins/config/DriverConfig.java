package jenkins.config;


import net.bytebuddy.implementation.bind.annotation.Default;
import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:environment",
        "system:properties",
        "classpath:config/driver.properties"
})
public interface DriverConfig extends Config {
    @Key("browser.name")
    @DefaultValue("chrome")
    String browserName();
    @Key("browser.version")
    @DefaultValue("100.0")
    String browserVersion();
    @Key("browser.size")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("browser.remote.url")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    String browserRemoteUrl();


}

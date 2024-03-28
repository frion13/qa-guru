package rest.api.ui.homework3.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:/tmp.secret.properties",
        "classpath:auth.properties"
})

public interface AuthConfig extends Config {
    @Key("username")
    String userName();

    @Key("password")
    String password();
}

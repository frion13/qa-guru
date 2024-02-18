package jenkins.config;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/${environment}.properties"})
public interface RegistrationConfig extends Config {
    @Key("first.name")
    String firstName();
    @Key("last.name")
    String lastName();
    @Key("user.email")
    String userEmail();
    @Key("user.phone")
    String phoneNumber();



}

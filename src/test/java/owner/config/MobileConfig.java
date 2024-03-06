package owner.config;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.FIRST) //выберет 1 файл MERGE-опследний
@Config.Sources({
        "classpath:${device}.properties",
        "classpath:mobile.properties"
})
public interface MobileConfig extends Config {

    @Key("device.name")
    String getDeviseName();
    @Key("platform.name")
    String getPlatformName();
    @Key("platform.version")
    String getPlatformVersion();


}

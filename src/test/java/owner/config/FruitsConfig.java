package owner.config;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.aeonbits.owner.Config;

import java.util.List;

public interface FruitsConfig extends Config {
    @Key("array")
    String[] getFruitsArray();

    @Key("list")
    List<String> getFruitsList();

    @Key("array-with-separator")
    @Separator(";")
    @DefaultValue("orange;banana")
    List<String> getFruitsListSeparator();

    @Key("array-with-default")
    @DefaultValue("orange,apple")
    String[]  getFruitsArrayWithDefaultValues();
}



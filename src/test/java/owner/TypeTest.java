package owner;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import owner.config.Browser;
import owner.config.TypeConfig;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TypeTest {
    @Test
    public void testInteger(){
        System.setProperty("integer", "11");
        TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());
        assertThat(config.getInteger()).isEqualTo(10);
    }

    @Test
    public void testDouble(){
        System.setProperty("double", "11.10");
        TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());
        assertThat(config.getDouble()).isEqualTo(11.10);
    }

    @Test
    public void testBoolean(){
        System.setProperty("boolean", "true");
        TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());
        assertThat(config.getBoolean()).isEqualTo(true);
    }

    @Test
    public void testEnum(){
        System.setProperty("enum", "CHROME");
        TypeConfig config = ConfigFactory.create(TypeConfig.class, System.getProperties());
        assertThat(config.getEnum()).isEqualTo(Browser.CHROME);
    }
}

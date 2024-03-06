package owner;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import owner.config.FruitsConfig;

import static org.assertj.core.api.Assertions.assertThat;


public class FroiysTest {
    @Test
    public void testArray(){
        System.setProperty("array", "banana,apple");
        FruitsConfig config = ConfigFactory.create(FruitsConfig.class, System.getProperties());
        assertThat(config.getFruitsArray())
                .containsExactly("banana", "apple");

    }

    @Test
    public void testList(){
        System.setProperty("list", "apple,orange");
        FruitsConfig config = ConfigFactory.create(FruitsConfig.class, System.getProperties());
        assertThat(config.getFruitsList())
                .containsExactly("apple", "orange");

    }

    @Test
    public void testArrayWithDefaultValues(){
        FruitsConfig config = ConfigFactory.create(FruitsConfig.class, System.getProperties());
        assertThat(config.getFruitsArrayWithDefaultValues())
                .containsExactly("orange", "apple");

    }

    @Test
    public void testListWithSeparator(){
        FruitsConfig config = ConfigFactory.create(FruitsConfig.class, System.getProperties());
        assertThat(config.getFruitsListSeparator())
                .containsExactly("orange", "banana");

    }

}

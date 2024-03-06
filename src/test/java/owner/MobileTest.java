package owner;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import owner.config.MobileConfig;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MobileTest {

   @Test
    public void testMobile(){
        MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());
        assertThat(config.getPlatformName()).isEqualTo("IOS");
        assertThat(config.getDeviseName()).isEqualTo("Iphone 13 Pro Max");
        assertThat(config.getPlatformVersion()).isEqualTo("13");

    }

    @Test
    public void testMobileWithSystemOverride(){
       System.setProperty("platform.version", "12");
        MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());
        assertThat(config.getPlatformVersion()).isEqualTo("12");

    }


    @Test
    public void testIphone(){
       System.setProperty("device", "iphone");
        MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());
        assertThat(config.getPlatformName()).isEqualTo("IOS");
        assertThat(config.getDeviseName()).isEqualTo("Iphone 13 Pro Max");
        assertThat(config.getPlatformVersion()).isEqualTo("13");

    }

    @Test
    public void testAndroid(){
        System.setProperty("device", "android");
        MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());
        assertThat(config.getPlatformName()).isEqualTo("Android");
        assertThat(config.getDeviseName()).isEqualTo("Google Pixel");
        assertThat(config.getPlatformVersion()).isEqualTo("24");

    }


}

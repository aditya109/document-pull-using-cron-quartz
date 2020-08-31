package cmd.handler.ConfigHandler;

import com.github.cron.cmd.handler.ConfigHandler;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Hashtable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class ReadConfigPropertiesTest {

    @Mock
    private final ConfigHandler configHandlerMock = new ConfigHandler();

    @Test
    public void ReadConfigProperties_ValidPath_ShouldReturnConfigAsDictionary() {
        String filename = "config/config.properties";

        Hashtable<String, String> config_expected = new Hashtable<>();

        assertEquals(configHandlerMock.readConfigProperties(filename).getClass(), config_expected.getClass());
    }

    @Test
    public void ReadConfigProperties_InvalidPath_ShouldNullObject() {
        String filename = "config/invaliding.properties";

        Object ob = configHandlerMock.readConfigProperties(filename);
    }

}

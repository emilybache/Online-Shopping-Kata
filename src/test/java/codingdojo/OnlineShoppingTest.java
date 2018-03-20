package codingdojo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class OnlineShoppingTest {

    @Test
    public void testSwitchStore() {
        Map<String, ModelObject> session = new HashMap<>();
        new OnlineShopping(session).switchStore(null);
    }
}

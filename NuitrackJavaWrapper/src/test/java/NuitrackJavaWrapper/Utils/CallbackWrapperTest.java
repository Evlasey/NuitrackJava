package NuitrackJavaWrapper.Utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class CallbackWrapperTest {

    @Test
    public void testUniqueHandle() {

        CallbackWrapper _cb1 = new CallbackWrapper();

        CallbackWrapper _cb2 = new CallbackWrapper();

        assertNotEquals(_cb1.getUniqueHandle(), _cb2.getUniqueHandle());
    }

    @Test
    public void testCallback() {

        final Integer expectedValue = 10;

        CallbackWrapper _cb = new CallbackWrapper();

        _cb.callback(expectedValue);

        assertEquals(expectedValue, _cb.getLastCallbackData()[0]);
    }

    @Test
    public void testCallbackDefaultValue() {

        CallbackWrapper _cb = new CallbackWrapper();

        assertNull(_cb.getLastCallbackData());
    }

    @Test
    public void testCleanCallbackValue() {

        CallbackWrapper _cb = new CallbackWrapper();

        _cb.callback(1);
        _cb.getLastCallbackData(); // get and clean last data

        assertNull(_cb.getLastCallbackData());
    }

    @Test
    public void testCallbackMultiargs() {

        final Integer expectedValue0 = 10;
        final String expectedValue1 = "test";

        CallbackWrapper _cb = new CallbackWrapper();

        _cb.callback(expectedValue0, expectedValue1);

        Object[] cbData = _cb.getLastCallbackData();
        assertEquals(expectedValue0, (Integer) cbData[0]);
        assertEquals(expectedValue1, (String) cbData[1]);

    }


}
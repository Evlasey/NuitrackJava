package NuitrackJavaWrapper.Native;

import org.junit.Test;
import static org.junit.Assert.*;

public class ChangeableObjectTest {

    @Test
    public void defaultValueTest() {
        ChangeableObject<Object> variable = new ChangeableObject<Object>();
        assertEquals(null, variable.getValue());
    }

    @Test
    public void setStringValueTest() {
        final ChangeableObject<String> variable = new ChangeableObject<String>();
        final String value = "testStringValue";

        variable.setValue(value);
        assertEquals(value, variable.getValue());
    }

    @Test
    public void setIntegerValueTest() {
        final ChangeableObject<Integer> variable = new ChangeableObject<Integer>();
        final Integer value = 123;

        variable.setValue(value);
        assertEquals(value, variable.getValue());
    }

}
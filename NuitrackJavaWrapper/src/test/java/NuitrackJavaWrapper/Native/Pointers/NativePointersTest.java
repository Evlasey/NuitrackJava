package NuitrackJavaWrapper.Native.Pointers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class NativePointersTest {

    @Parameterized.Parameters(name = "NativePointers test {index}. Classname: {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {NativePointer.class.getName()},
                {NuitrackErrorPtr.class.getName()},
                {NuitrackModulePtr.class.getName()},
        });
    }

    private String _exceptionClassName;
    private NativePointer _testExceptionObject;

    public NativePointersTest(String className) {
        _exceptionClassName = className;
    }

    @Before
    public void generateTestObject() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //create object by class name
        Class c = Class.forName(_exceptionClassName);
        _testExceptionObject = (NativePointer) c.newInstance();
    }

    @Test
    public void testPointerValue() {
        //check default value
        assertEquals(true, _testExceptionObject.isNull());

        //set max value of Long type
        _testExceptionObject.setPtr(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, _testExceptionObject.getPtr());

        //set max value of Integer type
        _testExceptionObject.setPtr(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, _testExceptionObject.getPtr());

        // actually pointers are equal to unsigned long, but java does not support unsigned long types to check.
    }

}
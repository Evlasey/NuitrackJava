package NuitrackJavaWrapper.Utils;

import NuitrackJavaWrapper.Types.Exceptions.NuitrackException;
import NuitrackJavaWrapper.Types.Exceptions.NuitrackExceptionType;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ExceptionTranslatorTest {

    @Parameterized.Parameters(name = "ExceptionTranslator test {index}. ExceptionType: {0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {NuitrackExceptionType.EXCEPTION, "Test message EXCEPTION"},
                {NuitrackExceptionType.TERMINATE_EXCEPTION, "Test message TERMINATE_EXCEPTION"},
                {NuitrackExceptionType.BAD_CONFIG_VALUE_EXCEPTION, "Test message BAD_CONFIG_VALUE_EXCEPTION"},
                {NuitrackExceptionType.CONFIG_NOT_FOUND_EXCEPTION, "Test message CONFIG_NOT_FOUND_EXCEPTION"},
                {NuitrackExceptionType.MODUDLE_NOT_FOUND_EXCEPTION, "Test message MODUDLE_NOT_FOUND_EXCEPTION"},
                {NuitrackExceptionType.LICENSE_NOT_ACQUIRED_EXCEPTION, "Test message LICENSE_NOT_ACQUIRED_EXCEPTION"},
                {NuitrackExceptionType.MODULE_NOT_INITIALIZED_EXCEPTION, "Test message MODULE_NOT_INITIALIZED_EXCEPTION"},
                {NuitrackExceptionType.MODULE_NOT_STARTED_EXCEPTION, "Test message MODULE_NOT_STARTED_EXCEPTION"},
        });
    }

    private static NuitrackExceptionType _exceptionType;
    private static String _exceptionMessage;

    public ExceptionTranslatorTest(NuitrackExceptionType eType, String eMessage) {
        _exceptionType = eType;
        _exceptionMessage = eMessage;
    }

    @Test
    public void generateExceptionByErrorCode() {
        try {
            ExceptionTranslator.generateExceptionByErrorCode(_exceptionType);
            fail("Should be an exception");
        } catch (NuitrackException e) {
            assertEquals(_exceptionType, e.type());
        }
    }

    @Test
    public void testGenerateExceptionByErrorCode() {
        try {
            ExceptionTranslator.generateExceptionByErrorCode(_exceptionType, _exceptionMessage);
            fail("Should be an exception");
        } catch (NuitrackException e) {
            assertEquals(_exceptionType, e.type());
            assertTrue(e.getMessage().contains(_exceptionMessage));
        }
    }

    @Ignore
    @Test
    public void testHandle() {
        // can't check it, because exception should be initialized in native code
        // (work with nuitrack_error (NuitrackExceptionPtr) pointers)
    }
}
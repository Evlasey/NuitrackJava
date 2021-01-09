package NuitrackJavaWrapper.Native;

import NuitrackJavaWrapper.Native.NuitrackImport.LibraryPreloader;
import NuitrackJavaWrapper.Native.NuitrackImport.Nuitrack_CAPI;
import NuitrackJavaWrapper.Native.Pointers.NuitrackErrorPtr;
import NuitrackJavaWrapper.Types.Exceptions.*;
import NuitrackJavaWrapper.Utils.ExceptionTranslator;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class NuitrackNativeInitializationTest {

    @Parameterized.Parameters(name = "Native init test {index}. Config file: \"{0}\". Expected exception: {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"", false}, // use default config (no exceptions)
                {"non-existent-file", true}, // check error for non-existent file (should be NuitrackException)
        });
    }

    private String _configFile;
    private boolean _expectedException;

    public NuitrackNativeInitializationTest(String configFile, Boolean expectedException) {
        this._configFile = configFile;
        this._expectedException = expectedException;
    }

    @Before
    public void PreloadLibrary() {
        LibraryPreloader.preloadJNIWrapper();
    }

    @After
    public void ReleaseNuitrack() {
        Nuitrack_CAPI.nuitrack_Release();
    }

    @Test
    public void testInitialization() { // this one isn't parameterized
        boolean thereWasAnException = false;
        try {
            NuitrackExceptionType ex =  Nuitrack_CAPI.nuitrack_Initialize();
            ExceptionTranslator.generateExceptionByErrorCode(ex);
        } catch (NuitrackException e) {
            thereWasAnException = true;
        }
        assertEquals(false, thereWasAnException);
    }

    @Test
    public void testInitializationFromConfig() {
        boolean thereWasAnException = false;
        try {
            NuitrackExceptionType ex =  Nuitrack_CAPI.nuitrack_InitializeFromConfig(this._configFile);
            ExceptionTranslator.generateExceptionByErrorCode(ex);
        } catch (NuitrackException e) {
            thereWasAnException = true;
        }
        assertEquals(_expectedException, thereWasAnException);
    }

    @Test
    public void testInitializationFromConfigE() {
        boolean thereWasAnException = false;
        try {
            NuitrackErrorPtr exPtr = new NuitrackErrorPtr();
            Nuitrack_CAPI.nuitrack_InitializeFromConfig_E(this._configFile, exPtr);
            ExceptionTranslator.handle(exPtr);
        } catch (NuitrackException e) {
            thereWasAnException = true;
        }
        assertEquals(_expectedException, thereWasAnException);
    }
}
package NuitrackJavaWrapper;

import NuitrackJavaWrapper.Native.NuitrackImport;
import NuitrackJavaWrapper.Types.Exceptions.NuitrackException;
import NuitrackJavaWrapper.Types.Exceptions.NuitrackExceptionType;
import NuitrackJavaWrapper.Utils.ExceptionTranslator;
import org.junit.*;

import static org.junit.Assert.*;

public class NuitrackTest {

    @Before
    public void setUp() throws Exception {
        Nuitrack.init("");
    }

    @After
    public void tearDown() throws Exception {
        Nuitrack.release();
        assertTrue(true);
    }

    @Test
    public void testRun() {
        Nuitrack.run();
        assertTrue(true);
    }

    @Test
    public void testUpdate() {
        Nuitrack.update();
        assertTrue(true);
    }

    @Test
    public void testConfigValue() {
        String value = "RealsenseDepthProvider";
        Nuitrack.setConfigValue("DefaultModules.DepthProvider", value);
        assertTrue(Nuitrack.getConfigValue("DefaultModules.DepthProvider").contains(value));
    }

    @Test
    public void testGetInstanceJson() {
        Nuitrack.getInstancesJson();
        assertTrue(true);
    }

    @Test
    public void testGetVersion() {
        assertTrue(Nuitrack.getVersion() > 0);
    }

    @Ignore
    @Test
    public void testGetLicense() {
        Nuitrack.getLicense();
        assertTrue(true);
    }
}
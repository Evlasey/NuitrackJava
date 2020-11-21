import NuitrackJavaWrapper.Native.Pointers.NuitrackErrorPtr;
import NuitrackJavaWrapper.Native.NuitrackImport;
import NuitrackJavaWrapper.Types.Exceptions.NuitrackExceptionType;
import NuitrackJavaWrapper.Utils.ExceptionTranslator;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("hello");
        NuitrackExceptionType ex =  NuitrackImport.nuitrack_Initialize();
        //NuitrackExceptionType ex =  NuitrackImport.nuitrack_InitializeFromConfig("lal");
        //NuitrackErrorPtr ex = new NuitrackErrorPtr();
        //NuitrackImport.nuitrack_InitializeFromConfig_E("sd", ex);
        System.out.println("Init res: " + ex);
        ExceptionTranslator.generateExceptionByErrorCode(ex);
        //ExceptionTranslator.handle(ex);
    }
}
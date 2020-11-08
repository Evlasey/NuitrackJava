import NuitrackJavaWrapper.Native.NuitrackImport;
import NuitrackJavaWrapper.Types.NuitrackException;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("hello");
        NuitrackImport nuitrack = new NuitrackImport();
        NuitrackException.Type ex = nuitrack.nuitrack_Initialize();
        System.out.println("Init res: " + ex.toString());
    }
}
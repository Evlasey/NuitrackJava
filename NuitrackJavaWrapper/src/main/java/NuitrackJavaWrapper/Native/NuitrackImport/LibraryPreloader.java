package NuitrackJavaWrapper.Native.NuitrackImport;

public final class LibraryPreloader {

    private static boolean _isPreloaded;

    public static void preloadJNIWrapper() {
        if (_isPreloaded)
            return;
        System.loadLibrary("nuitrack_jni");
        _isPreloaded = true;
    }

}

package NuitrackJavaWrapper.Native;

import NuitrackJavaWrapper.Types.NuitrackException;

public class NuitrackImport {
    static {
        System.loadLibrary("nuitrack_jni");
    }

    public native NuitrackException.Type nuitrack_Initialize();// __attribute__ ((deprecated));

    public native NuitrackException.Type nuitrack_InitializeFromConfig(String config_file);

    public native void nuitrack_InitializeFromConfig_E(String config, Long error_ptr);

    /*
    public native NuitrackException.Type nuitrack_SetConfigValue(String, String);

    public native NuitrackException.Type nuitrack_GetConfigValue(String key, String value, int bufferSize);

    public native NuitrackException.Type nuitrack_Run();

    public native NuitrackException.Type nuitrack_Update();

    public native NuitrackException.Type nuitrack_SyncUpdate(NuitrackModulePtr);

    public native NuitrackException.Type nuitrack_WaitSyncUpdate(NuitrackModulePtr);

    public native NuitrackException.Type nuitrack_Release();

    public native bool nuitrack_GetNuitrackModuleCanUpdate(NuitrackModule*);

    public native long nuitrack_GetNuitrackModuleTimestamp(NuitrackModule*);

    public native NuitrackException.Type nuitrack_GetLicense(String value, int bufferSize);

    public native NuitrackException.Type nuitrack_GetInstancesJson(String value, int bufferSize);
    public native void nuitrack_GetInstancesJsonSize(int* size, nuitrack_error** error);
    public native void nuitrack_GetInstancesJsonData(String data, nuitrack_error** error);

    public native NuitrackException.Type nuitrack_GetVersion(int* version);

    public native NuitrackException.Type nuitrack_GetExceptionType(NuitrackModulePtr module);

    public native void nuitrack_GetExceptionMessage(NuitrackModulePtr module, char * message, int size);

    public native NuitrackException.Type nuitrack_GetErrorType(nuitrack_error* e);

    public native String nuitrack_GetErrorMessage(nuitrack_error* e);

    public native void nuitrack_DestroyError(nuitrack_error* e);
*/
}

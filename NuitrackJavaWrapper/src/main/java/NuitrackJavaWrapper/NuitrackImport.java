package NuitrackJavaWrapper;

enum ExceptionType	{
        OK, ///< No exception
        EXCEPTION, ///< Exception
        TERMINATE_EXCEPTION, ///< TerminateException
        BAD_CONFIG_VALUE_EXCEPTION, ///< BadConfigValueException
        CONFIG_NOT_FOUND_EXCEPTION, ///< ConfigNotFoundException
        MODUDLE_NOT_FOUND_EXCEPTION, ///< ModuleNotFoundException
        LICENSE_NOT_ACQUIRED_EXCEPTION, ///< LicenseNotAcquiredException
        MODULE_NOT_INITIALIZED_EXCEPTION, ///< ModuleNotInitializedException
        MODULE_NOT_STARTED_EXCEPTION ///< ModuleNotStartedException
};
public class NuitrackImport {
    static {
        System.loadLibrary("libnuitrack");
    }

    public native ExceptionType nuitrack_Initialize();// __attribute__ ((deprecated));

    public native ExceptionType nuitrack_InitializeFromConfig(String config_file);

    /*
    public native void nuitrack_InitializeFromConfig_E(const char*, nuitrack_error** error);

    public native ExceptionType nuitrack_SetConfigValue(const char*, const char*);

    public native ExceptionType nuitrack_GetConfigValue(const char* key, char* value, int bufferSize);

    public native ExceptionType nuitrack_Run();

    public native ExceptionType nuitrack_Update();

    public native ExceptionType nuitrack_SyncUpdate(NuitrackModulePtr);

    public native ExceptionType nuitrack_WaitSyncUpdate(NuitrackModulePtr);

    public native ExceptionType nuitrack_Release();

    public native bool nuitrack_GetNuitrackModuleCanUpdate(NuitrackModule*);

    public native long nuitrack_GetNuitrackModuleTimestamp(NuitrackModule*);

    public native ExceptionType nuitrack_GetLicense(char* value, int bufferSize);

    public native ExceptionType nuitrack_GetInstancesJson(char* value, int bufferSize);
    public native void nuitrack_GetInstancesJsonSize(int* size, nuitrack_error** error);
    public native void nuitrack_GetInstancesJsonData(char* data, nuitrack_error** error);

    public native ExceptionType nuitrack_GetVersion(int* version);

    public native ExceptionType nuitrack_GetExceptionType(NuitrackModulePtr module);

    public native void nuitrack_GetExceptionMessage(NuitrackModulePtr module, char * message, int size);

    public native ExceptionType nuitrack_GetErrorType(nuitrack_error* e);

    public native const char* nuitrack_GetErrorMessage(nuitrack_error* e);

    public native void nuitrack_DestroyError(nuitrack_error* e);
*/
}

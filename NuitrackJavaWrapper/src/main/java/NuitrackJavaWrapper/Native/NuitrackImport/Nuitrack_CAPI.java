package NuitrackJavaWrapper.Native.NuitrackImport;

import NuitrackJavaWrapper.Native.ChangeableObject;
import NuitrackJavaWrapper.Native.Pointers.NuitrackErrorPtr;
import NuitrackJavaWrapper.Native.Pointers.NuitrackModulePtr;
import NuitrackJavaWrapper.Types.Exceptions.NuitrackExceptionType;

public final class Nuitrack_CAPI {

    public static native NuitrackExceptionType nuitrack_Initialize();

    public static native NuitrackExceptionType nuitrack_InitializeFromConfig(String config_file);

    public static native void nuitrack_InitializeFromConfig_E(String config, NuitrackErrorPtr outPtr);

    public static native NuitrackExceptionType nuitrack_SetConfigValue(String key, String value);

    public static native NuitrackExceptionType nuitrack_GetConfigValue(String key, ChangeableObject<String> outValue);

    public static native NuitrackExceptionType nuitrack_Run();

    public static native NuitrackExceptionType nuitrack_Update();

    public static native NuitrackExceptionType nuitrack_SyncUpdate(NuitrackModulePtr ptr);

    public static native NuitrackExceptionType nuitrack_WaitSyncUpdate(NuitrackModulePtr ptr);

    public static native NuitrackExceptionType nuitrack_Release();

    public static native boolean nuitrack_GetNuitrackModuleCanUpdate(NuitrackModulePtr ptr);

    public static native long nuitrack_GetNuitrackModuleTimestamp(NuitrackModulePtr ptr);

    public static native NuitrackExceptionType nuitrack_GetLicense(ChangeableObject<String> out);

    //public static native NuitrackExceptionType nuitrack_GetInstancesJson(ChangeableObject<String> out);

    //public static native void nuitrack_GetInstancesJsonSize(Integer size, NuitrackErrorPtr outPtr);
    public static native void nuitrack_GetInstancesJsonData(ChangeableObject<String> data, NuitrackErrorPtr outPtr);

    public static native NuitrackExceptionType nuitrack_GetVersion(ChangeableObject<Integer> outVersion);

    //public static native NuitrackExceptionType nuitrack_GetExceptionType(NuitrackModulePtr ptr);

    //public static native void nuitrack_GetExceptionMessage(NuitrackModulePtr ptr, ChangeableObject<String> outMessage);

    public static native NuitrackExceptionType nuitrack_GetErrorType(NuitrackErrorPtr ptr);

    public static native String nuitrack_GetErrorMessage(NuitrackErrorPtr ptr);

    public static native void nuitrack_DestroyError(NuitrackErrorPtr ptr);
}

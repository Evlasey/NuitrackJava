package NuitrackJavaWrapper.Native;

import NuitrackJavaWrapper.Native.Pointers.DepthSensorDataPtr;
import NuitrackJavaWrapper.Native.Pointers.NuitrackErrorPtr;
import NuitrackJavaWrapper.Native.Pointers.NuitrackModulePtr;
import NuitrackJavaWrapper.Native.Callbacks.DepthSensorCallbackInterface;
import NuitrackJavaWrapper.Types.Exceptions.NuitrackExceptionType;
import NuitrackJavaWrapper.Types.OutputMode;
import NuitrackJavaWrapper.Types.Vector3;

public final class NuitrackImport {

    private static boolean _isPreloaded;

    public static void preloadJNIWrapper() {
        if (_isPreloaded)
            return;
        System.loadLibrary("nuitrack_jni");
        _isPreloaded = true;
    }

    //##################
    // Nuitrack_CAPI
    //##################

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

    public static native NuitrackExceptionType nuitrack_GetExceptionType(NuitrackModulePtr ptr);

    public static native void nuitrack_GetExceptionMessage(NuitrackModulePtr ptr, ChangeableObject<String> outMessage);

    public static native NuitrackExceptionType nuitrack_GetErrorType(NuitrackErrorPtr ptr);

    public static native String nuitrack_GetErrorMessage(NuitrackErrorPtr ptr);

    public static native void nuitrack_DestroyError(NuitrackErrorPtr ptr);

    //##################
    // DepthSensor_CAPI
    //##################

    // public static native void nuitrack_registerDepthSensorCallback(NuitrackModulePtr, DepthSensorCallbackWrapper*);

    public static native NuitrackExceptionType nuitrack_CreateDepthSensor(NuitrackModulePtr ptr);

    public static native void nuitrack_DestroyDepthSensor(NuitrackModulePtr ptr);

    public static native long nuitrack_OnDepthSensorUpdate(NuitrackModulePtr ptr, DepthSensorCallbackInterface cb);
    public static native void nuitrack_OnDepthSensorUpdateDisconnect(NuitrackModulePtr ptr, long pointer);

    public static native DepthSensorDataPtr nuitrack_GetDepthSensorData(NuitrackModulePtr ptr);
    
    public static native void nuitrack_GetDepthSensorOutputMode(NuitrackModulePtr ptr, ChangeableObject<OutputMode> outOutputMode);

    public static native Vector3 nuitrack_ctypes_ConvertProjToRealCoordsXYZ(NuitrackModulePtr ptr, long x, long y, Long data);
    public static native Vector3 nuitrack_ctypes_ConvertProjToRealCoordsVector3(NuitrackModulePtr ptr, Vector3 vec);

    public static native Vector3 nuitrack_ctypes_ConvertRealToProjCoordsXYZ(NuitrackModulePtr ptr, float x, float y, float z);
    public static native Vector3 nuitrack_ctypes_ConvertRealToProjCoordsVector3(NuitrackModulePtr ptr, Vector3 vec);

    public static native boolean nuitrack_IsDepthSensorMirror(NuitrackModulePtr ptr);
    public static native void nuitrack_SetDepthSensorMirror(NuitrackModulePtr ptr, boolean mirroring);

    // DepthFrameImpl

    public static native void nuitrack_DestroyDepthSensorData(DepthSensorDataPtr ptr);
    public static native void nuitrack_AddDepthSensorDataRef(DepthSensorDataPtr ptr);

    //public static native long nuitrack_GetDepthFrameValueAtIndex(DepthSensorDataPtr ptr, int i);
    //public static native long nuitrack_GetDepthFrameValue(DepthSensorDataPtr ptr, int x, int y);

    public static native long nuitrack_GetDepthFrameRows(DepthSensorDataPtr ptr);

    public static native long nuitrack_GetDepthFrameCols(DepthSensorDataPtr ptr);

    public static native long nuitrack_GetDepthFrameID(DepthSensorDataPtr ptr);

    public static native long nuitrack_GetDepthFrameTimestamp(DepthSensorDataPtr ptr);

    public static native Long[] nuitrack_GetDepthFrameData(DepthSensorDataPtr ptr);

}

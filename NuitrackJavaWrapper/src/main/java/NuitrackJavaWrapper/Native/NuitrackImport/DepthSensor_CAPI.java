package NuitrackJavaWrapper.Native.NuitrackImport;

import NuitrackJavaWrapper.Native.Callbacks.DepthSensorCallbackInterface;
import NuitrackJavaWrapper.Native.ChangeableObject;
import NuitrackJavaWrapper.Native.Pointers.DepthFramePtr;
import NuitrackJavaWrapper.Native.Pointers.NuitrackModulePtr;
import NuitrackJavaWrapper.Types.Exceptions.NuitrackExceptionType;
import NuitrackJavaWrapper.Types.OutputMode;
import NuitrackJavaWrapper.Types.Vector3;

public final class DepthSensor_CAPI {

    public static native NuitrackExceptionType nuitrack_CreateDepthSensor(NuitrackModulePtr outPtr);

    public static native void nuitrack_DestroyDepthSensor(NuitrackModulePtr ptr);

    public static native long nuitrack_OnDepthSensorUpdate(NuitrackModulePtr ptr, DepthSensorCallbackInterface cb);
    public static native void nuitrack_OnDepthSensorUpdateDisconnect(NuitrackModulePtr ptr, long pointer);

    public static native DepthFramePtr nuitrack_GetDepthSensorData(NuitrackModulePtr ptr);

    public static native void nuitrack_GetDepthSensorOutputMode(NuitrackModulePtr ptr, ChangeableObject<OutputMode> outOutputMode);

    public static native Vector3 nuitrack_ctypes_ConvertProjToRealCoordsXYZ(NuitrackModulePtr ptr, long x, long y, long data);
    public static native Vector3 nuitrack_ctypes_ConvertProjToRealCoordsVector3(NuitrackModulePtr ptr, Vector3 vec);

    public static native Vector3 nuitrack_ctypes_ConvertRealToProjCoordsXYZ(NuitrackModulePtr ptr, float x, float y, float z);
    public static native Vector3 nuitrack_ctypes_ConvertRealToProjCoordsVector3(NuitrackModulePtr ptr, Vector3 vec);

    public static native boolean nuitrack_IsDepthSensorMirror(NuitrackModulePtr ptr);
    public static native void nuitrack_SetDepthSensorMirror(NuitrackModulePtr ptr, boolean mirroring);

}

package NuitrackJavaWrapper.Native.Callbacks;

import NuitrackJavaWrapper.Native.Pointers.DepthSensorDataPtr;

public interface DepthSensorCallbackInterface {
    void onNewFrameCallback(DepthSensorDataPtr ptr);
}

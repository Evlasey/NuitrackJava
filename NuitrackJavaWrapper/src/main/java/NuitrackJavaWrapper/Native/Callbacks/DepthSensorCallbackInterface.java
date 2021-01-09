package NuitrackJavaWrapper.Native.Callbacks;

import NuitrackJavaWrapper.Native.Pointers.DepthFramePtr;

public interface DepthSensorCallbackInterface {
    void onNewFrameCallback(DepthFramePtr ptr);
}

package NuitrackJavaWrapper.Native.NuitrackImport;

import NuitrackJavaWrapper.Native.Pointers.DepthFramePtr;

public final class DepthFrame_CAPI {

    public static native void nuitrack_DestroyDepthFramePtr(DepthFramePtr ptr);
    //public static native void nuitrack_AddDepthFramePtrRef(DepthFramePtr ptr);

    //public static native long nuitrack_GetDepthFrameValueAtIndex(DepthFramePtrPtr ptr, int i);
    //public static native long nuitrack_GetDepthFrameValue(DepthFramePtrPtr ptr, int x, int y);

    public static native long nuitrack_GetDepthFrameRows(DepthFramePtr ptr);

    public static native long nuitrack_GetDepthFrameCols(DepthFramePtr ptr);

    public static native long nuitrack_GetDepthFrameID(DepthFramePtr ptr);

    public static native long nuitrack_GetDepthFrameTimestamp(DepthFramePtr ptr);

    public static native long[] nuitrack_GetDepthFrameData(DepthFramePtr ptr);
}

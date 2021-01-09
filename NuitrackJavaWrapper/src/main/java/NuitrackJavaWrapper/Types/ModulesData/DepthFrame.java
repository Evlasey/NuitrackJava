package NuitrackJavaWrapper.Types.ModulesData;

import NuitrackJavaWrapper.Native.NuitrackImport.DepthFrame_CAPI;
import NuitrackJavaWrapper.Native.Pointers.DepthFramePtr;

public final class DepthFrame extends Frame {

    private DepthFramePtr _pimpl;

    public DepthFrame(DepthFramePtr pimpl) {
        _pimpl = pimpl;
    }

    public void dispose() {
        synchronized (this) {
            if(_pimpl.getPtr() != 0) {
                DepthFrame_CAPI.nuitrack_DestroyDepthFramePtr(_pimpl);
            }
        }
    }

    @Override
    protected void finalize() {
        dispose();
    }

    @Override
    public long getRows() {
        return DepthFrame_CAPI.nuitrack_GetDepthFrameRows(_pimpl);
    }

    @Override
    public long getCols() {
        return DepthFrame_CAPI.nuitrack_GetDepthFrameCols(_pimpl);
    }

    @Override
    public long getID() {
        return DepthFrame_CAPI.nuitrack_GetDepthFrameID(_pimpl);
    }

    @Override
    public long getTimestamp() {
        return DepthFrame_CAPI.nuitrack_GetDepthFrameTimestamp(_pimpl);
    }

    public long[] getData() {
        return DepthFrame_CAPI.nuitrack_GetDepthFrameData(_pimpl);
    }
}

package NuitrackJavaWrapper.Types.ModulesData;

import NuitrackJavaWrapper.Native.NuitrackImport;
import NuitrackJavaWrapper.Native.Pointers.DepthSensorDataPtr;

public final class DepthFrame extends Frame <Long> {

    private DepthSensorDataPtr _pimpl;

    public DepthFrame(DepthSensorDataPtr pimpl) {
        _pimpl = pimpl;
        NuitrackImport.nuitrack_AddDepthSensorDataRef(_pimpl);
    }

    public void dispose() {
        synchronized (this) {
            if(_pimpl.getPtr() != 0) {
                NuitrackImport.nuitrack_DestroyDepthSensorData(_pimpl);
            }
        }
    }

    @Override
    protected void finalize() {
        dispose();
    }

    @Override
    public long getRows() {
        return NuitrackImport.nuitrack_GetDepthFrameRows(_pimpl);
    }

    @Override
    public long getCols() {
        return NuitrackImport.nuitrack_GetDepthFrameCols(_pimpl);
    }

    @Override
    public long getID() {
        return NuitrackImport.nuitrack_GetDepthFrameID(_pimpl);
    }

    @Override
    public Long[] getData() {
        return NuitrackImport.nuitrack_GetDepthFrameData(_pimpl);
    }

    @Override
    public long getTimestamp() {
        return NuitrackImport.nuitrack_GetDepthFrameTimestamp(_pimpl);
    }
}

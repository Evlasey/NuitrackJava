package NuitrackJavaWrapper.Modules;

import NuitrackJavaWrapper.Native.NuitrackImport.Nuitrack_CAPI;
import NuitrackJavaWrapper.Native.Pointers.NuitrackModulePtr;

public abstract class NuitrackModule { // Originally it's HeaderOnlyAPI_Module + some updates

    protected NuitrackModulePtr _pimpl;

    public NuitrackModule() {
        _pimpl = new NuitrackModulePtr();
    }

    public final NuitrackModulePtr get() { return _pimpl;}

    public final boolean canUpdate() {
        return Nuitrack_CAPI.nuitrack_GetNuitrackModuleCanUpdate(_pimpl);
    }

    public final long getTimestamp() {
        return Nuitrack_CAPI.nuitrack_GetNuitrackModuleTimestamp(_pimpl);
    }
}

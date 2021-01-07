package NuitrackJavaWrapper.Modules;

import NuitrackJavaWrapper.Native.Pointers.NuitrackModulePtr;

public abstract class NuitrackModule { // Originally it's HeaderOnlyAPI_Module + some updates

    protected NuitrackModulePtr _pimpl;

    public NuitrackModule() {
        _pimpl = new NuitrackModulePtr();
    }

    public final boolean canUpdate() {
        //return nuitrack_GetNuitrackModuleCanUpdate(_pimpl);
        return false;
    }

    public final long getTimestamp() {
        //return nuitrack_GetNuitrackModuleTimestamp(_pimpl);
        return 0;
    }
}

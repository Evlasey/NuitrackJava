package NuitrackJavaWrapper.Native.Pointers;

public class NativePointer {

    protected Long _ptr = null;

    public final void setPtr(long ptr){
        this._ptr = ptr;
    }

    public final long getPtr(){
        return this._ptr;
    }

    public final boolean isNull() {
        if(this._ptr == null || this._ptr == 0)
            return true;
        return false;
    }
}

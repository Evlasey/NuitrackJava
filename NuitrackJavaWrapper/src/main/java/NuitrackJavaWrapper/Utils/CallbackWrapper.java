package NuitrackJavaWrapper.Utils;

public class CallbackWrapper {

    private static Integer _freeUniqueCallbackHandle = 0;
    private int _uniqueHandle;
    private Object[] _lastCallbackData;

    public CallbackWrapper() {
        synchronized (_freeUniqueCallbackHandle) {
            _uniqueHandle = _freeUniqueCallbackHandle;
            _freeUniqueCallbackHandle++;
        }
    }

    public final int getUniqueHandle() {
        return _uniqueHandle;
    }

    public final synchronized void callback(Object... values) {
        _lastCallbackData = values;
    }

    public final synchronized Object[] getLastCallbackData() {
        Object[] returnValue = _lastCallbackData;
        _lastCallbackData = null;
        return returnValue;
    }
}
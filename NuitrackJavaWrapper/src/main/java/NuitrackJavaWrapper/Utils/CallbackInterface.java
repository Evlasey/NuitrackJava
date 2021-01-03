package NuitrackJavaWrapper.Utils;

public final class CallbackInterface {

    private static Integer _freeUniqueCallbackHandle = 0;
    private int _uniqueHandle;
    private Object[] _lastCallbackData;

    public CallbackInterface() {
        synchronized (_freeUniqueCallbackHandle) {
            _uniqueHandle = _freeUniqueCallbackHandle;
            _freeUniqueCallbackHandle++;
        }
    }

    public int getUniqueHandle() {
        return _uniqueHandle;
    }

    public void callback(Object... values) {
        synchronized (this) {
            _lastCallbackData = values;
        }
    }

    public Object[] getLastCallbackData() {
        synchronized (this) {
            Object[] returnValue = _lastCallbackData;
            _lastCallbackData = null;
            return returnValue;
        }
    }
}
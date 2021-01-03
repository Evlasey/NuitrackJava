package NuitrackJavaWrapper.Utils;

import java.util.HashMap;
import java.util.Map;

public final class CallbackStruct {

    private HashMap<Integer, CallbackInterface> _callbacks;

    public CallbackStruct() {
        _callbacks = new HashMap<Integer, CallbackInterface>();
    }

    public void addCallback(CallbackInterface... cbObjects) {
        synchronized(_callbacks) {
            for (CallbackInterface cb : cbObjects) {
                _callbacks.put(cb.getUniqueHandle(), cb);
            }
        }
    }

    public void deleteCallback(CallbackInterface... cbObjects) {
        synchronized(_callbacks) {
            for (CallbackInterface cb : cbObjects) {
                _callbacks.remove(cb.getUniqueHandle());
            }
        }
    }

    public void executeAllCallbacks(Object... values) {
        synchronized(this) {
            for(Map.Entry<Integer, CallbackInterface> entry: _callbacks.entrySet()) {
                entry.getValue().callback(values);
            }
        }
    }

    public int getCallbacksCount() {
        synchronized(this) {
            return _callbacks.size();
        }
    }

};
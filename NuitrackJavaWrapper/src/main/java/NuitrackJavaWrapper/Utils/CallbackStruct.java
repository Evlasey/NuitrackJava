package NuitrackJavaWrapper.Utils;

import NuitrackJavaWrapper.Types.Disposable;

import java.util.HashMap;
import java.util.Map;

public final class CallbackStruct implements Disposable {

    private HashMap<Integer, CallbackWrapper> _callbacks;

    public CallbackStruct() {
        _callbacks = new HashMap<Integer, CallbackWrapper>();
    }

    public void addCallback(CallbackWrapper... cbObjects) {
        synchronized(_callbacks) {
            for (CallbackWrapper cb : cbObjects) {
                _callbacks.put(cb.getUniqueHandle(), cb);
            }
        }
    }

    public void deleteCallback(CallbackWrapper... cbObjects) {
        synchronized(_callbacks) {
            for (CallbackWrapper cb : cbObjects) {
                _callbacks.remove(cb.getUniqueHandle());
            }
        }
    }

    public void executeAllCallbacks(Object... values) {
        synchronized(this) {
            for(Map.Entry<Integer, CallbackWrapper> entry: _callbacks.entrySet()) {
                entry.getValue().callback(values);
            }
        }
    }

    public int getCallbacksCount() {
        synchronized(this) {
            return _callbacks.size();
        }
    }

    @Override
    public void dispose() {
        _callbacks.clear();
    }
};
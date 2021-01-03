package NuitrackJavaWrapper.Utils;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CallbackStructTest {

    @Test
    public void testMultipleCallbacks() {

        final int _callbackCount = 3;
        final int _callbackPassValue = 1;
        final int _expectedSumValue = _callbackCount * _callbackPassValue;
        int _resultSumValue = 0;

        CallbackStruct _cbStructure = new CallbackStruct();
        ArrayList<CallbackInterface> _callbacks = new ArrayList<CallbackInterface>();

        for(int i = 0; i < _callbackCount; i++) {
            CallbackInterface cb = new CallbackInterface();
            _callbacks.add(cb);
            _cbStructure.addCallback(cb);
        }

        _cbStructure.executeAllCallbacks(_callbackPassValue);

        for(CallbackInterface cb : _callbacks) {
            Object[] cbData = cb.getLastCallbackData();
            if(cbData != null)
                _resultSumValue += (Integer)cbData[0];
        }

        assertEquals(_expectedSumValue, _resultSumValue);
    }

    @Test
    public void testUnsubscribeCallbacks() {

        final int _callbackCount = 3;
        final int _callbackPassValue = 1;
        int _resultSumValue = 0;

        CallbackStruct _cbStructure = new CallbackStruct();
        ArrayList<CallbackInterface> _callbacks = new ArrayList<CallbackInterface>();

        for(int i = 0; i < _callbackCount; i++) {
            CallbackInterface cb = new CallbackInterface();
            _callbacks.add(cb);
            _cbStructure.addCallback(cb);
        }

        // pass value and clean it (should work by previous test)
        _cbStructure.executeAllCallbacks(_callbackPassValue);
        for(CallbackInterface cb : _callbacks) {
            cb.getLastCallbackData();
        }

        // unsubscribe everything but first callback
        boolean first = true;
        for(CallbackInterface cb : _callbacks) {
            if(first) {
                first = false;
                continue;
            }
            _cbStructure.deleteCallback(cb);
        }

        _cbStructure.executeAllCallbacks(_callbackPassValue);
        for(CallbackInterface cb : _callbacks) {
            Object[] cbData = cb.getLastCallbackData();
            if(cbData != null)
                _resultSumValue += (Integer)cbData[0];
        }

        assertEquals(_callbackPassValue, _resultSumValue);
    }
}
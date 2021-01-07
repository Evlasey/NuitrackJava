package NuitrackJavaWrapper.Modules;

import NuitrackJavaWrapper.Native.Callbacks.DepthSensorCallbackInterface;
import NuitrackJavaWrapper.Native.ChangeableObject;
import NuitrackJavaWrapper.Native.NuitrackImport;
import NuitrackJavaWrapper.Native.Pointers.DepthSensorDataPtr;
import NuitrackJavaWrapper.Native.Pointers.NativePointer;
import NuitrackJavaWrapper.Native.Pointers.NuitrackModulePtr;
import NuitrackJavaWrapper.Types.Disposable;
import NuitrackJavaWrapper.Types.Exceptions.NuitrackException;
import NuitrackJavaWrapper.Types.Exceptions.NuitrackExceptionType;
import NuitrackJavaWrapper.Types.ModulesData.DepthFrame;
import NuitrackJavaWrapper.Types.OutputMode;
import NuitrackJavaWrapper.Types.Vector3;
import NuitrackJavaWrapper.Utils.CallbackStruct;
import NuitrackJavaWrapper.Utils.CallbackWrapper;
import NuitrackJavaWrapper.Utils.ExceptionTranslator;
import NuitrackJavaWrapper.Types.Vector3;

public class DepthSensor extends NuitrackModule implements Disposable {

    public class DepthSensorCallbackWrapper extends CallbackWrapper {
        public DepthFrame getLastDepthFrame() {
            return (DepthFrame)getLastCallbackData()[0];
        }
    }

    private CallbackStruct _callbackStruct;
    private NuitrackModulePtr _pimpl;
    private NativePointer _nativeCallbackPtr;

    public static DepthSensor Create() throws NuitrackException {

        NuitrackModulePtr pimpl = new NuitrackModulePtr();

        NuitrackExceptionType exception_code = NuitrackImport.nuitrack_CreateDepthSensor(pimpl);

        ChangeableObject<String> exception_message = new ChangeableObject<String>();
        NuitrackImport.nuitrack_GetExceptionMessage(pimpl, exception_message);
        ExceptionTranslator.generateExceptionByErrorCode(exception_code, exception_message.getValue());

        return new DepthSensor(pimpl);
    }

    private DepthSensor(NuitrackModulePtr pimpl) {
        _pimpl = pimpl;
        _callbackStruct = new CallbackStruct();
        _nativeCallbackPtr = new NativePointer();
        DepthSensorCallbackInterface nativeCalllback = new DepthSensorCallbackInterface() {
            @Override
            public void onNewFrameCallback(DepthSensorDataPtr ptr) {
                _callbackStruct.executeAllCallbacks(new DepthFrame(ptr));
            }
        };

        _nativeCallbackPtr.setPtr(NuitrackImport.nuitrack_OnDepthSensorUpdate(_pimpl, nativeCalllback));
    }

    @Override
    public void dispose() {
        _callbackStruct.dispose();

        if(!_nativeCallbackPtr.isNull()) {
            NuitrackImport.nuitrack_OnDepthSensorUpdateDisconnect(_pimpl, _nativeCallbackPtr.getPtr());
            _nativeCallbackPtr.setPtr(0);
        }

        NuitrackImport.nuitrack_DestroyDepthSensor(_pimpl);
        _pimpl.setPtr(0);
    }

    /**
     * @brief Returns output mode of depth sensor.
     */
	public OutputMode getOutputMode() {
        ChangeableObject<OutputMode> mode = new ChangeableObject<OutputMode>();
        NuitrackImport.nuitrack_GetDepthSensorOutputMode(_pimpl, mode);
        return mode.getValue();
    }

    /**
     * @brief Returns true if mirror mode is enabled, false otherwise.
     */
    public boolean isMirror() {
        return NuitrackImport.nuitrack_IsDepthSensorMirror(_pimpl);
    }

    /**
     * @brief Set mirror mode state.
     *
     * Call setMirror(true) to enable mirror mode.
     * Call setMirror(false) to disable mirror mode.
     *
     * If the mirror mode is on, the depth map from the sensor will be
     * reflected horizontally before consequent processing.
     *
     * @param[in] mirror Mirror mode state to be set.
     */
    public void setMirror(boolean mirror) {
        NuitrackImport.nuitrack_SetDepthSensorMirror(_pimpl, mirror);
    }

    /**
     * @brief Add a callback for the new depth frame request.
     *
     * @param[in] callback Callback to be invoked at the new depth frame request.
     */
    public void connectOnNewFrame(DepthSensorCallbackWrapper callback) {
        _callbackStruct.addCallback(callback);
    }

    /**
     * @brief Remove a callback of the new depth frame request.
     *
     * @param[in] callback  the previously added callback.
     */
    public void disconnectOnNewFrame(DepthSensorCallbackWrapper callback) {
        _callbackStruct.deleteCallback(callback);
    }

    /**
     * @brief Returns smart pointer to the last available DepthFrame.
     */
    @Deprecated
    public DepthFrame getDepthFrame() {
        DepthSensorDataPtr data = NuitrackImport.nuitrack_GetDepthSensorData(_pimpl);
        DepthFrame newFrame = new DepthFrame(data);
        return newFrame;
    }

    /**
     * @brief Convert projective coordinates to real world coordinates from Vector3 point.
     * @param[in] p Projective point coordinates.
     * @return Converted real world point.
     */
    public Vector3 convertProjToRealCoords(Vector3 p) {
        return NuitrackImport.nuitrack_ctypes_ConvertProjToRealCoordsVector3(_pimpl, p);
    }

    /**
     * @brief Convert projective coordinates to real world coordinates from x, y and depth map.
     * @param[in] x,y Projective point coordinates.
     * @param[in] depth Depth map.
     * @return Converted real world point.
     */
    public Vector3 convertProjToRealCoords(long x, long y, Long depthFrameData) {
        return NuitrackImport.nuitrack_ctypes_ConvertProjToRealCoordsXYZ(_pimpl, x, y, depthFrameData);
    }

    /**
     * @brief Convert real world coordinates to projective coordinates from Vector3 point.
     * @param[in] p Real world point coordinates.
     * @return Converted projective point.
     */
    public Vector3 convertRealToProjCoords(Vector3 p) {
        return NuitrackImport.nuitrack_ctypes_ConvertRealToProjCoordsVector3(_pimpl, p);
    }

    /**
     * @brief Convert real world coordinates to projective coordinates from x, y, z.
     * @param[in] x,y,z Real world point coordinates.
     * @return Converted projective point.
     */
    public Vector3 convertRealToProjCoords(float x, float y, float z) {
        return NuitrackImport.nuitrack_ctypes_ConvertRealToProjCoordsXYZ(_pimpl, x, y, z);
    }

}

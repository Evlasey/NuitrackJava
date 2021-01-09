/*
 * Wrapper on real DepthSensor modue (DepthSensorPtr)
 * Use common wrapper JNI_Wrapper_NuitrackModule
 * 
 * JNI_Wrapper_NuitrackModule is used because there are some troubles 
 * with connecting callbacks on data update.
 */

#include "NuitrackJavaWrapper_Native_NuitrackImport_DepthSensor_CAPI.h"
#include <nuitrack/capi/DepthSensor_CAPI.h>
#include <nuitrack/modules/DepthSensor.h>
#include <NuitrackTypeConvertor.h>
#include <NuitrackJavaWrapperTypes.h>

using namespace tdv::nuitrack;
using namespace JniTypeConverters::Utils;
using namespace JniTypeConverters::BaseTypes;
using namespace JniTypeConverters::NuitrackJavaWrapper;

void callDepthSensorCallback(JNIEnv* env, jobject callbackObject, jobject passedObject) {
	jclass callbackClassName = env->GetObjectClass(callbackObject);
	const std::string callbackFunctionDataType = PACKAGE_PREFIX_NATIVE + "Pointers/DepthSensorDataPtr";
	const std::string callbackFunctionName = "onNewFrameCallback";
	const std::string callbackFunctionSignature = "(" + L(callbackFunctionDataType) + ")V";

	jmethodID j_setMethod = env->GetMethodID(callbackClassName, callbackFunctionName.c_str(), callbackFunctionSignature.c_str());

	env->CallVoidMethod(callbackObject, j_setMethod, passedObject);
}


JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthSensor_1CAPI_nuitrack_1CreateDepthSensor
(JNIEnv* _env, jclass _class, jobject _jobject_outNuitrackModulePtr) {
	
	ExceptionType eType = OK;
	try {
		JNI_Wrapper_NuitrackModule* jni_wrapper_module = new JNI_Wrapper_NuitrackModule();
		jni_wrapper_module->ptr = DepthSensor::create();
		updatePtr(_env, _jobject_outNuitrackModulePtr, jni_wrapper_module);
	}
	catch (Exception& e) {
		eType = e.type();
	}

	return convertNuitrackExceptionType(_env, eType);
}

JNIEXPORT void JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthSensor_1CAPI_nuitrack_1DestroyDepthSensor
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr) {
	using namespace tdv::nuitrack;
	JNI_Wrapper_NuitrackModule* jni_wrapper_module = convertPtr<JNI_Wrapper_NuitrackModule>(_env, _jobject_NuitrackModulePtr);
	if(jni_wrapper_module != NULL)
		delete jni_wrapper_module;
}

JNIEXPORT jlong JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthSensor_1CAPI_nuitrack_1OnDepthSensorUpdate
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr, jobject _jobject_DepthSensorCallbackInterface) 
{
	JNI_Wrapper_NuitrackModule* jni_wrapper_module = convertPtr<JNI_Wrapper_NuitrackModule>(_env, _jobject_NuitrackModulePtr);
	DepthSensor* jni_DepthSensorPtr = (DepthSensor*)jni_wrapper_module->ptr.get();
	auto handler = jni_DepthSensorPtr->connectOnNewFrame([_env, _jobject_DepthSensorCallbackInterface](DepthFrame::Ptr frame) {
		JNI_Wrapper_DepthFrame* jni_wrapper_frame = new JNI_Wrapper_DepthFrame();
		jni_wrapper_frame->ptr = frame;

		jobject jni_ptr = makePtr(_env, "DepthFramePtr");
		updatePtr(_env, jni_ptr, jni_wrapper_frame);

		callDepthSensorCallback(_env, _jobject_DepthSensorCallbackInterface, jni_ptr);
	});

	return (jlong)handler;
}

JNIEXPORT void JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthSensor_1CAPI_nuitrack_1OnDepthSensorUpdateDisconnect
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr, jlong _jlong_handler) {
	JNI_Wrapper_NuitrackModule* jni_wrapper_module = convertPtr<JNI_Wrapper_NuitrackModule>(_env, _jobject_NuitrackModulePtr);
	DepthSensor* jni_DepthSensorPtr = (DepthSensor*)jni_wrapper_module->ptr.get();
	jni_DepthSensorPtr->disconnectOnNewFrame(_jlong_handler);
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthSensor_1CAPI_nuitrack_1GetDepthSensorData
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr) {
	JNI_Wrapper_NuitrackModule* jni_wrapper_module = convertPtr<JNI_Wrapper_NuitrackModule>(_env, _jobject_NuitrackModulePtr);
	DepthSensor* jni_DepthSensorPtr = (DepthSensor*)jni_wrapper_module->ptr.get();

	JNI_Wrapper_DepthFrame* jni_wrapper_frame = new JNI_Wrapper_DepthFrame();
	jni_wrapper_frame->ptr = jni_DepthSensorPtr->getDepthFrame();

	jobject jni_ptr = makePtr(_env, "DepthFramePtr");
	updatePtr(_env, jni_ptr, jni_wrapper_frame);

	return jni_ptr;
}

JNIEXPORT void JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthSensor_1CAPI_nuitrack_1GetDepthSensorOutputMode
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr, jobject _jobject_outOutputMode) {
	JNI_Wrapper_NuitrackModule* jni_wrapper_module = convertPtr<JNI_Wrapper_NuitrackModule>(_env, _jobject_NuitrackModulePtr);
	DepthSensor* jni_DepthSensorPtr = (DepthSensor*)jni_wrapper_module->ptr.get();

	jobject java_outputMode = convertOutputMode(_env, jni_DepthSensorPtr->getOutputMode());

	updateChangeableObject(_env, _jobject_outOutputMode, java_outputMode);
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthSensor_1CAPI_nuitrack_1ctypes_1ConvertProjToRealCoordsXYZ
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr, jlong x, jlong y, jlong data) {
	JNI_Wrapper_NuitrackModule* jni_wrapper_module = convertPtr<JNI_Wrapper_NuitrackModule>(_env, _jobject_NuitrackModulePtr);
	DepthSensor* jni_DepthSensorPtr = (DepthSensor*)jni_wrapper_module->ptr.get();

	Vector3 vec = jni_DepthSensorPtr->convertProjToRealCoords((size_t)x, (size_t)x, (uint64_t)data);

	return convertVector3(_env, vec);
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthSensor_1CAPI_nuitrack_1ctypes_1ConvertProjToRealCoordsVector3
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr, jobject _jobject_Vector3) {
	JNI_Wrapper_NuitrackModule* jni_wrapper_module = convertPtr<JNI_Wrapper_NuitrackModule>(_env, _jobject_NuitrackModulePtr);
	DepthSensor* jni_DepthSensorPtr = (DepthSensor*)jni_wrapper_module->ptr.get();

	Vector3 passed_vec = convertVector3(_env, _jobject_Vector3);
	Vector3 result_vec = jni_DepthSensorPtr->convertProjToRealCoords(passed_vec);

	return convertVector3(_env, result_vec);
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthSensor_1CAPI_nuitrack_1ctypes_1ConvertRealToProjCoordsXYZ
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr, jfloat x, jfloat y, jfloat z) {
	JNI_Wrapper_NuitrackModule* jni_wrapper_module = convertPtr<JNI_Wrapper_NuitrackModule>(_env, _jobject_NuitrackModulePtr);
	DepthSensor* jni_DepthSensorPtr = (DepthSensor*)jni_wrapper_module->ptr.get();

	Vector3 vec = jni_DepthSensorPtr->convertRealToProjCoords((float)x, (float)x, (float)z);

	return convertVector3(_env, vec);
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthSensor_1CAPI_nuitrack_1ctypes_1ConvertRealToProjCoordsVector3
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr, jobject _jobject_Vector3) {
	JNI_Wrapper_NuitrackModule* jni_wrapper_module = convertPtr<JNI_Wrapper_NuitrackModule>(_env, _jobject_NuitrackModulePtr);
	DepthSensor* jni_DepthSensorPtr = (DepthSensor*)jni_wrapper_module->ptr.get();

	Vector3 passed_vec = convertVector3(_env, _jobject_Vector3);
	Vector3 result_vec = jni_DepthSensorPtr->convertRealToProjCoords(passed_vec);

	return convertVector3(_env, result_vec);
}

JNIEXPORT jboolean JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthSensor_1CAPI_nuitrack_1IsDepthSensorMirror
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr) {
	JNI_Wrapper_NuitrackModule* jni_wrapper_module = convertPtr<JNI_Wrapper_NuitrackModule>(_env, _jobject_NuitrackModulePtr);
	DepthSensor* jni_DepthSensorPtr = (DepthSensor*)jni_wrapper_module->ptr.get();

	return jni_DepthSensorPtr->isMirror();
}

JNIEXPORT void JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthSensor_1CAPI_nuitrack_1SetDepthSensorMirror
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr, jboolean _mirroring) {
	JNI_Wrapper_NuitrackModule* jni_wrapper_module = convertPtr<JNI_Wrapper_NuitrackModule>(_env, _jobject_NuitrackModulePtr);
	DepthSensor* jni_DepthSensorPtr = (DepthSensor*)jni_wrapper_module->ptr.get();

	jni_DepthSensorPtr->setMirror(_mirroring);
}

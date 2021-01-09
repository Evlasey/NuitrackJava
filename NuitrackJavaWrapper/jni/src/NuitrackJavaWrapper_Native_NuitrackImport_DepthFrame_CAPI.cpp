#include "NuitrackJavaWrapper_Native_NuitrackImport_DepthFrame_CAPI.h"
#include <nuitrack/capi/Nuitrack_CAPI.h>
#include <NuitrackTypeConvertor.h>
#include <NuitrackJavaWrapperTypes.h>

using namespace tdv::nuitrack;
using namespace JniTypeConverters::Utils;
using namespace JniTypeConverters::BaseTypes;
using namespace JniTypeConverters::NuitrackJavaWrapper;

JNIEXPORT void JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthFrame_1CAPI_nuitrack_1DestroyDepthFramePtr
(JNIEnv* _env, jclass _class, jobject _jobject_DepthFramePtr) {
	JNI_Wrapper_DepthFrame* jni_wrapper_frame = convertPtr<JNI_Wrapper_DepthFrame>(_env, _jobject_DepthFramePtr);
	if (jni_wrapper_frame != NULL)
		delete jni_wrapper_frame;
}

/*
 * Class:     NuitrackJavaWrapper_Native_NuitrackImport_DepthFrame_CAPI
 * Method:    nuitrack_GetDepthFrameRows
 * Signature: (LNuitrackJavaWrapper/Native/Pointers/DepthFramePtr;)J
 */
JNIEXPORT jlong JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthFrame_1CAPI_nuitrack_1GetDepthFrameRows
(JNIEnv* _env, jclass _class, jobject _jobject_DepthFramePtr) {
	JNI_Wrapper_DepthFrame* jni_wrapper_frame = convertPtr<JNI_Wrapper_DepthFrame>(_env, _jobject_DepthFramePtr);
	return jni_wrapper_frame->ptr->getRows();
}

/*
 * Class:     NuitrackJavaWrapper_Native_NuitrackImport_DepthFrame_CAPI
 * Method:    nuitrack_GetDepthFrameCols
 * Signature: (LNuitrackJavaWrapper/Native/Pointers/DepthFramePtr;)J
 */
JNIEXPORT jlong JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthFrame_1CAPI_nuitrack_1GetDepthFrameCols
(JNIEnv* _env, jclass _class, jobject _jobject_DepthFramePtr) {
	JNI_Wrapper_DepthFrame* jni_wrapper_frame = convertPtr<JNI_Wrapper_DepthFrame>(_env, _jobject_DepthFramePtr);
	return jni_wrapper_frame->ptr->getCols();
}

/*
 * Class:     NuitrackJavaWrapper_Native_NuitrackImport_DepthFrame_CAPI
 * Method:    nuitrack_GetDepthFrameID
 * Signature: (LNuitrackJavaWrapper/Native/Pointers/DepthFramePtr;)J
 */
JNIEXPORT jlong JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthFrame_1CAPI_nuitrack_1GetDepthFrameID
(JNIEnv* _env, jclass _class, jobject _jobject_DepthFramePtr) {
	JNI_Wrapper_DepthFrame* jni_wrapper_frame = convertPtr<JNI_Wrapper_DepthFrame>(_env, _jobject_DepthFramePtr);
	return jni_wrapper_frame->ptr->getID();
}

/*
 * Class:     NuitrackJavaWrapper_Native_NuitrackImport_DepthFrame_CAPI
 * Method:    nuitrack_GetDepthFrameTimestamp
 * Signature: (LNuitrackJavaWrapper/Native/Pointers/DepthFramePtr;)J
 */
JNIEXPORT jlong JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthFrame_1CAPI_nuitrack_1GetDepthFrameTimestamp
(JNIEnv* _env, jclass _class, jobject _jobject_DepthFramePtr) {
	JNI_Wrapper_DepthFrame* jni_wrapper_frame = convertPtr<JNI_Wrapper_DepthFrame>(_env, _jobject_DepthFramePtr);
	return jni_wrapper_frame->ptr->getTimestamp();
}

/*
 * Class:     NuitrackJavaWrapper_Native_NuitrackImport_DepthFrame_CAPI
 * Method:    nuitrack_GetDepthFrameData
 * Signature: (LNuitrackJavaWrapper/Native/Pointers/DepthFramePtr;)[Ljava/lang/Long;
 */
JNIEXPORT jlongArray JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_DepthFrame_1CAPI_nuitrack_1GetDepthFrameData
(JNIEnv* _env, jclass _class, jobject _jobject_DepthFramePtr) {
	JNI_Wrapper_DepthFrame* jni_wrapper_frame = convertPtr<JNI_Wrapper_DepthFrame>(_env, _jobject_DepthFramePtr);
	const uint16_t* data = jni_wrapper_frame->ptr->getData();
	int rows = jni_wrapper_frame->ptr->getCols();
	int cols = jni_wrapper_frame->ptr->getRows();

	jlongArray result = _env->NewLongArray(rows*cols);

	for (int i = 0; i < rows * cols; i++) {
		jlong val = (jlong)data[i];
		_env->SetLongArrayRegion(result, i, 1, &val);
	}

	return result;
}
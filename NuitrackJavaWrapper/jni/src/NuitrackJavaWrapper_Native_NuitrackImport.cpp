#include "NuitrackJavaWrapper_Native_NuitrackImport.h"
#include <nuitrack/capi/Nuitrack_CAPI.h>
#include <NuitrackTypeConvertor.h>

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1Initialize
  (JNIEnv *env, jobject obj)
{
	return jni_converter(env, nuitrack_Initialize());
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1InitializeFromConfig
(JNIEnv* env, jobject obj, jstring str)
{
	return jni_converter(env, nuitrack_InitializeFromConfig(jni_converter(env, str).c_str()));
}

JNIEXPORT void JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1InitializeFromConfig_1E
(JNIEnv*env, jobject obj1, jstring str, jobject obj2)
{
	printf("LOG");
}

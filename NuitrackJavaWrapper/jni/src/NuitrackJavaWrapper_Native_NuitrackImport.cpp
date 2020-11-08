#include "NuitrackJavaWrapper_Native_NuitrackImport.h"
#include <nuitrack/capi/Nuitrack_CAPI.h>
#include <NuitrackTypeConvertor.h>

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1Initialize
  (JNIEnv *env, jobject obj)
{
    jobject res = jni_converter(env, nuitrack_Initialize());
	return res;
}


JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1InitializeFromConfig
(JNIEnv* env, jobject obj, jstring str)
{
	return obj;
}

JNIEXPORT void JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1InitializeFromConfig_1E
(JNIEnv*, jobject, jstring, jobject)
{

}

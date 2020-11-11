#ifndef NuitrackTypeConvertor_h
#define NuitrackTypeConvertor_h

#include <jni.h>
#include "nuitrack/types/Error.h"
#include <string>

static const std::string PACKAGE_PREFIX = "NuitrackJavaWrapper/";
static const std::string PACKAGE_PREFIX_TYPES = PACKAGE_PREFIX + "Types/";

std::string jni_L(std::string fullClassName)
{
	return "L" + fullClassName + ";";
}

jstring jni_converter(JNIEnv* env, const std::string& nativeString) {
	return env->NewStringUTF(nativeString.c_str());
}

std::string jni_converter(JNIEnv* env, jstring jStr) {
    if (!jStr)
        return "";

    const jclass stringClass = env->GetObjectClass(jStr);
    const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");
    const jbyteArray stringJbytes = (jbyteArray)env->CallObjectMethod(jStr, getBytes, env->NewStringUTF("UTF-8"));

    size_t length = (size_t)env->GetArrayLength(stringJbytes);
    jbyte* pBytes = env->GetByteArrayElements(stringJbytes, NULL);

    std::string ret = std::string((char*)pBytes, length);
    env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);

    env->DeleteLocalRef(stringJbytes);
    env->DeleteLocalRef(stringClass);
    return ret;
}

jobject jni_converter(JNIEnv* env, const tdv::nuitrack::ExceptionType ex_type)
{
	const std::string className = PACKAGE_PREFIX_TYPES + "NuitrackException$Type";

    jclass j_class = env->FindClass(className.c_str());

    std::string fieldName = "";
    switch (ex_type)
    {
		case tdv::nuitrack::ExceptionType::OK: fieldName = "OK"; break;
		case tdv::nuitrack::ExceptionType::EXCEPTION: fieldName = "EXCEPTION"; break;
		case tdv::nuitrack::ExceptionType::TERMINATE_EXCEPTION: fieldName = "TERMINATE_EXCEPTION"; break;
		case tdv::nuitrack::ExceptionType::BAD_CONFIG_VALUE_EXCEPTION: fieldName = "BAD_CONFIG_VALUE_EXCEPTION"; break;
		case tdv::nuitrack::ExceptionType::CONFIG_NOT_FOUND_EXCEPTION: fieldName = "CONFIG_NOT_FOUND_EXCEPTION"; break;
		case tdv::nuitrack::ExceptionType::MODUDLE_NOT_FOUND_EXCEPTION: fieldName = "MODUDLE_NOT_FOUND_EXCEPTION"; break;
		case tdv::nuitrack::ExceptionType::LICENSE_NOT_ACQUIRED_EXCEPTION: fieldName = "LICENSE_NOT_ACQUIRED_EXCEPTION"; break;
		case tdv::nuitrack::ExceptionType::MODULE_NOT_INITIALIZED_EXCEPTION: fieldName = "MODULE_NOT_INITIALIZED_EXCEPTION"; break;
		case tdv::nuitrack::ExceptionType::MODULE_NOT_STARTED_EXCEPTION: fieldName = "MODULE_NOT_STARTED_EXCEPTION"; break;
    }

    jfieldID j_field = env->GetStaticFieldID(j_class, fieldName.c_str(), jni_L(className).c_str());
    jobject obj = env->GetStaticObjectField(j_class, j_field);
    return obj;
}


#endif //NuitrackTypeConvertor_h
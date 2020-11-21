#ifndef NuitrackTypeConvertor_h
#define NuitrackTypeConvertor_h

#include <jni.h>
#include "nuitrack/types/Error.h"
#include <string>

namespace JniTypeConverters
{
    namespace Utils 
    {
        std::string L(std::string fullClassName) {
            return "L" + fullClassName + ";";
        }

        jobject NullObject(JNIEnv* env) {
            return env->NewGlobalRef(NULL);
        }

        /*
        jobject createObject(JNIEnv* env, std::string _jclassSignature, std::string _jconstructorSignature, ...)
        {
            va_list args;
            va_start(args, _jconstructorSignature);

            jclass cls = env->FindClass(_jclassSignature.c_str());
            jmethodID constructor = env->GetMethodID(cls, "<init>", _jconstructorSignature.c_str());
            jobject resultObject = env->CallObjectMethodV(cls, constructor, args);

            va_end(args);
            return resultObject;
        }*/
    }
    
    namespace BaseTypes
    {
        jstring convertString(JNIEnv* env, const std::string& nativeString) {
            return env->NewStringUTF(nativeString.c_str());
        }

        std::string convertString(JNIEnv* env, jstring jStr) {
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
    }

    namespace NuitrackJavaWrapper
    {
        static const std::string PACKAGE_PREFIX = "NuitrackJavaWrapper/";
        static const std::string PACKAGE_PREFIX_TYPES = PACKAGE_PREFIX + "Types/";
        static const std::string PACKAGE_PREFIX_NATIVE = PACKAGE_PREFIX + "Native/";

        template<typename T>
        T* convertPtr(JNIEnv* env, jobject ptr) {
            jclass cls = env->GetObjectClass(ptr);
            jmethodID func = env->GetMethodID(cls, "getPtr", "()J");
            return (T*)env->CallLongMethod(ptr, func);
        }

        template<typename T>
        void updatePtr(JNIEnv* env, jobject ptr, T* value) { // I would rather to have a convertPtr function
            jclass cls = env->GetObjectClass(ptr);
            jmethodID func = env->GetMethodID(cls, "setPtr", "(J)V");
            env->CallVoidMethod(ptr, func, (jlong)value);
        }

        jobject convertNuitrackExceptionType(JNIEnv* env, const tdv::nuitrack::ExceptionType ex_type) {
            using Utils::L;
            const std::string className = PACKAGE_PREFIX_TYPES + "Exceptions/NuitrackExceptionType";

            jclass j_class = env->FindClass(className.c_str());

            std::string fieldName = "";
            switch (ex_type) {
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

            jfieldID j_field = env->GetStaticFieldID(j_class, fieldName.c_str(), L(className).c_str());
            jobject obj = env->GetStaticObjectField(j_class, j_field);
            return obj;
        }
    }
}

#endif //NuitrackTypeConvertor_h
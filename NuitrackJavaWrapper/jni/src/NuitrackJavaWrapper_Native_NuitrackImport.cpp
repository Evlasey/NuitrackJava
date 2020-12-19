#include "NuitrackJavaWrapper_Native_NuitrackImport.h"
#include <nuitrack/capi/Nuitrack_CAPI.h>
#include <NuitrackTypeConvertor.h>

using namespace JniTypeConverters::Utils;
using namespace JniTypeConverters::BaseTypes;
using namespace JniTypeConverters::NuitrackJavaWrapper;


JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1Initialize
(JNIEnv* _env, jclass _class)
{
	return convertNuitrackExceptionType(_env, nuitrack_Initialize());
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1InitializeFromConfig
(JNIEnv* _env, jclass _class, jstring _jstring_config)
{
	return convertNuitrackExceptionType(_env, nuitrack_InitializeFromConfig(convertString(_env, _jstring_config).c_str()));
}

JNIEXPORT void JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1InitializeFromConfig_1E
(JNIEnv* _env, jclass _class, jstring _jstring_config, jobject _jobject_outErrorPtr)
{
	nuitrack_error* result = nullptr;
	nuitrack_InitializeFromConfig_E(convertString(_env, _jstring_config).c_str(), &result);
	updatePtr<nuitrack_error>(_env, _jobject_outErrorPtr, result);
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1SetConfigValue
(JNIEnv* _env, jclass _class, jstring _jstring_key, jstring _jstring_value) {
	auto eType = nuitrack_SetConfigValue(
		convertString(_env, _jstring_key).c_str(), 
		convertString(_env, _jstring_value).c_str()
	);
	return convertNuitrackExceptionType(_env, eType);
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1GetConfigValue
(JNIEnv* _env, jclass _class, jstring _jstring_key, jobject _jstring_outValue) {
	const int bufferSize = 5000;
	std::string result;
	result.resize(bufferSize);

	auto eType = nuitrack_GetConfigValue(convertString(_env, _jstring_key).c_str(), (char*)result.c_str(), bufferSize);

	result.resize(strlen(result.c_str()));

	updateChangebleObject(_env, _jstring_outValue, convertString(_env, result));

	return convertNuitrackExceptionType(_env, eType);
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1Run
(JNIEnv* _env, jclass _class) {
	return convertNuitrackExceptionType(_env, nuitrack_Run());
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1Update
(JNIEnv* _env, jclass _class) {
	return convertNuitrackExceptionType(_env, nuitrack_Update());
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1SyncUpdate
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr) {
	return NullObject(_env);
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1WaitSyncUpdate
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr) {
	return NullObject(_env);
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1Release
(JNIEnv* _env, jclass _class) {
	return convertNuitrackExceptionType(_env, nuitrack_Release());
}

JNIEXPORT jboolean JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1GetNuitrackModuleCanUpdate
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr) {
	return true;
}

JNIEXPORT jlong JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1GetNuitrackModuleTimestamp
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr) {
	return (jlong)0;
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1GetLicense
(JNIEnv* _env, jclass _class, jobject _jstring_out) {
	const int bufferSize = 5000;
	std::string result;
	result.resize(bufferSize);
	auto eType = nuitrack_GetLicense((char*)result.c_str(), bufferSize);

	result.resize(strlen(result.c_str()));

	updateChangebleObject(_env, _jstring_out, convertString(_env, result));

	return convertNuitrackExceptionType(_env, eType);
}

// JNIEXPORT void JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1GetInstancesJson
// (JNIEnv* _env, jclass _class, jobject _jstring_out, jobject _jobject_outErrorPtr) {
// }

// JNIEXPORT void JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1GetInstancesJsonSize
// (JNIEnv* _env, jclass _class, jobject _jobject_size, jobject _jobject_outErrorPtr) { }

JNIEXPORT void JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1GetInstancesJsonData
(JNIEnv* _env, jclass _class, jobject _jstring_data, jobject _jobject_outErrorPtr) {
	using tdv::nuitrack::ExceptionType;

	int size = 0;
	nuitrack_error* e = nullptr;
	nuitrack_GetInstancesJsonSize(&size, &e);
	ExceptionType errorCode = nuitrack_GetErrorType(e);

	if(errorCode != ExceptionType::OK){
		updatePtr<nuitrack_error>(_env, _jobject_outErrorPtr, e);
		return;
	}

	std::string result;
	result.resize(size);
	nuitrack_GetInstancesJsonData((char*)result.c_str(), &e);
	errorCode = nuitrack_GetErrorType(e);

	if (errorCode != ExceptionType::OK) {
		updatePtr<nuitrack_error>(_env, _jobject_outErrorPtr, e);
		return;
	}

	updateChangebleObject(_env, _jstring_data, convertString(_env, result));
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1GetVersion
(JNIEnv* _env, jclass _class, jobject _jobject_outVersion) {
	int version;
	auto eType = nuitrack_GetVersion(&version);

	updateChangebleObject(_env, _jobject_outVersion, version);

	return convertNuitrackExceptionType(_env, eType);
}

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1GetExceptionType
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr) {
	return NullObject(_env);
}

JNIEXPORT void JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1GetExceptionMessage
(JNIEnv* _env, jclass _class, jobject _jobject_NuitrackModulePtr, jobject _jstring_outMessage) { }

JNIEXPORT jobject JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1GetErrorType
(JNIEnv* _env, jclass _class, jobject _jobject_errorPtr)
{
	nuitrack_error* e = convertPtr<nuitrack_error>(_env, _jobject_errorPtr);
	tdv::nuitrack::ExceptionType eType = nuitrack_GetErrorType(e);
	return convertNuitrackExceptionType(_env, eType);
}

JNIEXPORT jstring JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1GetErrorMessage
(JNIEnv* _env, jclass _class, jobject _jobject_errorPtr)
{
	nuitrack_error* e = convertPtr<nuitrack_error>(_env, _jobject_errorPtr);
	std::string msg(nuitrack_GetErrorMessage(e));
	return convertString(_env, msg);
}

JNIEXPORT void JNICALL Java_NuitrackJavaWrapper_Native_NuitrackImport_nuitrack_1DestroyError
(JNIEnv* _env, jclass _class, jobject _jobject_errorPtr)
{
	nuitrack_DestroyError(convertPtr<nuitrack_error>(_env, _jobject_errorPtr));
}


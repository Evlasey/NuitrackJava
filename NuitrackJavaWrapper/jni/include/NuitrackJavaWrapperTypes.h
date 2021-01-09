#ifndef NuitrackJavaWrapperTypes
#define NuitrackJavaWrapperTypes

#include <nuitrack/modules/HeaderOnlyAPI_Module.h>
#include <nuitrack/types/DepthFrame.h>

struct JNI_Wrapper_NuitrackModule {
	std::shared_ptr<tdv::nuitrack::HeaderOnlyAPI_Module> ptr;
};

struct JNI_Wrapper_DepthFrame {
	tdv::nuitrack::DepthFrame::Ptr ptr;
};

/*
class JNI_Wrapper_DepthSensorCallback {
public:
	JNI_Wrapper_DepthSensorCallback(JNIEnv* env, jobject jobject_DepthSensorCallbackInterface) {
		_env = env;
		_jobject_DepthSensorCallbackInterface = jobject_DepthSensorCallbackInterface;
	}

	// DepthSensorUpdateCallback
	void callback(DepthSensorDataPtr data) {

	}

private:
	JNIEnv* _env;
	jobject _jobject_DepthSensorCallbackInterface;
};
*/

#endif // !NuitrackJavaWrapperTypes
package NuitrackJavaWrapper;

import NuitrackJavaWrapper.Modules.NuitrackModule;
import NuitrackJavaWrapper.Native.ChangeableObject;
import NuitrackJavaWrapper.Native.NuitrackImport;
import NuitrackJavaWrapper.Native.Pointers.NuitrackErrorPtr;
import NuitrackJavaWrapper.Types.Exceptions.NuitrackException;
import NuitrackJavaWrapper.Types.Exceptions.NuitrackExceptionType;
import NuitrackJavaWrapper.Utils.ExceptionTranslator;

public final class Nuitrack
{
    /**
     * @brief Initialize %Nuitrack.
     *
     * This should be called before using any other %Nuitrack API functions.
     *
     * @note For Android OS: config file is located in the folder with unpacked assets after the installation of Nuitrack.apk
     * @param config Config file for %Nuitrack initialization.
     * @warning <b>Do not</b> specify the <i>config</i> value as it's set <b>automatically</b>. Specify the path to <i>nuitrack.config</i> <b>only</b> if the default location of <i>nuitrack.config</i> (defined after the installation of %Nuitrack runtime) was changed.
     * @throw NuitrackException
     */
    public static void init(final String config) throws NuitrackException {
        NuitrackImport.preloadJNIWrapper();
        NuitrackErrorPtr e = new NuitrackErrorPtr();
        NuitrackImport.nuitrack_InitializeFromConfig_E(config, e);
        ExceptionTranslator.handle(e);
    }

    /**
     * @brief Initialize %Nuitrack.
     *
     * This should be called before using any other %Nuitrack API functions.
     *
     * @note For Android OS: config file is located in the folder with unpacked assets after the installation of Nuitrack.apk
     * @warning <b>Do not</b> specify the <i>config</i> value as it's set <b>automatically</b>. Specify the path to <i>nuitrack.config</i> <b>only</b> if the default location of <i>nuitrack.config</i> (defined after the installation of %Nuitrack runtime) was changed.
     * @throw NuitrackException
     */
    public static void init() throws NuitrackException {
        init("");
    }

    /**
     * @brief Start processing the data provided by the sensor.
     *
     * The stages of data processing are determined
     * by the existing module objects. The data is processed asynchronously.
     *
     * @throw NuitrackException
     */
    public static void run() throws NuitrackException {
        NuitrackExceptionType e = NuitrackImport.nuitrack_Run();
        ExceptionTranslator.generateExceptionByErrorCode(e);
    }

    /**
     * @brief Initiate %Nuitrack update.
     *
     * Request new data from all created %Nuitrack modules. All module callbacks will be called.
     * No data synchronization is performed.
     *
     * @note Non-blocking operation.
     * @throw NuitrackException
     */
    public static void update() throws NuitrackException {
        NuitrackExceptionType e = NuitrackImport.nuitrack_Update();
        ExceptionTranslator.generateExceptionByErrorCode(e);
    }

    /**
     * @brief Initiate %Nuitrack module data update.
     *
     * Request new data from %Nuitrack module. When data becomes available, corresponding callbacks
     * will be called for given module and all the modules, that are required for it.
     * The data sent to these callbacks will be synchronized, so callback calls will be consistent.
     * Callback call order is defined by module dependency chain: from independent to dependent.
     * The call order of callbacks belonging to a particular module is defined by internal organization
     * of this module.
     *
     * @note Non-blocking operation.
     * @param module %Nuitrack module's Ptr.
     * @throw NuitrackException
     */
    /*public static void update(NuitrackModule module) throws NuitrackException {
        //ExceptionTranslator::generateExceptionByErrorCode(nuitrack_SyncUpdatePublic(module.get()));
    }*/

    /**
     * @brief Initiate %Nuitrack module update and wait for it.
     *
     * Similar to Nuitrack::update(std::shared_ptr<HeaderOnlyAPI_Module> module), but waits until all the required callbacks are called.
     *
     * @note Blocking operation.
     * @param module %Nuitrack module's Ptr.
     * @throw NuitrackException
     */
    /*public static void waitUpdate(NuitrackModule module) throws NuitrackException {
        //ExceptionTranslator::generateExceptionByErrorCode(nuitrack_WaitSyncUpdatePublic(module.get()));
    }*/

    /**
     * @brief Stop data processing and destroy all existing %Nuitrack modules.
     *
     * @note To restart Nuitrack after release you should call Nuitrack::init and create all required modules again.
     * @throw NuitrackException
     */
    public static void release() throws NuitrackException {
        /*CallbackStruct<IssuesData::Ptr>* callbackStruct =
            (CallbackStruct<IssuesData::Ptr>*)nuitrack_getIssuesCallbackStruct();
        if(callbackStruct != NULL)
        {
            nuitrack_setIssuesCallbackStruct(NULL);
            delete callbackStruct;
        }*/

        NuitrackExceptionType e = NuitrackImport.nuitrack_Release();
        ExceptionTranslator.generateExceptionByErrorCode(e);
    }

    /**
     * @brief Set the value of a %Nuitrack configuration parameter.
     *
     * @param key Parameter key
     * @param value Parameter value
     * @throw NuitrackException
     */
    public static void setConfigValue(final String key, final String value) throws NuitrackException {
        NuitrackExceptionType e = NuitrackImport.nuitrack_SetConfigValue(key, value);
        ExceptionTranslator.generateExceptionByErrorCode(e);
    }

    /**
     * @brief Get the value of a %Nuitrack configuration parameter.
     *
     * @param key Parameter key
     * @return Parameter value
     * @throw NuitrackException
     */
    public static String getConfigValue(final String key) throws NuitrackException {
        ChangeableObject<String> value = new ChangeableObject<String>();
        NuitrackExceptionType e = NuitrackImport.nuitrack_GetConfigValue(key, value);
        ExceptionTranslator.generateExceptionByErrorCode(e);
        return value.getValue();
    }

    /**
     * @brief Get the JSON string of Nuitrack instance-based API.
     *
     * @return JSON string
     * @throw NuitrackException
     */
    public static String getInstancesJson() throws NuitrackException {
        NuitrackErrorPtr e = new NuitrackErrorPtr();
        ChangeableObject<String> data = new ChangeableObject<String>();
        NuitrackImport.nuitrack_GetInstancesJsonData(data, e);
        ExceptionTranslator.handle(e);
        return data.getValue();
    }

    /**
     * @brief Get Nuitrack version.
     * The version is calculated by the formula: major * 10000 + minor * 100 + revision
     *
     * @return An integer value of Nuitrack version.
     * @throw NuitrackException
     */
    public static int getVersion() throws NuitrackException {
        ChangeableObject<Integer> version = new ChangeableObject<Integer>();
        NuitrackExceptionType e = NuitrackImport.nuitrack_GetVersion(version);
        ExceptionTranslator.generateExceptionByErrorCode(e);
        return version.getValue();
    }

    /**
     * @brief Callback type of the issue update request.
     *
     * @see tdv::nuitrack::IssuesData
     * @see tdv::nuitrack::Issue
     * @see connectOnIssuesUpdate
     */
    //typedef std::function<void (IssuesData::Ptr)> OnIssueUpdate;

    /**
     * @brief Add a callback for the issue update request.
     *
     * @param [in] callback Callback to be invoked at the issue update request.
     * @return Registered callback ID. You can use it to remove the callback.
     * @see disconnectOnIssuesUpdate
     */
    /*static uint64_t connectOnIssuesUpdate(const OnIssueUpdate& callback)
    {
        IssueTracker* issueTracker = NULL;
        nuitrack_getIssueTracker(&issueTracker);
        if(issueTracker == NULL)
            return 0;

        if(nuitrack_getIssuesCallbackStruct() == NULL)
        {
            CallbackStruct<IssuesData::Ptr>* ptr = new CallbackStruct<IssuesData::Ptr>();
            nuitrack_setIssuesCallbackStruct(ptr);

            IssueTrackerCallbackWrapper* callbackWrapper = new IssueTrackerCallbackWrapper();
            callbackWrapper->setIssueTracker(issueTracker);
            callbackWrapper->setFunctionAddress(&onIssuesUpdateCallback);
            nuitrack_registerIssuesTrackerCallback(callbackWrapper);
        }

        return ((CallbackStruct<IssuesData::Ptr>*)(nuitrack_getIssuesCallbackStruct()))->addCallback(callback);
    }*/

    /** @warning For internal use only. */
    /*static void onIssuesUpdateCallback(IssueTrackerData* data)
    {
        IssuesData::Ptr newData = IssuesData::Ptr(new IssuesData(data));
        CallbackStruct<IssuesData::Ptr>* callbackStruct =
            (CallbackStruct<IssuesData::Ptr>*)nuitrack_getIssuesCallbackStruct();
        if(callbackStruct == NULL)
            return;
        callbackStruct->executeAllCallbacks(newData);
    }*/
    /**
     * @brief Remove a callback of the issue update request.
     *
     * @param [in] handler ID of the previously added callback.
     * @see connectOnIssuesUpdate
     */
    /*static void disconnectOnIssuesUpdate(uint64_t handler)
    {
        CallbackStruct<IssuesData::Ptr>* callbackStruct =
            (CallbackStruct<IssuesData::Ptr>*)nuitrack_getIssuesCallbackStruct();
        if(callbackStruct == NULL)
            return;
        callbackStruct->deleteCallback(handler);
    }*/

    /**
     * @brief Get a list of available devices.
     *
     * @return List of available devices.
     * @throw NuitrackException
     */
    /*static std::vector<device::NuitrackDevice::Ptr> getDeviceList()
    {
        std::vector<device::NuitrackDevice::Ptr> result;
        int device_limit = 0;
        ExceptionTranslator::generateExceptionByErrorCode(nuitrack_nuitrackDevice_getDeviceListLimitConst(device_limit));
        std::vector<NuitrackDeviceDataPtr> bufferOfDevices(device_limit);
        ExceptionTranslator::generateExceptionByErrorCode(nuitrack_GetDeviceList(bufferOfDevices.data(), device_limit));

        for(int i = 0; i < device_limit; i++)
        {
            if(bufferOfDevices[i] == NULL)
                break;
            result.emplace_back(new device::NuitrackDevice(bufferOfDevices[i]));
        }

        return result;
    }*/

    /**
     * @brief Setting a device to run.
     *
     * @param dev A configured device that you want to use.
     * @throw NuitrackException
     */
    /*static void setDevice(device::NuitrackDevice::Ptr dev)
    {
        ExceptionTranslator::generateExceptionByErrorCode(nuitrack_SetDevice(dev->_pimpl));
    }*/

    /**
     * @brief Get current device license.
     *
     * @return JSON string.
     * @throw NuitrackException
     */
    public static String getLicense() throws NuitrackException {
        ChangeableObject<String> license = new ChangeableObject<String>();
        NuitrackExceptionType e = NuitrackImport.nuitrack_GetLicense(license);
        ExceptionTranslator.generateExceptionByErrorCode(e);
        return license.getValue();
    }
};
package NuitrackJavaWrapper.Types.Exceptions;

public enum NuitrackExceptionType{
    OK, ///< No exception
    EXCEPTION, ///< Exception
    TERMINATE_EXCEPTION, ///< TerminateException
    BAD_CONFIG_VALUE_EXCEPTION, ///< BadConfigValueException
    CONFIG_NOT_FOUND_EXCEPTION, ///< ConfigNotFoundException
    MODUDLE_NOT_FOUND_EXCEPTION, ///< ModuleNotFoundException
    LICENSE_NOT_ACQUIRED_EXCEPTION, ///< LicenseNotAcquiredException
    MODULE_NOT_INITIALIZED_EXCEPTION, ///< ModuleNotInitializedException
    MODULE_NOT_STARTED_EXCEPTION ///< ModuleNotStartedException
}
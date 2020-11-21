package NuitrackJavaWrapper.Utils;

import NuitrackJavaWrapper.Native.NuitrackImport;
import NuitrackJavaWrapper.Native.Pointers.NuitrackErrorPtr;
import NuitrackJavaWrapper.Types.Exceptions.*;

public class ExceptionTranslator {
    public static void generateExceptionByErrorCode(NuitrackExceptionType errorCode) throws NuitrackException
    {
        switch(errorCode)
        {
            case EXCEPTION:
                throw new NuitrackException("");
            case TERMINATE_EXCEPTION:
                throw new TerminateException("");
            case BAD_CONFIG_VALUE_EXCEPTION:
                throw new BadConfigValueException("");
            case CONFIG_NOT_FOUND_EXCEPTION:
                throw new ConfigNotFoundException("");
            case MODUDLE_NOT_FOUND_EXCEPTION:
                throw new ModuleNotFoundException("");
            case LICENSE_NOT_ACQUIRED_EXCEPTION:
                throw new LicenseNotAcquiredException("");
            case MODULE_NOT_INITIALIZED_EXCEPTION:
                throw new ModuleNotInitializedException("");
            case MODULE_NOT_STARTED_EXCEPTION:
                throw new ModuleNotStartedException("");
        }
    }

    public static void generateExceptionByErrorCode(NuitrackExceptionType errorCode, String errorMessage) throws NuitrackException
    {
        switch(errorCode)
        {
            case EXCEPTION:
                throw new NuitrackException("NuitrackException: " + errorMessage);
            case TERMINATE_EXCEPTION:
                throw new TerminateException("NuitrackException (TerminateException): " + errorMessage);
            case BAD_CONFIG_VALUE_EXCEPTION:
                throw new BadConfigValueException("NuitrackException (BadConfigValueException): " + errorMessage);
            case CONFIG_NOT_FOUND_EXCEPTION:
                throw new ConfigNotFoundException("NuitrackException (ConfigNotFoundException): " + errorMessage);
            case MODUDLE_NOT_FOUND_EXCEPTION:
                throw new ModuleNotFoundException("NuitrackException (ModuleNotFoundException): " + errorMessage);
            case LICENSE_NOT_ACQUIRED_EXCEPTION:
                throw new LicenseNotAcquiredException("NuitrackException (LicenseNotAcquiredException): " + errorMessage);
            case MODULE_NOT_INITIALIZED_EXCEPTION:
                throw new ModuleNotInitializedException("NuitrackException (ModuleNotInitializedException): " + errorMessage);
            case MODULE_NOT_STARTED_EXCEPTION:
                throw new ModuleNotStartedException("NuitrackException (ModuleNotStartedException): " + errorMessage);
        }
    }

    public static void handle(NuitrackErrorPtr e)  throws NuitrackException
    {
        if (e.isNull())
            return;

        NuitrackExceptionType errorCode = NuitrackImport.nuitrack_GetErrorType(e);
		final String errorMessage = NuitrackImport.nuitrack_GetErrorMessage(e);
        NuitrackImport.nuitrack_DestroyError(e);

        if (errorMessage.isEmpty())
            generateExceptionByErrorCode(errorCode);
		else
            generateExceptionByErrorCode(errorCode, errorMessage);
    }
}

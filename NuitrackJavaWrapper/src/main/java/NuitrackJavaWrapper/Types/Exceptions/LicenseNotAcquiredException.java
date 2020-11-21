package NuitrackJavaWrapper.Types.Exceptions;

public class LicenseNotAcquiredException extends TerminateException{
    public LicenseNotAcquiredException(String errorMessage) {
        super(errorMessage);
    }
    public NuitrackExceptionType type() {
        return NuitrackExceptionType.LICENSE_NOT_ACQUIRED_EXCEPTION;
    }
}

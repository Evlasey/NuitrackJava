package NuitrackJavaWrapper.Types.Exceptions;

public class TerminateException extends NuitrackException{
    public TerminateException(String errorMessage) {
        super(errorMessage);
    }
    public NuitrackExceptionType type() {
        return NuitrackExceptionType.TERMINATE_EXCEPTION;
    }
}

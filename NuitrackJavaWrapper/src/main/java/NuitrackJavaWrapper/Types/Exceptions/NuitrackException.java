package NuitrackJavaWrapper.Types.Exceptions;

public class NuitrackException extends RuntimeException {
    public NuitrackException(String errorMessage) {
        super(errorMessage);
    }
    public NuitrackExceptionType type() {
        return NuitrackExceptionType.EXCEPTION;
    }
}

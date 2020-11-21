package NuitrackJavaWrapper.Types.Exceptions;

public class BadConfigValueException extends NuitrackException {
    public BadConfigValueException(String errorMessage) {
        super(errorMessage);
    }
    public NuitrackExceptionType type() {
        return NuitrackExceptionType.BAD_CONFIG_VALUE_EXCEPTION;
    }
}

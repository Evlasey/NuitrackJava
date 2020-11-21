package NuitrackJavaWrapper.Types.Exceptions;

public class ConfigNotFoundException extends TerminateException{
    public ConfigNotFoundException(String errorMessage) {
        super(errorMessage);
    }
    public NuitrackExceptionType type() {
        return NuitrackExceptionType.CONFIG_NOT_FOUND_EXCEPTION;
    }
}

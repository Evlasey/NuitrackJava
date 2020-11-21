package NuitrackJavaWrapper.Types.Exceptions;

public class ModuleNotFoundException extends TerminateException{
    public ModuleNotFoundException(String errorMessage) {
        super(errorMessage);
    }
    public NuitrackExceptionType type() {
        return NuitrackExceptionType.MODUDLE_NOT_FOUND_EXCEPTION;
    }
}

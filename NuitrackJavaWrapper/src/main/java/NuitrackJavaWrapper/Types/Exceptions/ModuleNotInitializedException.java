package NuitrackJavaWrapper.Types.Exceptions;

public class ModuleNotInitializedException extends TerminateException{
    public ModuleNotInitializedException(String errorMessage) {
        super(errorMessage);
    }
    public NuitrackExceptionType type() {
        return NuitrackExceptionType.MODULE_NOT_INITIALIZED_EXCEPTION;
    }
}

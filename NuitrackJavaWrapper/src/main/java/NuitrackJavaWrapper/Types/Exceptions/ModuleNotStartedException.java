package NuitrackJavaWrapper.Types.Exceptions;

public class ModuleNotStartedException extends TerminateException{
    public ModuleNotStartedException(String errorMessage) {
        super(errorMessage);
    }
    public NuitrackExceptionType type() {
        return NuitrackExceptionType.MODULE_NOT_STARTED_EXCEPTION;
    }
}

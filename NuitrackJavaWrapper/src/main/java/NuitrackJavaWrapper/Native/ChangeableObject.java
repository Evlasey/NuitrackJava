package NuitrackJavaWrapper.Native;

public class ChangeableObject <T> {
    private T _value;

    public T getValue() {
        return _value;
    }

    public void setValue(T value) {
        _value = value;
    }
}

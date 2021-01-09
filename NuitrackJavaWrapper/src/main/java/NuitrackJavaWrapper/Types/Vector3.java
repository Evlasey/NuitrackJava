package NuitrackJavaWrapper.Types;

public final class Vector3 {

    private float _x;
    private float _y;
    private float _z;

    public Vector3(float x, float y, float z) {
        _x = x;
        _y = y;
        _z = z;
    }

    public float getX() {
        return _x;
    }

    public float getY() {
        return _y;
    }

    public float getZ() {
        return _z;
    }
}

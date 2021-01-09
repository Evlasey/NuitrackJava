package NuitrackJavaWrapper.Types;

public final class OutputMode {

    private int _fps;
    private int _xres;
    private int _yres;
    private float _hfov;

    public OutputMode(int fps, int xres, int yres, float hfov) {
        _fps = fps;
        _xres = xres;
        _yres = yres;
        _hfov = hfov;
    }

    /**
     * @brief Count of frames transferred per second.
     */
    public int get_fps() { return _fps; };

    /**
     * @brief X resolution (frame width).
     */
    public int get_xres() { return _xres; };

    /**
     * @brief Y resolution (frame height).
     */
    public int get_yres() { return _yres; };

    /**
     * @brief Horizontal field of view in radians.
     */
    public float get_hfov() { return _hfov; };
}

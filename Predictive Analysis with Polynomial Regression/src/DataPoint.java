/**
 * Clase que representa un punto de datos en el espacio de regresión.
 * Cada punto tiene un valor en el eje x y un valor en el eje y.
 */
public class DataPoint {
    private final double x;
    private final double y;

    public DataPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

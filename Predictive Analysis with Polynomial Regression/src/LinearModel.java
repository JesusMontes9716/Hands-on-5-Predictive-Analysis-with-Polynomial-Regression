import java.util.List;

/**
 * Clase para implementar un modelo de regresión lineal
 * utilizando el método de mínimos cuadrados.
 */
public class LinearModel {
    private double a, b; // Coeficientes de la regresión lineal

    /**
     * Entrena el modelo en función de un conjunto de datos de entrenamiento.
     * Calcula los coeficientes a y b para la ecuación y = ax + b.
     */
    public void train(List<DataPoint> data) {
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;
        int n = data.size();

        for (DataPoint point : data) {
            sumX += point.getX();
            sumY += point.getY();
            sumXY += point.getX() * point.getY();
            sumX2 += point.getX() * point.getX();
        }

        this.a = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        this.b = (sumY - this.a * sumX) / n;
    }

    /**
     * Realiza una predicción de y para un valor dado de x usando el modelo entrenado.
     */
    public double predict(double x) {
        return a * x + b;
    }

    /**
     * Calcula el coeficiente de determinación R^2 para evaluar el ajuste del modelo.
     * R^2 indica qué tan bien el modelo predice los datos observados.
     */
    public double calculateRSquared(List<DataPoint> data) {
        double sumSquaredResiduals = 0;
        double sumSquaredTotal = 0;
        double meanY = data.stream().mapToDouble(DataPoint::getY).average().orElse(0);

        for (DataPoint point : data) {
            double predictedY = predict(point.getX());
            sumSquaredResiduals += Math.pow(point.getY() - predictedY, 2);
            sumSquaredTotal += Math.pow(point.getY() - meanY, 2);
        }

        return 1 - (sumSquaredResiduals / sumSquaredTotal);
    }
}

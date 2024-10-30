import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase principal para ejecutar la regresión lineal.
 * Carga el dataset, entrena el modelo y evalúa el ajuste.
 */
public class Main {
    public static void main(String[] args) {
        List<DataPoint> dataset = loadDataset("src/dataset.csv");

        // Comprobar si se cargó el dataset correctamente
        if (dataset.isEmpty()) {
            System.out.println("No se pudo cargar el dataset.");
            return;
        }

        // Segmentación 70%-30% para entrenamiento y prueba
        Collections.shuffle(dataset); // Mezcla los datos de forma aleatoria
        int splitIndex = (int) (dataset.size() * 0.7);
        List<DataPoint> trainingData = dataset.subList(0, splitIndex);
        List<DataPoint> testData = dataset.subList(splitIndex, dataset.size());

        // Entrenar el modelo de regresión lineal
        LinearModel model = new LinearModel();
        model.train(trainingData);

        // Evaluar el modelo en el conjunto de prueba
        double rSquared = model.calculateRSquared(testData);
        System.out.println("Coeficiente de determinación (R^2): " + rSquared);

        // Prueba de predicción
        double prediction = model.predict(12);
        System.out.println("Predicción para x = 12: " + prediction);
    }

    /**
     * Carga el dataset desde un archivo CSV.
     * Cada línea del archivo debe tener dos valores: x e y, separados por comas.
     */
    private static List<DataPoint> loadDataset(String filePath) {
        List<DataPoint> dataset = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Leer la primera línea (encabezado)
            while ((line = br.readLine()) != null) { // Leer el resto de las líneas
                String[] values = line.split(",");
                double x = Double.parseDouble(values[0]);
                double y = Double.parseDouble(values[1]);
                dataset.add(new DataPoint(x, y));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return dataset;
    }
}

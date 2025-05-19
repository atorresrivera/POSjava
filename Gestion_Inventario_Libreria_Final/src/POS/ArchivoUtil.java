package POS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoUtil {

    // Leer líneas de un archivo CSV
    public static List<String> leerArchivo(String nombreArchivo) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo: " + nombreArchivo);
        }
        return lineas;
    }

    // Escribir una lista de líneas (sobrescribe)
    public static void escribirArchivo(String nombreArchivo, List<String> lineas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + nombreArchivo);
        }
    }

    // Agregar una línea (sin borrar lo anterior)
    public static void agregarLinea(String nombreArchivo, String linea) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al agregar línea al archivo: " + nombreArchivo);
        }
    }
}

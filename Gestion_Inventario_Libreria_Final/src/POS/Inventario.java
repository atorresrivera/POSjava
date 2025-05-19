package POS;

import java.util.*;
import java.io.*;

public class Inventario {
    private Map<String, Libro> libros; // idLibro → Libro
    private final String archivoLibros = "libros.csv";

    public Inventario() {
        libros = new HashMap<>();
        cargarLibros();
    }

    // Cargar libros desde el archivo CSV
    private void cargarLibros() {
        List<String> lineas = ArchivoUtil.leerArchivo(archivoLibros);
        for (String linea : lineas) {
            String[] partes = linea.split(",");
            if (partes.length == 5) {
                String id = partes[0];
                String titulo = partes[1];
                String autor = partes[2];
                double precio = Double.parseDouble(partes[3]);
                int cantidad = Integer.parseInt(partes[4]);

                Libro libro = new Libro(id, titulo, autor, precio, cantidad);
                libros.put(id, libro);
            }
        }
    }

    // Mostrar todos los libros
    public void mostrarLibros() {
        if (libros.isEmpty()) {
            System.out.println("Inventario vacío.");
        } else {
            for (Libro libro : libros.values()) {
                System.out.println(libro);
            }
        }
    }

    // Agregar un nuevo libro
    public void agregarLibro(Libro libro) {
        libros.put(libro.getId(), libro);
        guardarLibros();
    }

    // Buscar un libro por ID
    public Libro buscarLibro(String id) {
        return libros.get(id);
    }

    // Actualizar cantidad de un libro
    public void actualizarCantidad(String id, int nuevaCantidad) {
        Libro libro = libros.get(id);
        if (libro != null) {
            libro.setCantidad(nuevaCantidad);
            guardarLibros();
        }
    }

    // Guardar libros al archivo
    private void guardarLibros() {
        List<String> lineas = new ArrayList<>();
        for (Libro libro : libros.values()) {
            String linea = libro.getId() + "," +
                           libro.getTitulo() + "," +
                           libro.getAutor() + "," +
                           libro.getPrecio() + "," +
                           libro.getCantidad();
            lineas.add(linea);
        }
        ArchivoUtil.escribirArchivo(archivoLibros, lineas);
    }

    // Obtener lista de libros (para futuras operaciones)
    public Collection<Libro> getListaLibros() {
        return libros.values();
    }
}

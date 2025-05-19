package POS;

import java.time.LocalDateTime;
import java.util.*;

public class GestorVentas {
    private final String archivoVentas = "ventas.csv";
    private List<Venta> ventas;
    private Inventario inventario;

    public GestorVentas(Inventario inventario) {
        this.inventario = inventario;
        ventas = new ArrayList<>();
        cargarVentas();
    }

    // Registrar una nueva venta
    public void registrarVenta(Scanner scanner) {
        System.out.print("ID del libro: ");
        String idLibro = scanner.nextLine();

        Libro libro = inventario.buscarLibro(idLibro);
        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        System.out.print("Cantidad a vender: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        if (cantidad > libro.getCantidad()) {
            System.out.println("No hay suficiente stock disponible.");
            return;
        }

        double total = libro.getPrecio() * cantidad;
        String idVenta = "V" + (ventas.size() + 1); // Generar ID simple
        LocalDateTime fecha = LocalDateTime.now();

        Venta venta = new Venta(idVenta, idLibro, cantidad, total, fecha);
        ventas.add(venta);
        ArchivoUtil.agregarLinea(archivoVentas, venta.toCSV());

        // Actualizar inventario
        inventario.actualizarCantidad(idLibro, libro.getCantidad() - cantidad);

        System.out.println("Venta registrada con éxito:");
        System.out.println(venta);
    }

    // Mostrar todas las ventas
    public void mostrarVentas() {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            for (Venta venta : ventas) {
                System.out.println(venta);
            }
        }
    }

    // Cargar ventas desde archivo CSV
    private void cargarVentas() {
        List<String> lineas = ArchivoUtil.leerArchivo(archivoVentas);
        for (String linea : lineas) {
            String[] partes = linea.split(",");
            if (partes.length == 5) {
                String idVenta = partes[0];
                String idLibro = partes[1];
                int cantidad = Integer.parseInt(partes[2]);
                double total = Double.parseDouble(partes[3]);
                LocalDateTime fecha = LocalDateTime.parse(partes[4].trim().replace(" ", "T")); // Reemplazar espacio por T
                ventas.add(new Venta(idVenta, idLibro, cantidad, total, fecha));
            }
        }
    }

    // Obtener ventas (para reportes)
    public List<Venta> getVentas() {
        return ventas;
    }
}

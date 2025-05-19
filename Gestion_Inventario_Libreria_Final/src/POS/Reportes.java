package POS;

import java.util.*;

public class Reportes {
    private GestorVentas gestorVentas;
    private Inventario inventario;

    public Reportes(GestorVentas gestorVentas, Inventario inventario) {
        this.gestorVentas = gestorVentas;
        this.inventario = inventario;
    }

    public void mostrarSubmenuReportes(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n=== Submenú de Reportes ===");
            System.out.println("1. Total de ventas realizadas");
            System.out.println("2. Libro más vendido");
            System.out.println("3. Total de ingresos");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    totalVentas();
                    break;
                case 2:
                    libroMasVendido();
                    break;
                case 3:
                    totalIngresos();
                    break;
                case 4:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }

    private void totalVentas() {
        System.out.println("Total de ventas realizadas: " + gestorVentas.getVentas().size());
    }

    private void libroMasVendido() {
        Map<String, Integer> contador = new HashMap<>();

        for (Venta venta : gestorVentas.getVentas()) {
            contador.put(
                venta.getIdLibro(),
                contador.getOrDefault(venta.getIdLibro(), 0) + venta.getCantidadVendida()
            );
        }

        String idMasVendido = null;
        int maxCantidad = 0;

        for (Map.Entry<String, Integer> entry : contador.entrySet()) {
            if (entry.getValue() > maxCantidad) {
                idMasVendido = entry.getKey();
                maxCantidad = entry.getValue();
            }
        }

        if (idMasVendido != null) {
            Libro libro = inventario.buscarLibro(idMasVendido);
            System.out.println("Libro más vendido: " + (libro != null ? libro.getTitulo() : idMasVendido));
            System.out.println("Cantidad vendida: " + maxCantidad);
        } else {
            System.out.println("No hay ventas registradas.");
        }
    }

    private void totalIngresos() {
        double total = 0;
        for (Venta venta : gestorVentas.getVentas()) {
            total += venta.getTotal();
        }
        System.out.println("Total de ingresos por ventas: $" + total);
    }
}

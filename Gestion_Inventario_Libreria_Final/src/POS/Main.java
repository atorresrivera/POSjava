package POS;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();
        GestorVentas gestorVentas = new GestorVentas(inventario);
        Reportes reportes = new Reportes(gestorVentas, inventario);

        int opcion;
        do {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. Registrar nueva venta");
            System.out.println("2. Ver Inventario");
            System.out.println("3. Reportes");
            System.out.println("4. Ver Ventas");
            System.out.println("5. Guardar y Salir");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    gestorVentas.registrarVenta(scanner);
                    break;
                case 2:
                    inventario.mostrarLibros();
                    System.out.print("¿Quieres agregar un nuevo libro? (s/n): ");
                    String respuesta = scanner.nextLine().trim().toLowerCase();
                    if (respuesta.equals("s")) {
                        System.out.print("ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Autor: ");
                        String autor = scanner.nextLine();
                        System.out.print("Precio: ");
                        double precio = Double.parseDouble(scanner.nextLine());
                        System.out.print("Cantidad: ");
                        int cantidad = Integer.parseInt(scanner.nextLine());

                        Libro nuevoLibro = new Libro(id, titulo, autor, precio, cantidad);
                        inventario.agregarLibro(nuevoLibro);

                        System.out.println("Libro agregado exitosamente.");
                    }
                    break;

                case 3:
                    reportes.mostrarSubmenuReportes(scanner);
                    break;
                case 4:
                    gestorVentas.mostrarVentas();
                    break;
                case 5:
                    System.out.println("Guardando y saliendo del sistema...");
                    break;
                case 99:
                    // Opción oculta para agregar libro
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Precio: ");
                    double precio = Double.parseDouble(scanner.nextLine());
                    System.out.print("Cantidad: ");
                    int cantidad = Integer.parseInt(scanner.nextLine());

                    Libro nuevoLibro = new Libro(id, titulo, autor, precio, cantidad);
                    inventario.agregarLibro(nuevoLibro);
                    System.out.println("Libro agregado al inventario.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}

package POS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venta {
    private String idVenta;
    private String idLibro;
    private int cantidadVendida;
    private double total;
    private LocalDateTime fechaHora;

    public Venta(String idVenta, String idLibro, int cantidadVendida, double total, LocalDateTime fechaHora) {
        this.idVenta = idVenta;
        this.idLibro = idLibro;
        this.cantidadVendida = cantidadVendida;
        this.total = total;
        this.fechaHora = fechaHora;
    }

    // Getters
    public String getIdVenta() { return idVenta; }
    public String getIdLibro() { return idLibro; }
    public int getCantidadVendida() { return cantidadVendida; }
    public double getTotal() { return total; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    // Formato para guardar en archivo CSV
    public String toCSV() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return idVenta + "," + idLibro + "," + cantidadVendida + "," + total + "," + fechaHora.format(formatter);
    }

    // Mostrar en consola
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Venta ID: " + idVenta +
               ", Libro ID: " + idLibro +
               ", Cantidad: " + cantidadVendida +
               ", Total: $" + total +
               ", Fecha: " + fechaHora.format(formatter);
    }
}

package POS;

public class Libro {
    private String id;
    private String titulo;
    private String autor;
    private double precio;
    private int cantidad;

    public Libro(String id, String titulo, String autor, double precio, int cantidad) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Para mostrar información del libro
    @Override
    public String toString() {
        return id + " | " + titulo + " | " + autor + " | $" + precio + " | Cantidad: " + cantidad;
    }
}

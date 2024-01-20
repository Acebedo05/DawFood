package daw.productos;

import java.util.Objects;

/**
 *
 * @author khalid
 */
public class Comida {

    private String id;
    private String nombre;
    private boolean enStock;
    private String categoria;
    private String subcategoria;
    private String descripcion;
    private double precio;
    private double iva;

    public Comida(String id, String nombre, boolean enStock, String categoria, String subcategoria, String descripcion, double precio, double iva) {
        this.id = id;
        this.nombre = nombre;
        this.enStock = enStock;
        this.categoria = "Comida";
        this.subcategoria = subcategoria;
        this.descripcion = descripcion;
        this.precio = precio;
        this.iva = iva;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEnStock() {
        return enStock;
    }

    public void setEnStock(boolean enStock) {
        this.enStock = enStock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Comida{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", enStock=").append(enStock);
        sb.append(", categoria=").append(categoria);
        sb.append(", subcategoria=").append(subcategoria);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", precio=").append(precio);
        sb.append(", iva=").append(iva);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.nombre);
        hash = 61 * hash + (this.enStock ? 1 : 0);
        hash = 61 * hash + Objects.hashCode(this.categoria);
        hash = 61 * hash + Objects.hashCode(this.subcategoria);
        hash = 61 * hash + Objects.hashCode(this.descripcion);
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.precio) ^ (Double.doubleToLongBits(this.precio) >>> 32));
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.iva) ^ (Double.doubleToLongBits(this.iva) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comida other = (Comida) obj;
        if (this.enStock != other.enStock) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (Double.doubleToLongBits(this.iva) != Double.doubleToLongBits(other.iva)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.subcategoria, other.subcategoria)) {
            return false;
        }
        return Objects.equals(this.descripcion, other.descripcion);
    }

}

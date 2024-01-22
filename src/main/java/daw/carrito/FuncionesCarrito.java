package daw.carrito;

import daw.modos.FuncionesUsuario;
import daw.productos.Producto;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acebedo
 */
public class FuncionesCarrito {

    private List<Producto> carrito;
    private FuncionesUsuario funcionesUsuario;

    public FuncionesCarrito(FuncionesUsuario funcionesUsuario) {
        this.carrito = new ArrayList<>();
        this.funcionesUsuario = funcionesUsuario;
    }

    // Método para agregar productos seleccionados
    public void agregarProductoSeleccionado(Producto producto) {
        carrito.add(producto);
    }

    // Método para mostrar el menú de productos seleccionados con precios usando JOptionPane
    public void mostrarMenuCarritoConPrecios() {
        StringBuilder mensaje = new StringBuilder("Productos Seleccionados:\n");
        double precioTotalSinIVA = 0;
        double precioTotalConIVA = 0;

        for (Producto producto : carrito) {
            mensaje.append(producto.getNombre()).append(": ").append(producto.getPrecio()).append(" € (sin IVA)\n");

            // Calcular el precio total sin IVA
            precioTotalSinIVA += producto.getPrecio();

            // Calcular el precio total con IVA
            precioTotalConIVA += producto.getPrecioConIVA();
        }

        mensaje.append("Precio Total sin IVA: ").append(precioTotalSinIVA).append(" €\n");
        mensaje.append("Precio Total con IVA: ").append(precioTotalConIVA).append(" €");

        // Mostrar el mensaje utilizando JOptionPane
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Carrito", JOptionPane.INFORMATION_MESSAGE);
        
        // Llamar al método menuSeleccion de FuncionesUsuario.
        funcionesUsuario.menuSeleccion();
    }
}


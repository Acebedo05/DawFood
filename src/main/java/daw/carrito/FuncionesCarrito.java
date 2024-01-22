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

    private static List<Producto> carrito = new ArrayList<>();
    ;
    private FuncionesUsuario funcionesUsuario;

    public FuncionesCarrito(FuncionesUsuario funcionesUsuario) {
        this.funcionesUsuario = funcionesUsuario;
    }

    // Método para agregar productos seleccionados
    public static void agregarProductoAlCarrito(Producto producto) {
        // Pedir al usuario que ingrese la cantidad utilizando JOptionPane
        String cantidada = JOptionPane.showInputDialog("Ingrese la cantidad de '" + producto.getNombre() + "' que desea agregar al carrito:");

        try {
            // Convertir la cantidad ingresada a un número entero
            int cantidad = Integer.parseInt(cantidada);

            if (cantidad > 0) {
                for (int i = 0; i < cantidad; i++) {
                    // Agregar el producto al carrito la cantidad especificada de veces
                    carrito.add(producto);
                }

                JOptionPane.showMessageDialog(null, cantidad + " x '" + producto.getNombre() + "' ha sido agregado al carrito.");
            } else {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor que 0. No se ha agregado nada al carrito.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para mostrar el menú de productos seleccionados con precios usando JOptionPane
    public void mostrarMenuCarritoConPrecios() {
        try {
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

            mensaje.append("Precio Total sin IVA: ").append(String.format("%.2f", precioTotalSinIVA)).append(" €\n");
            mensaje.append("Precio Total con IVA: ").append(String.format("%.2f", precioTotalConIVA)).append(" €");

            // Mostrar el mensaje utilizando JOptionPane
            JOptionPane.showMessageDialog(null, mensaje.toString(), "Carrito", JOptionPane.INFORMATION_MESSAGE);

            // Llamar al método menuSeleccion de FuncionesUsuario
            funcionesUsuario.menuSeleccion();
        } catch (NullPointerException e) {
            // Imprimir la excepción en la terminal
            System.out.println("Excepción NullPointerException: " + e.getMessage());
        }
    }
}

package daw.carrito;

import daw.modos.FuncionesUsuario;
import daw.productos.Producto;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acebedo
 */
public class FuncionesCarrito {

    private static List<Producto> carrito = new ArrayList<>();
    private static List<AtributosTarjeta> listaDeTarjetas = new ArrayList<>();

    private FuncionesUsuario funcionesUsuario;

    public FuncionesCarrito(FuncionesUsuario funcionesUsuario) {
        this.funcionesUsuario = funcionesUsuario;
        listaDeTarjetas.add(new AtributosTarjeta(1234, LocalDate.of(2025, 12, 1), 123, 1000, "Juan Perez"));
        listaDeTarjetas.add(new AtributosTarjeta(5432, LocalDate.of(2025, 10, 4), 456, 500, "Maria Rodriguez"));
        listaDeTarjetas.add(new AtributosTarjeta(1122, LocalDate.of(2025, 8, 23), 789, 200, "Pedro Gomez"));
        listaDeTarjetas.add(new AtributosTarjeta(3322, LocalDate.of(2025, 5, 22), 234, 1500, "Ana Martinez"));
        listaDeTarjetas.add(new AtributosTarjeta(1422, LocalDate.of(2025, 3, 21), 567, 3000, "Carlos Herrera"));
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

    public static void noComprar() {
        carrito.clear();
    }

    // Método para realizar el proceso de pago
    public static void procesarPago() {
        boolean tarjetaValida = false;

        while (!tarjetaValida) {
            try {
                // Solicitar al usuario el número de tarjeta
                String stringNumTarj = JOptionPane.showInputDialog("Ingrese el número de su tarjeta:");

                // Verificar si la entrada es un número
                if (!esNumero(stringNumTarj)) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el número de tarjeta.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int numeroTarjeta = Integer.parseInt(stringNumTarj);

                    // Verificar si la tarjeta con ese número existe
                    AtributosTarjeta tarjeta = obtenerTarjetaPorNumero(numeroTarjeta);

                    if (tarjeta != null) {

                        // Si la tarjeta existe, solicitar la fecha de caducidad y el CVV
                        String fechaCaducidad = JOptionPane.showInputDialog("Ingrese la fecha de caducidad (MM/YY):");
                        String cvv = JOptionPane.showInputDialog("Ingrese el CVV:");

                        // Verificar que la fecha de caducidad y el CVV no sean nulos o vacíos
                        if (fechaCaducidad != null && cvv != null && !fechaCaducidad.isEmpty() && !cvv.isEmpty()) {
                            // Verificar la fecha de caducidad y el CVV
                            if (verificarFechaYCVV(tarjeta, fechaCaducidad, cvv)) {
                                // Calcular el precio total con IVA
                                double precioTotalConIVA = calcularPrecioTotalConIVA();

                                // Verificar si hay suficiente saldo para la compra
                                if (tarjeta.getSaldo() >= precioTotalConIVA) {
                                    // Realizar la compra descontando el saldo
                                    tarjeta.setSaldo(tarjeta.getSaldo() - precioTotalConIVA);

                                    // Llamar al método ticket.
                                    ticket();

                                    // Limpiar el carrito después de la compra
                                    carrito.clear();

                                    // Mostrar mensaje de compra realizada
                                    JOptionPane.showMessageDialog(null, "Compra realizada. Gracias por su compra!");

                                    // Salir del bucle
                                    tarjetaValida = true;
                                } else {
                                    // Mostrar mensaje de saldo insuficiente
                                    JOptionPane.showMessageDialog(null, "Saldo insuficiente. Ingrese otra tarjeta.");
                                }
                            } else {
                                // Mostrar mensaje de fecha de caducidad o CVV incorrecto
                                JOptionPane.showMessageDialog(null, "Fecha de caducidad o CVV incorrecto. Ingrese otra tarjeta.");
                            }
                        } else {
                            // Mostrar mensaje de fecha de caducidad o CVV nulo o vacío
                            JOptionPane.showMessageDialog(null, "Fecha de caducidad o CVV incorrecto. Ingrese otra tarjeta.");
                        }

                    } else {
                        // Mostrar mensaje de tarjeta no encontrada
                        JOptionPane.showMessageDialog(null, "Tarjeta no encontrada. Ingrese otra tarjeta.");
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para verificar si una cadena es un número
    private static boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static AtributosTarjeta obtenerTarjetaPorNumero(int numeroTarjeta) {
        for (AtributosTarjeta tarjeta : listaDeTarjetas) {
            if (tarjeta.getNumeroTarjeta() == numeroTarjeta) {
                return tarjeta;
            }
        }
        return null; // Retorna null si no se encuentra la tarjeta con el número proporcionado
    }

    // Método para verificar la fecha de caducidad y el CVV
    private static boolean verificarFechaYCVV(AtributosTarjeta tarjeta, String fechaCaducidad, String cvv) {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Verificar la fecha de caducidad
        if (tarjeta.getFechaVencimiento().isAfter(fechaActual) && fechaCaducidad.length() == 5) {
            // Verificar el CVV
            return tarjeta.getCvv() == Integer.parseInt(cvv);
        }

        return false;
    }

    // Método para calcular el precio total con IVA
    private static double calcularPrecioTotalConIVA() {
        double precioTotalConIVA = 0;

        for (Producto producto : carrito) {
            precioTotalConIVA += producto.getPrecioConIVA();
        }

        return precioTotalConIVA;
    }

    // Método para el ticket (Por Hacer)
    private static void ticket() {

    }

}

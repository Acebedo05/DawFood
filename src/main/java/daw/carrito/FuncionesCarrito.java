package daw.carrito;

import daw.modos.FuncionesUsuario;
import daw.productos.Producto;
import daw.tpv.FuncionesTPV;
import daw.tpv.ObjetosTPV;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private static List<AtributosTicket> listaDeTickets = new ArrayList<>();

    private FuncionesUsuario funcionesUsuario;
    private static int idPedidoContador = 1;

    public FuncionesCarrito(FuncionesUsuario funcionesUsuario) {
        this.funcionesUsuario = funcionesUsuario;
        listaDeTarjetas.add(new AtributosTarjeta(1234, LocalDate.of(2025, 12, 1), 123, 100, "Juan Perez"));
        listaDeTarjetas.add(new AtributosTarjeta(5432, LocalDate.of(2025, 10, 4), 456, 500, "Maria Rodriguez"));
        listaDeTarjetas.add(new AtributosTarjeta(1122, LocalDate.of(2025, 8, 23), 789, 200, "Pedro Gomez"));
        listaDeTarjetas.add(new AtributosTarjeta(3322, LocalDate.of(2025, 5, 22), 234, 1500, "Ana Martinez"));
        listaDeTarjetas.add(new AtributosTarjeta(1422, LocalDate.of(2025, 3, 21), 567, 3000, "Carlos Herrera"));
    }

    // Método para agregar productos seleccionados
    public static void agregarProductoAlCarrito(Producto producto) {
        // Verificar si el producto está en stock
        if (!producto.isEnStock()) {
            JOptionPane.showMessageDialog(null, "El producto '" + producto.getNombre() + "' no está disponible en stock.", "Producto no disponible", JOptionPane.WARNING_MESSAGE);
            return;
        }

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
        double precioTotal = calcularPrecioTotalConIVA();

        if (precioTotal != 0) {
            boolean tarjetaValida = false;
            int intentos = 0;

            while (!tarjetaValida && intentos < 2) {
                try {
                    // Solicitar al usuario el número de tarjeta
                    String stringNumTarj = JOptionPane.showInputDialog("Ingrese el número de su tarjeta:");

                    // Verificar si la entrada es un número
                    if (!esNumero(stringNumTarj)) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el número de tarjeta.", "Error", JOptionPane.ERROR_MESSAGE);
                        intentos++;
                    } else {
                        int numeroTarjeta = Integer.parseInt(stringNumTarj);

                        // Verificar si la tarjeta con ese número existe
                        AtributosTarjeta tarjeta = obtenerTarjetaPorNumero(numeroTarjeta);

                        if (tarjeta != null) {

                            // Si la tarjeta existe, solicitar la fecha de caducidad y el CVV
                            String fechaCaducidad = JOptionPane.showInputDialog("Ingrese la fecha de caducidad (YY/MM):");
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

                                        // Mostrar mensaje de compra realizada
                                        JOptionPane.showMessageDialog(null, "Compra realizada. Gracias por su compra!");

                                        // Llamar al método ticket.
                                        ticket();

                                        // Limpiar el carrito después de la compra
                                        carrito.clear();

                                        // Salir del bucle
                                        tarjetaValida = true;

                                        // Llamar al método encenderTPV.
                                        llamarEncenderTPV();

                                    } else {
                                        // Mostrar mensaje de saldo insuficiente
                                        JOptionPane.showMessageDialog(null, "Saldo insuficiente. Ingrese otra tarjeta.");
                                        intentos++;
                                    }
                                } else {
                                    // Mostrar mensaje de fecha de caducidad o CVV incorrecto
                                    JOptionPane.showMessageDialog(null, "Fecha de caducidad o CVV incorrecto. Ingrese otra tarjeta.");
                                    intentos++;
                                }
                            } else {
                                // Mostrar mensaje de fecha de caducidad o CVV nulo o vacío
                                JOptionPane.showMessageDialog(null, "Fecha de caducidad o CVV incorrecto. Ingrese otra tarjeta.");
                                intentos++;
                            }

                        } else {
                            // Mostrar mensaje de tarjeta no encontrada
                            JOptionPane.showMessageDialog(null, "Tarjeta no encontrada. Ingrese otra tarjeta.");
                            intentos++;
                        }
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    intentos++;
                }
            }

            if (intentos >= 2) {
                // Limpiar el carrito después de la compra
                carrito.clear();

                // Llamar al método encenderTPV.
                llamarEncenderTPV();
            }

        } else {
            // Mostrar mensaje de carrito vacío
            JOptionPane.showMessageDialog(null, "El carrito está vacío. No se puede realizar la compra.");

            // Limpiar el carrito después de la compra
            carrito.clear();

            // Llamar al método encenderTPV.
            llamarEncenderTPV();
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

    // Método para el ticket
    private static void ticket() {
        double precioFinal = calcularPrecioTotalConIVA();
        int idPedido = generarIdPedido();
        LocalDateTime fechaYHoraOperacion = LocalDateTime.now();

        StringBuilder productosComprados = new StringBuilder("\nProductos Seleccionados:\n");
        for (Producto producto : carrito) {
            productosComprados.append("ID: ").append(producto.getId()).append(" -- ");
            productosComprados.append("Nombre: ").append(producto.getNombre()).append(" -- ");
            productosComprados.append("Descripción: ").append(producto.getDescripcion()).append(" -- ");
            productosComprados.append("Precio: ").append(producto.getPrecio()).append(" € (sin IVA) -- ");
            productosComprados.append("IVA: ").append(producto.getIva()).append("\n");
        }

        AtributosTicket atributosTicket = new AtributosTicket(precioFinal, idPedido, fechaYHoraOperacion, productosComprados.toString());

        // Agregar el ticket a la lista
        listaDeTickets.add(atributosTicket);

        // Mostrar el ticket utilizando JOptionPane
        JOptionPane.showMessageDialog(null, atributosTicket.toString(), "Ticket de Compra", JOptionPane.INFORMATION_MESSAGE);

    }

    // Método para generar un ID de pedido único
    private static int generarIdPedido() {
        return idPedidoContador++;
    }

    // Método para volver al Menú Inicial
    private static void llamarEncenderTPV() {
        // Llamando al método encenderTPV de la clase FuncionesTPV
        FuncionesTPV funcionesTPV = ObjetosTPV.inicializarTPV();
        funcionesTPV.encenderTPV();
    }

    // Método para consultar las ventas realizadas.
    public static void consultarVentas() {
        // Preguntar al usuario qué tipo de consulta desea realizar
        String[] opciones = {"Ver las ventas de un día concreto", "Ver las ventas de hasta una fecha concreta", "Ver todas las ventas"};
        int seleccion = JOptionPane.showOptionDialog(null, "¿Qué prefieres?", "Consulta de Ventas", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;
        boolean mostrarTodos = false;

        switch (seleccion) {
            case 0:
                // Ver las ventas de un día concreto
                String fechaStr = JOptionPane.showInputDialog("Ingrese la fecha en formato YYYY-MM-DD:");
                try {
                    fechaInicio = LocalDate.parse(fechaStr);
                    fechaFin = fechaInicio.plusDays(1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Fecha inválida. Mostrando todas las ventas.");
                    mostrarTodos = true;
                }
                break;

            case 1:
                // Ver las ventas hasta una fecha concreta
                String fechaFinStr = JOptionPane.showInputDialog("Ingrese la fecha hasta la cual desea ver las ventas en formato YYYY-MM-DD:");
                try {
                    fechaFin = LocalDate.parse(fechaFinStr).plusDays(1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Fecha inválida. Mostrando todas las ventas.");
                    mostrarTodos = true;
                }
                break;

            case 2:
                // Ver todas las ventas
                mostrarTodos = true;
                break;

            default:
                // Opción no válida, mostrar todas las ventas
                mostrarTodos = true;
                break;
        }

        // Filtrar y mostrar los tickets.
        List<AtributosTicket> ticketsFiltrados = filtrarTickets(fechaInicio, fechaFin, mostrarTodos);

        // Verificar si hay tickets registrados.
        if (ticketsFiltrados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay ventas registradas en el período especificado.");
        } else {
            // Mostrar los tickets.
            StringBuilder mensaje = new StringBuilder("Ventas Realizadas:\n\n");

            for (AtributosTicket ticket : ticketsFiltrados) {
                mensaje.append("ID Pedido: ").append(ticket.getIdPedido()).append("\n");
                mensaje.append("Fecha y Hora: ").append(ticket.getFechaYHoraOperacion()).append("\n");
                mensaje.append("Productos:\n").append(ticket.getProductosComprados()).append("\n");
                mensaje.append("Precio Final: ").append(ticket.getPrecioFinal()).append(" €\n\n");
            }

            JOptionPane.showMessageDialog(null, mensaje.toString(), "Ventas Realizadas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para filtrar los tickets según las fechas y mostrarTodos
    private static List<AtributosTicket> filtrarTickets(LocalDate fechaInicio, LocalDate fechaFin, boolean mostrarTodos) {
        List<AtributosTicket> ticketsFiltrados = new ArrayList<>();

        for (AtributosTicket ticket : listaDeTickets) {
            LocalDate fechaOperacion = ticket.getFechaYHoraOperacion().toLocalDate();

            if ((fechaInicio == null || fechaOperacion.isAfter(fechaInicio) || fechaOperacion.isEqual(fechaInicio))
                    && (fechaFin == null || fechaOperacion.isBefore(fechaFin) || fechaOperacion.isEqual(fechaFin))
                    && (mostrarTodos || fechaOperacion.isEqual(LocalDate.now()))) {
                ticketsFiltrados.add(ticket);
            }
        }

        return ticketsFiltrados;
    }
}

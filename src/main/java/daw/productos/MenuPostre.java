package daw.productos;

import daw.carrito.FuncionesCarrito;
import daw.modos.FuncionesUsuario;
import daw.tpv.FuncionesTPV;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author acebedo
 */
public class MenuPostre {

    private FuncionesTPV funcionesTPV;
    private FuncionesCarrito funcionesCarrito;
    private FuncionesUsuario funcionesUsuario;

    public MenuPostre(FuncionesTPV funcionesTPV) {
        // Almacena la referencia a FuncionesTPV.
        this.funcionesTPV = funcionesTPV;
        this.funcionesCarrito = new FuncionesCarrito(this.funcionesUsuario);
    }

    // Listas para almacenar las diferentes categorías de postres.
    private static List<Producto> caseros = new ArrayList<>();

// Constructor de la clase, se inicializan las listas y se llama al método principal.
    public void MenuPostre() {
        // Mostrar el menú principal de comidas.
        menuSeleccionPostres();
    }

    public static void llamarInicializarPostre() {
        // Inicializar las listas.
        inicializarCaseros();
    }

    // Inicializar la lista de caseros con datos.
    private static void inicializarCaseros() {
        caseros.add(new Producto("C01", "Flan", 3.99, true, "Flan Casero", "Postre", 0.10, "Casero"));
        caseros.add(new Producto("C02", "Natilla", 3.99, true, "Natilla Casera", "Postre", 0.10, "Casero"));
        caseros.add(new Producto("C03", "Helado", 3.99, true, "Helado Casero", "Postre", 0.10, "Casero"));
    }

    // Método principal que muestra el menú de selección para el usuario.
    public void menuSeleccionPostres() {
        // Crear la ventana del menú.
        JFrame frame = new JFrame("Tipos de Postres");

        // Crear un panel para colocar los botones.
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        agregarBoton(panel, "Caseros", e -> {
            menuSeleccionCaseros();
            frame.dispose();
        });
        agregarBoton(panel, "Volver al menú de selección", e -> {
            funcionesTPV.iniciarModoUsuario();
            frame.dispose();
        });
        agregarBoton(panel, "Ver carrito", e -> {
            funcionesCarrito.mostrarMenuCarritoConPrecios();
        });

        frame.add(panel);
        frame.setSize(400, 160);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Método para agregar un botón al panel
    private void agregarBoton(JPanel panel, String texto, ActionListener actionListener) {
        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        JButton boton = new JButton(texto);
        boton.addActionListener(actionListener);
        // Agregar botones al panel.
        panel.add(boton);
    }

    // Método principal que muestra el menú de caseros al usuario.
    public void menuSeleccionCaseros() {
        // Crear la ventana del menú.
        JFrame frame = new JFrame("Tipos de Caseros");

        // Crear un panel para colocar los botones.
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        for (Producto caseros : caseros) {
            if ("Casero".equals(caseros.getSubcategoria())) {
                agregarBoton(panel, caseros.getNombre(), e -> {
                    FuncionesCarrito.agregarProductoAlCarrito(caseros);
                });
            }
        }
        agregarBoton(panel, "Consultar precios", e -> {
            consultarPreciosCaseros();
            frame.dispose();
        });
        agregarBoton(panel, "Volver al menú de selección", e -> {
            funcionesTPV.iniciarModoUsuario();
            frame.dispose();
        });
        agregarBoton(panel, "Ver carrito", e -> funcionesCarrito.mostrarMenuCarritoConPrecios());

        frame.add(panel);
        frame.setSize(400, 160);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Método para consultar precios de caseros    
    private void consultarPreciosCaseros() {
        // Obtener precios de caseros desde la lista
        StringBuilder preciosCaseros = new StringBuilder("Precios de Caseros:\n");

        // Iterar sobre la lista de Casero y mostrar solo los productos de la SubCategoría "Casero".
        for (Producto producto : caseros) {
            if ("Casero".equals(producto.getSubcategoria())) {
                preciosCaseros.append(producto.getNombre()).append(": ").append(producto.getPrecio()).append(" €").append("\n");
            }
        }

        // Mostrar el mensaje utilizando JOptionPane.
        JOptionPane.showMessageDialog(null, preciosCaseros.toString());

        // Volver al menú de selección de caseros.
        menuSeleccionCaseros();
    }

    // Metodo para añadir nuevo producto a caseros.
    public void añadirProductoACaseros() {

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nuevo producto:");

        // Validar si ya existe un producto con el mismo nombre.
        if (nombreProductoExistente(nombre)) {
            JOptionPane.showMessageDialog(null, "Ya existe un producto con el nombre proporcionado.");
            return;
        }

        double precio = obtenerPrecioValido();
        boolean enStock = true;
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripción del nuevo producto:");
        String categoria = "Comida";
        double iva = obtenerIVAValido();
        String subcategoria = obtenerSubcategoriaValida();
        String id = obtenerNuevoID(subcategoria);

        Producto nuevoProducto = new Producto(id, nombre, precio, enStock, descripcion, categoria, iva, subcategoria);
        caseros.add(nuevoProducto);

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(null, "Producto añadido correctamente a la lista de caseros.");

    }

    // Método para verificar si ya existe un producto con el mismo nombre.
    private boolean nombreProductoExistente(String nombre) {
        for (Producto producto : caseros) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return true; // Ya existe un producto con el mismo nombre.
            }
        }
        return false; // No hay ningún producto con el mismo nombre.
    }

    // Método para obtener un precio válido (mayor o igual a 0)
    private double obtenerPrecioValido() {
        double precio;
        while (true) {
            try {
                precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del nuevo producto:"));
                if (precio < 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un precio válido igual o mayor que 0.");
            }
        }
        return precio;
    }

    // Método para obtener un porcentaje de IVA válido (mayor o igual a 0)
    private double obtenerIVAValido() {
        double iva;
        while (true) {
            try {
                iva = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el porcentaje de IVA del nuevo producto (Ej: '0.10'):"));
                if (iva < 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un porcentaje de IVA válido igual o mayor que 0.");
            }
        }
        return iva;
    }

    // Método para obtener una subcategoría válida ("Casero")
    private String obtenerSubcategoriaValida() {
        String subcategoria;
        while (true) {
            subcategoria = JOptionPane.showInputDialog("Ingrese la subcategoría del nuevo producto (Casero):");

            subcategoria = subcategoria.toLowerCase();
            subcategoria = subcategoria.substring(0, 1).toUpperCase() + subcategoria.substring(1);

            if ("Casero".equals(subcategoria)) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una subcategoría válida (Casero).");
            }
        }
        return subcategoria;
    }

    // Método para obtener un ID basado en la subcategoría.
    private String obtenerNuevoID(String subcategoria) {
        String nuevoID;
        char subcategoriaLetra;

        // Determine the prefix based on the subcategoría
        switch (subcategoria.toLowerCase()) {
            case "casero":
                subcategoriaLetra = 'C';
                break;
            default:
                throw new IllegalArgumentException("Subcategoría no válida");
        }

        while (true) {
            nuevoID = JOptionPane.showInputDialog("Ingrese el nuevo ID (EJ: " + subcategoria + ": " + subcategoriaLetra + "01):");

            if (nuevoID.length() == 3
                    && nuevoID.charAt(0) == subcategoriaLetra
                    && Character.isDigit(nuevoID.charAt(1))
                    && Character.isDigit(nuevoID.charAt(2))) {

                boolean idExiste = false;
                for (Producto producto : caseros) {
                    if (producto.getId().equalsIgnoreCase(nuevoID)) {
                        idExiste = true;
                        break;
                    }
                }

                if (!idExiste) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "El ID ingresado ya existe.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El formato del ID es incorrecto. Debe ser " + subcategoriaLetra + " seguido por dos dígitos (EJ: " + subcategoriaLetra + "01).");
            }
        }
        return nuevoID;
    }

    // Metodo para borrar producto de caseros.
    public void borrarProducto(String nombreProducto) {
        // Iterar sobre la lista de caseros para encontrar el producto con el Nombre.
        for (Producto producto : caseros) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                // Eliminar el producto de la lista.
                caseros.remove(producto);

                JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                return;
            }
        }

        // Si el producto con el ID proporcionado no se encuentra, mostrar un mensaje de error.
        JOptionPane.showMessageDialog(null, "No se encontró ningún producto con el nombre proporcionado.");
    }

    // Método para editar producto de caseros.
    public void editarProducto(String nombreProducto) {
        // Buscar el producto en la lista basándose en el nombre proporcionado
        for (Producto producto : caseros) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                // Obtener el nombre actual del producto antes de realizar la edición
                String nombreActual = producto.getNombre();

                // Solicitar al usuario que elija qué atributo desea editar
                String[] opciones = {"Nombre", "Precio", "En Stock", "Descripción", "IVA", "Subcategoría"};
                String eleccion = (String) JOptionPane.showInputDialog(
                        null,
                        "Selecciona el atributo que deseas editar:",
                        "Editar Producto",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                // Actualizar el atributo seleccionado según la elección del usuario
                if (eleccion != null) {
                    switch (eleccion) {
                        case "Nombre":
                            String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del producto:");

                            // Validar si ya existe un producto con el mismo nombre
                            if (nombreProductoExistente(nuevoNombre)) {
                                JOptionPane.showMessageDialog(null, "Ya existe un producto con el nombre proporcionado.");
                                return;
                            }

                            producto.setNombre(nuevoNombre);
                            break;
                        case "Precio":
                            producto.setPrecio(obtenerPrecioValido());
                            break;
                        case "En Stock":
                            producto.setEnStock(obtenerStockValido());
                            break;
                        case "Descripción":
                            producto.setDescripcion(JOptionPane.showInputDialog("Ingrese la nueva descripción del producto:"));
                            break;
                        case "IVA":
                            producto.setIva(obtenerIVAValido());
                            break;
                        case "Subcategoría":
                            producto.setSubcategoria(obtenerSubcategoriaValida());
                            break;
                    }

                    JOptionPane.showMessageDialog(null, "Producto editado correctamente.");
                }

                return;
            }
        }

        // Si no se encuentra el producto con el nombre proporcionado:
        JOptionPane.showMessageDialog(null, "No se encontró ningún producto con el nombre proporcionado.");
    }

    // Método para obtener un valor booleano válido para el stock.
    private boolean obtenerStockValido() {
        String[] opciones = {"Sí", "No"};
        int eleccion = JOptionPane.showOptionDialog(
                null,
                "¿Está este producto en Stock?",
                "Stock",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        return eleccion == 0;  // Devuelve true si se selecciona "Sí", false si se selecciona "No"
    }

}

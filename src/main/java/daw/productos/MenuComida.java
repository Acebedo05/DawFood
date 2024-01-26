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
public class MenuComida {

    private FuncionesTPV funcionesTPV;
    private FuncionesCarrito funcionesCarrito;
    private FuncionesUsuario funcionesUsuario;

    public MenuComida(FuncionesTPV funcionesTPV) {
        // Almacena la referencia a FuncionesTPV.
        this.funcionesTPV = funcionesTPV;
        this.funcionesCarrito = new FuncionesCarrito(this.funcionesUsuario);
    }

    // Listas para almacenar las diferentes categorías de comidas.
    private static List<Producto> comidas = new ArrayList<>();

    // Constructor de la clase, se inicializan las listas y se llama al método principal.
    public void MenuComida() {
        // Mostrar el menú principal de comidas.
        menuSeleccionComidas();
    }

    public static void llamarInicializarComidas() {
        // Inicializar las listas.
        inicializarComidas();
    }

    // Inicializar la lista de pizzas con datos.
    private static void inicializarComidas() {
        comidas.add(new Producto("P01", "Pizza 4 Quesos", 8.99, true, "Pizza con cuatro tipos de quesos", "Comida", 0.10, "Pizza"));
        comidas.add(new Producto("P02", "Pizza Margarita", 9.99, true, "Pizza con queso y tomate", "Comida", 0.10, "Pizza"));
        comidas.add(new Producto("P03", "Pizza Boloñesa", 10.99, true, "Pizza con salsa boloñesa", "Comida", 0.10, "Pizza"));
        comidas.add(new Producto("H01", "Hamburguesa Ternera", 7.99, true, "Hamburguesa de ternera", "Comida", 0.10, "Hamburguesa"));
        comidas.add(new Producto("H02", "Hamburguesa Pollo", 6.99, true, "Hamburguesa de pollo", "Comida", 0.10, "Hamburguesa"));
        comidas.add(new Producto("H03", "Hamburguesa Vegetal", 5.99, true, "Hamburguesa vegetariana", "Comida", 0.10, "Hamburguesa"));
        comidas.add(new Producto("K01", "Kebab Ternera", 9.99, true, "Kebab de ternera", "Comida", 0.10, "Kebab"));
        comidas.add(new Producto("K02", "Kebab Pollo", 8.99, true, "Kebab de pollo", "Comida", 0.10, "Kebab"));
        comidas.add(new Producto("K03", "Kebab Mixto", 10.99, true, "Kebab mixto", "Comida", 0.10, "Kebab"));
    }

    // Método principal que muestra el menú de selección para el usuario.
    public void menuSeleccionComidas() {
        // Crear la ventana del menú.
        JFrame frame = new JFrame("Tipos de Comida");

        // Crear un panel para colocar los botones.
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        agregarBoton(panel, "Pizzas", e -> {
            menuSeleccionPizzas();
            frame.dispose();
        });
        agregarBoton(panel, "Hamburguesas", e -> {
            menuSeleccionHamburguesas();
            frame.dispose();
        });
        agregarBoton(panel, "Kebabs", e -> {
            menuSeleccionKebabs();
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

    // Método principal que muestra el menú de pizzas al usuario.
    public void menuSeleccionPizzas() {
        // Crear la ventana del menú.
        JFrame frame = new JFrame("Tipos de Pizzas");

        // Crear un panel para colocar los botones.
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        for (Producto pizza : comidas) {
            if ("Pizza".equals(pizza.getSubcategoria())) {
                agregarBoton(panel, pizza.getNombre(), e -> {
                    FuncionesCarrito.agregarProductoAlCarrito(pizza);
                });
            }
        }
        agregarBoton(panel, "Consultar precios", e -> {
            consultarPreciosPizzas();
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

    // Método principal que muestra el menú de hamburguesas al usuario.
    public void menuSeleccionHamburguesas() {
        // Crear la ventana del menú.
        JFrame frame = new JFrame("Tipos de Hamburguesas");

        // Crear un panel para colocar los botones.
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        for (Producto hamburguesa : comidas) {
            if ("Hamburguesa".equals(hamburguesa.getSubcategoria())) {
                agregarBoton(panel, hamburguesa.getNombre(), e -> {
                    FuncionesCarrito.agregarProductoAlCarrito(hamburguesa);
                });
            }
        }
        agregarBoton(panel, "Consultar precios", e -> {
            consultarPreciosHamburguesas();
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

    // Método principal que muestra el menú de kebabs al usuario.
    public void menuSeleccionKebabs() {
        // Crear la ventana del menú.
        JFrame frame = new JFrame("Tipos de Kebabs");

        // Crear un panel para colocar los botones.
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        for (Producto kebab : comidas) {
            if ("Kebab".equals(kebab.getSubcategoria())) {
                agregarBoton(panel, kebab.getNombre(), e -> {
                    FuncionesCarrito.agregarProductoAlCarrito(kebab);
                });
            }
        }
        agregarBoton(panel, "Consultar precios", e -> {
            consultarPreciosKebabs();
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

    // Método para consultar precios de hamburguesas.
    private void consultarPreciosHamburguesas() {
        // Obtener precios de hamburguesas desde la lista.
        StringBuilder preciosHamburguesas = new StringBuilder("Precios de Hamburguesas:\n");

        // Iterar sobre la lista de hamburguesas y mostrar solo los productos de la SubCategoría "Hamburguesa".
        for (Producto producto : comidas) {
            if ("Hamburguesa".equals(producto.getSubcategoria())) {
                preciosHamburguesas.append(producto.getNombre()).append(": ").append(producto.getPrecio()).append(" €").append("\n");
            }
        }

        // Mostrar el mensaje utilizando JOptionPane.
        JOptionPane.showMessageDialog(null, preciosHamburguesas.toString());

        // Volver al menú de selección de hamburguesas.
        menuSeleccionHamburguesas();
    }

    // Método para consultar precios de kebabs.
    private void consultarPreciosKebabs() {
        // Obtener precios de kebabs desde la lista.
        StringBuilder preciosKebabs = new StringBuilder("Precios de Kebabs:\n");

        // Iterar sobre la lista de kebabs y mostrar solo los productos de la SubCategoría "Kebab".
        for (Producto producto : comidas) {
            if ("Kebab".equals(producto.getSubcategoria())) {
                preciosKebabs.append(producto.getNombre()).append(": ").append(producto.getPrecio()).append(" €").append("\n");
            }
        }

        // Mostrar el mensaje utilizando JOptionPane.
        JOptionPane.showMessageDialog(null, preciosKebabs.toString());

        // Volver al menú de selección de kebabs.
        menuSeleccionKebabs();
    }

    // Método para consultar precios de pizzas.
    private void consultarPreciosPizzas() {
        // Obtener precios de pizzas desde la lista.
        StringBuilder preciosPizzas = new StringBuilder("Precios de Pizzas:\n");

        // Iterar sobre la lista de pizzas y mostrar solo los productos de la SubCategoría "Pizza".
        for (Producto producto : comidas) {
            if ("Pizza".equals(producto.getSubcategoria())) {
                preciosPizzas.append(producto.getNombre()).append(": ").append(producto.getPrecio()).append(" €").append("\n");
            }
        }

        // Mostrar el mensaje utilizando JOptionPane.
        JOptionPane.showMessageDialog(null, preciosPizzas.toString());

        // Volver al menú de selección de pizzas.
        menuSeleccionPizzas();
    }

    // Metodo para añadir nuevo producto a comidas.
    public void añadirProductoAComidas() {

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
        comidas.add(nuevoProducto);

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(null, "Producto añadido correctamente a la lista de comidas.");

    }

    // Método para verificar si ya existe un producto con el mismo nombre.
    private boolean nombreProductoExistente(String nombre) {
        for (Producto producto : comidas) {
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

    // Método para obtener una subcategoría válida ("Hamburguesas", "Pizzas" o "Kebabs")
    private String obtenerSubcategoriaValida() {
        String subcategoria;
        while (true) {
            subcategoria = JOptionPane.showInputDialog("Ingrese la subcategoría del nuevo producto (Hamburguesa / Pizza / Kebab):");

            subcategoria = subcategoria.toLowerCase();
            subcategoria = subcategoria.substring(0, 1).toUpperCase() + subcategoria.substring(1);

            if ("Hamburguesa".equals(subcategoria) || "Pizza".equals(subcategoria) || "Kebab".equals(subcategoria)) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una subcategoría válida (Hamburguesa / Pizza / Kebab).");
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
            case "pizza":
                subcategoriaLetra = 'P';
                break;
            case "hamburguesa":
                subcategoriaLetra = 'H';
                break;
            case "kebab":
                subcategoriaLetra = 'K';
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
                for (Producto producto : comidas) {
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

    // Metodo para borrar producto de comidas.
    public void borrarProducto(String nombreProducto) {
        // Iterar sobre la lista de comidas para encontrar el producto con el Nombre.
        for (Producto producto : comidas) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                // Eliminar el producto de la lista.
                comidas.remove(producto);

                JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                return;
            }
        }

        // Si el producto con el ID proporcionado no se encuentra, mostrar un mensaje de error.
        JOptionPane.showMessageDialog(null, "No se encontró ningún producto con el nombre proporcionado.");
    }

    // Método para editar producto de comidas.
    public void editarProducto(String nombreProducto) {
        // Buscar el producto en la lista basándose en el nombre proporcionado
        for (Producto producto : comidas) {
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

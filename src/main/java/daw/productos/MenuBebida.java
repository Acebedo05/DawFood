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
public class MenuBebida {

    private FuncionesTPV funcionesTPV;
    private FuncionesCarrito funcionesCarrito;
    private FuncionesUsuario funcionesUsuario;

    public MenuBebida(FuncionesTPV funcionesTPV) {
        // Almacena la referencia a FuncionesTPV.
        this.funcionesTPV = funcionesTPV;
        this.funcionesCarrito = new FuncionesCarrito(this.funcionesUsuario);
    }

    // Listas para almacenar las diferentes categorías de bebidas.
    private static List<Producto> bebidas = new ArrayList<>();

    // Constructor de la clase, se inicializan las listas y se llama al método principal.
    public void MenuBebida() {
        // Mostrar el menú principal de bebidas.
        menuSeleccionBebidas();
    }

    public static void llamarInicializarBebidas() {
        // Inicializar las listas.
        inicializarBebidas();
    }

    // Inicializar la lista de bebidas alcohólicas con datos.
    private static void inicializarBebidas() {
        bebidas.add(new Producto("A01", "Vino", 4.99, true, "Vino", "Bebida", 0.21, "Alcoholica"));
        bebidas.add(new Producto("A02", "Sidra", 4.99, true, "Sidra", "Bebida", 0.21, "Alcoholica"));
        bebidas.add(new Producto("A03", "Cerveza", 1.99, true, "Cerveza", "Bebida", 0.21, "Alcoholica"));
        bebidas.add(new Producto("R01", "CocaCola", 1.99, true, "CocaCola", "Bebida", 0.21, "Refresco"));
        bebidas.add(new Producto("R02", "Fanta", 1.99, true, "Fanta", "Bebida", 0.21, "Refresco"));
        bebidas.add(new Producto("R03", "Aquarius", 1.99, true, "Aquarius", "Bebida", 0.21, "Refresco"));
        bebidas.add(new Producto("AG01", "Agua Natural", 0.99, true, "Agua Natural", "Bebida", 0.10, "Agua"));
        bebidas.add(new Producto("AG02", "Agua con Gas", 0.99, true, "Agua con Gas", "Bebida", 0.10, "Agua"));
        bebidas.add(new Producto("AG03", "Agua Fria", 0.99, true, "Agua Fría", "Bebida", 0.10, "Agua"));
    }

    // Método principal que muestra el menú de selección para el usuario.
    public void menuSeleccionBebidas() {
        // Crear la ventana del menú.
        JFrame frame = new JFrame("Tipos de Bebidas");

        // Crear un panel para colocar los botones.
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        agregarBoton(panel, "Alcoholicas", e -> {
            menuSeleccionAlcoholicas();
            frame.dispose();
        });
        agregarBoton(panel, "Refrescos", e -> {
            menuSeleccionRefrescos();
            frame.dispose();
        });
        agregarBoton(panel, "Aguas", e -> {
            menuSeleccionAguas();
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

    // Método principal que muestra el menú de vinos al usuario.
    public void menuSeleccionAlcoholicas() {
        // Crear la ventana del menú.
        JFrame frame = new JFrame("Tipos de Alcoholicas");

        // Crear un panel para colocar los botones.
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        for (Producto alcoholica : bebidas) {
            if ("Alcoholica".equals(alcoholica.getSubcategoria())) {
                agregarBoton(panel, alcoholica.getNombre(), e -> {
                    FuncionesCarrito.agregarProductoAlCarrito(alcoholica);
                });
            }
        }
        agregarBoton(panel, "Consultar precios", e -> {
            consultarPreciosAlcoholicas();
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

    // Método principal que muestra el menú de selección de refrescos al usuario.
    public void menuSeleccionRefrescos() {
        // Crear la ventana del menú.
        JFrame frame = new JFrame("Tipos de Refrescos");

        // Crear un panel para colocar los botones.
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        for (Producto refresco : bebidas) {
            if ("Refresco".equals(refresco.getSubcategoria())) {
                agregarBoton(panel, refresco.getNombre(), e -> {
                    FuncionesCarrito.agregarProductoAlCarrito(refresco);
                });
            }
        }
        agregarBoton(panel, "Consultar precios", e -> {
            consultarPreciosRefrescos();
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

    // Método principal que muestra el menú de selección de aguas al usuario.
    public void menuSeleccionAguas() {
        // Crear la ventana del menú.
        JFrame frame = new JFrame("Tipos de Agua");

        // Crear un panel para colocar los botones.
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        for (Producto agua : bebidas) {
            if ("Agua".equals(agua.getSubcategoria())) {
                agregarBoton(panel, agua.getNombre(), e -> {
                    FuncionesCarrito.agregarProductoAlCarrito(agua);
                });
            }
        }
        agregarBoton(panel, "Consultar precios", e -> {
            consultarPreciosAguas();
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

    // Método para consultar precios de alcoholicas    
    private void consultarPreciosAlcoholicas() {
        // Obtener precios de alcoholicas desde la lista
        StringBuilder preciosAlcoholicas = new StringBuilder("Precios de Alcoholicas:\n");

        // Iterar sobre la lista de Alcoholica y mostrar solo los productos de la SubCategoría "Alcoholica".
        for (Producto producto : bebidas) {
            if ("Alcoholica".equals(producto.getSubcategoria())) {
                preciosAlcoholicas.append(producto.getNombre()).append(": ").append(producto.getPrecio()).append(" €").append("\n");
            }
        }

        // Mostrar el mensaje utilizando JOptionPane.
        JOptionPane.showMessageDialog(null, preciosAlcoholicas.toString());

        // Volver al menú de selección de alcoholicas.
        menuSeleccionAlcoholicas();
    }

    // Método para consultar precios de refrescos    
    private void consultarPreciosRefrescos() {
        // Obtener precios de refrescos desde la lista
        StringBuilder preciosRefrescos = new StringBuilder("Precios de Refrescos:\n");

        // Iterar sobre la lista de Refresco y mostrar solo los productos de la SubCategoría "Refresco".
        for (Producto producto : bebidas) {
            if ("Refresco".equals(producto.getSubcategoria())) {
                preciosRefrescos.append(producto.getNombre()).append(": ").append(producto.getPrecio()).append(" €").append("\n");
            }
        }

        // Mostrar el mensaje utilizando JOptionPane.
        JOptionPane.showMessageDialog(null, preciosRefrescos.toString());

        // Volver al menú de selección de refrescos.
        menuSeleccionRefrescos();
    }

    // Método para consultar precios de aguas    
    private void consultarPreciosAguas() {
        // Obtener precios de aguas desde la lista
        StringBuilder preciosAguas = new StringBuilder("Precios de Aguas:\n");

        // Iterar sobre la lista de Agua y mostrar solo los productos de la SubCategoría "Agua".
        for (Producto producto : bebidas) {
            if ("Agua".equals(producto.getSubcategoria())) {
                preciosAguas.append(producto.getNombre()).append(": ").append(producto.getPrecio()).append(" €").append("\n");
            }
        }

        // Mostrar el mensaje utilizando JOptionPane.
        JOptionPane.showMessageDialog(null, preciosAguas.toString());

        // Volver al menú de selección de aguas.
        menuSeleccionAguas();
    }

    // Metodo para añadir nuevo producto a bebidas.
    public void añadirProductoABebidas() {

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nuevo producto:");
        double precio = obtenerPrecioValido();
        boolean enStock = true;
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripción del nuevo producto:");
        String categoria = "Comida";
        double iva = obtenerIVAValido();
        String subcategoria = obtenerSubcategoriaValida();
        String id = obtenerNuevoID();

        Producto nuevoProducto = new Producto(id, nombre, precio, enStock, descripcion, categoria, iva, subcategoria);
        bebidas.add(nuevoProducto);

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(null, "Producto añadido correctamente a la lista de bebidas.");

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

    // Método para obtener una subcategoría válida ("Alcoholicas", "Aguas" o "Refrescos")
    private String obtenerSubcategoriaValida() {
        String subcategoria;
        while (true) {
            subcategoria = JOptionPane.showInputDialog("Ingrese la subcategoría del nuevo producto (Alcoholica / Refresco / Agua):");

            subcategoria = subcategoria.toLowerCase();
            subcategoria = subcategoria.substring(0, 1).toUpperCase() + subcategoria.substring(1);

            if ("Alcoholica".equals(subcategoria) || "Refresco".equals(subcategoria) || "Agua".equals(subcategoria)) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una subcategoría válida (Alcoholica / Refresco / Agua).");
            }
        }
        return subcategoria;
    }

    // Método para obtener un ID.
    private String obtenerNuevoID() {
        String nuevoID;
        while (true) {
            nuevoID = JOptionPane.showInputDialog("Ingrese el nuevo ID (EJ: (Alcoholica: A01 // Refresco: R01 // Agua: AG01)):");

            boolean idExiste = false;
            for (Producto producto : bebidas) {
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
        }
        return nuevoID;
    }

    // Metodo para borrar producto de bebidas.
    public void borrarProducto(String nombreProducto) {
        // Iterar sobre la lista de bebidas para encontrar el producto con el Nombre.
        for (Producto producto : bebidas) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                // Eliminar el producto de la lista.
                bebidas.remove(producto);

                JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                return;
            }
        }

        // Si el producto con el ID proporcionado no se encuentra, mostrar un mensaje de error.
        JOptionPane.showMessageDialog(null, "No se encontró ningún producto con el nombre proporcionado.");
    }

}

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
    private List<Producto> comidas;

    // Constructor de la clase, se inicializan las listas y se llama al método principal.
    public void MenuComida() {
        this.comidas = new ArrayList<>();

        // Inicializar las listas.
        inicializarComidas();

        // Mostrar el menú principal de comidas.
        menuSeleccionComidas();
    }

    // Inicializar la lista de pizzas con datos.
    private void inicializarComidas() {
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
    
}

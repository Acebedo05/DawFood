package daw.productos;

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

    public MenuComida(FuncionesTPV funcionesTPV) {
        // Almacena la referencia a FuncionesTPV.
        this.funcionesTPV = funcionesTPV;
    }

    // Listas para almacenar las diferentes categorías de comidas.
    private List<Pizza> pizzas;
    private List<Hamburguesa> hamburguesas;
    private List<Kebab> kebabs;

    // Constructor de la clase, se inicializan las listas y se llama al método principal.
    public void MenuComida() {
        this.pizzas = new ArrayList<>();
        this.hamburguesas = new ArrayList<>();
        this.kebabs = new ArrayList<>();

        // Inicializar las listas.
        inicializarPizzas();
        inicializarHamburguesas();
        inicializarKebabs();

        // Mostrar el menú principal de comidas.
        menuSeleccionComidas();
    }

    // Inicializar la lista de pizzas con datos.
    private void inicializarPizzas() {
        pizzas.add(new Pizza("P01", "Queso", 8.99, true, "Pizza con cuatro tipos de quesos", "Comida", 0.10, "Pizza"));
        pizzas.add(new Pizza("P02", "Margarita", 9.99, true, "Pizza con queso y tomate", "Comida", 0.10, "Pizza"));
        pizzas.add(new Pizza("P03", "Boloñesa", 10.99, true, "Pizza con salsa boloñesa", "Comida", 0.10, "Pizza"));
    }

    // Inicializar la lista de hamburguesas con datos.
    private void inicializarHamburguesas() {
        hamburguesas.add(new Hamburguesa("H01", "Ternera", 7.99, true, "Hamburguesa de ternera", "Comida", 0.10, "Hamburguesa"));
        hamburguesas.add(new Hamburguesa("H02", "Pollo", 6.99, true, "Hamburguesa de pollo", "Comida", 0.10, "Hamburguesa"));
        hamburguesas.add(new Hamburguesa("H03", "Vegetal", 5.99, true, "Hamburguesa vegetariana", "Comida", 0.10, "Hamburguesa"));
    }

    // Inicializar la lista de kebabs con datos.
    private void inicializarKebabs() {
        kebabs.add(new Kebab("K01", "Ternera", 9.99, true, "Kebab de ternera", "Comida", 0.10, "Kebab"));
        kebabs.add(new Kebab("K02", "Pollo", 8.99, true, "Kebab de pollo", "Comida", 0.10, "Kebab"));
        kebabs.add(new Kebab("K03", "Mixto", 10.99, true, "Kebab mixto", "Comida", 0.10, "Kebab"));
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
        agregarBoton(panel, "No Comprar", e -> {
            hola();
            frame.dispose();
        });
        agregarBoton(panel, "Ver carrito", e -> {
            hola();
            frame.dispose();
        });

        frame.add(panel);
        frame.setSize(400, 130);
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
        agregarBoton(panel, "4 Quesos", e -> hola());
        agregarBoton(panel, "Margarita", e -> hola());
        agregarBoton(panel, "Boloñesa", e -> hola());
        agregarBoton(panel, "Consultar precios", e -> {
            consultarPreciosPizzas();
            frame.dispose();
        });
        agregarBoton(panel, "Volver al menú de selección", e -> {
            funcionesTPV.iniciarModoUsuario();
            frame.dispose();
        });
        agregarBoton(panel, "No Comprar", e -> hola());
        agregarBoton(panel, "Ver carrito", e -> hola());

        frame.add(panel);
        frame.setSize(400, 130);
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
        agregarBoton(panel, "Ternera", e -> hola());
        agregarBoton(panel, "Pollo", e -> hola());
        agregarBoton(panel, "Vegetariana", e -> hola());
        agregarBoton(panel, "Consultar precios", e -> {
            consultarPreciosHamburguesas();
            frame.dispose();
        });
        agregarBoton(panel, "Volver al menú de selección", e -> {
            funcionesTPV.iniciarModoUsuario();
            frame.dispose();
        });
        agregarBoton(panel, "No Comprar", e -> hola());
        agregarBoton(panel, "Ver carrito", e -> hola());

        frame.add(panel);
        frame.setSize(400, 130);
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
        agregarBoton(panel, "Ternera", e -> hola());
        agregarBoton(panel, "Pollo", e -> hola());
        agregarBoton(panel, "Mixto", e -> hola());
        agregarBoton(panel, "Consultar precios", e -> {
            consultarPreciosKebabs();
            frame.dispose();
        });
        agregarBoton(panel, "Volver al menú de selección", e -> {
            funcionesTPV.iniciarModoUsuario();
            frame.dispose();
        });
        agregarBoton(panel, "No Comprar", e -> hola());
        agregarBoton(panel, "Ver carrito", e -> hola());

        frame.add(panel);
        frame.setSize(400, 130);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void hola() {
        System.out.println("hola");
    }

    // Método para consultar precios de hamburguesas.
    private void consultarPreciosHamburguesas() {
        // Obtener precios de hamburguesas desde la lista.
        StringBuilder preciosHamburguesas = new StringBuilder("Precios de Hamburguesas:\n");

        // Iterar sobre la lista de hamburguesas.
        for (Hamburguesa hamburguesa : hamburguesas) {
            preciosHamburguesas.append(hamburguesa.getNombre()).append(": ").append(hamburguesa.getPrecio()).append(" €").append("\n");
        }

        // Mostrar el mensaje utilizando JOptionPane.
        JOptionPane.showMessageDialog(null, preciosHamburguesas.toString());

        // Volver al menú de selección de hamburguesas.
        menuSeleccionHamburguesas();
    }

    // Método para consultar precios de kebabs    
    private void consultarPreciosKebabs() {
        // Obtener precios de kebabs desde la lista
        StringBuilder preciosKebabs = new StringBuilder("Precios de Kebabs:\n");

        // Iterar sobre la lista de kebabs.
        for (Kebab kebab : kebabs) {
            preciosKebabs.append(kebab.getNombre()).append(": ").append(kebab.getPrecio()).append(" €").append("\n");
        }

        // Mostrar el mensaje utilizando JOptionPane.
        JOptionPane.showMessageDialog(null, preciosKebabs.toString());

        // Volver al menú de selección de kebabs.
        menuSeleccionKebabs();
    }

    // Método para consultar precios de pizzas    
    private void consultarPreciosPizzas() {
        // Obtener precios de pizzas desde la lista
        StringBuilder preciosPizzas = new StringBuilder("Precios de Pizzas:\n");

        // Iterar sobre la lista de pizzas.
        for (Pizza pizza : pizzas) {
            preciosPizzas.append(pizza.getNombre()).append(": ").append(pizza.getPrecio()).append(" €").append("\n");
        }

        // Mostrar el mensaje utilizando JOptionPane.
        JOptionPane.showMessageDialog(null, preciosPizzas.toString());

        // Volver al menú de selección de pizzas.
        menuSeleccionPizzas();
    }

}

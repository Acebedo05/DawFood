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
public class MenuBebida {

    private FuncionesTPV funcionesTPV;

    public MenuBebida(FuncionesTPV funcionesTPV) {
        // Almacena la referencia a FuncionesTPV.
        this.funcionesTPV = funcionesTPV;
    }

    // Listas para almacenar las diferentes categorías de bebidas.
    private List<Producto> bebidas;

    // Constructor de la clase, se inicializan las listas y se llama al método principal.
    public void MenuBebida() {
        this.bebidas = new ArrayList<>();

        // Inicializar las listas.
        inicializarBebidas();

        // Mostrar el menú principal de bebidas.
        menuSeleccionBebidas();
    }

    // Inicializar la lista de bebidas alcohólicas con datos.
    private void inicializarBebidas() {
        bebidas.add(new Producto("A01", "Vino", 4.99, true, "Vino", "Bebida", 0.21, "Alcoholica"));
        bebidas.add(new Producto("A02", "Sidra", 4.99, true, "Sidra", "Bebida", 0.21, "Alcoholica"));
        bebidas.add(new Producto("A03", "Cerveza", 1.99, true, "Cerveza", "Bebida", 0.21, "Alcoholica"));
        bebidas.add(new Producto("R01", "CocaCola", 1.99, true, "CocaCola", "Bebida", 0.21, "Refresco"));
        bebidas.add(new Producto("R02", "Fanta", 1.99, true, "Fanta", "Bebida", 0.21, "Refresco"));
        bebidas.add(new Producto("R03", "Aquarius", 1.99, true, "Aquarius", "Bebida", 0.21, "Refresco"));
        bebidas.add(new Producto("AG01", "Natural", 0.99, true, "Agua Natural", "Bebida", 0.10, "Agua"));
        bebidas.add(new Producto("AG02", "Gas", 0.99, true, "Agua con Gas", "Bebida", 0.10, "Agua"));
        bebidas.add(new Producto("AG03", "Fria", 0.99, true, "Agua Fría", "Bebida", 0.10, "Agua"));
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

    // Método principal que muestra el menú de vinos al usuario.
    public void menuSeleccionAlcoholicas() {
        // Crear la ventana del menú.
        JFrame frame = new JFrame("Tipos de Alcoholicas");

        // Crear un panel para colocar los botones.
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        agregarBoton(panel, "Vino", e -> hola());
        agregarBoton(panel, "Sidra", e -> hola());
        agregarBoton(panel, "Cerveza", e -> hola());
        agregarBoton(panel, "Consultar precios", e -> {
            consultarPreciosAlcoholicas();
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

    // Método principal que muestra el menú de selección de refrescos al usuario.
    public void menuSeleccionRefrescos() {
        // Crear la ventana del menú.
        JFrame frame = new JFrame("Tipos de Refrescos");

        // Crear un panel para colocar los botones.
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        agregarBoton(panel, "CocaCola", e -> hola());
        agregarBoton(panel, "Fanta", e -> hola());
        agregarBoton(panel, "Aquarius", e -> hola());
        agregarBoton(panel, "Consultar precios", e -> {
            consultarPreciosRefrescos();
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

    // Método principal que muestra el menú de selección de aguas al usuario.
    public void menuSeleccionAguas() {
        // Crear la ventana del menú.
        JFrame frame = new JFrame("Tipos de Agua");

        // Crear un panel para colocar los botones.
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        agregarBoton(panel, "Natural", e -> hola());
        agregarBoton(panel, "Con Gas", e -> hola());
        agregarBoton(panel, "Fría", e -> hola());
        agregarBoton(panel, "Consultar precios", e -> {
            consultarPreciosAguas();
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
}

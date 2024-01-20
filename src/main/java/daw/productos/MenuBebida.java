package daw.productos;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author acebedo
 */
public class MenuBebida {

    // Listas para almacenar las diferentes categorías de bebidas.
    private List<Alcoholica> alcoholicas;
    private List<Refrescos> refrescos;
    private List<Agua> aguas;

    // Constructor de la clase, se inicializan las listas y se llama al método principal.
    public void MenuBebida() {
        this.alcoholicas = new ArrayList<>();
        this.refrescos = new ArrayList<>();
        this.aguas = new ArrayList<>();

        // Inicializar las listas.
        inicializarAlcoholicas();
        inicializarRefrescos();
        inicializarAguas();

        // Mostrar el menú principal de bebidas.
        menuSeleccionBebidas();
    }

    // Inicializar la lista de bebidas alcohólicas con datos.
    private void inicializarAlcoholicas() {
        alcoholicas.add(new Alcoholica("A01", "Vino", 4.99, true, "Vino", "Bebida", 0.21, "Alcoholica"));
        alcoholicas.add(new Alcoholica("A02", "Sidra", 4.99, true, "Sidra", "Bebida", 0.21, "Alcoholica"));
        alcoholicas.add(new Alcoholica("A03", "Cerveza", 1.99, true, "Cerveza", "Bebida", 0.21, "Alcoholica"));
    }

    // Inicializar la lista de bebidas Refrescos con datos.
    private void inicializarRefrescos() {
        refrescos.add(new Refrescos("R01", "CocaCola", 1.99, true, "CocaCola", "Bebida", 0.21, "Refresco"));
        refrescos.add(new Refrescos("R02", "Fanta", 1.99, true, "Fanta", "Bebida", 0.21, "Refresco"));
        refrescos.add(new Refrescos("R03", "Aquarius", 1.99, true, "Aquarius", "Bebida", 0.21, "Refresco"));
    }

    // Inicializar la lista de bebidas Aguas con datos.
    private void inicializarAguas() {
        aguas.add(new Agua("AG01", "Natural", 0.99, true, "Agua Natural", "Bebida", 0.10, "Agua"));
        aguas.add(new Agua("AG02", "Gas", 0.99, true, "Agua con Gas", "Bebida", 0.10, "Agua"));
        aguas.add(new Agua("AG03", "Fria", 0.99, true, "Agua Fría", "Bebida", 0.10, "Agua"));
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
            hola();
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
        agregarBoton(panel, "Volver al menú de selección", e -> hola());
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
        agregarBoton(panel, "Volver al menú de selección", e -> hola());
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
        agregarBoton(panel, "Volver al menú de selección", e -> hola());
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
}

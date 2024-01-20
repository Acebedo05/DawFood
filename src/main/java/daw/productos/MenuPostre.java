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
public class MenuPostre {
    
    // Listas para almacenar las diferentes categorías de postres.
    private List<Casero> caseros;

// Constructor de la clase, se inicializan las listas y se llama al método principal.
    public void MenuPostre() {
        this.caseros = new ArrayList<>();

        // Inicializar las listas.
        inicializarCaseros();

        // Mostrar el menú principal de comidas.
        menuSeleccionPostres();
    }

    // Inicializar la lista de caseros con datos.
    private void inicializarCaseros() {
        caseros.add(new Casero("C01", "Flan", 3.99, true, "Flan Casero", "Postre", 0.10, "Casero"));
        caseros.add(new Casero("C02", "Natilla", 3.99, true, "Natilla Casera", "Postre", 0.10, "Casero"));
        caseros.add(new Casero("C03", "Helado", 3.99, true, "Helado Casero", "Postre", 0.10, "Casero"));
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

    // Método principal que muestra el menú de caseros al usuario.
    public void menuSeleccionCaseros() {
        // Crear la ventana del menú.
        JFrame frame = new JFrame("Tipos de Caseros");

        // Crear un panel para colocar los botones.
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        agregarBoton(panel, "Flan", e -> hola());
        agregarBoton(panel, "Natilla", e -> hola());
        agregarBoton(panel, "Helado", e -> hola());
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

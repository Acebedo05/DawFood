package daw.modos;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author khalid
 */
public class FuncionesUsuario {

    // Método principal que muestra el menú de selección para el usuario.
    public void menuSeleccion() {
        // Crear la ventana del menú.
        JFrame frame = new JFrame("Menú de Selección");
        
        // Crear un panel para colocar los botones.
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        agregarBoton(panel, "Ver Carrito", e -> verCarrito());
        agregarBoton(panel, "Ver Tipos Comidas", e -> verTipoComida());
        agregarBoton(panel, "Ver Tipos Bebidas", e -> verTipoBebida());
        agregarBoton(panel, "Ver Postres", e -> verPostres());
        agregarBoton(panel, "No Comprar", e -> noComprar());

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

    // Método para mostrar el carrito del usuario.
    private void verCarrito() {
        // Por hacer
        System.out.println("verCarrito");
    }

    // Método para mostrar los tipos de comida al usuario.
    private void verTipoComida() {
        // Por hacer
        System.out.println("verTipoComida");
    }

    // Método para mostrar los tipos de bebidas al usuario.
    private void verTipoBebida() {
        // Por hacer
        System.out.println("verTipoBebida");
    }

    // Método para mostrar los postres al usuario.
    private void verPostres() {
        // Por hacer
        System.out.println("verPostres");
    }

    // Método para que el usuario pueda cancelar la compra.
    private void noComprar() {
        // Por hacer
        System.out.println("noComprar");
    }
}

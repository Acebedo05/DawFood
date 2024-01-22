package daw.modos;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import daw.productos.MenuComida;
import daw.productos.MenuBebida;
import daw.productos.MenuPostre;

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
        agregarBoton(panel, "Ver Tipos Comidas", e -> {
            verTipoComida();
            frame.dispose();
        });
        agregarBoton(panel, "Ver Tipos Bebidas", e -> {
            verTipoBebida();
            frame.dispose();
        });
        agregarBoton(panel, "Ver Postres", e -> {
            verPostres();
            frame.dispose();
        });
        agregarBoton(panel, "Ver Carrito", e -> {
            verCarrito();
            frame.dispose();
        });
        agregarBoton(panel, "No Comprar", e -> {
            noComprar();
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

    // Método para mostrar el carrito del usuario.
    private void verCarrito() {
        // Por hacer
        System.out.println("verCarrito");
    }

    private MenuComida menuComida;
    private MenuBebida menuBebida;
    private MenuPostre menuPostre;

    // Constructor que recibe referencias a los objetos MenuComida, MenuBebida y MenuPostre.
    public FuncionesUsuario(MenuComida menuComida, MenuBebida menuBebida, MenuPostre menuPostre) {
        this.menuComida = menuComida;
        this.menuBebida = menuBebida;
        this.menuPostre = menuPostre;
    }

    // Método para mostrar los tipos de comida al usuario.
    private void verTipoComida() {
        // Llamar al método MenuComida de la clase MenuComida.
        menuComida.mostrarMenuComida();
    }

    // Método para mostrar los tipos de bebidas al usuario.
    private void verTipoBebida() {
        // Llamar al método MenuBebida de la clase MenuBebida.
        menuBebida.MenuBebida();
    }

    // Método para mostrar los postres al usuario.
    private void verPostres() {
        // Llamar al método MenuPostre de la clase MenuPostre.
        menuPostre.MenuPostre();
    }

    // Método para que el usuario pueda cancelar la compra.
    private void noComprar() {
        // Por hacer
        System.out.println("noComprar");
    }
}

package daw.modos;

import java.text.SimpleDateFormat;
import java.util.Date;
import daw.tpv.AtributosTPV;
import daw.tpv.FuncionesTPV;
import daw.tpv.ObjetosTPV;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author khalid
 */
public class FuncionesAdministrador {

    private AtributosTPV atributosTPV;

    public FuncionesAdministrador(AtributosTPV atributosTPV) {
        this.atributosTPV = atributosTPV;
    }

    // Método para ver la hora actual del TPV.
    public void verHoraTPV() {
        Date fechaHoraActual = atributosTPV.getFechaHoraActual();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaActual = formatoHora.format(fechaHoraActual);

        JOptionPane.showMessageDialog(null, "La hora actual del TPV es: " + horaActual);
    }

    // Método para consultar las ventas realizadas.
    public void consultarVentas() {
        // Por Hacer.
        System.out.println("consultarVentas");
    }

    // Método para borrar productos existentes.
    public void borrarProductos() {
        // Por Hacer.
        System.out.println("borrarProductos");
    }

    // Método para añadir nuevos productos.
    public void añadirProductos() {
        // Por Hacer.
        System.out.println("añadirProductos");
    }

    // Método para cambaiar datos de los productos.
    public void cambiarDatosProductos() {
        // Por Hacer.
        System.out.println("cambiarDatosProductos");
    }

    // Método para mostrar un menú con las obciones que tiene el administrador.
    public void mostrarMenu() {
        // Crear la ventana del menú.
        JFrame frame = new JFrame("Menú Administrador");
        
        // Crear un panel para colocar los botones.
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Crear botones para cada opción del menú.
        // Asociar ActionListener a cada botón.
        agregarBoton(panel, "Ver Hora TPV", e -> verHoraTPV());
        agregarBoton(panel, "Consultar Ventas", e -> consultarVentas());
        agregarBoton(panel, "Borrar Productos", e -> borrarProductos());
        agregarBoton(panel, "Añadir Productos", e -> añadirProductos());
        agregarBoton(panel, "Cambiar Datos Productos", e -> cambiarDatosProductos());
        agregarBoton(panel, "Volver al Menú Inicial", e -> volverAlMenuInicial(frame));
        agregarBoton(panel, "Apagar TPV", e -> apagarTPV());

        frame.add(panel);
        frame.setSize(400, 165);
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

    // Método para volver al Menú Inicial
    private void volverAlMenuInicial(JFrame frame) {
        // Cerrar la ventana actual.
        frame.dispose();
        // Llamando al método encenderTPV de la clase FuncionesTPV
        FuncionesTPV funcionesTPV = ObjetosTPV.inicializarTPV();
        funcionesTPV.encenderTPV();
    }

    // Método para detener la ejecución del programa.
    private void apagarTPV() {
        System.out.println("Apagando TPV...");
        System.exit(0);
    }
    
}

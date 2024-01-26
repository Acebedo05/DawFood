package daw.tpv;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Random;
import javax.swing.JDialog;
import daw.modos.FuncionesAdministrador;
import daw.productos.MenuComida;
import daw.productos.MenuBebida;
import daw.productos.MenuPostre;

/**
 *
 * @author acebedo
 */
public class FuncionesTPV {

    private AtributosTPV atributosTPV;

    // Constructor de la clase FuncionesTPV.
    public FuncionesTPV(AtributosTPV atributosTPV) {
        this.atributosTPV = atributosTPV;
    }

    // Método para encender el TPV, mostrando un mensaje de bienvenida y permitiendo seleccionar el modo.
    public void encenderTPV() {
        // Mensaje de bienvenida.
        String mensajeInicio = "¡Bienvenido al TPV!\n"
                + "Ubicación del TPV: " + atributosTPV.getUbicacion() + "\n"
                + "Número de Serie: " + atributosTPV.getNumeroSerie() + "\n"
                + "Fecha y Hora Actual: " + atributosTPV.getFechaHoraActual();

        JOptionPane.showMessageDialog(null, mensajeInicio);

        // Crear instancias de las clases MenuComida, MenuBebida y MenuPostre.
        MenuComida menuComida = new MenuComida(this);
        MenuBebida menuBebida = new MenuBebida(this);
        MenuPostre menuPostre = new MenuPostre(this);

        // Crear un JDialog para seleccionar el modo.
        JDialog dialog = new JDialog();
        dialog.setTitle("Seleccione una opción");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Crear un panel con los botones de modo usuario y modo administrador.
        JPanel panel = new JPanel();
        JButton botonUsuario = new JButton("Modo Usuario");
        JButton botonAdmin = new JButton("Modo Administrador");
        panel.add(botonUsuario);
        panel.add(botonAdmin);

        // Agregamos ActionListener para los botones.
        botonUsuario.addActionListener(e -> {
            iniciarModoUsuario();
            dialog.dispose();  // Cierra la ventana después de elegir Modo Usuario.
        });

        botonAdmin.addActionListener(e -> {
            iniciarModoAdmin();
            dialog.dispose();  // Cierra la ventana después de elegir Modo Administrador.
        });

        // Agregar el panel al diálogo.
        dialog.add(panel);

        // Ajustar el tamaño y hacemos visible el diálogo.
        dialog.setSize(400, 130);
        dialog.setLocationRelativeTo(null);  // Centrar el diálogo en la pantalla
        dialog.setVisible(true);
    }

// Método para iniciar el modo de usuario.
    public void iniciarModoUsuario() {
        // Crear una instancia de la clase MenuComida.
        MenuComida menuComida = new MenuComida(this);

        // Crear una instancia de la clase MenuBebida.
        MenuBebida menuBebida = new MenuBebida(this);

        // Crear una instancia de la clase MenuPostre.
        MenuPostre menuPostre = new MenuPostre(this);

        // Pasar la instancia de MenuComida al constructor de FuncionesUsuario.
        daw.modos.FuncionesUsuario funcionesUsuario = new daw.modos.FuncionesUsuario(menuComida, menuBebida, menuPostre);

        // Llamar al método menuSeleccion de la clase FuncionesUsuario.
        funcionesUsuario.menuSeleccion();
    }

    // Método para iniciar el modo de administrador, generando o solicitando la contraseña de administrador.
    public void iniciarModoAdmin() {
        String password;
        int intentosMaximos = 3;
        int intentos = 0;

        do {
            if (atributosTPV.getPasswordAdmin() == null || atributosTPV.getPasswordAdmin().isEmpty()) {
                // Si la contraseña de administrador aún no se ha generado, se crea una nueva.
                password = generarPasswordAleatoria();
                atributosTPV.setPasswordAdmin(password);
                System.out.println("Contraseña de administrador generada: " + password);
                password = JOptionPane.showInputDialog(null, "Ingrese la contraseña de administrador:");
            } else {
                // Si ya hay una contraseña de administrador creada, se solicita.
                password = JOptionPane.showInputDialog(null, "Ingrese la contraseña de administrador:");
            }

            if (password != null && validarPasswordAdmin(password)) {
                // Crear instancia de FuncionesAdministrador.
                FuncionesAdministrador funcionesAdmin = new FuncionesAdministrador(atributosTPV);
                // Llamar al método mostrarMenu de la instancia de FuncionesAdministrador.
                funcionesAdmin.mostrarMenu();
                break;  // Sale del bucle si la contraseña es correcta.
            } else {
                intentos++;
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta. Intento " + intentos + " de " + intentosMaximos);
            }
        } while (intentos < intentosMaximos);

        if (intentos == intentosMaximos) {
            JOptionPane.showMessageDialog(null, "Número máximo de intentos alcanzado. No se puede acceder al modo administrador.");
            encenderTPV();
        }

    }

    /*
        Método para validar si la contraseña generada cumple con los requisitos.
        REQUISITOS:
            - 1 Letra mayúscula.
            - 1 Letra minúscula.
            - 1 Número.
            - 1 Carácter especial.
     */
    private boolean validarPasswordAdmin(String password) {
        // Obtener la contraseña generada aleatoriamente
        String passwordGenerada = atributosTPV.getPasswordAdmin();

        // Validar que la contraseña ingresada sea la misma que la generada
        return password.equals(passwordGenerada);
    }

    // Método para generar una contraseña aleatoria.
    private String generarPasswordAleatoria() {
        // Genera una contraseña aleatoria con los requisitos especificados.
        Random random = new Random();
        String password = "";

        // Caracteres permitidos
        char[] letrasMinusculas = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] letrasMayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] numeros = "0123456789".toCharArray();
        char[] caracteresEspeciales = "#$%&()*+,-.:;<=>@".toCharArray();

        // Añade al menos una minúscula, mayúscula, número y caracter especial.
        password += letrasMinusculas[random.nextInt(letrasMinusculas.length)];
        password += letrasMayusculas[random.nextInt(letrasMayusculas.length)];
        password += numeros[random.nextInt(numeros.length)];
        password += caracteresEspeciales[random.nextInt(caracteresEspeciales.length)];

        // Completa la contraseña hasta llegar a 6 caracteres.
        while (password.length() < 6) {
            int randomIndex = random.nextInt(caracteresEspeciales.length);
            password += caracteresEspeciales[randomIndex];
        }

        return password;
    }

}

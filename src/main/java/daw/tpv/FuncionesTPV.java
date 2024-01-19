package daw.tpv;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Random;
import javax.swing.JDialog;
import daw.modos.FuncionesAdministrador;

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
        dialog.setSize(400, 75);
        dialog.setLocationRelativeTo(null);  // Centrar el diálogo en la pantalla
        dialog.setVisible(true);
    }

    // Método para iniciar el modo de usuario.
    public void iniciarModoUsuario() {
        // Llamar al método menuSeleccion de la clase FuncionesUsuario.
        daw.modos.FuncionesUsuario funcionesUsuario = new daw.modos.FuncionesUsuario();
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

    // Método para realizar una compra (REVISAR).
    public void realizarCompra() {
        JOptionPane.showMessageDialog(null, "Compra Realizada");
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
        // Validar que la contraseña cumpla con los requisitos.
        boolean tieneMinuscula = false;
        boolean tieneMayuscula = false;
        boolean tieneNumero = false;
        boolean tieneCaracterEspecial = false;

        for (char caracter : password.toCharArray()) {
            // Si hay una letra minúscula, se pone true.
            if (Character.isLowerCase(caracter)) {
                tieneMinuscula = true;
            // Si hay una letra mayúscula, se pone true.
            } else if (Character.isUpperCase(caracter)) {
                tieneMayuscula = true;
            // Si hay un número, se pone true.
            } else if (Character.isDigit(caracter)) {
                tieneNumero = true;
            // Si hay un caracter especial, se pone true.
            } else if ("#$%&()*+,-.:;<=>@".indexOf(caracter) != -1) {
                tieneCaracterEspecial = true;
            }
        }
        
        // Si algino de los valores es false, no funciona.
        return tieneMinuscula && tieneMayuscula && tieneNumero && tieneCaracterEspecial;
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

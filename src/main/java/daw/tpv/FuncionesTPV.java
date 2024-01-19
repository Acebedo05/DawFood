package daw.tpv;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Random;

/**
 *
 * @author acebedo
 */
public class FuncionesTPV {

    private AtributosTPV atributosTPV;

    public FuncionesTPV(AtributosTPV atributosTPV) {
        this.atributosTPV = atributosTPV;
    }

    // REVISAR REVIASR REVISAR
    // Método para encender el TPV, mostrando un mensaje de bienvenida y permitiendo seleccionar el modo.
    public void encenderTPV() {
        String mensajeInicio = "¡Bienvenido al TPV!\n"
                + "Ubicación del TPV: " + atributosTPV.getUbicacion() + "\n"
                + "Número de Serie: " + atributosTPV.getNumeroSerie() + "\n"
                + "Fecha y Hora Actual: " + atributosTPV.getFechaHoraActual();

        JOptionPane.showMessageDialog(null, mensajeInicio);

        // Creamos un panel con los botones
        JPanel panel = new JPanel();
        JButton botonUsuario = new JButton("Modo Usuario");
        JButton botonAdmin = new JButton("Modo Administrador");
        panel.add(botonUsuario);
        panel.add(botonAdmin);

        // Agregamos ActionListener para los botones
        botonUsuario.addActionListener(e -> iniciarModoUsuario());
        botonAdmin.addActionListener(e -> iniciarModoAdmin());

        // Mostramos el JOptionPane con el panel y los botones
        int opcionSeleccionada = JOptionPane.showOptionDialog(
                null,
                panel,
                "Seleccione una opción",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[]{},
                null);
    }
    // REVISAR REVIASR REVISAR

    // Método para iniciar el modo de usuario.
    public void iniciarModoUsuario() {
        System.out.println("Modo Usuario Iniciado");
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
                System.out.println("Modo Administrador Iniciado");
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
            if (Character.isLowerCase(caracter)) {
                tieneMinuscula = true;
            } else if (Character.isUpperCase(caracter)) {
                tieneMayuscula = true;
            } else if (Character.isDigit(caracter)) {
                tieneNumero = true;
            } else if ("#$%&()*+,-.:;<=>@".indexOf(caracter) != -1) {
                tieneCaracterEspecial = true;
            }
        }

        if (!(tieneMinuscula && tieneMayuscula && tieneNumero && tieneCaracterEspecial)) {
            System.out.println("Contraseña incorrecta. No se puede acceder al modo administrador.");
            return false;
        }

        return true;
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

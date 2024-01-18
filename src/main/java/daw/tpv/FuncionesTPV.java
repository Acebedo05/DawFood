package daw.tpv;

import javax.swing.JOptionPane;
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

    // Método para encender el TPV, mostrando un mensaje de bienvenida y permitiendo seleccionar el modo.
    public void encenderTPV() {
        String mensajeInicio = "¡Bienvenido al TPV!\n"
                + "Ubicación del TPV: " + atributosTPV.getUbicacion() + "\n"
                + "Número de Serie: " + atributosTPV.getNumeroSerie() + "\n"
                + "Fecha y Hora Actual: " + atributosTPV.getFechaHoraActual();

        JOptionPane.showMessageDialog(null, mensajeInicio);

        String opcionSeleccionada = JOptionPane.showInputDialog(
                null,
                "Seleccione una opción:\n\n"
                + "1. Modo Usuario\n"
                + "2. Modo Administrador"
        );

        switch (opcionSeleccionada) {
            case "Modo Usuario" ->
                iniciarModoUsuario();
            case "Modo Administrador" ->
                iniciarModoAdmin();
            default ->
                JOptionPane.showMessageDialog(null, "Opción no válida");
        }
    }

    // Método para iniciar el modo de usuario (CAMBIAR A SOUT).
    public void iniciarModoUsuario() {
        JOptionPane.showMessageDialog(null, "Modo Usuario Iniciado");
    }

    // Método para iniciar el modo de administrador, generando o solicitando la contraseña de administrador.
    public void iniciarModoAdmin() {
        String password;
        if (atributosTPV.getPasswordAdmin() == null || atributosTPV.getPasswordAdmin().isEmpty()) {
            // Si la contraseña de administrador aún no se ha generado, se crea una nueva.
            password = generarPasswordAleatoria();
            atributosTPV.setPasswordAdmin(password);
            JOptionPane.showMessageDialog(null, "Contraseña de administrador generada: " + password);
        } else {
            // Si ya hay una contraseña de administrador creada, se solicita.
            password = JOptionPane.showInputDialog(null, "Ingrese la contraseña de administrador:");
        }

        if (password != null && validarPasswordAdmin(password)) {
            JOptionPane.showMessageDialog(null, "Modo Administrador Iniciado");
        } else {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta. No se puede acceder al modo administrador.");
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
            JOptionPane.showMessageDialog(null, "La contraseña no cumple con los criterios especificados.");
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

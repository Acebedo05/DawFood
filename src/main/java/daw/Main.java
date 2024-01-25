package daw;

import daw.tpv.FuncionesTPV;
import daw.tpv.ObjetosTPV;
import daw.productos.MenuComida;

/**
 *
 * @author acebedo
 */
public class Main {

    public static void main(String[] args) {

        // Inicializar el TPV desde la clase ObjetosTPV.
        FuncionesTPV funcionesTPV = ObjetosTPV.inicializarTPV();

        // Iniciar el TPV.
        funcionesTPV.encenderTPV();
        
        MenuComida menuComida = null;
        menuComida.llamarInicializarComidas();

    }
}

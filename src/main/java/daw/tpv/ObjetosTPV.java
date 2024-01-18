package daw.tpv;

/**
 *
 * @author acebedo
 */
public class ObjetosTPV {

    public static FuncionesTPV inicializarTPV() {
        // Crear instancias de AtributosTPV y FuncionesTPV
        AtributosTPV atributosTPV = new AtributosTPV("Estepona", null);
        FuncionesTPV funcionesTPV = new FuncionesTPV(atributosTPV);

        return funcionesTPV;
    }

}

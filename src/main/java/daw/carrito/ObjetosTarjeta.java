package daw.carrito;

import java.time.LocalDate;

/**
 *
 * @author acebedo
 */
public class ObjetosTarjeta {

    public static void main(String[] args) {
        
        // Crear cinco instancias de AtributosTarjeta
        AtributosTarjeta tarjeta1 = new AtributosTarjeta(1234, LocalDate.of(2025, 12, 1), 123, 1000, "Juan Perez");
        AtributosTarjeta tarjeta2 = new AtributosTarjeta(5432, LocalDate.of(2025, 10, 4), 456, 500, "Maria Rodriguez");
        AtributosTarjeta tarjeta3 = new AtributosTarjeta(1122, LocalDate.of(2025, 8, 23), 789, 200, "Pedro Gomez");
        AtributosTarjeta tarjeta4 = new AtributosTarjeta(3322, LocalDate.of(2025, 5, 22), 234, 1500, "Ana Martinez");
        AtributosTarjeta tarjeta5 = new AtributosTarjeta(1422, LocalDate.of(2025, 3, 21), 567, 3000, "Carlos Herrera");

    }
}

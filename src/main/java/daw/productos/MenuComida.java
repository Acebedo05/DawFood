package daw.productos;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author krach
 */
public class MenuComida {

    private ArrayList<Comida> comidas;

    public void MenuComidas() {
        comidas = new ArrayList<>();
        inicializarComidas();

    }

    private void inicializarComidas() {

        comidas.add(new Pizza("P001", "4 Quesos", true, "Comidas", "Pizzas", "Pizza hecha de 4 quesos distintos", 12, 0.10));
        comidas.add(new Pizza("P002", "Margarita", true, "Comidas", "Pizzas", "Pizza basica", 10, 0.10));
        comidas.add(new Pizza("P003", "Boloñesa", true, "Comidas", "Pizzas", "Pizza hecha de queso y  carne", 15, 0.10));

        comidas.add(new Hamburguesa("H001", "Pollo", true, "Comidas", "Hamburguesa", "Hamburguesa de pollo", 6, 0.10));
        comidas.add(new Hamburguesa("H002", "Ternera", true, "Comidas", "Hamburguesa", "Hamburguesa de ternera", 10, 0.10));
        comidas.add(new Hamburguesa("H003", "Vegetal", true, "Comidas", "Hamburguesa", "Hamburguesa vegetal", 12, 0.10));

        comidas.add(new Kebab("K001", "Pollo", true, "Comidas", "Kebab", "Kebab de pollo", 4, 0.10));
        comidas.add(new Kebab("K002", "Ternera", true, "Comidas", "Kebab", "Kebab de ternera", 4.50, 0.10));
        comidas.add(new Kebab("K003", "Mixto", true, "Comidas", "Kebab", "Kebab mixto", 5.50, 0.10));

    }

//    public void mostrarMenu() {
//        JOptionPane.showConfirmDialog(null, hola());
//        Por hacer
//    }

}

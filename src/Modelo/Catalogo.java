package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {

    public static final int NIVEL = 5;

    public static List<Pokemon> crear() {
        List<Pokemon> lista = new ArrayList<>();
        lista.add(new PokemonFuego("Charmander", NIVEL, 90, 14, 8, 11));
        lista.add(new PokemonFuego("Vulpix", NIVEL, 85, 13, 9, 12));
        lista.add(new PokemonAgua("Squirtle", NIVEL, 100, 11, 12, 9));
        lista.add(new PokemonAgua("Psyduck", NIVEL, 95, 12, 9, 10));
        lista.add(new PokemonPlanta("Bulbasaur", NIVEL, 100, 12, 10, 8));
        lista.add(new PokemonPlanta("Oddish", NIVEL, 90, 13, 8, 7));
        lista.add(new PokemonElectrico("Pikachu", NIVEL, 80, 14, 7, 15));
        lista.add(new PokemonElectrico("Voltorb", NIVEL, 75, 12, 9, 16));
        return lista;
    }
}

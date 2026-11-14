package Modelo;

import Modelo.Pokemon;

public class PokemonFuego extends Pokemon {

    private static final long serialVersionUID = 1L;

    public PokemonFuego(String nombre, int nivel, int ps, int ataque, int defensa, int velocidad) {
        super(nombre, TipoPokemon.FUEGO, nivel, ps, ataque, defensa, velocidad, "Lanzallamas", 20);
    }

    @Override
    public String habilidadEspecial(Pokemon objetivo) {
        int dano = golpear(objetivo, getPotenciaHabilidad(), 1.0);
        return resumenGolpe(getNombreHabilidad(), objetivo, dano);
    }

    @Override
    public String previsualizarHabilidad(Pokemon objetivo) {
        int dano = calcularDano(objetivo, getPotenciaHabilidad(), 1.0);
        return textoDano(objetivo, dano);
    }
}

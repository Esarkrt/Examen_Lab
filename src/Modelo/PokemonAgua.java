package Modelo;

import Modelo.Pokemon;

public class PokemonAgua extends Pokemon {

    private static final long serialVersionUID = 1L;

    public PokemonAgua(String nombre, int nivel, int ps, int ataque, int defensa, int velocidad) {
        super(nombre, TipoPokemon.AGUA, nivel, ps, ataque, defensa, velocidad, "Hidropulso", 18);
    }

    @Override
    public String habilidadEspecial(Pokemon objetivo) {
        int dano = golpear(objetivo, getPotenciaHabilidad(), 1.0);
        int curado = recuperarPS(getPsMax() / 10);
        String msg = resumenGolpe(getNombreHabilidad(), objetivo, dano);
        if (curado > 0) {
            msg += " " + getNombre() + " recupera " + curado + " PS.";
        }
        return msg;
    }

    @Override
    public String previsualizarHabilidad(Pokemon objetivo) {
        int dano = calcularDano(objetivo, getPotenciaHabilidad(), 1.0);
        int curacion = Math.min(getPsMax() / 10, getPsMax() - getPs());
        return textoDano(objetivo, dano) + ". Te curas " + curacion + " PS";
    }
}

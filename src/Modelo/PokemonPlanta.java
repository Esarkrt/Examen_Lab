package Modelo;

import Modelo.Pokemon;

public class PokemonPlanta extends Pokemon {

    private static final long serialVersionUID = 1L;

    public PokemonPlanta(String nombre, int nivel, int ps, int ataque, int defensa, int velocidad) {
        super(nombre, TipoPokemon.PLANTA, nivel, ps, ataque, defensa, velocidad, "Drenadoras", 18);
    }

    
    @Override
    public String habilidadEspecial(Pokemon objetivo) {
        int dano = golpear(objetivo, getPotenciaHabilidad(), 1.0);
        int absorbido = recuperarPS(dano / 2);
        String msg = resumenGolpe(getNombreHabilidad(), objetivo, dano);
        if (absorbido > 0) {
            msg += " " + getNombre() + " absorbe " + absorbido + " PS.";
        }
        return msg;
    }

    @Override
    public String previsualizarHabilidad(Pokemon objetivo) {
        int dano = Math.min(calcularDano(objetivo, getPotenciaHabilidad(), 1.0), objetivo.getPs());
        int absorcion = Math.min(dano / 2, getPsMax() - getPs());
        return textoDano(objetivo, dano) + ". Absorbes " + absorcion + " PS";
    }
}

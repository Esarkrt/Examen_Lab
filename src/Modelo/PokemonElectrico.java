package Modelo;

import Modelo.Pokemon;

public class PokemonElectrico extends Pokemon {
    private static final long serialVersionUID = 1L;
    private static final double PROB_CRITICO = 0.25;
    private static final double MULT_CRITICO = 1.5;

    public PokemonElectrico(String nombre, int nivel, int ps, int ataque, int defensa, int velocidad) {
        super(nombre, TipoPokemon.ELECTRICO, nivel, ps, ataque, defensa, velocidad, "Impactrueno", 17);
    }

    
    @Override
    public String habilidadEspecial(Pokemon objetivo) {
        boolean critico = RNG.nextDouble() < PROB_CRITICO;
        int dano = golpear(objetivo, getPotenciaHabilidad(), critico ? MULT_CRITICO : 1.0);
        String msg = resumenGolpe(getNombreHabilidad(), objetivo, dano);
        if (critico) msg += " ¡Golpe crítico!";
        return msg;
    }

    @Override
    public String previsualizarHabilidad(Pokemon objetivo) {
        int normal = calcularDano(objetivo, getPotenciaHabilidad(), 1.0);
        int critico = calcularDano(objetivo, getPotenciaHabilidad(), MULT_CRITICO);
        return textoDano(objetivo, normal) + ". Si es crítico (25%): " + Math.min(critico, objetivo.getPs()) + " PS";
    }
}
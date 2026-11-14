/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.EnumSet;
import java.util.Set;

/**
 *
 * @author Garrett-PC
 */
public enum EstadoBatalla {
    PREPARACION("Preparación"),
    ESPERANDO_ACCION("Esperando acción"),
    RESOLVIENDO_RONDA("Resolviendo ronda"),
    ELEGIR_REEMPLAZO("Elegir reemplazo"),
    VICTORIA("Victoria"),
    DERROTA("Derrota"),
    EMPATE("Empate");

    private final String etiqueta;

    EstadoBatalla(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public boolean esFinal() {
        return this == VICTORIA || this == DERROTA || this == EMPATE;
    }

    
    public static EstadoBatalla porEquipos(boolean jugadorSinPokemon, boolean rivalSinPokemon) {
        if (jugadorSinPokemon && rivalSinPokemon) {
            return EMPATE;
        }
        if (jugadorSinPokemon) {
            return DERROTA;
        }
        return VICTORIA;
    }

    
    public static EstadoBatalla porPuntos(int psJugador, int psRival) {
        if (psJugador > psRival) {
            return VICTORIA;
        }
        if (psJugador < psRival) {
            return DERROTA;
        }
        return EMPATE;
    }

    public String mensajeFinal(String entrenador) {
        switch (this) {
            case VICTORIA:
                return "¡Victoria! " + entrenador + " ganó la batalla.";
            case DERROTA:
                return "Derrota. " + entrenador + " perdió la batalla.";
            case EMPATE:
                return "¡Empate! Nadie ganó la batalla.";
            default:
                return "La batalla sigue en curso.";
        }
    }
}

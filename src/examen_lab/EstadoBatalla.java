/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_lab;

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
}

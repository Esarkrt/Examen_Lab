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
public enum TipoPokemon {
    FUEGO("Fuego"), AGUA("Agua"), PLANTA("Planta"), ELECTRICO("Eléctrico");

    private static final double[][] TABLA = {
        {0.5, 0.5, 2.0, 1.0},
        {2.0, 0.5, 0.5, 1.0},
        {0.5, 2.0, 0.5, 1.0},
        {1.0, 2.0, 0.5, 0.5}
    };

    private final String nombre;

    TipoPokemon(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public double multiplicadorContra(TipoPokemon defensor) {
        return TABLA[this.ordinal()][defensor.ordinal()];
    }

    public static String mensajeEfectividad(double multiplicador) {
        if (multiplicador > 1.0) {
            return "¡Es muy eficaz!";
        }
        if (multiplicador < 1.0) {
            return "No es muy eficaz…";
        }
        return "";
    }
}

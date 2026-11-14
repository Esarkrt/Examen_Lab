/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Garrett-PC
 */
public enum Efectividad {
    MUY_EFICAZ("¡Es muy eficaz!"),
    NORMAL(""),
    POCO_EFICAZ("No es muy eficaz…");

    private final String mensaje;

    Efectividad(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public static Efectividad desde(double multiplicador) {
        if (multiplicador > 1.0) {
            return MUY_EFICAZ;
        }
        if (multiplicador < 1.0) {
            return POCO_EFICAZ;
        }
        return NORMAL;
    }
}

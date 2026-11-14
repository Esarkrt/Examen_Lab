/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;

/**
 *
 * @author Garrett-PC
 */
public enum AccionBatalla {

    ATACAR("Atacar", new AtaqueBasico()) {
        @Override
        protected String validarEspecifico(List<Pokemon> equipo, int activo, int cambio) {
            Pokemon p = equipo.get(activo);
            return getHabilidad().puedeUsar(p) ? null : p.getNombre() + " no puede atacar.";
        }
    },

    HABILIDAD_ESPECIAL("Habilidad especial", new HabilidadEspecial()) {
        @Override
        protected String validarEspecifico(List<Pokemon> equipo, int activo, int cambio) {
            Pokemon p = equipo.get(activo);
            if (p.getUsosHabilidad() <= 0) return p.getNombre() + " no tiene usos de habilidad especial.";
            return null;
        }
    },

    RECUPERAR("Recuperar", new Recuperacion()) {
        @Override
        protected String validarEspecifico(List<Pokemon> equipo, int activo, int cambio) {
            Pokemon p = equipo.get(activo);
            if (p.getUsosRecuperar() <= 0) return p.getNombre() + " no tiene usos de recuperación.";
            if (p.getPs() >= p.getPsMax()) return p.getNombre() + " ya tiene los PS completos.";
            return null;
        }
    },

    CAMBIAR("Cambiar de Pokémon", null) {
        @Override
        protected String validarEspecifico(List<Pokemon> equipo, int activo, int cambio) {
            if (cambio < 0 || cambio >= equipo.size()) return "Selecciona un Pokémon válido.";
            if (cambio == activo) return "Ese Pokémon ya está en combate.";
            if (equipo.get(cambio).estaDebilitado()) return "Ese Pokémon está debilitado.";
            return null;
        }
    };

    private final String etiqueta;
    private final Habilidad habilidad; 

    AccionBatalla(String etiqueta, Habilidad habilidad) {
        this.etiqueta = etiqueta;
        this.habilidad = habilidad;
    }

    
    protected abstract String validarEspecifico(List<Pokemon> equipo, int activo, int cambio);

    
    public final String motivoInvalido(List<Pokemon> equipo, int activo, int cambio) {
        Pokemon p = equipo.get(activo);
        if (p.estaDebilitado()) return p.getNombre() + " está debilitado y no puede actuar.";
        return validarEspecifico(equipo, activo, cambio);
    }

    public String ejecutar(Pokemon usuario, Pokemon objetivo) {
        return habilidad.ejecutar(usuario, objetivo);
    }

    public boolean esCambio() {
        return habilidad == null;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}


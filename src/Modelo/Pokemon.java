package Modelo;

import java.io.Serializable;
import java.util.Random;

public abstract class Pokemon implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    public static final int USOS_HABILIDAD = 3;
    public static final int USOS_RECUPERAR = 2;
    protected static final Random RNG = new Random();

    private final String nombre;
    private final TipoPokemon tipo;
    private final int nivel;
    private final int psMax;
    private int ps;
    private final int ataque;
    private final int defensa;
    private final int velocidad;
    private final String nombreHabilidad;
    private final int potenciaHabilidad;
    private int usosHabilidad = USOS_HABILIDAD;
    private int usosRecuperar = USOS_RECUPERAR;

    protected Pokemon(String nombre, TipoPokemon tipo, int nivel, int psMax, int ataque, int defensa,
            int velocidad, String nombreHabilidad, int potenciaHabilidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.psMax = psMax;
        this.ps = psMax;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.nombreHabilidad = nombreHabilidad;
        this.potenciaHabilidad = potenciaHabilidad;
    }

    public abstract String habilidadEspecial(Pokemon objetivo);

    public abstract String previsualizarHabilidad(Pokemon objetivo);

    public String getNombre() {
        return nombre;
    }

    public TipoPokemon getTipo() {
        return tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public int getPsMax() {
        return psMax;
    }

    public int getPs() {
        return ps;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getUsosHabilidad() {
        return usosHabilidad;
    }

    public int getUsosRecuperar() {
        return usosRecuperar;
    }

    public String getNombreHabilidad() {
        return nombreHabilidad;
    }

    public int getPotenciaHabilidad() {
        return potenciaHabilidad;
    }

    public boolean estaDebilitado() {
        return ps <= 0;
    }

    public int recibirDano(int dano) {
        int real = Math.min(Math.max(dano, 0), ps);
        ps -= real;
        return real;
    }

    public int recuperarPS(int cantidad) {
        int antes = ps;
        ps = Math.min(psMax, ps + Math.max(cantidad, 0));
        return ps - antes;
    }

    public boolean puedeUsarHabilidad() {
        return !estaDebilitado() && usosHabilidad > 0;
    }

    public void consumirUsoHabilidad() {
        if (usosHabilidad > 0) {
            usosHabilidad--;
        }
    }

    public boolean puedeRecuperar() {
        return !estaDebilitado() && ps < psMax && usosRecuperar > 0;
    }

    public int recuperar() {
        usosRecuperar--;
        return recuperarPS(psMax / 4);
    }

    public int calcularDano(Pokemon objetivo, int potencia, double multiplicadorExtra) {
        double mult = tipo.multiplicadorContra(objetivo.tipo) * multiplicadorExtra;
        long valor = Math.round((ataque + nivel + potencia - objetivo.defensa / 2.0) * mult);
        return (int) Math.max(1, valor);
    }

    public int golpear(Pokemon objetivo, int potencia, double multiplicadorExtra) {
        return objetivo.recibirDano(calcularDano(objetivo, potencia, multiplicadorExtra));
    }

    public String resumenGolpe(String movimiento, Pokemon objetivo, int dano) {
        String efectividad = tipo.mensajeContra(objetivo.tipo);
        String texto = nombre + " usa " + movimiento + " contra " + objetivo.nombre + ". ";
        if (!efectividad.isEmpty()) {
            texto += efectividad + " ";
        }
        return texto + "Causa " + dano + " de daño.";
    }

    protected String textoDano(Pokemon objetivo, int dano) {
        int real = Math.min(dano, objetivo.ps);
        String texto = "Daño estimado: " + real + " PS";
        String efectividad = tipo.mensajeContra(objetivo.tipo);
        if (!efectividad.isEmpty()) {
            texto += " (" + efectividad + ")";
        }
        if (real >= objetivo.ps) {
            texto += " - lo debilitaría";
        }
        return texto;
    }

    @Override
    public Pokemon clone() throws CloneNotSupportedException {
        try {
            return (Pokemon) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo.getNombre() + ", Nv " + nivel + ") PS " + ps + "/" + psMax;
    }
}

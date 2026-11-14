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
import java.io.Serializable;
import java.util.Random;
public abstract class Pokemon implements Serializable,Cloneable {
    private static final long serialVersionUID = 1L;
    protected String nombre;
    protected TipoPokemon tipo;
    protected int psMax;
    protected int ps;
    protected int ataque;
    protected int defensa;
    protected int velocidad;
    private final int ataqueEspecialDisp=3;
    private final int recuperacionDisp=2;
    protected int nivel; 

    public Pokemon(String nombre, TipoPokemon tipo, int psMax, int ps, int ataque, int defensa, int velocidad, int nivel) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.psMax = psMax;
        this.ps = ps;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.nivel = 5;
    }
    
}

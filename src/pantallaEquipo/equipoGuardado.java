package pantallaEquipo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class equipoGuardado implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombreentrenador;
    private List<Pokemon> listaequipo;

    public equipoGuardado(String nombreentrenador, List<Pokemon> listaequipo) {
        this.nombreentrenador = nombreentrenador;
        this.listaequipo = new ArrayList<>();
        for (Pokemon p : listaequipo) {
            this.listaequipo.add(p.clone());
        }
    }

    public String getNombreEntrenador() {
        return nombreentrenador;
    }

    public List<Pokemon> getListaEquipo() {
        return listaequipo;
    }
}

enum TipoPokemon {
    FUEGO, AGUA, PLANTA, ELECTRICO;

    private static final double[][] tablaefectividad = {
        {0.5, 0.5, 2.0, 1.0},
        {2.0, 0.5, 0.5, 1.0},
        {0.5, 2.0, 0.5, 1.0},
        {1.0, 2.0, 0.5, 0.5}
    };

    public double obtenerMultiplicador(TipoPokemon defensor) {
        return tablaefectividad[this.ordinal()][defensor.ordinal()];
    }

    public String obtenerMensajeEfectividad(TipoPokemon defensor) {
        double multiplicador = obtenerMultiplicador(defensor);
        if (multiplicador == 2.0) {
            return "¡Es muy eficaz!";
        }
        if (multiplicador == 0.5) {
            return "No es muy eficaz...";
        }
        return "";
    }
}

abstract class Pokemon implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    protected String nombre;
    protected TipoPokemon tipo;
    protected int nivel = 5;
    protected int psmaximos;
    protected int psactuales;
    protected int ataque;
    protected int defensa;
    protected int velocidad;
    protected int usoshabilidad = 3;
    protected int usosrecuperacion = 2;

    public Pokemon(String nombre, TipoPokemon tipo, int psmaximos, int ataque, int defensa, int velocidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.psmaximos = psmaximos;
        this.psactuales = psmaximos;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
    }

    public boolean estaDebilitado() {
        return psactuales <= 0;
    }

    public void recibirDanio(int danio) {
        this.psactuales = Math.max(0, this.psactuales - danio);
    }

    public void restaurarSalud(int cantidad) {
        this.psactuales = Math.min(this.psmaximos, this.psactuales + cantidad);
    }

    public int calcularDanio(Pokemon defensor, int potencia, double multiplicadorextra) {
        double multtipo = this.tipo.obtenerMultiplicador(defensor.getTipo());
        double base = (this.ataque + this.nivel + potencia - (defensor.getDefensa() / 2.0)) * multtipo * multiplicadorextra;
        return Math.max(1, (int) Math.round(base));
    }

    public String atacar(Pokemon defensor) {
        int danio = calcularDanio(defensor, 10, 1.0);
        defensor.recibirDanio(danio);
        String msgefecto = this.tipo.obtenerMensajeEfectividad(defensor.getTipo());
        return nombre + " atacó a " + defensor.getNombre() + " causando " + danio + " de daño. " + msgefecto;
    }

    public abstract String usarHabilidadEspecial(Pokemon defensor);

    public abstract String getHabilidadEspecial();

    public String recuperar() {
        if (usosrecuperacion <= 0 || psactuales >= psmaximos) {
            return nombre + " no puede recuperarse.";
        }
        usosrecuperacion--;
        int cura = (int) Math.floor(psmaximos * 0.25);
        restaurarSalud(cura);
        return nombre + " recuperó " + cura + " PS. (Usos restantes: " + usosrecuperacion + ")";
    }

    @Override
    public Pokemon clone() {
        try {
            return (Pokemon) super.clone();
        } catch (CloneNotSupportedException error) {
            throw new AssertionError(error);
        }
    }

    @Override
    public String toString() {
        return nombre + " [" + tipo + "] - Niv." + nivel + " (PS: " + psactuales + "/" + psmaximos + ")";
    }

    public String getNombre() { return nombre; }
    public TipoPokemon getTipo() { return tipo; }
    public int getNivel() { return nivel; }
    public int getPsMaximos() { return psmaximos; }
    public int getPsActuales() { return psactuales; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getVelocidad() { return velocidad; }
    public int getUsosHabilidad() { return usoshabilidad; }
    public int getUsosRecuperacion() { return usosrecuperacion; }
}

class PokemonFuego extends Pokemon {
    private static final long serialVersionUID = 1L;

    public PokemonFuego(String nombre, int psmaximos, int ataque, int defensa, int velocidad) {
        super(nombre, TipoPokemon.FUEGO, psmaximos, ataque, defensa, velocidad);
    }

    @Override
    public String usarHabilidadEspecial(Pokemon defensor) {
        usoshabilidad--;
        int danio = calcularDanio(defensor, 20, 1.0);
        defensor.recibirDanio(danio);
        return nombre + " usó Llamarada (" + danio + " daño). " + tipo.obtenerMensajeEfectividad(defensor.getTipo());
    }

    @Override
    public String getHabilidadEspecial() {
        return "Llamarada (Potencia 20, daño elevado)";
    }
}

class PokemonAgua extends Pokemon {
    private static final long serialVersionUID = 1L;

    public PokemonAgua(String nombre, int psmaximos, int ataque, int defensa, int velocidad) {
        super(nombre, TipoPokemon.AGUA, psmaximos, ataque, defensa, velocidad);
    }

    @Override
    public String usarHabilidadEspecial(Pokemon defensor) {
        usoshabilidad--;
        int danio = calcularDanio(defensor, 18, 1.0);
        defensor.recibirDanio(danio);
        int cura = (int) Math.floor(psmaximos * 0.10);
        restaurarSalud(cura);
        return nombre + " usó Hidropulso (" + danio + " daño) y recuperó " + cura + " PS. " + tipo.obtenerMensajeEfectividad(defensor.getTipo());
    }

    @Override
    public String getHabilidadEspecial() {
        return "Hidropulso (Potencia 18 + recupera 10% PS)";
    }
}

class PokemonPlanta extends Pokemon {
    private static final long serialVersionUID = 1L;

    public PokemonPlanta(String nombre, int psmaximos, int ataque, int defensa, int velocidad) {
        super(nombre, TipoPokemon.PLANTA, psmaximos, ataque, defensa, velocidad);
    }

    @Override
    public String usarHabilidadEspecial(Pokemon defensor) {
        usoshabilidad--;
        int danio = calcularDanio(defensor, 18, 1.0);
        defensor.recibirDanio(danio);
        int absorbido = danio / 2;
        restaurarSalud(absorbido);
        return nombre + " usó Gigadrenado (" + danio + " daño) y absorbió " + absorbido + " PS. " + tipo.obtenerMensajeEfectividad(defensor.getTipo());
    }

    @Override
    public String getHabilidadEspecial() {
        return "Gigadrenado (Potencia 18 + absorbe 50% daño)";
    }
}

class PokemonElectrico extends Pokemon {
    private static final long serialVersionUID = 1L;

    public PokemonElectrico(String nombre, int psmaximos, int ataque, int defensa, int velocidad) {
        super(nombre, TipoPokemon.ELECTRICO, psmaximos, ataque, defensa, velocidad);
    }

    @Override
    public String usarHabilidadEspecial(Pokemon defensor) {
        usoshabilidad--;
        boolean critico = Math.random() < 0.25;
        double multcritico = critico ? 1.5 : 1.0;
        int danio = calcularDanio(defensor, 17, multcritico);
        defensor.recibirDanio(danio);
        return nombre + " usó Impactrueno (" + danio + " daño)" + (critico ? " ¡CRÍTICO! " : ". ") + tipo.obtenerMensajeEfectividad(defensor.getTipo());
    }

    @Override
    public String getHabilidadEspecial() {
        return "Impactrueno (Potencia 17, 25% crítico x1.5)";
    }
}

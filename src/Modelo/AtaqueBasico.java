package Modelo;

public class AtaqueBasico implements Habilidad {
    public static final int POTENCIA = 10;

    @Override
    public String getNombre() {
        return "Ataque";
    }

    @Override
    public boolean puedeUsar(Pokemon usuario) {
        return !usuario.estaDebilitado();
    }

    @Override
    public String ejecutar(Pokemon usuario, Pokemon objetivo) {
        int dano = usuario.golpear(objetivo, POTENCIA, 1.0);
        return usuario.resumenGolpe(getNombre(), objetivo, dano);
    }
}
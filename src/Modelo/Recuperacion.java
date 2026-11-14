package Modelo;

import Modelo.Pokemon;
import Modelo.Habilidad;

public class Recuperacion implements Habilidad {

    @Override
    public String getNombre() {
        return "Recuperar";
    }

    @Override
    public boolean puedeUsar(Pokemon usuario) {
        return usuario.puedeRecuperar();
    }

    @Override
    public String ejecutar(Pokemon usuario, Pokemon objetivo) {
        int curado = usuario.recuperar();
        return usuario.getNombre() + " usa Recuperar y restaura " + curado + " PS.";
    }
}
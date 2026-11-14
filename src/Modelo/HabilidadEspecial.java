package Modelo;

import Modelo.Habilidad;

public class HabilidadEspecial implements Habilidad {

    @Override
    public String getNombre() {
        return "Habilidad especial";
    }

    @Override
    public boolean puedeUsar(Pokemon usuario) {
        return usuario.puedeUsarHabilidad();
    }

    @Override
    public String ejecutar(Pokemon usuario, Pokemon objetivo) {
        usuario.consumirUsoHabilidad();
        return usuario.habilidadEspecial(objetivo);
    }
}
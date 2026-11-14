package Modelo;

import Modelo.Pokemon;
import java.util.List;


public class UtilEquipo {

    
    public static int sumaPS(List<Pokemon> equipo, int i) {
        if (i >= equipo.size()) return 0;
        return equipo.get(i).getPs() + sumaPS(equipo, i + 1);
    }

    
    public static int primerDisponible(List<Pokemon> equipo, int i, int excluir) {
        if (i >= equipo.size()) return -1;
        if (i != excluir && !equipo.get(i).estaDebilitado()) return i;
        return primerDisponible(equipo, i + 1, excluir);
    }

    public static int contarDebilitados(List<Pokemon> equipo, int i) {
        if (i >= equipo.size()) return 0;
        int actual = equipo.get(i).estaDebilitado() ? 1 : 0;
        return actual + contarDebilitados(equipo, i + 1);
    }
}
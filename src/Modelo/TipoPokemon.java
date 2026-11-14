package Modelo;

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
        return TABLA[ordinal()][defensor.ordinal()];
    }

    public String mensajeContra(TipoPokemon defensor) {
        double m = multiplicadorContra(defensor);
        if (m > 1) return "¡Es muy eficaz!";
        if (m < 1) return "No es muy eficaz…";
        return "";
    }
}
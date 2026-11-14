package Modelo;

import Modelo.AccionBatalla;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Batalla {

    public static final int MAX_RONDAS = 50;

    private final String entrenador;
    private final String rival = "Rival CPU";
    private final List<Pokemon> equipoJugador = new ArrayList<>();
    private final List<Pokemon> equipoRival = new ArrayList<>();
    private final Random rnd = new Random();

    private int activoJ = 0;
    private int activoR = 0;
    private int ronda = 1;
    private EstadoBatalla estado = EstadoBatalla.PREPARACION;

    public Batalla(String entrenador, List<Pokemon> equipo, List<Pokemon> catalogo) throws CloneNotSupportedException {
        if (equipo.isEmpty()) {
            throw new IllegalArgumentException("El equipo no puede estar vacío.");
        }
        this.entrenador = entrenador;
        for (Pokemon p : equipo) {
            equipoJugador.add(p.clone());
            equipoRival.add(catalogo.get(rnd.nextInt(catalogo.size())).clone());
        }
    }

    public List<String> iniciar() {
        estado = EstadoBatalla.ESPERANDO_ACCION;
        List<String> msgs = new ArrayList<>();
        msgs.add("¡Comienza la batalla: " + entrenador + " vs " + rival + "!");
        msgs.add(entrenador + " envía a " + getActivoJugador().getNombre() + ".");
        msgs.add(rival + " envía a " + getActivoRival().getNombre() + ".");
        return msgs;
    }

    public String motivoInvalido(AccionBatalla accion, int indiceCambio) {
        if (estado != EstadoBatalla.ESPERANDO_ACCION) {
            return "Ahora no se puede actuar.";
        }
        return accion.motivoInvalido(equipoJugador, activoJ, indiceCambio);
    }

    public boolean accionValida(AccionBatalla accion, int indiceCambio) {
        return motivoInvalido(accion, indiceCambio) == null;
    }

    public List<Integer> indicesDisponiblesJugador() {
        return disponibles(equipoJugador, activoJ);
    }

    public List<String> ejecutarRonda(AccionBatalla accionJ, int indiceCambioJ) {
        String motivo = motivoInvalido(accionJ, indiceCambioJ);
        if (motivo != null) {
            throw new IllegalStateException(motivo);
        }

        estado = EstadoBatalla.RESOLVIENDO_RONDA;
        List<String> msgs = new ArrayList<>();
        msgs.add("--- Ronda " + ronda + " ---");

        List<Integer> dispR = disponibles(equipoRival, activoR);
        List<AccionBatalla> validasR = new ArrayList<>();
        for (AccionBatalla a : AccionBatalla.values()) {
            int prueba = (a.esCambio() && !dispR.isEmpty()) ? dispR.get(0) : -1;
            if (a.motivoInvalido(equipoRival, activoR, prueba) == null) {
                validasR.add(a);
            }
        }
        AccionBatalla accionR = validasR.get(rnd.nextInt(validasR.size()));
        int indiceR = accionR.esCambio() ? dispR.get(rnd.nextInt(dispR.size())) : -1;

        if (accionJ.esCambio()) {
            msgs.add(entrenador + " retira a " + getActivoJugador().getNombre()
                    + " y envía a " + equipoJugador.get(indiceCambioJ).getNombre() + ".");
            activoJ = indiceCambioJ;
        }
        if (accionR.esCambio()) {
            msgs.add(rival + " retira a " + getActivoRival().getNombre()
                    + " y envía a " + equipoRival.get(indiceR).getNombre() + ".");
            activoR = indiceR;
        }

        boolean jPrimero = getActivoJugador().getVelocidad() >= getActivoRival().getVelocidad();
        if (jPrimero) {
            if (!accionJ.esCambio()) {
                actuar(true, accionJ, msgs);
            }
            if (!accionR.esCambio()) {
                actuar(false, accionR, msgs);
            }
        } else {
            if (!accionR.esCambio()) {
                actuar(false, accionR, msgs);
            }
            if (!accionJ.esCambio()) {
                actuar(true, accionJ, msgs);
            }
        }

        cerrarRonda(msgs);
        return msgs;
    }

    public List<String> elegirReemplazo(int indice) {
        if (estado != EstadoBatalla.ELEGIR_REEMPLAZO) {
            throw new IllegalStateException("No se debe elegir reemplazo ahora.");
        }
        if (!indicesDisponiblesJugador().contains(indice)) {
            throw new IllegalStateException("Ese Pokémon no está disponible.");
        }
        List<String> msgs = new ArrayList<>();
        activoJ = indice;
        msgs.add("¡Adelante, " + getActivoJugador().getNombre() + "!");
        avanzarRonda(msgs);
        return msgs;
    }

    private void actuar(boolean esJugador, AccionBatalla accion, List<String> msgs) {
        Pokemon usuario = esJugador ? getActivoJugador() : getActivoRival();
        Pokemon objetivo = esJugador ? getActivoRival() : getActivoJugador();
        boolean ataca = accion != AccionBatalla.RECUPERAR;

        if (usuario.estaDebilitado()) {
            msgs.add(usuario.getNombre() + " está debilitado y pierde su acción.");
            return;
        }
        if (ataca && objetivo.estaDebilitado()) {
            return;
        }

        msgs.add(accion.ejecutar(usuario, objetivo));
        if (ataca && objetivo.estaDebilitado()) {
            msgs.add(objetivo.getNombre() + " se debilitó.");
        }
    }

    private void cerrarRonda(List<String> msgs) {

        if (getActivoRival().estaDebilitado()) {
            int n = UtilEquipo.primerDisponible(equipoRival, 0, activoR);
            if (n >= 0) {
                activoR = n;
                msgs.add(rival + " envía a " + getActivoRival().getNombre() + ".");
            }
        }

        boolean jSin = UtilEquipo.primerDisponible(equipoJugador, 0, -1) < 0;
        boolean rSin = UtilEquipo.primerDisponible(equipoRival, 0, -1) < 0;
        if (jSin || rSin) {
            estado = EstadoBatalla.porEquipos(jSin, rSin);
            msgs.add(estado.mensajeFinal(entrenador));
            return;
        }

        if (getActivoJugador().estaDebilitado()) {
            estado = EstadoBatalla.ELEGIR_REEMPLAZO;
            msgs.add(entrenador + ", elige tu siguiente Pokémon.");
            return;
        }
        avanzarRonda(msgs);
    }

    private void avanzarRonda(List<String> msgs) {
        if (ronda >= MAX_RONDAS) {
            int sumJ = UtilEquipo.sumaPS(equipoJugador, 0);
            int sumR = UtilEquipo.sumaPS(equipoRival, 0);
            msgs.add("Se alcanzó el límite de " + MAX_RONDAS + " rondas. PS restantes: "
                    + entrenador + " " + sumJ + " vs " + rival + " " + sumR + ".");
            estado = EstadoBatalla.porPuntos(sumJ, sumR);
            msgs.add(estado.mensajeFinal(entrenador));
        } else {
            ronda++;
            estado = EstadoBatalla.ESPERANDO_ACCION;
        }
    }

    private static List<Integer> disponibles(List<Pokemon> equipo, int activo) {
        List<Integer> lista = new ArrayList<>();
        for (int i = 0; i < equipo.size(); i++) {
            if (i != activo && !equipo.get(i).estaDebilitado()) {
                lista.add(i);
            }
        }
        return lista;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public String getRival() {
        return rival;
    }

    public int getRonda() {
        return ronda;
    }

    public EstadoBatalla getEstado() {
        return estado;
    }

    public boolean haTerminado() {
        return estado.esFinal();
    }

    public Pokemon getActivoJugador() {
        return equipoJugador.get(activoJ);
    }

    public Pokemon getActivoRival() {
        return equipoRival.get(activoR);
    }

    public List<Pokemon> getEquipoJugador() {
        return Collections.unmodifiableList(equipoJugador);
    }

    public List<Pokemon> getEquipoRival() {
        return Collections.unmodifiableList(equipoRival);
    }

    public int getIndiceActivoJugador() {
        return activoJ;
    }

    public int getIndiceActivoRival() {
        return activoR;
    }

    public int debilitadosJugador() {
        return UtilEquipo.contarDebilitados(equipoJugador, 0);
    }

    public int debilitadosRival() {
        return UtilEquipo.contarDebilitados(equipoRival, 0);
    }

    public String getResumenFinal() {
        return estado.mensajeFinal(entrenador) + "\n\n"
                + "Rondas jugadas: " + ronda + "\n"
                + "Pokémon debilitados de " + entrenador + ": " + debilitadosJugador() + "\n"
                + "Pokémon debilitados de " + rival + ": " + debilitadosRival() + "\n\n"
                + "Pokémon en pie de " + entrenador + ": " + enPie(equipoJugador) + "\n"
                + "Pokémon en pie de " + rival + ": " + enPie(equipoRival);
    }

    private static String enPie(List<Pokemon> equipo) {
        String texto = "";
        for (Pokemon p : equipo) {
            if (!p.estaDebilitado()) {
                if (!texto.isEmpty()) {
                    texto += ", ";
                }
                texto += p.getNombre() + " (" + p.getPs() + " PS)";
            }
        }
        return texto.isEmpty() ? "ninguno" : texto;
    }
}

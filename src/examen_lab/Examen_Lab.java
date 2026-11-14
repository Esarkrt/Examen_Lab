/*
 * Examen 1 - Parcial 1: Batallas Pokémon
 * Laboratorio de Programación II - UNITEC
 */
package examen_lab;

import Modelo.Batalla;
import Modelo.Catalogo;
import Modelo.Pokemon;
import P_Batalla.PanelBatalla;
import P_Incio_Registro.PanelInicio;
import P_Resultado.PanelResultado;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Clase principal: arma la ventana y conecta las pantallas.
 *
 * Flujo: Inicio -> Batalla -> Resultado -> Inicio.
 *
 * @author Garrett-PC
 */
public class Examen_Lab {

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Batallas Pokémon");

        CardLayout tarjetas = new CardLayout();
        JPanel contenedor = new JPanel(tarjetas);

        PanelInicio panelInicio = new PanelInicio();
        PanelBatalla panelBatalla = new PanelBatalla();
        PanelResultado panelResultado = new PanelResultado();

        // Pantalla 1 -> Pantalla 3
        panelInicio.setListener(new PanelInicio.Listener() {
            @Override
            public void continuar(String nombre) {
                List<Pokemon> catalogo = Catalogo.crear();
                List<Pokemon> equipo = new ArrayList<>();

                // Equipo temporal mientras se integra la pantalla de selección (4.2).
                equipo.add(catalogo.get(0)); // Charmander
                equipo.add(catalogo.get(2)); // Squirtle
                equipo.add(catalogo.get(4)); // Bulbasaur

                try {
                    panelBatalla.iniciarBatalla(nombre, equipo, catalogo);
                    tarjetas.show(contenedor, "batalla");
                } catch (CloneNotSupportedException ex) {
                    JOptionPane.showMessageDialog(ventana, "No se pudo iniciar la batalla.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Pantalla 3 -> Pantalla 4
        panelBatalla.setListener(new PanelBatalla.Listener() {
            @Override
            public void batallaTerminada(Batalla batalla) {
                panelResultado.mostrar(batalla);
                tarjetas.show(contenedor, "resultado");
            }
        });

        // Pantalla 4 -> Pantalla 1 / salida
        panelResultado.setListener(new PanelResultado.Listener() {
            @Override
            public void nuevaPartida() {
                panelInicio.setNombreEntrenador(panelResultado.getNombreEntrenador());
                tarjetas.show(contenedor, "inicio");
            }

            @Override
            public void salir() {
                if (confirmarSalida(ventana)) {
                    System.exit(0);
                }
            }
        });

        contenedor.add(panelInicio, "inicio");
        contenedor.add(panelBatalla, "batalla");
        contenedor.add(panelResultado, "resultado");

        ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ventana.setContentPane(contenedor);
        ventana.setResizable(false);
        ventana.pack();
        ventana.setLocationRelativeTo(null);

        URL icono = Examen_Lab.class.getResource("/imagenes/logo.png");
        if (icono != null) {
            Image imagen = new ImageIcon(icono).getImage();
            ventana.setIconImage(imagen);
        }

        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (confirmarSalida(ventana)) {
                    System.exit(0);
                }
            }
        });

        tarjetas.show(contenedor, "inicio");
        ventana.setVisible(true);
    }

    private static boolean confirmarSalida(JFrame ventana) {
        int opcion = JOptionPane.showConfirmDialog(ventana,
                "¿Seguro que quieres salir del juego?",
                "Salir",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        return opcion == JOptionPane.YES_OPTION;
    }
}

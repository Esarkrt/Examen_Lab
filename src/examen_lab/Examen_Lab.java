/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_lab;

import P_Incio_Registro.PanelInicio;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Garrett-PC
 */
public class Examen_Lab {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Batallas Pokémon");
        final PanelInicio panelInicio = new PanelInicio();

        ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ventana.setContentPane(panelInicio);
        ventana.pack();
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);

        URL icono = Examen_Lab.class.getResource("/imagenes/logo.png");
        if (icono != null) {
            Image imagen = new ImageIcon(icono).getImage();
            ventana.setIconImage(imagen);
        }

        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (panelInicio.confirmarSalida()) {
                    System.exit(0);
                }
            }
        });

        ventana.setVisible(true);
    }
}

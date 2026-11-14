package examen_lab;

import pantallaEquipo.panelEquipo;

public class Examen_Lab {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new panelEquipo().setVisible(true);
        });
    }
}

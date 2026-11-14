
package P_Incio_Registro;

import P_Incio_Registro.ValidadorNombre;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelInicio extends JPanel implements ActionListener {

    private JLabel lblLogo, lblTitulo, lblSubtitulo, lblNombre;
    private JTextField txtNombre;
    private JButton btnContinuar, btnInstrucciones, btnSalir;
    private JLabel lblFondo;

    public PanelInicio() {
        setLayout(null);
        setPreferredSize(new Dimension(900, 600));

        lblLogo = new JLabel();
        lblLogo.setIcon(new ImageIcon(getClass().getResource("/imagenes/logo.png")));
        lblLogo.setBounds(390, 25, 120, 120);
        add(lblLogo);

        lblTitulo = new JLabel("BATALLAS POKEMON", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 38));
        lblTitulo.setForeground(new Color(255, 215, 0));
        lblTitulo.setBounds(150, 160, 600, 50);
        add(lblTitulo);

        lblSubtitulo = new JLabel("Registro del entrenador", JLabel.CENTER);
        lblSubtitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setBounds(150, 210, 600, 30);
        add(lblSubtitulo);

        lblNombre = new JLabel("Nombre del entrenador:");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setBounds(300, 275, 300, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 16));
        txtNombre.setBounds(300, 305, 300, 35);
        add(txtNombre);

        btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 16));
        btnContinuar.setBounds(300, 365, 300, 40);
        btnContinuar.addActionListener(this);
        add(btnContinuar);

        btnInstrucciones = new JButton("Instrucciones");
        btnInstrucciones.setFont(new Font("Arial", Font.BOLD, 16));
        btnInstrucciones.setBounds(300, 420, 300, 40);
        btnInstrucciones.addActionListener(this);
        add(btnInstrucciones);

        btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 16));
        btnSalir.setBounds(300, 475, 300, 40);
        btnSalir.addActionListener(this);
        add(btnSalir);

        lblFondo = new JLabel();
        lblFondo.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondo.png")));
        lblFondo.setBounds(0, 0, 900, 600);
        add(lblFondo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnContinuar) {
            continuar();
        } else if (e.getSource() == btnInstrucciones) {
            mostrarInstrucciones();
        } else if (e.getSource() == btnSalir) {
            salir();
        }
    }
    
    private void continuar() {
        String nombre = txtNombre.getText();
        String error = ValidadorNombre.validar(nombre);

        if (error != null) {
            JOptionPane.showMessageDialog(this, error,
                    "Nombre inválido", JOptionPane.ERROR_MESSAGE);
            txtNombre.requestFocus();
            return;
        }

        String nombreLimpio = ValidadorNombre.recortar(nombre);
        txtNombre.setText(nombreLimpio);

        JOptionPane.showMessageDialog(this, "¡Bienvenido, entrenador " + nombreLimpio + "!\n"+"Tu nombre fue registrado correctamente.", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarInstrucciones() {
        JOptionPane.showMessageDialog(this,
                "Cómo jugar:\n\n"
                + "1. Escribe el nombre de tu entrenador.\n"
                + "2. Elige tu Pokémon para la batalla.\n"
                + "3. Gana los combates para ser el campeón.\n\n"
                + "El nombre debe tener de 3 a 10 caracteres y solo\n"
                + "puede tener letras, números y espacios.",
                "Instrucciones", JOptionPane.INFORMATION_MESSAGE);
    }

    private void salir() {
        if (confirmarSalida()) {
            System.exit(0);
        }
    }

    public boolean confirmarSalida() {
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Seguro que quieres salir del juego?",
                "Salir",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        return opcion == JOptionPane.YES_OPTION;
    }

    public String getNombreEntrenador() {
        return ValidadorNombre.recortar(txtNombre.getText());
    }
}

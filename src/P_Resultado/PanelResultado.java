package P_Resultado;

import Modelo.Batalla;
import Modelo.EstadoBatalla;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

/**
 * Pantalla 4: resultado de la partida.
 *
 * Muestra si el entrenador ganó o perdió, el resumen de la batalla
 * y los botones "Nueva partida" y "Salir".
 *
 * @author Garrett-PC
 */
public class PanelResultado extends JPanel implements ActionListener {

    public interface Listener {
        void nuevaPartida();
        void salir();
    }

    private Listener listener;
    private Batalla batalla;

    private final JLabel lblLogo = new JLabel();
    private final JLabel lblTitulo = new JLabel("RESULTADO DE LA BATALLA", SwingConstants.CENTER);
    private final JLabel lblResultado = new JLabel(" ", SwingConstants.CENTER);
    private final JTextArea areaResumen = new JTextArea();
    private final JButton btnNueva = new JButton("Nueva partida");
    private final JButton btnSalir = new JButton("Salir");
    private final JLabel lblFondo = new JLabel();

    public PanelResultado() {
        setLayout(null);
        setPreferredSize(new Dimension(900, 600));

        lblLogo.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/logo.png"))
                .getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)));
        lblLogo.setBounds(405, 30, 90, 90);
        add(lblLogo);

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 34));
        lblTitulo.setForeground(new Color(255, 215, 0));
        lblTitulo.setBounds(100, 130, 700, 45);
        add(lblTitulo);

        lblResultado.setFont(new Font("Arial", Font.BOLD, 26));
        lblResultado.setForeground(Color.WHITE);
        lblResultado.setBounds(100, 180, 700, 40);
        add(lblResultado);

        areaResumen.setEditable(false);
        areaResumen.setLineWrap(true);
        areaResumen.setWrapStyleWord(true);
        areaResumen.setFont(new Font("Arial", Font.PLAIN, 14));
        areaResumen.setBackground(Color.WHITE);
        areaResumen.setForeground(Color.BLACK);

        JScrollPane scroll = new JScrollPane(areaResumen);
        TitledBorder borde = BorderFactory.createTitledBorder("Resumen de la batalla");
        borde.setTitleColor(Color.WHITE);
        borde.setTitleFont(new Font("Arial", Font.BOLD, 14));
        scroll.setBorder(borde);
        scroll.setBackground(new Color(15, 25, 42));
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setBounds(150, 240, 600, 220);
        add(scroll);

        btnNueva.setFont(new Font("Arial", Font.BOLD, 16));
        btnNueva.setFocusPainted(false);
        btnNueva.setBounds(250, 490, 180, 45);
        btnNueva.addActionListener(this);
        add(btnNueva);

        btnSalir.setFont(new Font("Arial", Font.BOLD, 16));
        btnSalir.setFocusPainted(false);
        btnSalir.setBounds(470, 490, 180, 45);
        btnSalir.addActionListener(this);
        add(btnSalir);

        Image fondo = new ImageIcon(getClass().getResource("/imagenes/fondo.png")).getImage();
        lblFondo.setIcon(new ImageIcon(fondo.getScaledInstance(900, 600, Image.SCALE_SMOOTH)));
        lblFondo.setBounds(0, 0, 900, 600);
        add(lblFondo);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * Carga los datos de la batalla terminada en la pantalla.
     */
    public void mostrar(Batalla batalla) {
        this.batalla = batalla;

        EstadoBatalla estado = batalla.getEstado();
        String nombre = batalla.getEntrenador();

        if (estado == EstadoBatalla.VICTORIA) {
            lblResultado.setText("¡Victoria, " + nombre + "!");
            lblResultado.setForeground(new Color(120, 255, 140));
        } else if (estado == EstadoBatalla.DERROTA) {
            lblResultado.setText("Derrota, " + nombre + "...");
            lblResultado.setForeground(new Color(255, 130, 120));
        } else {
            lblResultado.setText("¡Empate, " + nombre + "!");
            lblResultado.setForeground(Color.WHITE);
        }

        areaResumen.setText(batalla.getResumenFinal());
        areaResumen.setCaretPosition(0);
    }

    public String getNombreEntrenador() {
        return batalla == null ? "" : batalla.getEntrenador();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (listener == null) {
            return;
        }
        if (e.getSource() == btnNueva) {
            listener.nuevaPartida();
        } else if (e.getSource() == btnSalir) {
            listener.salir();
        }
    }
}

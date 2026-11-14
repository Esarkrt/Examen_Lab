package P_Batalla;

import java.awt.*;
import java.net.URL;
import java.util.List;
import javax.swing.*;
import Modelo.*;

public class PanelBatalla extends JPanel {

    public interface Listener {
        void batallaTerminada(Batalla batalla);
    }

    private static final Color TINTA = new Color(35, 45, 55);
    private static final Color TINTA_SUAVE = new Color(105, 105, 100);
    private static final Color DORADO = new Color(140, 100, 10);

    private class Estado {

        private final JLabel panel = new JLabel();
        private final JLabel entrenador = new JLabel(" ");
        private final JLabel nombre = new JLabel(" ");
        private final JLabel nivel = new JLabel(" ", SwingConstants.RIGHT);
        private final JLabel tipo = new JLabel(" ", SwingConstants.RIGHT);
        private final JLabel ps = new JLabel(" ", SwingConstants.RIGHT);
        private final JLabel usos = new JLabel(" ");
        private final JProgressBar barra = new JProgressBar();

        Estado(int x, int y) {
            panel.setIcon(icono("/imagenes/panel_estado.png", 376, 108));
            panel.setLayout(null);
            panel.setBounds(x, y, 376, 108);
            add(panel);

            etiqueta(panel, entrenador, 11, Font.BOLD, DORADO, 20, 8, 240, 16);
            etiqueta(panel, nivel, 12, Font.BOLD, DORADO, 250, 8, 108, 16);
            etiqueta(panel, nombre, 19, Font.BOLD, TINTA, 20, 24, 240, 26);
            etiqueta(panel, tipo, 11, Font.PLAIN, TINTA_SUAVE, 258, 28, 100, 18);
            etiqueta(panel, ps, 12, Font.BOLD, TINTA, 20, 72, 338, 18);
            etiqueta(panel, usos, 10, Font.PLAIN, TINTA_SUAVE, 20, 86, 338, 14);

            barra.setBounds(20, 56, 338, 14);
            barra.setBackground(new Color(55, 55, 55));
            barra.setBorder(BorderFactory.createLineBorder(new Color(35, 35, 35), 1));
            barra.setStringPainted(false);
            barra.setUI(new javax.swing.plaf.basic.BasicProgressBarUI());
            panel.add(barra);
        }

        void mostrar(Pokemon p, String dueno) {
            entrenador.setText(dueno);
            nombre.setText(p.getNombre());
            nivel.setText("Nv " + p.getNivel());
            tipo.setText("[" + p.getTipo().getNombre() + "]");
            ps.setText("PS " + p.getPs() + " / " + p.getPsMax());
            usos.setText("Habilidad " + p.getUsosHabilidad() + " / 3     Recuperar " + p.getUsosRecuperar() + " / 2");
            barra.setMaximum(p.getPsMax());
            barra.setValue(p.getPs());
            double fraccion = (double) p.getPs() / p.getPsMax();
            if (fraccion > 0.5) barra.setForeground(new Color(72, 180, 80));
            else if (fraccion > 0.2) barra.setForeground(new Color(230, 170, 30));
            else barra.setForeground(new Color(210, 60, 50));
        }
    }

    private Listener listener;
    private Batalla batalla;
    private Estado jugador;
    private Estado rival;

    private final JLabel lblFondo = new JLabel();
    private final JLabel lblSpriteJ = new JLabel();
    private final JLabel lblSpriteR = new JLabel();
    private final JLabel panelDialogo = new JLabel();
    private final JLabel lblRonda = new JLabel("Ronda 1 / 50", SwingConstants.CENTER);
    private final JLabel lblTurno = new JLabel(" ");
    private final JLabel lblEstado = new JLabel(" ");
    private final JTextArea areaMensajes = new JTextArea();
    private final JButton btnAtacar = new JButton("Atacar");
    private final JButton btnEspecial = new JButton("Habilidad");
    private final JButton btnRecuperar = new JButton("Recuperar");
    private final JButton btnCambiar = new JButton("Cambiar");
    private final JButton btnResultado = new JButton("Ver resultado");

    public PanelBatalla() {
        this(null);
    }

    public PanelBatalla(Listener listener) {
        this.listener = listener;
        setLayout(null);
        setPreferredSize(new Dimension(900, 600));
        crearEscenario();
        jugador = new Estado(22, 20);
        rival = new Estado(502, 302);
        crearDialogo();
        conectarBotones();
        lblFondo.setIcon(icono("/imagenes/fondo_batalla.png", 900, 600));
        lblFondo.setBounds(0, 0, 900, 600);
        add(lblFondo);
        bloquearControles();
    }

    private void crearEscenario() {
        lblSpriteR.setBounds(640, 68, 190, 190);
        add(lblSpriteR);
        lblSpriteJ.setBounds(86, 162, 240, 240);
        add(lblSpriteJ);
        lblRonda.setIcon(icono("/imagenes/pill_ronda.png", 190, 34));
        lblRonda.setHorizontalTextPosition(SwingConstants.CENTER);
        lblRonda.setVerticalTextPosition(SwingConstants.CENTER);
        lblRonda.setFont(new Font("Arial", Font.BOLD, 13));
        lblRonda.setForeground(Color.WHITE);
        lblRonda.setBounds(688, 8, 190, 34);
        add(lblRonda);
    }

    private void crearDialogo() {
        panelDialogo.setIcon(icono("/imagenes/panel_dialogo.png", 840, 165));
        panelDialogo.setLayout(null);
        panelDialogo.setBounds(30, 418, 840, 165);
        add(panelDialogo);

        etiqueta(panelDialogo, lblTurno, 14, Font.BOLD, new Color(120, 88, 10), 26, 10, 470, 22);
        etiqueta(panelDialogo, lblEstado, 11, Font.PLAIN, TINTA_SUAVE, 26, 31, 470, 16);

        areaMensajes.setEditable(false);
        areaMensajes.setLineWrap(true);
        areaMensajes.setWrapStyleWord(true);
        areaMensajes.setFont(new Font("Arial", Font.PLAIN, 12));
        areaMensajes.setForeground(TINTA);
        areaMensajes.setOpaque(false);
        areaMensajes.setBorder(null);

        JScrollPane scroll = new JScrollPane(areaMensajes);
        scroll.setBounds(24, 50, 478, 102);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        panelDialogo.add(scroll);

        boton(btnAtacar, "/imagenes/btn_atacar.png", 516, 16, 150, 44);
        boton(btnEspecial, "/imagenes/btn_especial.png", 674, 16, 150, 44);
        boton(btnRecuperar, "/imagenes/btn_recuperar.png", 516, 66, 150, 44);
        boton(btnCambiar, "/imagenes/btn_cambiar.png", 674, 66, 150, 44);
        boton(btnResultado, "/imagenes/btn_resultado_ancho.png", 516, 116, 308, 38);
        btnEspecial.setToolTipText("Habilidad especial del Pokemon activo");
        btnCambiar.setToolTipText("Cambiar de Pokemon");
    }

    private void etiqueta(JLabel padre, JLabel etiqueta, int tamano, int estilo, Color color, int x, int y, int ancho, int alto) {
        etiqueta.setFont(new Font("Arial", estilo, tamano));
        etiqueta.setForeground(color);
        etiqueta.setBounds(x, y, ancho, alto);
        padre.add(etiqueta);
    }

    private void boton(JButton boton, String imagen, int x, int y, int ancho, int alto) {
        boton.setBounds(x, y, ancho, alto);
        boton.setIcon(icono(imagen, ancho, alto));
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setVerticalTextPosition(SwingConstants.CENTER);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setOpaque(false);
        boton.setMargin(new Insets(0, 0, 0, 0));
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setForeground(Color.WHITE);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelDialogo.add(boton);
    }

    private void conectarBotones() {
        btnAtacar.addActionListener(e -> ejecutar(AccionBatalla.ATACAR, -1));
        btnEspecial.addActionListener(e -> ejecutar(AccionBatalla.HABILIDAD_ESPECIAL, -1));
        btnRecuperar.addActionListener(e -> ejecutar(AccionBatalla.RECUPERAR, -1));
        btnCambiar.addActionListener(e -> cambiarPokemon());
        btnResultado.addActionListener(e -> {
            if (batalla != null && batalla.haTerminado() && listener != null) {
                listener.batallaTerminada(batalla);
            }
        });
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void iniciarBatalla(String nombreEntrenador, List<Pokemon> equipo, List<Pokemon> catalogo) throws CloneNotSupportedException {
        batalla = new Batalla(nombreEntrenador, equipo, catalogo);
        areaMensajes.setText("");
        agregarMensajes(batalla.iniciar());
        actualizar();
    }

    public Batalla getBatalla() {
        return batalla;
    }

    private void ejecutar(AccionBatalla accion, int indice) {
        if (batalla == null) return;
        bloquearControles();
        try {
            agregarMensajes(batalla.ejecutarRonda(accion, indice));
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Accion no valida", JOptionPane.WARNING_MESSAGE);
        }
        actualizar();

        if (batalla.getEstado() == EstadoBatalla.ELEGIR_REEMPLAZO) {
            Integer elegido = null;
            while (elegido == null) {
                elegido = preguntarPokemon("Tu Pokemon fue debilitado. Elige el reemplazo:", "Elegir reemplazo");
            }
            agregarMensajes(batalla.elegirReemplazo(elegido));
            actualizar();
        }
    }

    private void cambiarPokemon() {
        if (batalla == null || batalla.indicesDisponiblesJugador().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tienes otros Pokemon disponibles.");
            return;
        }
        Integer elegido = preguntarPokemon("Elige el Pokemon que entrara:", "Cambiar de Pokemon");
        if (elegido != null) ejecutar(AccionBatalla.CAMBIAR, elegido);
    }

    private Integer preguntarPokemon(String mensaje, String titulo) {
        List<Integer> indices = batalla.indicesDisponiblesJugador();
        String[] opciones = new String[indices.size()];
        for (int k = 0; k < opciones.length; k++) {
            Pokemon p = batalla.getEquipoJugador().get(indices.get(k));
            opciones[k] = p.getNombre() + " [" + p.getTipo().getNombre() + "]  PS " + p.getPs() + "/" + p.getPsMax();
        }
        Object r = JOptionPane.showInputDialog(this, mensaje, titulo, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (r == null) return null;
        for (int k = 0; k < opciones.length; k++) {
            if (opciones[k].equals(r)) return indices.get(k);
        }
        return null;
    }

    private void actualizar() {
        Pokemon pj = batalla.getActivoJugador();
        Pokemon pr = batalla.getActivoRival();
        lblRonda.setText("Ronda " + batalla.getRonda() + " / " + Batalla.MAX_RONDAS);
        lblEstado.setText("Estado: " + batalla.getEstado().getEtiqueta());
        jugador.mostrar(pj, batalla.getEntrenador());
        rival.mostrar(pr, batalla.getRival());
        lblSpriteJ.setIcon(sprite(rutaSprite(pj, true), 240, 240));
        lblSpriteR.setIcon(sprite(rutaSprite(pr, false), 190, 190));

        if (batalla.getEstado() == EstadoBatalla.ESPERANDO_ACCION) {
            lblTurno.setText("Turno de " + batalla.getEntrenador() + ": elige una accion");
            btnAtacar.setEnabled(batalla.accionValida(AccionBatalla.ATACAR, -1));
            btnEspecial.setEnabled(batalla.accionValida(AccionBatalla.HABILIDAD_ESPECIAL, -1));
            btnRecuperar.setEnabled(batalla.accionValida(AccionBatalla.RECUPERAR, -1));
            btnCambiar.setEnabled(!batalla.indicesDisponiblesJugador().isEmpty());
            btnEspecial.setText(pj.getNombreHabilidad() + " (" + pj.getUsosHabilidad() + ")");
            btnRecuperar.setText("Recuperar (" + pj.getUsosRecuperar() + ")");
            btnEspecial.setToolTipText(pj.previsualizarHabilidad(pr));
            btnResultado.setEnabled(false);
        } else {
            bloquearControles();
            if (batalla.haTerminado()) {
                lblTurno.setText("Batalla terminada");
                lblEstado.setText("Pulsa Ver resultado para ver el resumen final.");
                btnResultado.setEnabled(true);
            } else {
                lblTurno.setText("Resolviendo...");
            }
        }
    }

    private void bloquearControles() {
        JButton[] botones = {btnAtacar, btnEspecial, btnRecuperar, btnCambiar, btnResultado};
        for (JButton boton : botones) {
            boton.setEnabled(false);
        }
    }

    private void agregarMensajes(List<String> mensajes) {
        for (String mensaje : mensajes) {
            areaMensajes.append(mensaje + "\n");
        }
        areaMensajes.setCaretPosition(areaMensajes.getDocument().getLength());
    }

    private String rutaSprite(Pokemon p, boolean espalda) {
        return "/imagenes/pokemon/" + (espalda ? "back" : "front") + "/" + p.getNombre().toLowerCase() + ".png";
    }

    private ImageIcon icono(String ruta, int ancho, int alto) {
        return escalar(ruta, ancho, alto, Image.SCALE_SMOOTH);
    }

    private ImageIcon sprite(String ruta, int ancho, int alto) {
        return escalar(ruta, ancho, alto, Image.SCALE_REPLICATE);
    }

    private ImageIcon escalar(String ruta, int ancho, int alto, int modo) {
        URL recurso = getClass().getResource(ruta);
        if (recurso == null) recurso = getClass().getResource("/imagenes/logo.png");
        return new ImageIcon(new ImageIcon(recurso).getImage().getScaledInstance(ancho, alto, modo));
    }
}

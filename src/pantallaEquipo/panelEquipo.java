package pantallaEquipo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class panelEquipo extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(panelEquipo.class.getName());

    private DefaultListModel<Pokemon> modelocatalogo;
    private DefaultListModel<Pokemon> modeloequipo;
    private gestorEquipo gestorequipo;
    private String nombreentrenador = "Entrenador";

    public interface Listener {
        void equipoListo(List<Modelo.Pokemon> equipo);
    }

    private Listener listener;

    public panelEquipo() {
        initComponents();
        inicializarModelos();
    }

    public panelEquipo(String nombreentrenador) {
        this.nombreentrenador = nombreentrenador;
        initComponents();
        inicializarModelos();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private void inicializarModelos() {
        gestorequipo = new gestorEquipo();
        modelocatalogo = new DefaultListModel<>();
        modeloequipo = new DefaultListModel<>();

        lista_catalogo.setModel(modelocatalogo);
        lista_equipo.setModel(modeloequipo);

        modelocatalogo.addElement(new PokemonFuego("Charmander", 90, 14, 8, 11));
        modelocatalogo.addElement(new PokemonFuego("Vulpix", 85, 13, 9, 12));
        modelocatalogo.addElement(new PokemonAgua("Squirtle", 100, 11, 12, 9));
        modelocatalogo.addElement(new PokemonAgua("Psyduck", 95, 12, 9, 10));
        modelocatalogo.addElement(new PokemonPlanta("Bulbasaur", 100, 12, 10, 8));
        modelocatalogo.addElement(new PokemonPlanta("Oddish", 90, 13, 8, 7));
        modelocatalogo.addElement(new PokemonElectrico("Pikachu", 80, 14, 7, 15));
        modelocatalogo.addElement(new PokemonElectrico("Voltorb", 75, 12, 9, 16));

        actualizarContadorEquipo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_titulo = new javax.swing.JLabel();
        lbl_catalogo = new javax.swing.JLabel();
        scroll_catalogo = new javax.swing.JScrollPane();
        lista_catalogo = new javax.swing.JList<>();
        lbl_ficha = new javax.swing.JLabel();
        scroll_ficha = new javax.swing.JScrollPane();
        area_ficha = new javax.swing.JTextArea();
        lbl_equipo = new javax.swing.JLabel();
        scroll_equipo = new javax.swing.JScrollPane();
        lista_equipo = new javax.swing.JList<>();
        btn_agregar = new javax.swing.JButton();
        btn_quitar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_restaurar = new javax.swing.JButton();
        btn_crear = new javax.swing.JButton();
        btn_comenzar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Batallas Pokémon - Armado de Equipo");

        lbl_titulo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_titulo.setText("Selección y Armado del Equipo");

        lbl_catalogo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_catalogo.setText("Catálogo disponible:");

        lista_catalogo.addListSelectionListener(this::lista_catalogoValueChanged);
        scroll_catalogo.setViewportView(lista_catalogo);

        lbl_ficha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ficha.setText("Ficha del Pokémon:");

        area_ficha.setEditable(false);
        area_ficha.setColumns(18);
        area_ficha.setRows(8);
        scroll_ficha.setViewportView(area_ficha);

        lbl_equipo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_equipo.setText("Equipo: 0/6");

        scroll_equipo.setViewportView(lista_equipo);

        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(this::btn_agregarActionPerformed);

        btn_quitar.setText("Quitar");
        btn_quitar.addActionListener(this::btn_quitarActionPerformed);

        btn_guardar.setText("Guardar equipo");
        btn_guardar.addActionListener(this::btn_guardarActionPerformed);

        btn_restaurar.setText("Restaurar equipo");
        btn_restaurar.addActionListener(this::btn_restaurarActionPerformed);

        btn_crear.setText("Crear Pokémon");
        btn_crear.addActionListener(this::btn_crearActionPerformed);

        btn_comenzar.setText("Comenzar batalla");
        btn_comenzar.setEnabled(false);
        btn_comenzar.addActionListener(this::btn_comenzarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_titulo)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_catalogo)
                            .addComponent(scroll_catalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_ficha)
                            .addComponent(scroll_ficha, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_equipo)
                            .addComponent(scroll_equipo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_restaurar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_crear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_comenzar)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbl_titulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_catalogo)
                    .addComponent(lbl_ficha)
                    .addComponent(lbl_equipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scroll_catalogo, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(scroll_ficha)
                    .addComponent(scroll_equipo))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_agregar)
                    .addComponent(btn_quitar)
                    .addComponent(btn_guardar)
                    .addComponent(btn_restaurar)
                    .addComponent(btn_crear)
                    .addComponent(btn_comenzar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lista_catalogoValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lista_catalogoValueChanged
        if (!evt.getValueIsAdjusting()) {
            Pokemon seleccionado = lista_catalogo.getSelectedValue();
            if (seleccionado != null) {
                String ficha = "Nombre: " + seleccionado.getNombre() + "\n"
                        + "Tipo: " + seleccionado.getTipo() + "\n"
                        + "Nivel: " + seleccionado.getNivel() + "\n"
                        + "PS: " + seleccionado.getPsMaximos() + "\n"
                        + "Ataque: " + seleccionado.getAtaque() + "\n"
                        + "Defensa: " + seleccionado.getDefensa() + "\n"
                        + "Velocidad: " + seleccionado.getVelocidad() + "\n"
                        + "Habilidad: " + seleccionado.getHabilidadEspecial();
                area_ficha.setText(ficha);
            }
        }
    }//GEN-LAST:event_lista_catalogoValueChanged

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        Pokemon seleccionado = lista_catalogo.getSelectedValue();
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this,
                    "Debes seleccionar un Pokémon del catálogo para agregarlo.",
                    "Sin selección",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (modeloequipo.size() >= 6) {
            JOptionPane.showMessageDialog(this,
                    "Tu equipo ya está lleno (máximo 6 Pokémon).",
                    "Equipo lleno",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int copias = 0;
        for (int i = 0; i < modeloequipo.size(); i++) {
            if (modeloequipo.getElementAt(i).getNombre().equalsIgnoreCase(seleccionado.getNombre())) {
                copias++;
            }
        }
        if (copias >= 2) {
            JOptionPane.showMessageDialog(this,
                    "Solo puedes agregar hasta 2 copias de " + seleccionado.getNombre() + ".",
                    "Límite de copias alcanzado",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Pokemon copiapokemon = seleccionado.clone();
        modeloequipo.addElement(copiapokemon);
        actualizarContadorEquipo();
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quitarActionPerformed
        int indicesel = lista_equipo.getSelectedIndex();
        if (indicesel == -1) {
            JOptionPane.showMessageDialog(this,
                    "Debes seleccionar un Pokémon de 'Mi equipo' para quitarlo.",
                    "Sin selección",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        modeloequipo.remove(indicesel);
        actualizarContadorEquipo();
    }//GEN-LAST:event_btn_quitarActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        if (modeloequipo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No puedes guardar un equipo vacío. Agrega al menos 1 Pokémon.",
                    "Equipo vacío",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            equipoGuardado datos = new equipoGuardado(nombreentrenador, obtenerListaEquipo());
            gestorequipo.guardar(datos);
            JOptionPane.showMessageDialog(this,
                    "Equipo guardado en memoria correctamente.",
                    "Equipo guardado",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar el equipo en memoria: " + error.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_restaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_restaurarActionPerformed
        if (!gestorequipo.hayEquipoGuardado()) {
            JOptionPane.showMessageDialog(this,
                    "No hay ningún equipo guardado en memoria para restaurar.",
                    "Sin respaldo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            equipoGuardado restaurado = gestorequipo.restaurar();
            modeloequipo.clear();
            for (Pokemon p : restaurado.getListaEquipo()) {
                modeloequipo.addElement(p.clone());
            }
            actualizarContadorEquipo();
            JOptionPane.showMessageDialog(this,
                    "Equipo restaurado desde la memoria (" + modeloequipo.size() + " Pokémon).",
                    "Equipo restaurado",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this,
                    "Error al restaurar el equipo: " + error.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_restaurarActionPerformed

    private void btn_crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_crearActionPerformed
        formularioPokemon ventanaform = new formularioPokemon(this);
        ventanaform.setVisible(true);
    }//GEN-LAST:event_btn_crearActionPerformed

    private void btn_comenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_comenzarActionPerformed
        if (modeloequipo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "El equipo debe tener al menos 1 Pokémon para comenzar la batalla.",
                    "Equipo incompleto",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (listener != null) {
            listener.equipoListo(obtenerEquipoParaBatalla());
            return;
        }
        Pokemon primerpokemon = modeloequipo.getElementAt(0);
        JOptionPane.showMessageDialog(this,
                "¡Comenzando batalla con " + modeloequipo.size() + " Pokémon!\n"
                + "Primer Pokémon en salir al combate: " + primerpokemon.getNombre(),
                "Iniciar Batalla",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btn_comenzarActionPerformed

    private void actualizarContadorEquipo() {
        int total = modeloequipo.size();
        lbl_equipo.setText("Equipo: " + total + "/6");
        btn_comenzar.setEnabled(total >= 1);
    }

    public boolean existeEnCatalogo(String nombrebuscar) {
        for (int i = 0; i < modelocatalogo.size(); i++) {
            if (modelocatalogo.getElementAt(i).getNombre().equalsIgnoreCase(nombrebuscar)) {
                return true;
            }
        }
        return false;
    }

    public void agregarAlCatalogo(Pokemon nuevopokemon) {
        modelocatalogo.addElement(nuevopokemon);
    }

    public List<Modelo.Pokemon> obtenerEquipoParaBatalla() {
        List<Modelo.Pokemon> lista = new ArrayList<>();
        for (int i = 0; i < modeloequipo.size(); i++) {
            Pokemon p = modeloequipo.getElementAt(i);
            String tipo = p.getTipo().name();
            Modelo.Pokemon convertido;
            if (tipo.equals("FUEGO")) {
                convertido = new Modelo.PokemonFuego(p.getNombre(), p.getNivel(), p.getPsMaximos(), p.getAtaque(), p.getDefensa(), p.getVelocidad());
            } else if (tipo.equals("AGUA")) {
                convertido = new Modelo.PokemonAgua(p.getNombre(), p.getNivel(), p.getPsMaximos(), p.getAtaque(), p.getDefensa(), p.getVelocidad());
            } else if (tipo.equals("PLANTA")) {
                convertido = new Modelo.PokemonPlanta(p.getNombre(), p.getNivel(), p.getPsMaximos(), p.getAtaque(), p.getDefensa(), p.getVelocidad());
            } else {
                convertido = new Modelo.PokemonElectrico(p.getNombre(), p.getNivel(), p.getPsMaximos(), p.getAtaque(), p.getDefensa(), p.getVelocidad());
            }
            lista.add(convertido);
        }
        return lista;
    }

    public List<Pokemon> obtenerListaEquipo() {
        List<Pokemon> lista = new ArrayList<>();
        for (int i = 0; i < modeloequipo.size(); i++) {
            lista.add(modeloequipo.getElementAt(i));
        }
        return lista;
    }

    public List<Pokemon> obtenerListaCatalogo() {
        List<Pokemon> lista = new ArrayList<>();
        for (int i = 0; i < modelocatalogo.size(); i++) {
            lista.add(modelocatalogo.getElementAt(i));
        }
        return lista;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new panelEquipo().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area_ficha;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_comenzar;
    private javax.swing.JButton btn_crear;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_quitar;
    private javax.swing.JButton btn_restaurar;
    private javax.swing.JLabel lbl_catalogo;
    private javax.swing.JLabel lbl_equipo;
    private javax.swing.JLabel lbl_ficha;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JList<Pokemon> lista_catalogo;
    private javax.swing.JList<Pokemon> lista_equipo;
    private javax.swing.JScrollPane scroll_catalogo;
    private javax.swing.JScrollPane scroll_equipo;
    private javax.swing.JScrollPane scroll_ficha;
    // End of variables declaration//GEN-END:variables
}

package pantallaEquipo;

import javax.swing.JOptionPane;

public class formularioPokemon extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(formularioPokemon.class.getName());
    private panelEquipo panelequipo;

    public formularioPokemon() {
        initComponents();
    }

    public formularioPokemon(panelEquipo panelequipo) {
        this.panelequipo = panelequipo;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_titulo = new javax.swing.JLabel();
        lbl_nombre = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        lbl_tipo = new javax.swing.JLabel();
        cbo_tipo = new javax.swing.JComboBox<>();
        btn_guardar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear Pokémon Personalizado");

        lbl_titulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_titulo.setText("Nuevo Pokémon para el Catálogo");

        lbl_nombre.setText("Nombre (3 a 10 letras):");

        lbl_tipo.setText("Tipo:");

        cbo_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FUEGO", "AGUA", "PLANTA", "ELECTRICO" }));

        btn_guardar.setText("Agregar al catálogo");
        btn_guardar.addActionListener(this::btn_guardarActionPerformed);

        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(this::btn_cancelarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_titulo)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_nombre)
                            .addComponent(lbl_tipo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(cbo_tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_cancelar)))
                .addContainerGap(152, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbl_titulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nombre)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_tipo)
                    .addComponent(cbo_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar)
                    .addComponent(btn_cancelar))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        String nombrelimpio = txt_nombre.getText().trim();

        boolean caracteresvalidos = true;
        for (int i = 0; i < nombrelimpio.length(); i++) {
            char letra = nombrelimpio.charAt(i);
            if (!Character.isLetterOrDigit(letra) && letra != ' ') {
                caracteresvalidos = false;
                break;
            }
        }

        if (nombrelimpio.length() < 3 || nombrelimpio.length() > 10 || !caracteresvalidos) {
            JOptionPane.showMessageDialog(this,
                    "El nombre debe tener entre 3 y 10 caracteres (solo letras, números y espacios).",
                    "Nombre no válido",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tipotexto = (String) cbo_tipo.getSelectedItem();
        Pokemon nuevopokemon;

        switch (tipotexto) {
            case "FUEGO":
                nuevopokemon = new PokemonFuego(nombrelimpio, 90, 14, 9, 11);
                break;
            case "AGUA":
                nuevopokemon = new PokemonAgua(nombrelimpio, 95, 12, 11, 10);
                break;
            case "PLANTA":
                nuevopokemon = new PokemonPlanta(nombrelimpio, 95, 12, 10, 9);
                break;
            case "ELECTRICO":
            default:
                nuevopokemon = new PokemonElectrico(nombrelimpio, 85, 14, 8, 15);
                break;
        }

        if (panelequipo != null) {
            if (panelequipo.existeEnCatalogo(nombrelimpio)) {
                JOptionPane.showMessageDialog(this,
                        "Ya existe un Pokémon con ese nombre en el catálogo.",
                        "Nombre duplicado",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            panelequipo.agregarAlCatalogo(nuevopokemon);
        }

        JOptionPane.showMessageDialog(this,
                "¡" + nombrelimpio + " (" + tipotexto + ") fue agregado al catálogo!",
                "Pokémon creado",
                JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btn_cancelarActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new formularioPokemon().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JComboBox<String> cbo_tipo;
    private javax.swing.JLabel lbl_nombre;
    private javax.swing.JLabel lbl_tipo;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author ismae
 */
public class VistaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        MenuSalir = new javax.swing.JMenu();
        SalirAPP = new javax.swing.JMenuItem();
        MenuMonitores = new javax.swing.JMenu();
        jGestionDeMonitores = new javax.swing.JMenuItem();
        MenuSocios = new javax.swing.JMenu();
        jGestionDeSocios = new javax.swing.JMenuItem();
        MenuActividad = new javax.swing.JMenu();
        jSociosPorActividad = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana principal de la aplicación");

        MenuSalir.setText("Salir");

        SalirAPP.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SalirAPP.setText("Salir de la aplicación");
        SalirAPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirAPPActionPerformed(evt);
            }
        });
        MenuSalir.add(SalirAPP);

        jMenuBar2.add(MenuSalir);

        MenuMonitores.setText("Monitores");

        jGestionDeMonitores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jGestionDeMonitores.setText("Gestión de Monitores");
        jGestionDeMonitores.setActionCommand("GestiónDeMonitores");
        jGestionDeMonitores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGestionDeMonitoresActionPerformed(evt);
            }
        });
        MenuMonitores.add(jGestionDeMonitores);

        jMenuBar2.add(MenuMonitores);

        MenuSocios.setText("Socios");

        jGestionDeSocios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jGestionDeSocios.setText("Gestión de Socios");
        jGestionDeSocios.setActionCommand("GestionDeSocios");
        MenuSocios.add(jGestionDeSocios);

        jMenuBar2.add(MenuSocios);

        MenuActividad.setText("Actividad");

        jSociosPorActividad.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jSociosPorActividad.setText("Socios por Actividad");
        MenuActividad.add(jSociosPorActividad);

        jMenuBar2.add(MenuActividad);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 952, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jGestionDeMonitoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGestionDeMonitoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jGestionDeMonitoresActionPerformed

    private void SalirAPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirAPPActionPerformed

    }//GEN-LAST:event_SalirAPPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuActividad;
    public javax.swing.JMenu MenuMonitores;
    public javax.swing.JMenu MenuSalir;
    public javax.swing.JMenu MenuSocios;
    public javax.swing.JMenuItem SalirAPP;
    public javax.swing.JMenuItem jGestionDeMonitores;
    public javax.swing.JMenuItem jGestionDeSocios;
    private javax.swing.JMenuBar jMenuBar2;
    public javax.swing.JMenuItem jSociosPorActividad;
    // End of variables declaration//GEN-END:variables
}

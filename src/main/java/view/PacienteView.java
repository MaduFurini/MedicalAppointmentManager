/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.PacienteController;
import java.awt.Color;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Paciente;

/**
 *
 * @author dudaf
 */
public class PacienteView extends javax.swing.JFrame {
    private DefaultTableModel table;

    /**
     * Creates new form AgendaView
     */
    public PacienteView() {
        initComponents();
        getContentPane().setBackground(Color.white);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
       
        setLocationRelativeTo(null); 

        List<Paciente> pacientes = PacienteController.index();
        table = (DefaultTableModel) jTable.getModel();

        for (Paciente paciente : pacientes) {
            table.addRow(new Object[]{
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getEmail(),
                paciente.getSexo(),
                paciente.getEndereco(),
                paciente.getDtNascimento()
            });
        }

        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnAgendaMenu = new javax.swing.JButton();
        btnPacientesMenu = new javax.swing.JButton();
        btnFuncionariosMenu = new javax.swing.JButton();
        sairBtn = new javax.swing.JButton();
        btnFuncionariosMenu1 = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        inputProcurar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setFocusableWindowState(false);
        setPreferredSize(new java.awt.Dimension(1900, 2000));

        jPanel2.setBackground(new java.awt.Color(198, 165, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\images\\logo_white.png"));

        btnAgendaMenu.setBackground(new java.awt.Color(198, 165, 255));
        btnAgendaMenu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnAgendaMenu.setForeground(new java.awt.Color(255, 255, 255));
        btnAgendaMenu.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\images\\calendar_icon.png"));
        btnAgendaMenu.setText("Agenda");
        btnAgendaMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(205, 170, 255), 2));
        btnAgendaMenu.setContentAreaFilled(false);
        btnAgendaMenu.setFocusPainted(false);
        btnAgendaMenu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgendaMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendaMenuActionPerformed(evt);
            }
        });

        btnPacientesMenu.setBackground(new java.awt.Color(198, 165, 255));
        btnPacientesMenu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnPacientesMenu.setForeground(new java.awt.Color(255, 255, 255));
        btnPacientesMenu.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\images\\patient_icon.png"));
        btnPacientesMenu.setText("Pacientes");
        btnPacientesMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(205, 170, 255), 2));
        btnPacientesMenu.setContentAreaFilled(false);
        btnPacientesMenu.setFocusPainted(false);
        btnPacientesMenu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPacientesMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacientesMenuActionPerformed(evt);
            }
        });

        btnFuncionariosMenu.setBackground(new java.awt.Color(198, 165, 255));
        btnFuncionariosMenu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnFuncionariosMenu.setForeground(new java.awt.Color(255, 255, 255));
        btnFuncionariosMenu.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\images\\doctor_icon.png"));
        btnFuncionariosMenu.setText("Funcionários");
        btnFuncionariosMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(205, 170, 255), 2));
        btnFuncionariosMenu.setContentAreaFilled(false);
        btnFuncionariosMenu.setFocusPainted(false);
        btnFuncionariosMenu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFuncionariosMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncionariosMenuActionPerformed(evt);
            }
        });

        sairBtn.setBackground(new java.awt.Color(198, 165, 255));
        sairBtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        sairBtn.setForeground(new java.awt.Color(255, 255, 255));
        sairBtn.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\images\\logout_icon.png"));
        sairBtn.setText("Sair");
        sairBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(205, 170, 255), 2));
        sairBtn.setContentAreaFilled(false);
        sairBtn.setFocusPainted(false);
        sairBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sairBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairBtnActionPerformed(evt);
            }
        });

        btnFuncionariosMenu1.setBackground(new java.awt.Color(198, 165, 255));
        btnFuncionariosMenu1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnFuncionariosMenu1.setForeground(new java.awt.Color(255, 255, 255));
        btnFuncionariosMenu1.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\images\\doctor_icon.png"));
        btnFuncionariosMenu1.setText("Sair");
        btnFuncionariosMenu1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(205, 170, 255), 2));
        btnFuncionariosMenu1.setContentAreaFilled(false);
        btnFuncionariosMenu1.setFocusPainted(false);
        btnFuncionariosMenu1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFuncionariosMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncionariosMenu1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
            .addComponent(btnAgendaMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPacientesMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnFuncionariosMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sairBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnFuncionariosMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgendaMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPacientesMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFuncionariosMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFuncionariosMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sairBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnNovo.setBackground(new java.awt.Color(198, 165, 255));
        btnNovo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\images\\new_icon.png"));
        btnNovo.setText("NOVO");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("PACIENTES");

        btnEditar.setBackground(new java.awt.Color(198, 165, 255));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\images\\edit_icon.png"));
        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setBackground(new java.awt.Color(198, 165, 255));
        btnExcluir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\images\\delete_icon.png"));
        btnExcluir.setText("EXCLUIR");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Procurar");

        inputProcurar.setForeground(new java.awt.Color(204, 204, 204));
        inputProcurar.setText("Nome do paciente...");
        inputProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputProcurarActionPerformed(evt);
            }
        });

        jTable.setBackground(new java.awt.Color(198, 165, 255));
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "CPF", "Email", "Sexo", "Endereço", "Data Nascimento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(inputProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(220, 220, 220))
        );

        setBounds(0, 0, 1504, 707);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        NewPacienteView np = new NewPacienteView(0);
        np.setVisible(true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void inputProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputProcurarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputProcurarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        table = (DefaultTableModel) jTable.getModel();
        int selectedRow = jTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                    "Por favor, selecione um paciente para continuar",
                    "Erro", JOptionPane.ERROR_MESSAGE
                );
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Você tem certeza que deseja excluir este paciente?", 
            "Confirmação", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            int id = (int) table.getValueAt(selectedRow, 0);

            PacienteController.destroy(id);

            table.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Paciente excluído com sucesso.");
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnPacientesMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacientesMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPacientesMenuActionPerformed

    private void btnFuncionariosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncionariosMenuActionPerformed
        this.setVisible(false);

//        f.setVisible(true);
    }//GEN-LAST:event_btnFuncionariosMenuActionPerformed

    private void sairBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairBtnActionPerformed
        this.setVisible(false);

//        l.setVisible(true);
    }//GEN-LAST:event_sairBtnActionPerformed

    private void btnAgendaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendaMenuActionPerformed
        this.setVisible(false);

//        a.setVisible(true);
    }//GEN-LAST:event_btnAgendaMenuActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        table = (DefaultTableModel) jTable.getModel();
        int selectedRow = jTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Por favor, selecione um paciente para continuar",
                "Erro", JOptionPane.ERROR_MESSAGE
            );
        } 

        int id = (int) table.getValueAt(selectedRow, 0);
        NewPacienteView na = new NewPacienteView(id);
        na.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnFuncionariosMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncionariosMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFuncionariosMenu1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PacienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PacienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PacienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PacienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PacienteView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgendaMenu;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFuncionariosMenu;
    private javax.swing.JButton btnFuncionariosMenu1;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPacientesMenu;
    private javax.swing.JTextField inputProcurar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JButton sairBtn;
    // End of variables declaration//GEN-END:variables
}
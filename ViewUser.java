
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import dao.ConnectionProvider;
import java.sql.Statement;
import javax.swing.table.TableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author ahsan
 */
public class ViewUser extends javax.swing.JFrame {

    private String username = "";
    private int appuser_pk;
    private Object Ahsan;

    /**
     * Creates new form ViewUser
     */
    public ViewUser() {
        initComponents();
    }

    public ViewUser(String tempUsername) {
        initComponents();
        username = tempUsername;
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        jLabel2.setText("View User");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 200, 50));

        jButton1.setIcon(new javax.swing.ImageIcon("D:\\Pharmacy management tools\\cancel001-icon-front-side.png")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 0, 20, 20));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 93, 1480, 20));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Role", "Date of birth ", "Mobile Number", "Username", "Password", "Address"
            }
        ));
        jTable2.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jTable2ComponentAdded(evt);
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1460, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("Click on row to Delete User");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 540, 170, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);    // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        try {
            Connection con = ConnectionProvider.getCon();
            Statement stm = con.createStatement();
            ResultSet rt;
            rt = stm.executeQuery("select *from appuser");
            while (rt.next()) {
                model.addRow(new Object[] { /*rt.getString("appuser_pk"),*/ rt.getString("userRole"), rt.getString("dob"), rt.getString("mobileNumber"), rt.getString("username"), rt.getString("password"), rt.getString("address")});

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_formComponentShown

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int index = jTable2.getSelectedRow();
        TableModel model = jTable2.getModel();
        String id = model.getValueAt(index, 0).toString();
        String usernameTable = model.getValueAt(index, 5).toString();
        String name = model.getValueAt(index, 1).toString();
        if (username.equals(name) || "Ahsan".equals(name)|| username.equals(usernameTable)) {
            JOptionPane.showMessageDialog(null, "You can't delete your own account");
        } else {
            int a = JOptionPane.showConfirmDialog(null, "Do you want to delete this user?", "Select", JOptionPane.YES_NO_OPTION);
             if (a == 0) {
                try {
                    Connection con = ConnectionProvider.getCon();
                    PreparedStatement ps = con.prepareStatement("delete from appuser where userName=?");
                    ps.setString(1, id);
                     int deletedRows=ps.executeUpdate();
                    if(deletedRows>0){
                    JOptionPane.showMessageDialog(null, "User Successfully Deleted");
                    setVisible(false);
                    new ViewUser(username).setVisible(true);

                    //  new ViewUser(username).setVisible(true);
                }else{
                        JOptionPane.showMessageDialog(null,"Unable to delete this user");
                        }
               
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }


    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jTable2ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2ComponentAdded

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
            java.util.logging.Logger.getLogger(ViewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

    private void setLocationRelatedTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
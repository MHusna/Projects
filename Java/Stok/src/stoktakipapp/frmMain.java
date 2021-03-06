package stoktakipapp;

import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author mehme
 */
public class frmMain extends javax.swing.JFrame {
    public static DefaultTableModel model;
    /**
     * Creates new form frmMain
     */
    public frmMain() throws SQLException {
        initComponents();
        model = (DefaultTableModel)tblProduct.getModel();
        try{
            ArrayList<Product> products = getProduct();
            for(Product product:products){
                Object[] row = {
                    product.getProductId(),
                    product.getStk_kod(),
                    product.getUrun_ad(),
                    product.getUrun_kdv(),
                    product.getUrun_fiyat(),
                    product.getStok_durum()};
                model.addRow(row);
            }
        }catch(SQLException exception){
            
        }
    }
    
    public static ArrayList<Product> getProduct() throws SQLException{
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;
        
        ArrayList<Product> products = null;
        try{
            connection = dbHelper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select * from product");
            products = new ArrayList<Product>();
            while(resultSet.next()){
                products.add(new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("stk_kod"),
                        resultSet.getString("urun_ad"),
                        resultSet.getInt("urun_kdv"),
                        resultSet.getFloat("urun_fiyat"),
                        resultSet.getInt("stok_durum"))
                );
            }
        }catch(SQLException exception){
            dbHelper.showErrorMessage(exception);
        }
        finally{
            statement.close();
            connection.close();
        }
        return products;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        btnEkle = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ARAMA");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Stok Kodu", "Urun Adi", "KDV", "Urun Fiyat", "Stok Durumu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblProductMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduct);
        if (tblProduct.getColumnModel().getColumnCount() > 0) {
            tblProduct.getColumnModel().getColumn(0).setResizable(false);
            tblProduct.getColumnModel().getColumn(1).setResizable(false);
            tblProduct.getColumnModel().getColumn(2).setResizable(false);
            tblProduct.getColumnModel().getColumn(3).setResizable(false);
            tblProduct.getColumnModel().getColumn(4).setResizable(false);
            tblProduct.getColumnModel().getColumn(5).setResizable(false);
        }

        btnEkle.setText("YEN?? EKLE");
        btnEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEkleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(351, 351, 351)
                        .addComponent(btnEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(207, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnEkle, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        String searchKey = txtSearch.getText();
        TableRowSorter<DefaultTableModel> tableRowSorter =
                new TableRowSorter<DefaultTableModel>(model);
        tblProduct.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey));
        
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEkleActionPerformed
        
        frmNewProduct frmnewProductPage = new frmNewProduct();
        frmnewProductPage.setVisible(true);
       
    }//GEN-LAST:event_btnEkleActionPerformed

    frmProductProperties jtRowData = new frmProductProperties();
    private void tblProductMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMousePressed
        
        int index = tblProduct.getSelectedRow();
        TableModel model = tblProduct.getModel();
        String product_id = model.getValueAt(index, 0).toString();
        String stokKodu = model.getValueAt(index, 1).toString();
        String urunAdi = model.getValueAt(index, 2).toString();
        String urunKdv = model.getValueAt(index, 3).toString();
        String urunFiyat = model.getValueAt(index, 4).toString();
        String stokDurum = model.getValueAt(index, 5).toString();
        
        jtRowData.setVisible(true);
        jtRowData.pack();
        
        jtRowData.txtID.setText(product_id);
        jtRowData.txtStokKodu.setText(stokKodu);
        jtRowData.txtUrunAdi.setText(urunAdi);
        jtRowData.txtKDV.setText(urunKdv);
        jtRowData.txtUrunFiyati.setText(urunFiyat);
        jtRowData.txtStokDurum.setText(stokDurum);
        
    }//GEN-LAST:event_tblProductMousePressed

    public static void tableRefresh(){
        model.setRowCount(0);
        model = (DefaultTableModel)tblProduct.getModel();
        try{
            ArrayList<Product> products = getProduct();
            for(Product product:products){
                Object[] row = {
                    product.getProductId(),
                    product.getStk_kod(),
                    product.getUrun_ad(),
                    product.getUrun_kdv(),
                    product.getUrun_fiyat(),
                    product.getStok_durum()};
                model.addRow(row);
            }
        }catch(SQLException exception){
            
        }
    }
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
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frmMain().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnEkle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tblProduct;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

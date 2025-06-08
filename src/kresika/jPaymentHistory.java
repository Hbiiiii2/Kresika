/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kresika;

/**
 *
 * @author Hbiiiiii2
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class jPaymentHistory extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(jPaymentHistory.class.getName());
    private TableRowSorter<DefaultTableModel> sorter;

    /**
     * Creates new form jPaymentHistory
     */
    public jPaymentHistory() {
        initComponents();
        loadBookingHistory(); // Muat data saat form dibuat
        addSearchFunctionality(); // Tambahkan fungsi pencarian

        setTitle("Payment History");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); // Agar tidak menutup seluruh aplikasi
        setLocationRelativeTo(null);
    }

    // Meload semua history Pembelian dari Data base dan menampilkannya dalam format Tabel
    private void loadBookingHistory() {
        // Membuat model tabel
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Membuat semua sel tidak bisa diedit
            }
        };

        // Menambahkan kolom ke model
        model.addColumn("Booking Code");
        model.addColumn("Orderer Name");
        model.addColumn("E-mail");
        model.addColumn("Train");
        model.addColumn("destination");
        model.addColumn("Class");
        model.addColumn("Departure Date");
        model.addColumn("Total price (Rp)");
        model.addColumn("Order Date");

        // Query untuk mengambil data riwayat pemesanan dengan JOIN
        String sql = "SELECT "
                + "b.kode_pemesanan, b.nama_pemesan, b.email_pemesan, b.total_harga, b.tanggal_pemesanan, "
                + "t.nama_kereta, "
                + "r.stasiun_tujuan_nama, "
                + "tc.kode_kelas, "
                + "s.waktu_keberangkatan "
                + "FROM bookings b "
                + "JOIN schedules s ON b.id_jadwal = s.id_jadwal "
                + "JOIN trains t ON s.id_kereta = t.id_kereta "
                + "JOIN routes r ON s.id_rute = r.id_rute "
                + "JOIN train_classes tc ON s.id_kelas = tc.id_kelas "
                + "ORDER BY b.tanggal_pemesanan DESC"; // Urutkan berdasarkan yang terbaru

        try {
            koneksi.connect();
            Connection conn = koneksi.con;
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            // Mengisi model dengan data dari database
            while (res.next()) {
                model.addRow(new Object[]{
                    res.getString("kode_pemesanan"),
                    res.getString("nama_pemesan"),
                    res.getString("email_pemesan"),
                    res.getString("nama_kereta"),
                    res.getString("stasiun_tujuan_nama"),
                    res.getString("kode_kelas"),
                    res.getString("waktu_keberangkatan"),
                    res.getDouble("total_harga"),
                    res.getString("tanggal_pemesanan")
                });
            }
            jTableHistory.setModel(model);

            // Mengatur format harga agar mudah dibaca
            jTableHistory.getColumnModel().getColumn(7).setCellRenderer(new CurrencyRenderer());

        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Error loading payment history: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Failed to load data from database: " + e.getMessage());
        }
    }
    
    // Method untuk mencari dan memfilter 
    private void addSearchFunctionality() {
        // Membuat Sorter dari model tabel
        sorter = new TableRowSorter<>((DefaultTableModel) jTableHistory.getModel());
        jTableHistory.setRowSorter(sorter);

        // Menambahkan listener ke search field
        jSearchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(jSearchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(jSearchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(jSearchField.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    // Filter tidak case-sensitive
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }
        });
    }
    
    // Method curency untuk harga agar lebih mudah di baca
    class CurrencyRenderer extends DefaultTableCellRenderer {

        private final NumberFormat formatter = NumberFormat.getIntegerInstance(new Locale("id", "ID"));

        public CurrencyRenderer() {
            super(); // Memanggil konstruktor superclass
            setHorizontalAlignment(javax.swing.SwingConstants.RIGHT); // Meratakan angka ke kanan
        }

        @Override
        public void setValue(Object value) {
            // Cek jika value adalah Number, lalu format. Jika tidak, tampilkan string kosong.
            if ((value != null) && (value instanceof Number)) {
                setText(formatter.format(value));
            } else {
                setText("");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHistory = new javax.swing.JTable();
        jBtnBack = new javax.swing.JButton();
        SearchIcon = new javax.swing.JLabel();
        jSearchField = new javax.swing.JTextField();
        jBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1728, 1117));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableHistory);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 1430, 740));

        jBtnBack.setContentAreaFilled(false);
        jBtnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBackActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 940, 290, 80));

        SearchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/SearchIcon.png"))); // NOI18N
        getContentPane().add(SearchIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 43, -1, -1));

        jSearchField.setFont(new java.awt.Font("Baloo Da 2", 1, 24)); // NOI18N
        jSearchField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jSearchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 30, 310, 50));

        jBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PaymentHistory.png"))); // NOI18N
        getContentPane().add(jBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBackActionPerformed
        new jHomePage().setVisible(true); // Pindah ke halaman utama
        this.dispose();
    }//GEN-LAST:event_jBtnBackActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new jPaymentHistory().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SearchIcon;
    private javax.swing.JLabel jBackground;
    private javax.swing.JButton jBtnBack;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jSearchField;
    private javax.swing.JTable jTableHistory;
    // End of variables declaration//GEN-END:variables
}

package kresika;

import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import kresika.jPassangerForm; // Pastikan import ini ada

/**
 *
 * @author Administrator
 */
public class jAvailabelDate extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(jAvailabelDate.class.getName());

    // Variabel untuk menyimpan data yang dipilih dari tabel
    private String selectedTrainCode = null;
    private String selectedWaktuBerangkat = null;
    private String selectedRute = null;
    private Double selectedHarga = 0.0;
    private String selectedNamaKereta = null;
    private String selectedKodeKelas = null;

    /**
     * Metode untuk memuat data dari database ke dalam jTable1.
     */
    private void load_Table() {
        // Membuat objek DefaultTableModel
        DefaultTableModel model = new DefaultTableModel() {
            // Membuat semua sel tidak bisa diedit oleh pengguna
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Menambahkan kolom ke model
        model.addColumn("No.");
        model.addColumn("Nama Kereta");
        model.addColumn("Tujuan");
        model.addColumn("Kelas");
        model.addColumn("Waktu Berangkat");
        model.addColumn("Harga (Rp)"); // Menambahkan kembali kolom Harga
        model.addColumn("Sisa Kursi");
        model.addColumn("Train Code");

        try {
            int no = 1;
            // Query SQL untuk mengambil data dengan JOIN, termasuk harga
            String sql = "SELECT t.nama_kereta, r.stasiun_tujuan_nama, tc.kode_kelas, "
                    + "s.waktu_keberangkatan, s.harga, s.sisa_kursi, s.train_code "
                    + "FROM schedules s "
                    + "JOIN trains t ON s.id_kereta = t.id_kereta "
                    + "JOIN routes r ON s.id_rute = r.id_rute "
                    + "JOIN train_classes tc ON s.id_kelas = tc.id_kelas "
                    + "ORDER BY s.waktu_keberangkatan ASC";

            koneksi.connect();
            Connection conn = koneksi.con;

            if (conn != null) {
                Statement stm = conn.createStatement();
                ResultSet res = stm.executeQuery(sql);

                while (res.next()) {
                    model.addRow(new Object[]{
                        no++,
                        res.getString("nama_kereta"),
                        res.getString("stasiun_tujuan_nama"),
                        res.getString("kode_kelas"),
                        res.getString("waktu_keberangkatan"),
                        res.getDouble("harga"), // Menambahkan data harga sebagai Double
                        res.getInt("sisa_kursi"),
                        res.getString("train_code")
                    });
                }
                jTable1.setModel(model);

                // --- PENAMBAHAN KODE UNTUK FORMAT HARGA ---
                // Mengatur Cell Renderer khusus untuk kolom harga (indeks 5)
                jTable1.getColumnModel().getColumn(5).setCellRenderer(new CurrencyRenderer());

            } else {
                JOptionPane.showMessageDialog(this, "Koneksi ke database null, tidak dapat memuat data.");
            }

        } catch (SQLException e) {
            logger.log(java.util.logging.Level.SEVERE, "Error memuat data tabel: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Gagal memuat data dari database: " + e.getMessage());
        }
    }

    /**
     * Creates new form jAvailabelDate
     */
    public jAvailabelDate() {
        initComponents();
        load_Table();

        // Mengatur properti tampilan tabel
        jTable1.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        jTable1.setRowHeight(25);
        jTable1.setAutoCreateRowSorter(true);

        // --- IMPLEMENTASI SELEKSI BARIS ---
        // Mengatur agar hanya satu baris yang bisa dipilih pada satu waktu
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Menambahkan listener untuk mendeteksi perubahan seleksi baris
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // Pastikan event tidak sedang dalam proses adjusting (untuk menghindari eksekusi ganda)
                if (!event.getValueIsAdjusting()) {
                    // Dapatkan baris yang dipilih
                    int selectedRow = jTable1.getSelectedRow();

                    // Pastikan ada baris yang benar-benar dipilih
                    if (selectedRow != -1) {
                        // Ambil semua data yang diperlukan dari baris yang dipilih
                        selectedNamaKereta = jTable1.getValueAt(selectedRow, 1).toString();
                        selectedRute = jTable1.getValueAt(selectedRow, 2).toString();
                        selectedKodeKelas = jTable1.getValueAt(selectedRow, 3).toString();
                        selectedWaktuBerangkat = jTable1.getValueAt(selectedRow, 4).toString();
                        selectedHarga = (Double) jTable1.getValueAt(selectedRow, 5);
                        selectedTrainCode = jTable1.getValueAt(selectedRow, 7).toString();
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonNext = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBtnBack = new javax.swing.JButton();
        BGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonNext.setContentAreaFilled(false);
        jButtonNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(1285, 960, 310, 80));

        jTable1.setFont(new java.awt.Font("Baloo Da 2", 0, 24)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 197, 1430, 730));

        jBtnBack.setContentAreaFilled(false);
        jBtnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBackActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 953, 290, 100));

        BGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Available dateB.png"))); // NOI18N
        getContentPane().add(BGround, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        if (selectedTrainCode == null) {
            JOptionPane.showMessageDialog(this, "Silakan klik salah satu baris jadwal terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            // --- KODE BARU UNTUK REDIRECT ---
            // Membuat rute lengkap (Asal - Tujuan)
            String ruteLengkap = "Tangerang - " + selectedRute;

            // Membuka jPassengerForm dengan data yang dipilih.
            // Pastikan jPassengerForm memiliki konstruktor yang cocok dengan parameter ini.
            jPassangerForm passengerForm = new jPassangerForm(
                    selectedNamaKereta,
                    ruteLengkap,
                    selectedKodeKelas,
                    selectedWaktuBerangkat,
                    selectedHarga,
                    selectedTrainCode
            );
            passengerForm.setVisible(true);
            this.dispose();

        }
    }//GEN-LAST:event_jButtonNextActionPerformed

    private void jBtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBackActionPerformed
        new jHomePage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    class CurrencyRenderer extends DefaultTableCellRenderer {

        private final NumberFormat formatter = NumberFormat.getIntegerInstance(new Locale("id", "ID"));

        public CurrencyRenderer() {
            setHorizontalAlignment(javax.swing.SwingConstants.RIGHT); // Meratakan angka ke kanan
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // Ambil komponen label default
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Jika nilai adalah angka, format sebagai mata uang
            if (value instanceof Number) {
                setText(formatter.format(value));
            }

            return c;
        }
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new jAvailabelDate().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BGround;
    private javax.swing.JButton jBtnBack;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

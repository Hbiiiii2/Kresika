/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kresika;

// Import untuk database dan Swing
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// Import untuk File I/O
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

// Import untuk pembuatan PDF dengan Apache PDFBox
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.awt.image.BufferedImage;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;

// Import untuk pembuatan Barcode dengan ZXing
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

// Import untuk Pengiriman Email dengan JavaMail (disimpan untuk simulasi)
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Hbiiiiii2
 */
public class jPayment extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(jPayment.class.getName());

    private double totalHargaDiterima;
    private String namaDiterima;
    private String emailDiterima;
    private int jumlahTiketDiterima;
    private String trainCodeDiterima;
    private String namaKeretaDiterima;
    private String ruteDiterima;
    private String waktuBerangkatDiterima;
    private String kelasDiterima;
    private int idJadwalDiterima;

    /**
     * Creates new form jPayment
     */
    public jPayment() {
        initComponents();
        setTitle("Rincian Pembayaran (No Data)");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jChangeDue.setText("Rp 0");
    }

    public jPayment(String nama, String email, int jumlahTiket, double totalHarga, String namaKereta, String rute, String waktuBerangkat, String kelas, String trainCode) {
        initComponents(); // Inisialisasi komponen UI

        // Simpan data yang diterima
        this.namaDiterima = nama;
        this.emailDiterima = email;
        this.jumlahTiketDiterima = jumlahTiket;
        this.totalHargaDiterima = totalHarga;
        this.namaKeretaDiterima = namaKereta;
        this.ruteDiterima = rute;
        this.waktuBerangkatDiterima = waktuBerangkat;
        this.kelasDiterima = kelas;
        this.trainCodeDiterima = trainCode;

        // Tampilkan data di UI
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        jName.setText(this.namaDiterima);
        jEmail.setText(this.emailDiterima);
        jNumberTicket.setText(String.valueOf(this.jumlahTiketDiterima));

        jSeatClass.setText(this.kelasDiterima);   // Menampilkan kelas di jSeatClass
        jClassTicket.setText(namaKereta);        // Menampilkan nama kereta di jClassTicket

        // Menampilkan hanya nama stasiun tujuan di jTujuan1
        if (rute != null && rute.contains(" - ")) {
            String tujuanSaja = rute.split(" - ")[1].trim();
            jTujuan1.setText(tujuanSaja);
        } else {
            jTujuan1.setText(rute);
        }

        jTotalPayment.setText(formatter.format(this.totalHargaDiterima));
        jChangeDue.setText("Rp 0");

        // Logika kembalian otomatis
        addCashReceivedListener();

        // Atur properti window
        setTitle("Rincian Pembayaran");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Method untuk mengatur Cash di terima
    private void addCashReceivedListener() {
        jChashReceived.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateChange();
            }
        });
    }

    // Method untuk mengatur uang kembalian
    private void calculateChange() {
        String cashText = jChashReceived.getText();
        if (cashText.isEmpty()) {
            jChangeDue.setText("Rp 0");
            return;
        }
        try {
            double cashReceived = Double.parseDouble(cashText);
            double changeDue = cashReceived - this.totalHargaDiterima;
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
            jChangeDue.setText(formatter.format(changeDue));
        } catch (NumberFormatException ex) {
            jChangeDue.setText("Input tidak valid");
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

        jTotalPayment = new javax.swing.JLabel();
        jName = new javax.swing.JLabel();
        jNumberTicket = new javax.swing.JLabel();
        jEmail = new javax.swing.JLabel();
        jSeatClass = new javax.swing.JLabel();
        jChangeDue = new javax.swing.JLabel();
        jChashReceived = new javax.swing.JTextField();
        jTujuan1 = new javax.swing.JLabel();
        jBack = new javax.swing.JButton();
        jPayBtn = new javax.swing.JButton();
        jClassTicket = new javax.swing.JLabel();
        jBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTotalPayment.setFont(new java.awt.Font("Baloo Da 2", 1, 36)); // NOI18N
        jTotalPayment.setText("jLabel2");
        jTotalPayment.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jTotalPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 480, 240, 50));

        jName.setFont(new java.awt.Font("Baloo Da 2", 0, 24)); // NOI18N
        jName.setText("jLabel2");
        jName.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jName, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, 330, 30));

        jNumberTicket.setFont(new java.awt.Font("Baloo Da 2", 0, 24)); // NOI18N
        jNumberTicket.setText("jLabel2");
        jNumberTicket.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jNumberTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 290, 330, 30));

        jEmail.setFont(new java.awt.Font("Baloo Da 2", 0, 24)); // NOI18N
        jEmail.setText("jLabel2");
        jEmail.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 350, 330, -1));

        jSeatClass.setFont(new java.awt.Font("Baloo Da 2", 0, 24)); // NOI18N
        jSeatClass.setText("jLabel2");
        jSeatClass.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jSeatClass, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 350, 330, 30));

        jChangeDue.setFont(new java.awt.Font("Baloo Da 2", 0, 24)); // NOI18N
        jChangeDue.setText("jLabel2");
        jChangeDue.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jChangeDue, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 750, 500, 70));
        getContentPane().add(jChashReceived, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 752, 320, 40));

        jTujuan1.setFont(new java.awt.Font("Baloo Da 2", 1, 36)); // NOI18N
        jTujuan1.setText("jLabel2");
        jTujuan1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jTujuan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 480, 240, 60));

        jBack.setContentAreaFilled(false);
        jBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(jBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 930, 330, 100));

        jPayBtn.setContentAreaFilled(false);
        jPayBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPayBtnActionPerformed(evt);
            }
        });
        getContentPane().add(jPayBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 933, 350, 100));

        jClassTicket.setFont(new java.awt.Font("Baloo Da 2", 1, 36)); // NOI18N
        jClassTicket.setText("jLabel1");
        getContentPane().add(jClassTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 200, 40));

        jBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PaymentD.png"))); // NOI18N
        getContentPane().add(jBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPayBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPayBtnActionPerformed
        // 1. Validasi input uang tunai
        double cashReceived;
        try {
            cashReceived = Double.parseDouble(jChashReceived.getText());
            if (cashReceived < this.totalHargaDiterima) {
                JOptionPane.showMessageDialog(this, "Uang tunai kurang!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Input uang tunai tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conn = null;
        String kodePemesanan = "KRSK-" + System.currentTimeMillis();
        String dataBarcode = kodePemesanan + "-" + this.trainCodeDiterima;
        String pdfFilePath = System.getProperty("user.home") + "/Downloads/TicketKresika/ETiket-" + kodePemesanan + ".pdf"; //variabel Menyimpan file e-ticket ke lokal

        try {
            koneksi.connect();
            conn = koneksi.con;
            conn.setAutoCommit(false);

            String sqlGetJadwal = "SELECT id_jadwal FROM schedules WHERE train_code = ?";
            try (PreparedStatement pstGetJadwal = conn.prepareStatement(sqlGetJadwal)) {
                pstGetJadwal.setString(1, this.trainCodeDiterima);
                try (ResultSet rs = pstGetJadwal.executeQuery()) {
                    if (rs.next()) {
                        this.idJadwalDiterima = rs.getInt("id_jadwal");
                    } else {
                        throw new SQLException("Jadwal tidak ditemukan.");
                    }
                }
            }

            String sqlUpdateKursi = "UPDATE schedules SET sisa_kursi = sisa_kursi - ? WHERE id_jadwal = ? AND sisa_kursi >= ?";
            try (PreparedStatement pstUpdate = conn.prepareStatement(sqlUpdateKursi)) {
                pstUpdate.setInt(1, this.jumlahTiketDiterima);
                pstUpdate.setInt(2, this.idJadwalDiterima);
                pstUpdate.setInt(3, this.jumlahTiketDiterima);
                if (pstUpdate.executeUpdate() == 0) {
                    throw new SQLException("Kursi tidak mencukupi.");
                }
            }

            String sqlInsertBooking = "INSERT INTO bookings (kode_pemesanan, id_jadwal, nama_pemesan, email_pemesan, jumlah_tiket, total_harga, barcode) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstInsert = conn.prepareStatement(sqlInsertBooking)) {
                pstInsert.setString(1, kodePemesanan);
                pstInsert.setInt(2, this.idJadwalDiterima);
                pstInsert.setString(3, this.namaDiterima);
                pstInsert.setString(4, this.emailDiterima);
                pstInsert.setInt(5, this.jumlahTiketDiterima);
                pstInsert.setDouble(6, this.totalHargaDiterima);
                pstInsert.setString(7, dataBarcode);
                pstInsert.executeUpdate();
            }

            conn.commit();

            generatePdfReceipt(pdfFilePath, kodePemesanan, dataBarcode);

            // Fungsi untuk mengirim email dan isinya
            String emailSubject = "E-Tiket Kresika Anda - " + kodePemesanan;
            String emailBody = "Yth. " + this.namaDiterima + ",\n\n"
                    + "Terima kasih telah melakukan pemesanan tiket melalui Kresika.\n"
                    + "Pembayaran Anda telah kami terima dan tiket Anda telah berhasil diterbitkan.\n\n"
                    + "Berikut adalah detail perjalanan Anda:\n"
                    + "   - Kereta: " + this.namaKeretaDiterima + " (" + this.kelasDiterima + ")\n"
                    + "   - Rute: " + this.ruteDiterima + "\n"
                    + "   - Waktu Berangkat: " + this.waktuBerangkatDiterima + "\n\n"
                    + "E-Tiket Anda terlampir dalam email ini dalam format PDF. Mohon tunjukkan e-tiket ini saat akan melakukan boarding.\n\n"
                    + "Selamat menikmati perjalanan Anda!\n\n"
                    + "Hormat kami,\n"
                    + "Tim Kresika";

            // Mengirim email dengan body yang sudah diperbarui
            sendEmailWithAttachment(this.emailDiterima, emailSubject, emailBody, pdfFilePath);

            JOptionPane.showMessageDialog(this, "Payment Successful!\nE-Ticket telah disimpan dan dikirim ke email Anda.", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            new jAvailabelDate().setVisible(true);
            this.dispose();

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                logger.log(java.util.logging.Level.SEVERE, "Rollback gagal.", ex);
            }
            JOptionPane.showMessageDialog(this, "Error Database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Transaksi berhasil, namun gagal membuat PDF atau mengirim email: " + e.getMessage(), "Peringatan", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
            new jAvailabelDate().setVisible(true);
            this.dispose();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                logger.log(java.util.logging.Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jPayBtnActionPerformed
    // method pembuat e-ticket berbentuk pdf
    private void generatePdfReceipt(String filePath, String bookingCode, String barcodeData) throws IOException, Exception {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            float yPosition = page.getMediaBox().getUpperRightY() - 50;
            float margin = 50;

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                // 1. Tambahkan Logo Kresika
                try (InputStream in = getClass().getResourceAsStream("/image/logo_kresika.png")) {
                    if (in == null) {
                        System.err.println("Logo tidak ditemukan! Pastikan file logo ada di /image/");
                    } else {
                        PDImageXObject logoImage = PDImageXObject.createFromByteArray(document, in.readAllBytes(), "logo");
                        float logoWidth = 100;
                        float logoHeight = (logoWidth / logoImage.getWidth()) * logoImage.getHeight();
                        contentStream.drawImage(logoImage, margin, yPosition - logoHeight + 20, logoWidth, logoHeight);
                    }
                } catch (Exception e) {
                    System.err.println("Gagal memuat logo: " + e.getMessage());
                }

                // 2. Tambahkan Judul
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                contentStream.newLineAtOffset(margin + 180, yPosition);
                contentStream.showText("E-TIKET PERJALANAN");
                contentStream.endText();

                yPosition -= 50;

                // 3. Gambar garis pemisah
                contentStream.setStrokingColor(java.awt.Color.GRAY);
                contentStream.setLineWidth(1);
                contentStream.moveTo(margin, yPosition);
                contentStream.lineTo(page.getMediaBox().getWidth() - margin, yPosition);
                contentStream.stroke();
                yPosition -= 30;

                // 4. Detail Pemesanan dan QR Code
                // Area Kiri: Detail Teks
                float leftX = margin;
                float currentY = yPosition;
                currentY = drawDetailRow(contentStream, leftX, currentY, "Kode Booking", bookingCode);
                currentY = drawDetailRow(contentStream, leftX, currentY, "Nama Pemesan", this.namaDiterima);
                currentY = drawDetailRow(contentStream, leftX, currentY, "Kereta", this.namaKeretaDiterima + " (" + this.kelasDiterima + ")");
                currentY = drawDetailRow(contentStream, leftX, currentY, "Rute", this.ruteDiterima);
                currentY = drawDetailRow(contentStream, leftX, currentY, "Waktu Berangkat", this.waktuBerangkatDiterima);
                currentY = drawDetailRow(contentStream, leftX, currentY, "Jumlah Tiket", String.valueOf(this.jumlahTiketDiterima) + " Penumpang");
                currentY -= 10; // Spasi sebelum total
                drawDetailRow(contentStream, leftX, currentY, "Total Pembayaran", NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(this.totalHargaDiterima));

                // Area Kanan: QR Code
                BufferedImage qrCodeImage = createQRCodeImage(barcodeData, 150, 150);
                PDImageXObject pdImage = LosslessFactory.createFromImage(document, qrCodeImage);
                float qrX = page.getMediaBox().getWidth() - margin - pdImage.getWidth();
                float qrY = yPosition - pdImage.getHeight() - 20;
                contentStream.drawImage(pdImage, qrX, qrY, pdImage.getWidth(), pdImage.getHeight());

            }
            document.save(filePath);
        }
    }

    //Method formating untuk e-ticket agar hasil nya lebih konsisten dan rapih
    private float drawDetailRow(PDPageContentStream contentStream, float x, float y, String label, String value) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(label);
        contentStream.endText();

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(x + 120, y); // Jarak antara label dan value
        contentStream.showText(": " + value);
        contentStream.endText();

        return y - 25; // Mengembalikan posisi Y untuk baris selanjutnya
    }

    //Method untuk membuat QR Code e-Ticket
    private BufferedImage createQRCodeImage(String text, int width, int height) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    //Method untuk mengirim via email smtp.gmail
    private void sendEmailWithAttachment(String recipientEmail, String subject, String body, String filePath) throws MessagingException {
        final String fromEmail = "kresikabsi.official@gmail.com"; // Ganti dengan email Gmail Anda
        final String password = "cuwdnrmykzckvbax"; // Ganti dengan "App Password" dari akun Google Anda

        Properties props = new Properties();

        //465 (SSL)
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        //587 (TLS) 
//        props.put("mail.smtp.host", "smtp.gmail.com"); 
//        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
        //LocalHost
//        props.put("mail.smtp.host", "localhost"); 
//        props.put("mail.smtp.port", "1025");      
//        props.put("mail.smtp.auth", "false");    
//        props.put("mail.smtp.starttls.enable", "false"); 
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filePath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(new File(filePath).getName());
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Email berhasil dikirim ke " + recipientEmail);
        } catch (MessagingException e) {
            System.out.println("Error occurence : ");
            e.printStackTrace();
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new jPayment().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBack;
    private javax.swing.JLabel jBackground;
    private javax.swing.JLabel jChangeDue;
    private javax.swing.JTextField jChashReceived;
    private javax.swing.JLabel jClassTicket;
    private javax.swing.JLabel jEmail;
    private javax.swing.JLabel jName;
    private javax.swing.JLabel jNumberTicket;
    private javax.swing.JButton jPayBtn;
    private javax.swing.JLabel jSeatClass;
    private javax.swing.JLabel jTotalPayment;
    private javax.swing.JLabel jTujuan1;
    // End of variables declaration//GEN-END:variables
}

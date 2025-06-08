<p align="center">
  <img src="logo_kresika.png" alt="Kresika Logo" width="150"/>
</p>

<h1 align="center">Aplikasi Pemesanan Tiket Kereta "Kresika"</h1>

<p align="center">
  Aplikasi desktop berbasis Java Swing yang dirancang untuk petugas loket dalam mengelola dan memproses pemesanan tiket kereta api secara efisien dan terstruktur.
</p>

---

## 🚀 Deskripsi Proyek

Kresika adalah sistem informasi pemesanan tiket kereta api yang dibangun sebagai proyek mini untuk mendemonstrasikan implementasi aplikasi desktop dengan koneksi database. Sistem ini ditujukan untuk petugas loket di stasiun keberangkatan tetap (Tangerang), memungkinkan mereka untuk mengelola jadwal perjalanan dan melayani pemesanan tiket pelanggan secara lengkap, mulai dari pemilihan jadwal hingga pencetakan e-tiket PDF dengan QR Code.

---

## ✨ Fitur Utama

-   🔐 **Login Petugas:** Sistem diamankan dengan form login untuk memastikan hanya staf yang berwenang yang dapat mengakses data.
-   🗓️ **Manajemen Jadwal:**
    -   Menampilkan semua jadwal yang tersedia dalam tabel yang fungsional dengan fitur **pencarian** dan **pengurutan** data.
    -   Menambahkan jadwal baru melalui form khusus, dengan pilihan data kereta, rute, dan kelas yang dinamis dari database.
-   🎫 **Alur Pemesanan Tiket:**
    -   Proses pemesanan yang terstruktur: pilih jadwal, isi data penumpang, dan lakukan pembayaran.
    -   Data jadwal yang dipilih (rute, tanggal, kelas, harga) secara otomatis diteruskan antar halaman untuk mengurangi kesalahan input.
-   💰 **Proses Pembayaran:**
    -   Menampilkan halaman rincian yang jelas sebelum konfirmasi pembayaran.
    -   Fitur **kalkulator kembalian otomatis** untuk membantu petugas saat transaksi tunai.
-   📄 **Pencetakan E-Tiket PDF:**
    -   Setelah pembayaran berhasil, transaksi disimpan ke database, dan stok kursi diperbarui.
    * Sistem secara otomatis membuat **E-Tiket dalam format PDF** yang berisi detail lengkap perjalanan dan **QR code unik** untuk validasi.
    * E-Tiket disimpan secara lokal di folder "Downloads", dan otomatis ke kirim ke email pembeli tiket.
-   📊 **Riwayat Pembayaran:**
    * Menampilkan seluruh riwayat transaksi yang pernah tercatat di database dengan detail yang komprehensif.
    * Dilengkapi fitur pencarian dan pengurutan data untuk memudahkan petugas melacak transaksi.

---

## 🗃️ Struktur Database `kresika_db`

Database dirancang untuk menormalkan data dan memastikan integritas. Berikut adalah detail untuk setiap tabel:

### `users`
Menyimpan data login petugas loket.
| Nama Kolom | Tipe Data | Keterangan |
|------------|-----------|------------|
| `id` | `INT` | Primary Key, Auto Increment |
| `username` | `VARCHAR(50)` | Unik, untuk login |
| `password` | `VARCHAR(255)` | Sebaiknya disimpan dalam format hash |
| `fullname` | `VARCHAR(100)`| Nama lengkap petugas |
| `email` | `VARCHAR(100)`| Email unik petugas |

### `trains`
Menyimpan informasi dasar kereta.
| Nama Kolom | Tipe Data | Keterangan |
|------------|-----------|------------|
| `id_kereta` | `INT` | Primary Key, Auto Increment |
| `nama_kereta` | `VARCHAR(100)`| Contoh: "BSI EXP", "KAI EXP" |
| `tipe_kereta` | `VARCHAR(50)` | Contoh: "Eksekutif" |

### `routes`
Menyimpan daftar rute tujuan dari stasiun asal tetap (Tangerang).
| Nama Kolom | Tipe Data | Keterangan |
|------------|-----------|------------|
| `id_rute` | `INT` | Primary Key, Auto Increment |
| `stasiun_tujuan_nama` | `VARCHAR(100)`| Unik, Contoh: "Bandung" |

### `train_classes`
Menyimpan tipe kelas kursi.
| Nama Kolom | Tipe Data | Keterangan |
|------------|-----------|------------|
| `id_kelas` | `INT` | Primary Key, Auto Increment |
| `kode_kelas` | `VARCHAR(10)` | Unik, Contoh: "1E", "2B" |
| `nama_kelas` | `VARCHAR(50)` | Deskripsi kelas, Contoh: "Eksekutif A" |

### `schedules`
Tabel inti yang berisi jadwal perjalanan spesifik.
| Nama Kolom | Tipe Data | Keterangan |
|------------|-----------|------------|
| `id_jadwal` | `INT` | Primary Key, Auto Increment |
| `id_kereta` | `INT` | Foreign Key ke `trains` |
| `id_rute` | `INT` | Foreign Key ke `routes` |
| `id_kelas` | `INT` | Foreign Key ke `train_classes` |
| `train_code` | `VARCHAR(20)` | Kode unik untuk jadwal |
| `waktu_keberangkatan` | `DATETIME` | Waktu berangkat dari Tangerang |
| `waktu_kedatangan` | `DATETIME` | Perkiraan waktu tiba di tujuan |
| `harga` | `DECIMAL(12,2)`| Harga tiket |
| `total_kursi`| `INT` | Total kapasitas kursi |
| `sisa_kursi` | `INT` | Sisa kursi yang tersedia |


### `bookings`
Mencatat setiap transaksi pemesanan yang berhasil.
| Nama Kolom | Tipe Data | Keterangan |
|------------|-----------|------------|
| `id_pemesanan`| `INT` | Primary Key, Auto Increment |
| `kode_pemesanan`| `VARCHAR(20)` | Kode unik transaksi |
| `id_jadwal` | `INT` | Foreign Key ke `schedules` |
| `nama_pemesan`| `VARCHAR(100)`| Nama pelanggan |
| `email_pemesan`| `VARCHAR(100)`| Email pelanggan |
| `jumlah_tiket`| `INT` | Jumlah tiket yang dibeli |
| `total_harga` | `DECIMAL(14,2)`| Total pembayaran |
| `barcode` | `VARCHAR(255)`| Data unik untuk QR Code |
| `tanggal_pemesanan` | `TIMESTAMP` | Waktu transaksi dibuat |

---

## ⚙️ Teknologi dan Library

berikut adalah teknologi dan library yang di gunakan:

* **Bahasa:** Java (Versi `24` sesuai konfigurasi `javac.source`)
* **Framework/UI:** Java Swing & AWT
* **Database:** MySQL
* **Library Eksternal:**
    * **MySQL Connector/J 9.2.0:** Untuk koneksi database.
        * Tautan: `mysql-connector-j-9.2.0.jar`
    * **Apache PDFBox 2.0.31:** Untuk membuat file PDF.
        * Tautan Unduhan: [`pdfbox-2.0.31.jar`](https://repo1.maven.org/maven2/org/apache/pdfbox/pdfbox/2.0.31/pdfbox-2.0.31.jar)
    * **FontBox 2.0.31:** Dependensi untuk PDFBox.
        * Tautan Unduhan: [`fontbox-2.0.31.jar`](https://repo1.maven.org/maven2/org/apache/pdfbox/fontbox/2.0.31/fontbox-2.0.31.jar)
    * **Commons Logging 1.3.1:** Dependensi untuk PDFBox.
        * Tautan Unduhan: [`commons-logging-1.3.1.jar`](https://repo1.maven.org/maven2/commons-logging/commons-logging/1.3.1/commons-logging-1.3.1.jar)
    * **ZXing (Zebra Crossing) 3.5.3:** Untuk membuat gambar QR Code.
        * Tautan Unduhan (Core): [`core-3.5.3.jar`](https://repo1.maven.org/maven2/com/google/zxing/core/3.5.3/core-3.5.3.jar)
        * Tautan Unduhan (JavaSE): [`javase-3.5.3.jar`](https://repo1.maven.org/maven2/com/google/zxing/javase/3.5.3/javase-3.5.3.jar)
    * **JavaMail 1.6.2 :** Untuk mengirim email.
        * Tautan Unduhan: [`javax.mail-1.6.2.jar`](https://repo1.maven.org/maven2/com/sun/mail/javax.mail/1.6.2/javax.mail-1.6.2.jar)
    * **Activation 1.1.1:** Dependensi untuk JavaMail.
        * Tautan Unduhan: [`activation-1.1.1.jar`](https://repo1.maven.org/maven2/javax/activation/activation/1.1.1/activation-1.1.1.jar)
    * **Absolute Layout:** Library NetBeans untuk membantu penataan komponen Swing secara bebas.

---

## 📦 Setup dan Instalasi

1.  **Database:**
    * Pastikan Anda memiliki server database MySQL yang berjalan.
    * Buat database baru dengan nama `kresika_db`.
    * Jalankan skrip SQL untuk membuat semua tabel yang dijelaskan di atas.
    * Isi tabel `trains`, `routes`, dan `train_classes` dengan data awal.

2.  **Konfigurasi Koneksi:**
    * Buka file `src/kresika/koneksi.java`.
    * Ubah nilai `url`, `user`, dan `pass` agar sesuai dengan konfigurasi database MySQL Anda.
        ```java
        con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/kresika_db", // Sesuaikan nama DB
                "root", // Sesuaikan username DB
                "" // Sesuaikan password DB
        );
        ```

3.  **Proyek NetBeans:**
    * Buka proyek ini menggunakan IDE NetBeans.
    * Klik kanan pada folder **"Libraries"** di dalam proyek, lalu pilih **"Add JAR/Folder..."**.
    * Tambahkan semua file `.jar` dari library eksternal yang disebutkan di atas.
    * Lakukan **Clean and Build** pada proyek.

4.  **Menjalankan Aplikasi:**
    * Jalankan file utama proyek, yaitu `kresika.Kresika.java`.

---

## 📁 Struktur File Proyek
Proyek ini diorganisir dalam paket `kresika` dengan struktur file sebagai berikut:

```
src/
└── kresika/
├── Kresika.java            # Main class untuk memulai aplikasi
├── koneksi.java            # Helper class untuk koneksi database
├── loginPage.java          # JFrame untuk login petugas
├── jHomePage.java          # JFrame untuk menu utama
├── jAvailabelDate.java     # JFrame untuk menampilkan & memilih jadwal
├── jAddSchedule.java       # JFrame untuk menambah jadwal baru
├── jPassangerForm.java     # JFrame untuk mengisi data penumpang
├── jPayment.java           # JFrame untuk rincian & proses pembayaran
└── jPaymentHistory.java    # JFrame untuk menampilkan riwayat transaksi
 ```



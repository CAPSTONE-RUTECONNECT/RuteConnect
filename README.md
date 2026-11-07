<!-- ====================================================== -->
<!--                    HEADER / BANNER                     -->
<!-- ====================================================== -->

<div align="center">

<img src="https://raw.githubusercontent.com/Sitinursinta04/PRAKTIKUM_PBO/main/Dokumentasi/second/RuteConnect_%20Journey%20and%20Delivery%20Network.png" width="100%" alt="RuteConnect Banner">

<br>

# RUTECONNECT

### Connecting People, Delivering Possibilities.

**Sistem Informasi Terintegrasi Pemesanan Transportasi Daring dan Pengiriman Dokumen Berbasis Digital**

<br>

<img src="https://img.shields.io/badge/PROJECT-PBO-142D4E?style=for-the-badge">
<img src="https://img.shields.io/badge/GROUP-27-1E4775?style=for-the-badge">
<img src="https://img.shields.io/badge/JAVA-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
<img src="https://img.shields.io/badge/DATABASE-MYSQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

<br><br>

**Program Studi Sistem Informasi**  
Fakultas Teknik · Universitas Mulawarman  
2026

</div>

---

<!-- ====================================================== -->
<!--                       ABOUT                            -->
<!-- ====================================================== -->

## 📌 Daftar Isi

1. [Nama Program](#-1-nama-program)
2. [Deskripsi Singkat](#-2-deskripsi-singkat)
3. [Fitur Utama Program](#-3-fitur-utama-program)
4. [Tech Stack](#-4-tech-stack)
5. [Perancangan Sistem](#-5-perancangan-sistem)
6. [Struktur Folder dan Package](#-6-struktur-folder-dan-package)
7. [Penerapan 4 Pilar OOP](#-7-penerapan-4-pilar-oop)
8. [Cara Penggunaan Program](#-8-cara-penggunaan-program)
9. [Kontributor](#-9-kontributor)
10. [Dokumentasi Konsultasi](#-10-dokumentasi-konsultasi)

---

## 🚀 1. Nama Program

<div align="center">

# RuteConnect

**Sistem Informasi Terintegrasi Pemesanan Transportasi Daring dan Pengiriman Dokumen Berbasis Digital**

</div>

RuteConnect merupakan aplikasi desktop yang dirancang untuk mengintegrasikan layanan transportasi dan pengiriman dokumen dalam satu sistem digital.

---

## 💡 2. Deskripsi Singkat

RuteConnect merupakan sebuah aplikasi berbasis desktop yang dikembangkan menggunakan bahasa pemrograman Java untuk mempermudah proses pemesanan layanan transportasi dan pengiriman dokumen secara digital.

Aplikasi ini menggabungkan dua jenis layanan utama, yaitu **pemesanan transportasi** untuk kebutuhan perjalanan pelanggan serta **pengiriman dokumen** untuk membantu proses pengantaran berkas atau dokumen ke lokasi tujuan.

Melalui RuteConnect, pelanggan dapat melakukan registrasi akun, masuk ke aplikasi, memilih layanan, menentukan lokasi penjemputan dan tujuan, melihat estimasi jarak serta biaya, melakukan pembayaran, hingga memantau status dan riwayat pesanan.

Sistem ini juga menyediakan pengelolaan data melalui beberapa lapisan, seperti model, DAO, service, dan tampilan GUI. Dengan dukungan database MySQL, data pengguna, layanan, pesanan, dan transaksi dapat dikelola secara terstruktur.

<br>

<div align="center">

<img src="https://raw.githubusercontent.com/Sitinursinta04/PRAKTIKUM_PBO/main/Dokumentasi/second/RuteConnect_%20Digital%20Transport%20and%20Delivery.png" width="85%" alt="Ilustrasi Sistem RuteConnect">

<sub><i>Ilustrasi konsep layanan transportasi dan pengiriman dokumen RuteConnect.</i></sub>

</div>

---

## ⚙️ 3. Fitur Utama Program

RuteConnect memiliki beberapa fitur yang dikelompokkan berdasarkan peran pengguna dan kebutuhan pengelolaan sistem.

### 👤 A. Pelanggan

| Fitur | Deskripsi |
|---|---|
| Registrasi Akun | Pengguna dapat membuat akun baru dengan mengisi nama, email, nomor telepon, dan password. |
| Login | Pengguna dapat masuk menggunakan email dan password yang telah terdaftar. |
| Pemesanan Transportasi | Melakukan pemesanan layanan transportasi berdasarkan lokasi penjemputan dan tujuan. |
| Pengiriman Dokumen | Melakukan pemesanan layanan pengiriman dokumen dengan mengisi informasi penerima dan detail dokumen. |
| Pemilihan Lokasi | Menentukan lokasi penjemputan dan tujuan melalui fitur peta. |
| Estimasi Jarak | Menghitung jarak perjalanan berdasarkan lokasi yang dipilih. |
| Perhitungan Tarif | Menghitung biaya layanan berdasarkan jenis layanan dan jarak perjalanan. |
| Pembayaran | Mendukung metode pembayaran tunai, e-wallet, dan saldo RutePay. |
| Top Up Saldo | Melakukan pengisian saldo dompet digital RutePay. |
| Status dan Riwayat | Melihat perkembangan status serta riwayat pesanan yang telah dilakukan. |
| Pembatalan Pesanan | Mengajukan pembatalan pesanan sesuai ketentuan status pesanan. |

### 🛵 B. Driver

Pengelolaan proses pesanan driver didukung melalui class service dan DAO.

| Fitur | Deskripsi |
|---|---|
| Melihat Pesanan | Mengakses data pesanan yang tersedia untuk diproses. |
| Menerima Pesanan | Menerima pesanan pelanggan yang tersedia. |
| Menolak Pesanan | Menangani penolakan pesanan sesuai mekanisme sistem. |
| Memperbarui Status | Memperbarui perkembangan status pesanan hingga selesai. |
| Pembatalan Pesanan | Menangani pembatalan pesanan oleh driver. |
| Pengelolaan Data Driver | Menyimpan dan memperbarui informasi driver serta status keaktifannya. |

### 🛠️ C. Admin

Pengelolaan administrasi sistem didukung melalui class `AdminService`, DAO, dan `LaporanService`.

| Fitur | Deskripsi |
|---|---|
| Kelola Layanan | Menambahkan, memperbarui, dan menghapus data layanan. |
| Kelola Tarif | Memperbarui tarif layanan berdasarkan kebutuhan sistem. |
| Kelola Driver | Memperbarui status keaktifan dan menghapus data driver. |
| Kelola Data Pengguna | Mengakses data pengguna melalui pengelolaan DAO. |
| Kelola Pesanan | Mengakses dan mengelola data pesanan melalui lapisan DAO dan service. |
| Kelola Transaksi | Mengakses data transaksi yang tersimpan pada database. |
| Laporan Transaksi | Menghitung total pembayaran dan total top up. |
| Ekspor Laporan | Mengekspor data transaksi ke dalam format CSV. |

> **Catatan:** Fitur yang tersedia pada layer service/DAO tidak selalu berarti sudah memiliki halaman GUI tersendiri. Dokumentasi tampilan hanya perlu menampilkan halaman yang benar-benar tersedia pada aplikasi.

---

## 💻 4. Tech Stack

RuteConnect dikembangkan menggunakan beberapa teknologi dan perangkat pendukung untuk membangun aplikasi desktop, mengelola database, serta membantu proses pengembangan.

### A. Core Technologies

<div align="center">

| Teknologi | Logo |
|---|---|
| Java 21 | <img src="https://cdn.simpleicons.org/openjdk/ED8B00" width="42"> |
| Java Swing | <img src="https://img.shields.io/badge/Java%20Swing-GUI-142D4E?style=flat-square"> |
| MySQL | <img src="https://cdn.simpleicons.org/mysql/4479A1" width="42"> |
| JDBC | <img src="https://img.shields.io/badge/JDBC-Database%20Connectivity-4479A1?style=flat-square"> |
| Apache Maven | <img src="https://cdn.simpleicons.org/apachemaven/C71A36" width="42"> |
| NetBeans IDE | <img src="https://cdn.simpleicons.org/apachenetbeans/1B6AC6" width="42"> |

</div>

| Teknologi | Kegunaan |
|---|---|
| Java 21 | Bahasa pemrograman utama untuk membangun aplikasi. |
| Java Swing | Membuat antarmuka aplikasi desktop. |
| MySQL | Menyimpan dan mengelola data aplikasi. |
| JDBC | Menghubungkan aplikasi Java dengan database MySQL. |
| Apache Maven | Mengelola konfigurasi proyek dan dependensi. |
| NetBeans IDE | Lingkungan pengembangan aplikasi Java. |

### B. Development Environment

<div align="center">

<img src="https://cdn.simpleicons.org/laragon/0E8A70" width="45" alt="Laragon">
&nbsp;&nbsp;&nbsp;
<img src="https://cdn.simpleicons.org/apachenetbeans/1B6AC6" width="45" alt="NetBeans">

<br>

**Laragon** &nbsp; | &nbsp; **Apache NetBeans**

</div>

- **Laragon:** Membantu menjalankan dan mengelola lingkungan database lokal.
- **Apache NetBeans:** Digunakan untuk menulis, mengelola, dan menjalankan source code Java.

### C. AI Development Assistants

<div align="center">

<img src="https://cdn.simpleicons.org/anthropic/D97757" width="45" alt="Claude">
&nbsp;&nbsp;&nbsp;
<img src="https://cdn.simpleicons.org/openai/412991" width="45" alt="ChatGPT">

<br>

**Claude** &nbsp; | &nbsp; **ChatGPT**

</div>

Digunakan sebagai alat bantu dalam proses pengembangan, seperti memahami konsep pemrograman, membantu penelusuran kesalahan, dan mendukung penyusunan dokumentasi proyek.

### D. Platform Referensi Layanan

<div align="center">

<img src="https://cdn.simpleicons.org/gojek/00AA13" width="45" alt="Gojek">
&nbsp;&nbsp;&nbsp;
<img src="https://cdn.simpleicons.org/grab/00B14F" width="45" alt="Grab">

<br>

**Gojek** &nbsp; | &nbsp; **Grab**

</div>

Gojek dan Grab digunakan sebagai referensi konsep layanan transportasi dan pemesanan digital. Keduanya bukan API yang diintegrasikan secara langsung ke dalam aplikasi RuteConnect.

### E. Dependencies

Dependensi yang tercantum dalam `pom.xml`:

| Dependency | Versi | Kegunaan |
|---|---|---|
| MySQL Connector/J | 8.3.0 | Menghubungkan Java dengan database MySQL. |
| AbsoluteLayout | RELEASE310 | Mendukung pengaturan tata letak komponen GUI. |

---

## 🏗️ 5. Perancangan Sistem

Perancangan sistem RuteConnect dibuat untuk menggambarkan alur proses aplikasi, interaksi antara pengguna dengan sistem, serta hubungan antarentitas pada database.

### A. Flowchart

Flowchart menggambarkan alur proses aplikasi mulai dari pengguna membuka aplikasi, melakukan login atau registrasi, memilih layanan, hingga proses pemesanan dan transaksi.

<details>
<summary><b>📊 Klik untuk melihat seluruh Flowchart RuteConnect</b></summary>

<br>

<table>
<tr>
<td width="50%" align="center">

**1. Flowchart Login**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Login.drawio.png?raw=true" width="100%" alt="Flowchart Login">

</td>
<td width="50%" align="center">

**2. Flowchart Registrasi**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Registrasi.drawio.png?raw=true" width="100%" alt="Flowchart Registrasi">

</td>
</tr>

<tr>
<td width="50%" align="center">

**3. Flowchart Menu Customer**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Menu%20Customer.drawio.png?raw=true" width="100%" alt="Flowchart Menu Customer">

</td>
<td width="50%" align="center">

**4. Customer – Transportasi**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Customer%20-%20Transportasi.drawio.png?raw=true" width="100%" alt="Flowchart Customer Transportasi">

</td>
</tr>

<tr>
<td width="50%" align="center">

**5. Customer – Pengiriman Dokumen**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Customer%20-%20Pengiriman%20Dokumen.drawio.png?raw=true" width="100%" alt="Flowchart Pengiriman Dokumen">

</td>
<td width="50%" align="center">

**6. Customer – Pembayaran**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Customer%20-%20Pembayaran.drawio.png?raw=true" width="100%" alt="Flowchart Pembayaran">

</td>
</tr>

<tr>
<td width="50%" align="center">

**7. Customer – Top Up**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Customer%20-%20Top%20Up.drawio.png?raw=true" width="100%" alt="Flowchart Top Up">

</td>
<td width="50%" align="center">

**8. Customer – Status dan Riwayat**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Customer%20-%20Status%20%26%20Riwayat.drawio.png?raw=true" width="100%" alt="Flowchart Status dan Riwayat">

</td>
</tr>

<tr>
<td width="50%" align="center">

**9. Flowchart Driver**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Driver.drawio.png?raw=true" width="100%" alt="Flowchart Driver">

</td>
<td width="50%" align="center">

**10. Admin – Menu Admin**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Admin%20-%20Menu%20Admin.drawio.png?raw=true" width="100%" alt="Flowchart Menu Admin">

</td>
</tr>

<tr>
<td width="50%" align="center">

**11. Admin – Kelola Data**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi9-Admin%20-%20Kelola%20Data.drawio.png?raw=true" width="100%" alt="Flowchart Kelola Data">

</td>
<td width="50%" align="center">

**12. Admin – Data Pesanan**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi9-Admin%20-%20Data%20Pesanan.drawio.png?raw=true" width="100%" alt="Flowchart Data Pesanan">

</td>
</tr>

<tr>
<td width="50%" align="center">

**13. Admin – Data Transaksi**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi9-Admin%20-%20Data%20Transaksi.drawio.png?raw=true" width="100%" alt="Flowchart Data Transaksi">

</td>
<td width="50%" align="center">

**14. Admin – Laporan**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi9-Admin%20-%20Laporan.drawio.png?raw=true" width="100%" alt="Flowchart Laporan Admin">

</td>
</tr>
</table>

</details>

### B. Use Case Diagram

Use Case Diagram menggambarkan hubungan antara aktor dengan fitur-fitur yang tersedia pada sistem RuteConnect.

Aktor yang terlibat meliputi:

- **Pelanggan:** Melakukan registrasi, login, pemesanan layanan, pembayaran, top up, serta melihat status dan riwayat pesanan.
- **Driver:** Mengelola pesanan yang diterima dan memperbarui status perjalanan atau pengiriman.
- **Admin:** Mengelola data dan mendukung pengawasan operasional sistem.

<div align="center">

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/diagram%20PA(1)-Page-5.drawio(1).png?raw=true" width="85%" alt="Use Case Diagram RuteConnect">

<sub><b>Use Case Diagram RuteConnect</b></sub>

</div>

### C. Entity Relationship Diagram (ERD)

Entity Relationship Diagram (ERD) digunakan untuk menggambarkan struktur data dan hubungan antarentitas yang digunakan dalam database RuteConnect.

ERD ditampilkan dalam dua bentuk, yaitu:

- **ERD Logical:** Menggambarkan entitas, atribut, dan hubungan antarentitas secara konseptual.
- **ERD Relational:** Menggambarkan struktur tabel, primary key, foreign key, dan relasi yang diterapkan pada database.

<table>
<tr>
<td width="50%" align="center">

**ERD Logical**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-02%20at%2019.38.02.jpeg?raw=true" width="100%" alt="ERD Logical">

<sub>Gambaran entitas dan hubungan data secara konseptual.</sub>

</td>
<td width="50%" align="center">

**ERD Relational**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-02%20at%2019.38.02.%20(1).jpeg?raw=true" width="100%" alt="ERD Relational">

<sub>Gambaran struktur tabel dan relasi database.</sub>

</td>
</tr>
</table>

---

## 📁 6. Struktur Folder dan Package

Struktur proyek RuteConnect disusun menggunakan beberapa package untuk memisahkan fungsi program, mulai dari konfigurasi database, pengelolaan data, logika bisnis, hingga antarmuka pengguna.

Berikut merupakan struktur folder berdasarkan source code proyek:

```text
SistemRuteConnect/
│
├── pom.xml
│
└── src/
    ├── main/
    │   └── java/
    │       │
    │       ├── config/
    │       │   ├── Koneksi.java
    │       │   └── TestKoneksi.java
    │       │
    │       ├── dao/
    │       │   ├── BaseDao.java
    │       │   ├── CrudDao.java
    │       │   ├── DriverDao.java
    │       │   ├── LayananDao.java
    │       │   ├── PelangganDao.java
    │       │   ├── PesananDao.java
    │       │   ├── TransaksiDao.java
    │       │   └── UserDao.java
    │       │
    │       ├── main/
    │       │   ├── SistemRuteConnect.java
    │       │   ├── gambar/
    │       │   │   ├── Background.png
    │       │   │   ├── Container.png
    │       │   │   ├── Icon.png
    │       │   │   ├── Lock Icon.png
    │       │   │   ├── Vector.png
    │       │   │   └── logo ruteconnect.png
    │       │   │
    │       │   └── resources/
    │       │       └── index.html
    │       │
    │       ├── model/
    │       │   ├── Admin.java
    │       │   ├── Dokumen.java
    │       │   ├── Driver.java
    │       │   ├── Layanan.java
    │       │   ├── Pelanggan.java
    │       │   ├── Pesanan.java
    │       │   ├── Transaksi.java
    │       │   ├── Transportasi.java
    │       │   └── User.java
    │       │
    │       ├── service/
    │       │   ├── AdminService.java
    │       │   ├── Hasil.java
    │       │   ├── LaporanService.java
    │       │   ├── OrderService.java
    │       │   ├── PesananService.java
    │       │   ├── TopUpService.java
    │       │   └── WalletService.java
    │       │
    │       ├── util/
    │       │   ├── AuthService.java
    │       │   ├── Fmt.java
    │       │   ├── JarakHelper.java
    │       │   ├── PetaServer.java
    │       │   ├── Session.java
    │       │   ├── Tx.java
    │       │   └── Validator.java
    │       │
    │       └── view/
    │           ├── FrameMenu.java
    │           ├── FrameMenu.form
    │           ├── Maps.java
    │           ├── Maps.form
    │           ├── MenuPelangganForm.java
    │           ├── MenuPelangganForm.form
    │           ├── PengirimanDokumen.java
    │           ├── PengirimanDokumen.form
    │           ├── RegistrasiForm.java
    │           ├── RegistrasiForm.form
    │           ├── StatusPembayaran.java
    │           ├── StatusPembayaran.form
    │           ├── StatusRiwayat.java
    │           ├── StatusRiwayat.form
    │           ├── TopUpForm.java
    │           └── TopUpForm.form
    │
    └── test/
        └── java/
```

### Penjelasan Package

| Package | Fungsi |
|---|---|
| `config` | Mengatur koneksi aplikasi dengan database MySQL. |
| `dao` | Menangani operasi pengambilan, penyimpanan, perubahan, dan penghapusan data pada database. |
| `main` | Menyediakan class utama untuk menjalankan aplikasi serta menyimpan resource gambar dan halaman peta. |
| `model` | Menyimpan representasi objek dan struktur data yang digunakan dalam aplikasi. |
| `service` | Menangani logika bisnis, proses pemesanan, transaksi, top up, dan laporan. |
| `util` | Menyediakan fungsi pendukung seperti autentikasi, validasi, perhitungan jarak, session, dan transaksi database. |
| `view` | Menyediakan tampilan antarmuka aplikasi menggunakan Java Swing. |

---

## 🧩 7. Penerapan 4 Pilar OOP

Dalam pengembangan RuteConnect, konsep Object-Oriented Programming (OOP) diterapkan untuk membuat struktur program lebih terorganisasi, mudah dikelola, dan dapat dikembangkan.

### A. Encapsulation (Enkapsulasi)

Encapsulation merupakan konsep untuk melindungi atribut suatu class dengan membatasi akses langsung dari luar class.

Pada RuteConnect, konsep ini diterapkan dengan menggunakan access modifier `private` pada atribut dan menyediakan method getter serta setter untuk mengakses atau mengubah nilai atribut.

**Contoh penerapan pada `User.java`:**

```java
public class User {
    private int userId;
    private String nama;
    private String noTelepon;
    private String email;
    private String password;
    private String role;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
```

**Penerapan:** Atribut pengguna tidak dapat diakses secara langsung dari luar class, melainkan melalui method yang telah disediakan.

### B. Inheritance (Pewarisan)

Inheritance merupakan konsep pewarisan atribut dan method dari suatu class induk kepada class turunannya.

Pada RuteConnect, class `User` menjadi class induk yang diwariskan kepada class `Admin`, `Driver`, dan `Pelanggan`.

Selain itu, class `Pesanan` menjadi class induk bagi `Transportasi` dan `Dokumen`.

**Contoh penerapan:**

```java
public class Driver extends User {
    private String merkKendaraan;
    protected String noPlatKendaraan;
    private String jenisKendaraan;
    private String statusAktif;
}
```

```java
public class Pelanggan extends User {
    private String alamatFavorit;
    private double saldoWallet;
}
```

```java
public class Transportasi extends Pesanan {
    private int jumlahPenumpang;
    private String statusPenjemputan;
}
```

**Penerapan:** Class turunan dapat menggunakan atribut dan method yang diwariskan dari class induknya serta memiliki atribut tambahan sesuai kebutuhan masing-masing.

### C. Polymorphism (Polimorfisme)

Polymorphism merupakan konsep yang memungkinkan suatu method memiliki implementasi berbeda pada class turunannya.

Pada RuteConnect, konsep ini diterapkan melalui abstract method `getJenisPesanan()` pada class `Pesanan`, yang kemudian diimplementasikan oleh class `Transportasi` dan `Dokumen`.

**Contoh penerapan:**

```java
public abstract class Pesanan {

    public abstract String getJenisPesanan();

}
```

Implementasi pada class `Transportasi`:

```java
public class Transportasi extends Pesanan {

    @Override
    public String getJenisPesanan() {
        return JENIS_TRANSPORTASI;
    }
}
```

Implementasi pada class `Dokumen`:

```java
public class Dokumen extends Pesanan {

    @Override
    public String getJenisPesanan() {
        return JENIS_DOKUMEN;
    }
}
```

**Penerapan:** Method `getJenisPesanan()` memiliki implementasi yang berbeda sesuai jenis pesanan, sehingga sistem dapat membedakan pesanan transportasi dan pengiriman dokumen.

### D. Abstraction (Abstraksi)

Abstraction merupakan konsep menyembunyikan detail implementasi dan hanya menampilkan bagian penting yang diperlukan.

Pada RuteConnect, abstraksi diterapkan melalui abstract class `Pesanan`, abstract class `BaseDao`, serta interface `CrudDao`.

**Contoh penerapan pada `CrudDao.java`:**

```java
public interface CrudDao<T> {

    boolean insert(T obj);

    boolean update(T obj);

    boolean delete(int id);

    T findById(int id);

    List<T> findAll();

}
```

Interface tersebut mendefinisikan operasi dasar pengelolaan data tanpa menjelaskan detail implementasinya.

**Penerapan:** Setiap class DAO dapat menggunakan kontrak operasi CRUD yang sama, sedangkan detail proses pengelolaan database diimplementasikan pada masing-masing class DAO.

---

## 🖥️ 8. Cara Penggunaan Program

Berikut merupakan gambaran penggunaan aplikasi RuteConnect melalui antarmuka berbasis Java Swing.

### A. Halaman Login

Halaman login digunakan oleh pengguna untuk masuk ke dalam aplikasi dengan memasukkan email dan password yang telah terdaftar.

<div align="center">

<img src="docs/screenshots/01-halaman-awal.png" width="85%" alt="Halaman Login RuteConnect">

<sub><b>Gambar 1. Halaman Login RuteConnect</b></sub>

</div>

### B. Halaman Registrasi

Halaman registrasi digunakan oleh pengguna baru untuk membuat akun dengan mengisi informasi nama lengkap, email, nomor telepon, dan password.

<div align="center">

<img src="docs/screenshots/02-halaman-registrasi.png" width="85%" alt="Halaman Registrasi RuteConnect">

<sub><b>Gambar 2. Halaman Registrasi RuteConnect</b></sub>

</div>

### C. Dashboard Pelanggan

Dashboard pelanggan menampilkan menu utama yang dapat digunakan untuk mengakses layanan transportasi, pengiriman dokumen, top up saldo, serta status dan riwayat pesanan.

<div align="center">

<img src="docs/screenshots/03-dashboard-pelanggan.png" width="85%" alt="Dashboard Pelanggan RuteConnect">

<sub><b>Gambar 3. Dashboard Pelanggan</b></sub>

</div>

### D. Halaman Pemesanan dan Pengiriman Dokumen

Halaman ini digunakan untuk memilih layanan pengiriman dokumen dan mengisi informasi yang diperlukan dalam proses pengiriman.

<div align="center">

<img src="docs/screenshots/04-pengiriman-dokumen.png" width="85%" alt="Halaman Pengiriman Dokumen">

<sub><b>Gambar 4. Halaman Pengiriman Dokumen</b></sub>

</div>

### E. Halaman Pembayaran

Halaman pembayaran menampilkan informasi biaya pesanan dan pilihan metode pembayaran yang dapat digunakan oleh pelanggan.

<div align="center">

<img src="docs/screenshots/05-halaman-pembayaran.png" width="85%" alt="Halaman Pembayaran RuteConnect">

<sub><b>Gambar 5. Halaman Pembayaran</b></sub>

</div>

### F. Halaman Status dan Riwayat

Halaman ini digunakan untuk melihat perkembangan status pesanan serta riwayat transaksi yang telah dilakukan.

<div align="center">

<img src="docs/screenshots/06-status-riwayat.png" width="85%" alt="Halaman Status dan Riwayat">

<sub><b>Gambar 6. Halaman Status dan Riwayat</b></sub>

</div>

---

## 👥 9. Kontributor

Proyek RuteConnect dikembangkan oleh Kelompok 27 sebagai bagian dari tugas mata kuliah Pemrograman Berorientasi Objek (PBO), Program Studi Sistem Informasi, Fakultas Teknik, Universitas Mulawarman.

| No. | Nama | NIM | Peran |
|:---:|---|---|---|
| 1 | Nama Anggota 1 | NIM | Project Leader |
| 2 | Nama Anggota 2 | NIM | Anggota |
| 3 | Nama Anggota 3 | NIM | Anggota |
| 4 | Nama Anggota 4 | NIM | Anggota |
| 5 | Nama Anggota 5 | NIM | Anggota |

---

## 📸 10. Dokumentasi Konsultasi

Dokumentasi berikut merupakan bagian dari proses konsultasi dan pengembangan proyek RuteConnect.

<table>
<tr>
<td width="50%" align="center">

**Dokumentasi Konsultasi 1**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2016.42.39.jpeg?raw=true" width="100%" alt="Dokumentasi Konsultasi 1">

</td>
<td width="50%" align="center">

**Dokumentasi Konsultasi 2**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2016.42.39.1.jpeg?raw=true" width="100%" alt="Dokumentasi Konsultasi 2">

</td>
</tr>

<tr>
<td width="50%" align="center">

**Dokumentasi Konsultasi 3**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2016.42.39.4.jpeg?raw=true" width="100%" alt="Dokumentasi Konsultasi 3">

</td>
<td width="50%" align="center">

**Dokumentasi Konsultasi 4**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2016.42.39.3.jpeg?raw=true" width="100%" alt="Dokumentasi Konsultasi 4">

</td>
</tr>

<tr>
<td width="50%" align="center">

**Dokumentasi Konsultasi 5**

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2016.42.39.2.jpeg?raw=true" width="100%" alt="Dokumentasi Konsultasi 5">

</td>
<td width="50%" align="center">

</td>
</tr>
</table>

---

<!-- ====================================================== -->
<!--                       FOOTER                           -->
<!-- ====================================================== -->

<div align="center">

<img src="https://img.shields.io/badge/RUTECONNECT-Connecting%20People%2C%20Delivering%20Possibilities-142D4E?style=for-the-badge">

<br><br>

**RuteConnect — Connecting Your Journey, Simplifying Your Delivery.**

<br>

<sub>Developed with dedication by Kelompok 27</sub>

<sub>Program Studi Sistem Informasi · Fakultas Teknik</sub>

<sub>Universitas Mulawarman · 2026</sub>

</div>

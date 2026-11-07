<!-- ====================================================== -->
<!--                    RUTECONNECT                         -->
<!-- ====================================================== -->

<div align="center">

  <img src="https://capsule-render.vercel.app/api?type=rect&color=0B1F3A&height=200&section=header&text=RUTECONNECT&fontSize=55&fontColor=FFFFFF&fontAlignY=40&desc=Connecting%20People,%20Delivering%20Possibilities&descAlignY=65&descSize=17" width="100%" />

  <br/>

  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Java%20Swing-GUI-0B1F3A?style=for-the-badge" />
  <img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/Maven-Build%20Tool-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" />

  <br/><br/>

  **Sistem Informasi Terintegrasi Pemesanan Transportasi Daring dan Pengiriman Dokumen Berbasis Digital**

  <br/>

  *One Platform, Connected Routes, Better Services.*

</div>

---

## 📑 Daftar Isi

1. [Nama Program](#-1-nama-program)
2. [Deskripsi Singkat](#-2-deskripsi-singkat)
3. [Fitur Utama Program](#-3-fitur-utama-program)
4. [Tech Stack](#-4-tech-stack)
5. [Perancangan Sistem](#-5-perancangan-sistem)
6. [Struktur Package](#-6-struktur-package)
7. [Penerapan Konsep OOP](#-7-penerapan-konsep-object-oriented-programming-oop)
8. [Cara Penggunaan Program](#-8-cara-penggunaan-program)
9. [Kontributor](#-9-kontributor)
10. [Dokumentasi Konsultasi](#-10-dokumentasi-konsultasi)

---

## 🚀 1. Nama Program

**RuteConnect**

RuteConnect merupakan aplikasi sistem informasi terintegrasi yang dikembangkan untuk mendukung layanan pemesanan transportasi daring dan pengiriman dokumen secara digital.

Nama *RuteConnect* berasal dari gabungan kata *Route* dan *Connect*, yang menggambarkan sebuah platform untuk menghubungkan pengguna dengan layanan transportasi dan pengiriman melalui satu sistem.

---

## 📝 2. Deskripsi Singkat

RuteConnect adalah aplikasi berbasis desktop yang dirancang untuk mengintegrasikan layanan pemesanan transportasi daring dan pengiriman dokumen dalam satu platform digital.

Aplikasi ini dikembangkan menggunakan bahasa pemrograman Java dengan antarmuka grafis Java Swing serta database MySQL untuk mendukung pengelolaan data secara terstruktur.

RuteConnect melibatkan tiga jenis pengguna, yaitu **Pelanggan, Driver, dan Admin**. Setiap pengguna memiliki hak akses dan fungsi yang berbeda sesuai dengan perannya dalam sistem.

Melalui aplikasi ini, pelanggan dapat mengakses layanan transportasi maupun pengiriman dokumen. Driver berperan dalam menerima dan menangani pesanan, sedangkan Admin bertugas mengelola data serta aktivitas operasional aplikasi.

Dengan konsep layanan yang terintegrasi, RuteConnect diharapkan dapat memberikan pengalaman penggunaan yang lebih praktis, terorganisasi, dan mudah diakses.

---

## ✨ 3. Fitur Utama Program

RuteConnect memiliki beberapa fitur utama yang disesuaikan dengan kebutuhan masing-masing pengguna.

### 👤 A. Pelanggan

| Fitur | Deskripsi |
|---|---|
| Registrasi | Memungkinkan pengguna membuat akun untuk mengakses aplikasi. |
| Login | Memungkinkan pelanggan masuk menggunakan akun yang telah terdaftar. |
| Dashboard Pelanggan | Menampilkan halaman utama pelanggan dan pilihan layanan yang tersedia. |
| Layanan Transportasi | Menyediakan akses untuk melakukan pemesanan layanan transportasi. |
| Pengiriman Dokumen | Menyediakan akses untuk layanan pengiriman dokumen. |
| Top Up | Menyediakan halaman untuk melakukan pengisian saldo. |
| Riwayat Pesanan | Menampilkan informasi riwayat aktivitas pemesanan pelanggan. |
| Informasi Akun | Menampilkan informasi akun pengguna. |

### 🛵 B. Driver

| Fitur | Deskripsi |
|---|---|
| Login Driver | Memungkinkan driver masuk ke aplikasi menggunakan akun yang terdaftar. |
| Dashboard Driver | Menampilkan halaman utama khusus driver. |
| Informasi Pesanan | Menampilkan informasi pesanan yang tersedia. |
| Penerimaan Pesanan | Mendukung proses penerimaan pesanan oleh driver. |
| Pembaruan Status | Mendukung pembaruan status pesanan sesuai proses layanan. |

### 🛠️ C. Admin

| Fitur | Deskripsi |
|---|---|
| Login Admin | Memungkinkan admin mengakses sistem sesuai hak aksesnya. |
| Dashboard Admin | Menampilkan halaman utama pengelolaan aplikasi. |
| Manajemen Pengguna | Mendukung pengelolaan data pengguna. |
| Manajemen Layanan | Mendukung pengelolaan informasi layanan. |
| Manajemen Pesanan | Mendukung pemantauan dan pengelolaan data pesanan. |
| Manajemen Transaksi | Mendukung pengelolaan informasi transaksi. |
| Laporan | Mendukung penyajian informasi aktivitas sistem. |

---

## 💻 4. Tech Stack

RuteConnect dikembangkan menggunakan beberapa teknologi, bahasa pemrograman, database, framework, dan tools pendukung.

### A. Teknologi Utama

| Teknologi | Logo | Kegunaan |
|---|---|---|
| Java 21 | <img src="https://cdn.simpleicons.org/openjdk/ED8B00" width="35"/> | Bahasa pemrograman utama untuk membangun aplikasi. |
| Java Swing | <img src="https://cdn.simpleicons.org/java/ED8B00" width="35"/> | Membuat antarmuka grafis atau Graphical User Interface (GUI). |
| MySQL | <img src="https://cdn.simpleicons.org/mysql/4479A1" width="35"/> | Mengelola dan menyimpan data aplikasi. |
| JDBC | <img src="https://cdn.simpleicons.org/java/ED8B00" width="35"/> | Menghubungkan aplikasi Java dengan database MySQL. |
| Maven | <img src="https://cdn.simpleicons.org/apachemaven/C71A36" width="35"/> | Mengelola dependensi dan proses pembangunan proyek. |

### B. Development Environment

| Tools | Logo | Kegunaan |
|---|---|---|
| NetBeans IDE | <img src="https://cdn.simpleicons.org/apachenetbeans/1B6AC6" width="35"/> | Lingkungan pengembangan untuk menulis, menjalankan, dan mengelola kode Java. |
| Laragon | <img src="https://cdn.simpleicons.org/laragon/0E8A70" width="35"/> | Membantu menjalankan lingkungan database lokal. |
| Git | <img src="https://cdn.simpleicons.org/git/F05032" width="35"/> | Mengelola versi dan perubahan kode program. |
| GitHub | <img src="https://cdn.simpleicons.org/github/181717" width="35"/> | Menyimpan dan mengelola repository proyek. |

### C. Tools Pendukung Pengembangan

| Tools | Logo | Kegunaan |
|---|---|---|
| Claude | <img src="https://cdn.simpleicons.org/anthropic/D97757" width="35"/> | Membantu proses eksplorasi ide dan pengembangan program. |
| ChatGPT | <img src="https://cdn.simpleicons.org/openai/412991" width="35"/> | Membantu memahami konsep, mencari referensi, dan mengevaluasi solusi pemrograman. |

### D. Referensi Konsep Layanan

RuteConnect mengadaptasi konsep layanan transportasi dan pengiriman daring yang telah dikenal masyarakat sebagai referensi dalam merancang alur dan pengalaman penggunaan aplikasi.

<div align="center">

  <img src="https://cdn.simpleicons.org/gojek/00AA13" width="75"/>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://cdn.simpleicons.org/grab/00B14F" width="75"/>

  <br/>

  **Gojek &nbsp; | &nbsp; Grab**

  <sub>Digunakan sebagai referensi konsep layanan, bukan sebagai bagian dari integrasi resmi aplikasi.</sub>

</div>

---

## 🧩 5. Perancangan Sistem

Perancangan sistem dilakukan untuk memberikan gambaran mengenai alur kerja, interaksi pengguna, serta hubungan antarentitas dalam aplikasi RuteConnect.

Perancangan ini mencakup Flowchart, Use Case Diagram, dan Entity Relationship Diagram (ERD).

### A. Flowchart

Flowchart menggambarkan alur proses aplikasi mulai dari pengguna membuka aplikasi, melakukan login, memilih layanan, hingga proses pemesanan dan transaksi.

<div align="center">

  <img src="docs/flowchart/flowchart-ruteconnect.png" width="90%" alt="Flowchart RuteConnect"/>

  <br/>

  <sub>Gambar 1. Flowchart Sistem RuteConnect</sub>

</div>

### B. Use Case Diagram

Use Case Diagram menggambarkan hubungan antara aktor dengan fungsi-fungsi yang tersedia di dalam sistem. Aktor utama dalam aplikasi ini terdiri atas Pelanggan, Driver, dan Admin.

<div align="center">

  <img src="docs/diagram/use-case-ruteconnect.png" width="90%" alt="Use Case Diagram RuteConnect"/>

  <br/>

  <sub>Gambar 2. Use Case Diagram RuteConnect</sub>

</div>

### C. Entity Relationship Diagram (ERD)

Entity Relationship Diagram (ERD) menggambarkan struktur basis data, entitas yang digunakan, atribut setiap entitas, serta hubungan antarentitas dalam aplikasi RuteConnect.

<div align="center">

  <img src="docs/diagram/erd-ruteconnect.png" width="90%" alt="Entity Relationship Diagram RuteConnect"/>

  <br/>

  <sub>Gambar 3. Entity Relationship Diagram RuteConnect</sub>

</div>

---

## 📂 6. Struktur Package

Struktur package disusun untuk memisahkan bagian konfigurasi, akses database, model data, layanan aplikasi, utilitas, dan tampilan antarmuka. Pemisahan ini bertujuan agar kode program lebih terorganisasi dan mudah dikembangkan.

```text
RuteConnect/
│
├── src/
│   └── main/
│       ├── java/
│       │   ├── config/
│       │   │   ├── Koneksi.java
│       │   │   └── TestKoneksi.java
│       │   │
│       │   ├── dao/
│       │   │   ├── BaseDao.java
│       │   │   ├── CrudDao.java
│       │   │   ├── DriverDao.java
│       │   │   ├── LayananDao.java
│       │   │   ├── PelangganDao.java
│       │   │   ├── PesananDao.java
│       │   │   ├── TransaksiDao.java
│       │   │   └── UserDao.java
│       │   │
│       │   ├── main/
│       │   │   ├── SistemRuteConnect.java
│       │   │   ├── gambar/
│       │   │   └── resources/
│       │   │       └── index.html
│       │   │
│       │   ├── model/
│       │   │   ├── Admin.java
│       │   │   ├── Dokumen.java
│       │   │   ├── Driver.java
│       │   │   ├── Layanan.java
│       │   │   ├── Pelanggan.java
│       │   │   ├── Pesanan.java
│       │   │   ├── Transaksi.java
│       │   │   ├── Transportasi.java
│       │   │   └── User.java
│       │   │
│       │   ├── service/
│       │   │   ├── AdminService.java
│       │   │   ├── Hasil.java
│       │   │   ├── LaporanService.java
│       │   │   ├── OrderService.java
│       │   │   ├── PesananService.java
│       │   │   ├── TopUpService.java
│       │   │   └── WalletService.java
│       │   │
│       │   ├── util/
│       │   │   ├── AuthService.java
│       │   │   ├── Fmt.java
│       │   │   ├── JarakHelper.java
│       │   │   ├── PetaServer.java
│       │   │   ├── Session.java
│       │   │   ├── Tx.java
│       │   │   └── Validator.java
│       │   │
│       │   └── view/
│       │       ├── FrameMenu.java
│       │       ├── Maps.java
│       │       ├── MenuPelangganForm.java
│       │       ├── PengirimanDokumen.java
│       │       ├── RegistrasiForm.java
│       │       ├── StatusPembayaran.java
│       │       ├── StatusRiwayat.java
│       │       └── TopUpForm.java
│       │
│       └── resources/
│
├── docs/
│   ├── diagram/
│   │   ├── use-case-ruteconnect.png
│   │   └── erd-ruteconnect.png
│   │
│   ├── flowchart/
│   │   └── flowchart-ruteconnect.png
│   │
│   └── screenshots/
│       ├── 01-halaman-awal.png
│       ├── 02-login.png
│       ├── 03-dashboard-pelanggan.png
│       ├── 04-halaman-pemesanan.png
│       ├── 05-dashboard-driver.png
│       └── 06-dashboard-admin.png
│
├── pom.xml
└── README.md
```

### Penjelasan Package

| Package | Fungsi |
|---|---|
| `config` | Mengatur konfigurasi dan koneksi database. |
| `dao` | Menangani proses akses data dan operasi database. |
| `main` | Menyimpan kelas utama untuk menjalankan aplikasi. |
| `model` | Menyimpan kelas yang merepresentasikan objek dan entitas dalam sistem. |
| `service` | Menangani logika bisnis dan proses layanan aplikasi. |
| `util` | Menyediakan fungsi pendukung seperti autentikasi, validasi, format data, dan pengelolaan sesi. |
| `view` | Menyimpan tampilan antarmuka aplikasi menggunakan Java Swing. |

---

## 🧠 7. Penerapan Konsep Object-Oriented Programming (OOP)

Dalam pengembangan RuteConnect, diterapkan empat konsep utama Object-Oriented Programming (OOP), yaitu Encapsulation, Inheritance, Polymorphism, dan Abstraction.

### A. Encapsulation

Encapsulation merupakan konsep untuk membungkus data dan fungsi dalam sebuah kelas serta membatasi akses langsung terhadap atribut tertentu.

Pada RuteConnect, konsep ini diterapkan melalui atribut dengan access modifier `private` dan penggunaan method getter serta setter untuk mengakses atau mengubah nilai atribut.

**Contoh penerapan pada kelas User:**

```java
public class User {

    private int userId;
    private String nama;
    private String noTelepon;
    private String email;
    private String password;
    private String role;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
```

**Penjelasan:**

Atribut pada kelas `User` menggunakan access modifier `private`, sehingga tidak dapat diakses secara langsung dari luar kelas. Pengaksesan dan perubahan data dilakukan melalui method yang telah disediakan.

### B. Inheritance

Inheritance merupakan konsep pewarisan atribut dan method dari suatu kelas induk kepada kelas turunannya.

Pada RuteConnect, kelas `User` berperan sebagai kelas induk yang diwariskan kepada kelas `Pelanggan`, `Driver`, dan `Admin`.

**Contoh penerapan:**

```java
public class Pelanggan extends User {
    // Atribut dan method khusus pelanggan
}

public class Driver extends User {
    // Atribut dan method khusus driver
}

public class Admin extends User {
    // Atribut dan method khusus admin
}
```

**Penjelasan:**

Kelas `Pelanggan`, `Driver`, dan `Admin` mewarisi atribut serta method yang dimiliki kelas `User`. Dengan demikian, setiap jenis pengguna dapat memiliki identitas dasar yang sama tanpa harus menuliskan ulang seluruh atribut dan method tersebut.

### C. Polymorphism

Polymorphism merupakan konsep yang memungkinkan method dengan nama yang sama memiliki implementasi atau perilaku yang berbeda sesuai dengan objek yang menggunakannya.

Pada RuteConnect, konsep ini diterapkan melalui method `getJenisPesanan()` yang memiliki implementasi berbeda pada kelas `Transportasi` dan `Dokumen`.

**Contoh penerapan:**

```java
public abstract class Pesanan {

    public abstract String getJenisPesanan();
}
```

```java
public class Transportasi extends Pesanan {

    @Override
    public String getJenisPesanan() {
        return "TRANSPORTASI";
    }
}
```

```java
public class Dokumen extends Pesanan {

    @Override
    public String getJenisPesanan() {
        return "DOKUMEN";
    }
}
```

**Penjelasan:**

Method `getJenisPesanan()` didefinisikan pada kelas induk `Pesanan`, kemudian diimplementasikan kembali oleh kelas turunannya. Hasil yang diberikan berbeda sesuai dengan jenis pesanan, yaitu `TRANSPORTASI` dan `DOKUMEN`.

### D. Abstraction

Abstraction merupakan konsep penyederhanaan objek dengan menampilkan bagian penting dan menyembunyikan detail implementasi yang tidak perlu diketahui oleh pengguna kelas.

Pada RuteConnect, konsep ini diterapkan melalui kelas abstrak `Pesanan` dan interface `CrudDao`.

**Contoh penerapan kelas abstrak:**

```java
public abstract class Pesanan {

    public abstract String getJenisPesanan();
}
```

**Contoh penerapan interface:**

```java
public interface CrudDao<T> {

    boolean tambah(T data);

    boolean ubah(T data);

    boolean hapus(int id);

    T cariById(int id);
}
```

**Penjelasan:**

Kelas abstrak `Pesanan` menyediakan kerangka dasar bagi jenis pesanan yang akan dibuat, sedangkan interface `CrudDao` mendefinisikan kontrak operasi dasar pengelolaan data. Kelas yang mengimplementasikan interface tersebut harus menyediakan implementasi dari method yang telah didefinisikan.

---

## 🖥️ 8. Cara Penggunaan Program

Bagian ini menjelaskan alur penggunaan aplikasi RuteConnect mulai dari halaman awal hingga akses berdasarkan peran pengguna.

### A. Tampilan Awal Aplikasi

Saat aplikasi dijalankan, pengguna akan diarahkan ke halaman awal RuteConnect. Halaman ini menjadi tampilan pembuka sebelum pengguna melanjutkan ke proses autentikasi.

<div align="center">

  <img src="docs/screenshots/01-halaman-awal.png" width="85%" alt="Halaman Awal RuteConnect"/>

  <br/>

  <sub>Gambar 1. Tampilan Awal Aplikasi</sub>

</div>

### B. Login Pengguna

Pengguna memasukkan email dan password yang telah terdaftar untuk masuk ke dalam aplikasi. Sistem akan memproses autentikasi dan mengarahkan pengguna sesuai dengan peran atau hak aksesnya.

<div align="center">

  <img src="docs/screenshots/02-login.png" width="85%" alt="Halaman Login RuteConnect"/>

  <br/>

  <sub>Gambar 2. Halaman Login</sub>

</div>

### C. Dashboard Pelanggan

Setelah berhasil masuk sebagai pelanggan, pengguna akan diarahkan ke dashboard pelanggan. Halaman ini menjadi pusat akses untuk menggunakan layanan yang tersedia, melihat informasi akun, dan mengakses fitur pendukung lainnya.

<div align="center">

  <img src="docs/screenshots/03-dashboard-pelanggan.png" width="85%" alt="Dashboard Pelanggan RuteConnect"/>

  <br/>

  <sub>Gambar 3. Dashboard Pelanggan</sub>

</div>

### D. Halaman Pemesanan

Pada halaman pemesanan, pelanggan dapat memilih jenis layanan yang ingin digunakan, yaitu transportasi atau pengiriman dokumen. Selanjutnya, pelanggan dapat mengisi informasi yang diperlukan sesuai dengan layanan yang dipilih.

<div align="center">

  <img src="docs/screenshots/04-halaman-pemesanan.png" width="85%" alt="Halaman Pemesanan RuteConnect"/>

  <br/>

  <sub>Gambar 4. Halaman Pemesanan</sub>

</div>

### E. Dashboard Driver

Driver memiliki halaman dashboard tersendiri untuk mengakses informasi pesanan. Melalui halaman ini, driver dapat melihat detail pesanan, menerima pesanan, dan memperbarui status layanan sesuai dengan proses yang berlangsung.

<div align="center">

  <img src="docs/screenshots/05-dashboard-driver.png" width="85%" alt="Dashboard Driver RuteConnect"/>

  <br/>

  <sub>Gambar 5. Dashboard Driver</sub>

</div>

### F. Dashboard Admin

Admin memiliki akses untuk mengelola informasi dan aktivitas operasional aplikasi. Pengelolaan tersebut mencakup data pengguna, layanan, pesanan, transaksi, serta informasi pendukung lainnya.

<div align="center">

  <img src="docs/screenshots/06-dashboard-admin.png" width="85%" alt="Dashboard Admin RuteConnect"/>

  <br/>

  <sub>Gambar 6. Dashboard Admin</sub>

</div>

---

## 👥 9. Kontributor

Proyek RuteConnect dikembangkan secara berkelompok sebagai bagian dari tugas mata kuliah Pemrograman Berorientasi Objek (PBO), Program Studi Sistem Informasi, Universitas Mulawarman.

Berikut merupakan daftar anggota kelompok yang berkontribusi dalam proses perancangan dan pengembangan aplikasi.

| No. | Nama | NIM | Peran |
|:---:|---|---|---|
| 1 | [Nama Anggota 1] | [NIM] | Project Leader |
| 2 | [Nama Anggota 2] | [NIM] | System Development |
| 3 | [Nama Anggota 3] | [NIM] | System Development |
| 4 | [Nama Anggota 4] | [NIM] | System Development |
| 5 | [Nama Anggota 5] | [NIM] | System Development |

---

## 📸 10. Dokumentasi Konsultasi

Dokumentasi berikut merupakan kumpulan foto kegiatan konsultasi, diskusi, dan evaluasi selama proses perancangan serta pengembangan aplikasi RuteConnect.

<div align="center">

  <table>
    <tr>
      <td align="center" width="50%">
        <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2016.42.39.jpeg?raw=true" width="100%" alt="Dokumentasi Konsultasi 1"/>
        <br/>
        <sub>Dokumentasi Konsultasi 1</sub>
      </td>
      <td align="center" width="50%">
        <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2016.42.39.1.jpeg?raw=true" width="100%" alt="Dokumentasi Konsultasi 2"/>
        <br/>
        <sub>Dokumentasi Konsultasi 2</sub>
      </td>
    </tr>
    <tr>
      <td align="center" width="50%">
        <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2016.42.39.4.jpeg?raw=true" width="100%" alt="Dokumentasi Konsultasi 3"/>
        <br/>
        <sub>Dokumentasi Konsultasi 3</sub>
      </td>
      <td align="center" width="50%">
        <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2016.42.39.3.jpeg?raw=true" width="100%" alt="Dokumentasi Konsultasi 4"/>
        <br/>
        <sub>Dokumentasi Konsultasi 4</sub>
      </td>
    </tr>
    <tr>
      <td align="center" width="50%">
        <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2016.42.39.2.jpeg?raw=true" width="100%" alt="Dokumentasi Konsultasi 5"/>
        <br/>
        <sub>Dokumentasi Konsultasi 5</sub>
      </td>
      <td></td>
    </tr>
  </table>

</div>

---

<!-- ====================================================== -->
<!--                         FOOTER                         -->
<!-- ====================================================== -->

<div align="center">

  <img src="https://img.shields.io/badge/RUTECONNECT-Connecting%20People-0B1F3A?style=for-the-badge" />

  <h2>RUTECONNECT</h2>

  **Connecting People, Delivering Possibilities.**

  <br/>

  *One Platform, Connected Routes, Better Services.*

  <br/><br/>

  <sub>Developed with Java | Sistem Informasi | Universitas Mulawarman © 2026</sub>

</div>

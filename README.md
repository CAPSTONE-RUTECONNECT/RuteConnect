
<!-- ============================================================ -->
<!--                    ROUTECONNECT README                       -->
<!-- ============================================================ -->

<div align="center">

<img src="https://raw.githubusercontent.com/Sitinursinta04/PRAKTIKUM_PBO/main/Dokumentasi/second/RuteConnect_%20Journey%20and%20Delivery%20Network.png"
     width="100%"
     alt="RuteConnect - Journey and Delivery Network"/>

<br/>

<p>
  <strong>INTEGRATED TRANSPORTATION & DOCUMENT DELIVERY SYSTEM</strong>
</p>

<p>
  <em>Connecting People, Delivering Possibilities.</em>
</p>

<br/>

<img src="https://img.shields.io/badge/JAVA-21-0B1F3A?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/INTERFACE-JAVA%20SWING-164B78?style=for-the-badge"/>
<img src="https://img.shields.io/badge/DATABASE-MYSQL-087EAC?style=for-the-badge&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/BUILD-MAVEN-183A5A?style=for-the-badge&logo=apachemaven&logoColor=white"/>

<br/><br/>

**A Smarter Way to Move and Deliver.**

</div>

---

# 📑 DAFTAR ISI

<div align="center">

| NO. | BAGIAN | NO. | BAGIAN |
|:---:|---|:---:|---|
| 01 | [Nama Program](#-01-nama-program) | 06 | [Struktur Folder/Package](#-06-struktur-folderpackage) |
| 02 | [Deskripsi Singkat](#-02-deskripsi-singkat) | 07 | [Penerapan 4 Pilar OOP](#-07-penerapan-4-pilar-oop) |
| 03 | [Fitur Utama Program](#-03-fitur-utama-program) | 08 | [Cara Penggunaan Program](#-08-cara-penggunaan-program) |
| 04 | [Tech Stack](#-04-tech-stack) | 09 | [Kontributor](#-09-kontributor) |
| 05 | [Perancangan Sistem](#-05-perancangan-sistem) | | |

</div>

---

# 🚀 01. NAMA PROGRAM

<div align="center">

# RuteConnect

### Sistem Informasi Terintegrasi Pemesanan Transportasi Daring dan Pengiriman Dokumen Berbasis Digital

<img src="https://img.shields.io/badge/TRANSPORTATION-Connected%20Journey-0B1F3A?style=for-the-badge"/>
<img src="https://img.shields.io/badge/DOCUMENT%20DELIVERY-Smart%20Delivery-164B78?style=for-the-badge"/>

</div>

**RuteConnect** merupakan aplikasi desktop yang mengintegrasikan layanan pemesanan transportasi daring dan pengiriman dokumen dalam satu platform digital.

Nama RuteConnect berasal dari gabungan kata *Rute* dan *Connect*. Nama ini merepresentasikan tujuan aplikasi untuk menghubungkan pengguna dengan layanan perjalanan dan pengiriman secara lebih praktis melalui satu sistem yang terintegrasi.

> **Our Vision:** Menghadirkan kemudahan dalam menghubungkan perjalanan dan pengiriman melalui satu platform digital.

---

# 📖 02. DESKRIPSI SINGKAT

RuteConnect adalah aplikasi sistem informasi berbasis desktop yang dikembangkan untuk memberikan kemudahan dalam melakukan pemesanan layanan transportasi daring dan pengiriman dokumen secara digital.

Aplikasi ini mengintegrasikan dua kebutuhan layanan dalam satu platform, sehingga pengguna dapat melakukan perjalanan maupun mengirimkan dokumen melalui sistem yang sama.

RuteConnect memiliki tiga jenis hak akses pengguna, yaitu **Pelanggan, Driver, dan Admin**. Setiap pengguna memiliki akses dan tanggung jawab yang berbeda sesuai dengan perannya di dalam sistem.

Sistem ini dikembangkan menggunakan bahasa pemrograman Java dengan antarmuka Java Swing serta database MySQL untuk menyimpan dan mengelola data aplikasi.

## 🗺️ Gambaran Konsep RuteConnect

<div align="center">

<img src="https://raw.githubusercontent.com/Sitinursinta04/PRAKTIKUM_PBO/main/Dokumentasi/second/RuteConnect_%20Digital%20Transport%20and%20Delivery.png"
     width="95%"
     alt="RuteConnect Digital Transport and Delivery"/>

<sub><em>Gambaran konsep layanan perjalanan dan pengiriman dokumen RuteConnect.</em></sub>

</div>

## ✨ Tujuan Pengembangan

| No. | Tujuan |
|:---:|---|
| 01 | Mengintegrasikan layanan transportasi dan pengiriman dokumen dalam satu aplikasi. |
| 02 | Mempermudah proses pemesanan dan pengelolaan layanan secara digital. |
| 03 | Menyediakan informasi status pesanan secara terstruktur. |
| 04 | Membantu pengelolaan data pelanggan, driver, layanan, dan transaksi. |
| 05 | Menerapkan konsep Object-Oriented Programming dalam pengembangan aplikasi. |

## 👤 Pengguna Sistem

<table>
<tr>
<td width="33%" align="center">

### 👤
**PELANGGAN**

<sub>Pengguna layanan yang melakukan pemesanan transportasi maupun pengiriman dokumen.</sub>

</td>
<td width="33%" align="center">

### 🛵
**DRIVER**

<sub>Pengguna yang menerima dan menjalankan pesanan dari pelanggan.</sub>

</td>
<td width="33%" align="center">

### ⚙️
**ADMIN**

<sub>Pengguna yang bertanggung jawab mengelola data dan aktivitas sistem.</sub>

</td>
</tr>
</table>

---

# ⚙️ 03. FITUR UTAMA PROGRAM

RuteConnect menyediakan sejumlah fitur yang disesuaikan dengan hak akses masing-masing pengguna. Setiap fitur dirancang untuk mendukung proses pemesanan, pengelolaan, dan pemantauan layanan.

## 👤 A. Pelanggan

| Fitur | Deskripsi |
|---|---|
| Registrasi | Membuat akun untuk menggunakan layanan RuteConnect. |
| Login | Masuk ke sistem menggunakan akun terdaftar. |
| Pemesanan Transportasi | Membuat pesanan layanan transportasi. |
| Pengiriman Dokumen | Melakukan pemesanan layanan pengiriman dokumen. |
| Pembatalan Pesanan | Membatalkan pesanan sesuai ketentuan sistem. |
| Status Pesanan | Melihat perkembangan status pesanan. |
| Riwayat Pesanan | Melihat daftar transaksi pemesanan sebelumnya. |
| Dompet Digital | Melihat dan melakukan pengisian saldo. |
| Riwayat Transaksi | Melihat catatan transaksi dan top up. |

## 🛵 B. Driver

| Fitur | Deskripsi |
|---|---|
| Login | Masuk ke sistem sebagai driver. |
| Pesanan Tersedia | Melihat daftar pesanan yang tersedia. |
| Detail Pesanan | Melihat informasi pelanggan dan tujuan pesanan. |
| Menerima Pesanan | Menerima pesanan yang tersedia. |
| Menolak Pesanan | Menolak pesanan sesuai mekanisme sistem. |
| Pembaruan Status | Memperbarui status pengerjaan pesanan. |
| Riwayat Pesanan | Melihat pesanan yang pernah ditangani. |

## ⚙️ C. Admin

| Fitur | Deskripsi |
|---|---|
| Login Admin | Masuk ke halaman pengelolaan sistem. |
| Manajemen Pengguna | Mengelola data pelanggan dan driver. |
| Manajemen Layanan | Mengelola informasi dan tarif layanan. |
| Data Pesanan | Melihat dan memantau seluruh pesanan. |
| Data Transaksi | Melihat informasi transaksi pengguna. |
| Laporan | Mengakses informasi laporan sistem. |

---

# 🛠️ 04. TECH STACK

RuteConnect dikembangkan dengan memanfaatkan beberapa teknologi yang saling mendukung dalam proses pembuatan antarmuka, pengolahan logika program, serta pengelolaan basis data.

## 💻 Teknologi yang Digunakan

| Teknologi | Kategori | Kegunaan |
|---|---|---|
| Java 21 | Bahasa Pemrograman | Membangun logika dan proses aplikasi. |
| Java Swing | GUI Framework | Membuat antarmuka aplikasi desktop. |
| MySQL | Database | Menyimpan dan mengelola data aplikasi. |
| JDBC | Database Connectivity | Menghubungkan aplikasi Java dengan MySQL. |
| Maven | Build Tool | Mengelola dependency dan proses build proyek. |
| NetBeans IDE | Development Environment | Lingkungan pengembangan aplikasi Java. |

## 🔗 Technology Ecosystem

<div align="center">

<img src="https://skillicons.dev/icons?i=java,mysql,maven,netbeans,git,github"
     alt="RuteConnect Technology Stack"/>

<br/><br/>

<sub>Technologies used in the development of RuteConnect.</sub>

</div>

---

# 🏗️ 05. PERANCANGAN SISTEM

Perancangan sistem merupakan tahapan yang dilakukan untuk menggambarkan alur kerja, interaksi pengguna, serta struktur data yang digunakan dalam aplikasi RuteConnect.

Perancangan ini terdiri atas tiga komponen utama, yaitu Flowchart, Use Case Diagram, dan Entity Relationship Diagram (ERD).

## A. Flowchart

Flowchart menggambarkan urutan proses dan alur kerja aplikasi RuteConnect, mulai dari pengguna membuka aplikasi, melakukan autentikasi, memilih layanan, hingga proses pemesanan.

<div align="center">

<img src="docs/flowchart/flowchart-ruteconnect.png"
     width="95%"
     alt="Flowchart RuteConnect"/>

**Gambar 1. Flowchart Sistem RuteConnect**

</div>

## B. Use Case Diagram

Use Case Diagram menggambarkan hubungan antara aktor dengan fungsi-fungsi yang tersedia di dalam sistem.

Terdapat tiga aktor utama dalam aplikasi:

- **Pelanggan:** melakukan pemesanan transportasi dan pengiriman dokumen.
- **Driver:** menerima serta mengelola pesanan.
- **Admin:** mengelola data pengguna, layanan, pesanan, dan transaksi.

<div align="center">

<img src="docs/diagram/use-case-ruteconnect.png"
     width="95%"
     alt="Use Case Diagram RuteConnect"/>

**Gambar 2. Use Case Diagram RuteConnect**

</div>

## C. Entity Relationship Diagram (ERD)

Entity Relationship Diagram digunakan untuk menggambarkan struktur basis data dan hubungan antar-entitas yang mendukung proses pengelolaan data aplikasi.

Entitas utama dalam perancangan database RuteConnect meliputi:

| No. | Entitas | Keterangan |
|:---:|---|---|
| 01 | User | Data umum pengguna sistem. |
| 02 | Pelanggan | Data pengguna layanan sebagai pelanggan. |
| 03 | Driver | Data pengguna layanan sebagai driver. |
| 04 | Admin | Data administrator sistem. |
| 05 | Layanan | Informasi jenis layanan. |
| 06 | Pesanan | Data pemesanan layanan. |
| 07 | Transportasi | Data layanan transportasi. |
| 08 | Dokumen | Data layanan pengiriman dokumen. |
| 09 | Transaksi | Data transaksi pengguna. |
| 10 | Top_Up | Data pengisian saldo. |

<div align="center">

<img src="docs/diagram/erd-ruteconnect.png"
     width="100%"
     alt="Entity Relationship Diagram RuteConnect"/>

**Gambar 3. Entity Relationship Diagram RuteConnect**

</div>

---

# 📂 06. STRUKTUR FOLDER/PACKAGE

Struktur folder dan package digunakan untuk mengelompokkan komponen aplikasi berdasarkan fungsi dan tanggung jawabnya. Pengorganisasian ini membantu menjaga keteraturan kode program serta mempermudah proses pemeliharaan dan pengembangan aplikasi.

## 📁 Struktur Proyek

```text
RuteConnect/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── ruteconnect/
│       │           ├── config/
│       │           ├── model/
│       │           ├── dao/
│       │           ├── service/
│       │           ├── view/
│       │           └── Main.java
│       │
│       └── resources/
│           └── images/
│
├── docs/
│   ├── flowchart/
│   ├── diagram/
│   └── screenshots/
│
├── pom.xml
└── README.md
```

## 📌 Penjelasan Package

| Package | Tanggung Jawab |
|---|---|
| `config` | Mengatur konfigurasi koneksi database. |
| `model` | Menyimpan class yang merepresentasikan objek dan data sistem. |
| `dao` | Menangani operasi pengambilan, penyimpanan, perubahan, dan penghapusan data. |
| `service` | Menangani logika proses bisnis aplikasi. |
| `view` | Menampilkan antarmuka pengguna menggunakan Java Swing. |
| `resources` | Menyimpan aset pendukung seperti gambar dan ikon. |

> Struktur di atas merupakan gambaran pengorganisasian proyek. Sesuaikan nama package, class, dan folder dengan struktur aktual pada proyek NetBeans.

---

# 🧩 07. PENERAPAN 4 PILAR OOP

RuteConnect dikembangkan dengan menerapkan konsep **Object-Oriented Programming (OOP)**. OOP merupakan pendekatan pemrograman yang mengorganisasikan program ke dalam objek yang memiliki atribut dan perilaku.

Terdapat empat pilar utama OOP yang menjadi dasar dalam pengembangan sistem, yaitu Encapsulation, Inheritance, Polymorphism, dan Abstraction.

## 01. Encapsulation — Enkapsulasi

Encapsulation merupakan konsep membungkus data dan metode dalam sebuah class serta membatasi akses langsung terhadap atribut tertentu.

Dalam RuteConnect, konsep ini dapat diterapkan pada class pengguna dan data pesanan dengan menggunakan access modifier `private`.

**Contoh kode:**

```java
public class User {
    private String idUser;
    private String nama;
    private String email;
    private String password;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
```

**Penerapan:** Atribut dibuat `private` agar tidak dapat diakses secara langsung dari luar class. Pengaksesan dan perubahan data dilakukan melalui method getter dan setter.

## 02. Inheritance — Pewarisan

Inheritance merupakan konsep pewarisan atribut dan method dari satu class kepada class lainnya.

Dalam RuteConnect, class `Pelanggan`, `Driver`, dan `Admin` dapat mewarisi atribut serta method umum dari class induk `User`.

**Contoh kode:**

```java
public class Pelanggan extends User {
    private String alamat;

    public String getAlamat() {
        return alamat;
    }
}
```

**Penerapan:** Class `Pelanggan` dapat menggunakan atribut dan method yang diwariskan dari class `User`, sehingga kode menjadi lebih terstruktur dan mengurangi pengulangan.

## 03. Polymorphism — Polimorfisme

Polymorphism merupakan konsep yang memungkinkan satu method memiliki perilaku berbeda berdasarkan objek yang menggunakannya.

**Contoh kode:**

```java
public class User {
    public void tampilkanDashboard() {
        System.out.println("Dashboard Pengguna");
    }
}
```

```java
public class Pelanggan extends User {
    @Override
    public void tampilkanDashboard() {
        System.out.println("Dashboard Pelanggan");
    }
}
```

```java
public class Driver extends User {
    @Override
    public void tampilkanDashboard() {
        System.out.println("Dashboard Driver");
    }
}
```

**Penerapan:** Method `tampilkanDashboard()` memiliki nama yang sama, tetapi menghasilkan perilaku berbeda sesuai dengan jenis objek yang menjalankannya.

## 04. Abstraction — Abstraksi

Abstraction merupakan konsep menyembunyikan detail implementasi dan hanya menampilkan fungsi utama yang diperlukan.

**Contoh kode:**

```java
public abstract class Layanan {
    public abstract void prosesPesanan();

    public void tampilkanInformasi() {
        System.out.println("Layanan RuteConnect");
    }
}
```

```java
public class Transportasi extends Layanan {
    @Override
    public void prosesPesanan() {
        System.out.println("Memproses pesanan transportasi");
    }
}
```

**Penerapan:** Class `Layanan` menjadi class abstrak yang mendefinisikan method `prosesPesanan()`. Class turunannya kemudian memberikan implementasi sesuai dengan jenis layanan.

> **Catatan:** Contoh kode pada bagian ini merupakan ilustrasi konsep. Pastikan contoh akhir disesuaikan dengan implementasi class dan method yang benar-benar terdapat dalam source code RuteConnect.

---

# 🖥️ 08. CARA PENGGUNAAN PROGRAM

Bagian ini menjelaskan tahapan penggunaan aplikasi RuteConnect melalui antarmuka pengguna atau Graphical User Interface (GUI).

Setiap tampilan berikut digunakan untuk memperlihatkan bagaimana pengguna berinteraksi dengan fitur yang tersedia di dalam sistem.

## A. Halaman Awal

Halaman awal merupakan tampilan pertama yang muncul ketika aplikasi RuteConnect dijalankan. Halaman ini menjadi pintu masuk pengguna sebelum mengakses layanan yang tersedia.

<div align="center">

<img src="docs/screenshots/01-halaman-awal.png"
     width="90%"
     alt="Halaman Awal RuteConnect"/>

**Gambar 4. Halaman Awal RuteConnect**

</div>

## B. Halaman Login

Pengguna memasukkan email dan password yang telah terdaftar untuk mengakses sistem sesuai dengan hak akses masing-masing.

<div align="center">

<img src="docs/screenshots/02-halaman-login.png"
     width="90%"
     alt="Halaman Login RuteConnect"/>

**Gambar 5. Halaman Login RuteConnect**

</div>

## C. Dashboard Pelanggan

Dashboard pelanggan menyediakan akses terhadap layanan transportasi, pengiriman dokumen, informasi akun, serta aktivitas pemesanan.

<div align="center">

<img src="docs/screenshots/03-dashboard-pelanggan.png"
     width="90%"
     alt="Dashboard Pelanggan RuteConnect"/>

**Gambar 6. Dashboard Pelanggan**

</div>

## D. Halaman Pemesanan

Halaman pemesanan digunakan pelanggan untuk memilih jenis layanan dan mengisi informasi yang diperlukan dalam proses pembuatan pesanan.

<div align="center">

<img src="docs/screenshots/04-halaman-pemesanan.png"
     width="90%"
     alt="Halaman Pemesanan RuteConnect"/>

**Gambar 7. Halaman Pemesanan**

</div>

## E. Dashboard Driver

Dashboard driver digunakan untuk melihat pesanan yang tersedia, memeriksa detail pesanan, serta menerima dan memperbarui status pengerjaan.

<div align="center">

<img src="docs/screenshots/05-dashboard-driver.png"
     width="90%"
     alt="Dashboard Driver RuteConnect"/>

**Gambar 8. Dashboard Driver**

</div>

## F. Dashboard Admin

Dashboard admin menyediakan akses untuk mengelola data pengguna, layanan, pesanan, serta informasi transaksi yang terdapat di dalam sistem.

<div align="center">

<img src="docs/screenshots/06-dashboard-admin.png"
     width="90%"
     alt="Dashboard Admin RuteConnect"/>

**Gambar 9. Dashboard Admin**

</div>

---

# 👥 09. KONTRIBUTOR

RuteConnect merupakan hasil pengembangan kolaboratif oleh mahasiswa Program Studi Sistem Informasi, Universitas Mulawarman, sebagai bagian dari penyelesaian proyek mata kuliah Pemrograman Berorientasi Objek (PBO).

## Meet Our Team

| No. | Nama | NIM | Peran |
|:---:|---|---|---|
| 01 | [Nama Anggota 1] | [NIM] | Project Leader |
| 02 | [Nama Anggota 2] | [NIM] | System Development |
| 03 | [Nama Anggota 3] | [NIM] | System Development |
| 04 | [Nama Anggota 4] | [NIM] | System Development |
| 05 | [Nama Anggota 5] | [NIM] | System Development |

<div align="center">

<br/>

<img src="https://img.shields.io/badge/UNIVERSITAS-MULAWARMAN-0B1F3A?style=for-the-badge"/>
<img src="https://img.shields.io/badge/PROGRAM%20STUDI-SISTEM%20INFORMASI-164B78?style=for-the-badge"/>

<br/><br/>

**RUTECONNECT**

*Connecting People, Delivering Possibilities.*

<sub>One Platform. Connected Routes. Better Services.</sub>

<br/><br/>

<sub>Developed by Group 27 · Information Systems · Universitas Mulawarman · 2026</sub>

</div>

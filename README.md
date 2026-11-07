
<!-- ===================================================== -->
<!--                    RUTECONNECT                        -->
<!-- ===================================================== -->

<div align="center">

<img src="https://raw.githubusercontent.com/Sitinursinta04/PRAKTIKUM_PBO/main/Dokumentasi/second/RuteConnect_%20Journey%20and%20Delivery%20Network.png" width="100%" alt="RuteConnect Banner"/>

<br/>

<h1>RUTECONNECT</h1>

<strong>INTEGRATED TRANSPORTATION & DOCUMENT DELIVERY SYSTEM</strong>

<p><em>Connecting People, Delivering Possibilities.</em></p>

<img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Java%20Swing-GUI-123B68?style=for-the-badge"/>
<img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/Maven-Build%20Tool-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>

<br/>

**Universitas Mulawarman | Program Studi Sistem Informasi | 2026**

</div>

---

# 📑 DAFTAR ISI

| No. | Bagian | Deskripsi |
|:---:|---|---|
| 01 | [Nama Program](#-1-nama-program) | Identitas aplikasi |
| 02 | [Deskripsi Singkat](#-2-deskripsi-singkat) | Gambaran umum sistem |
| 03 | [Fitur Utama Program](#-3-fitur-utama-program) | Fitur berdasarkan pengguna |
| 04 | [Tech Stack](#️-4-tech-stack) | Teknologi dan tools pendukung |
| 05 | [Perancangan Sistem](#️-5-perancangan-sistem) | Flowchart, Use Case, dan ERD |
| 06 | [Struktur Folder/Package](#-6-struktur-folderpackage) | Organisasi kode program |
| 07 | [Penerapan 4 Pilar OOP](#-7-penerapan-4-pilar-oop) | Konsep OOP dalam aplikasi |
| 08 | [Cara Penggunaan Program](#️-8-cara-penggunaan-program) | Panduan dan dokumentasi GUI |
| 09 | [Kontributor](#-9-kontributor) | Tim pengembang |

---

# 🚀 1. Nama Program

## RuteConnect

**RuteConnect: Sistem Informasi Terintegrasi Pemesanan Transportasi Daring dan Pengiriman Dokumen Berbasis Digital**

RuteConnect merupakan aplikasi desktop yang mengintegrasikan layanan pemesanan transportasi daring dan pengiriman dokumen dalam satu platform digital.

Nama RuteConnect berasal dari gabungan kata *Rute* dan *Connect*. Nama ini menggambarkan tujuan aplikasi untuk menghubungkan pengguna dengan layanan transportasi dan pengiriman melalui sistem yang praktis dan terintegrasi.

<div align="center">

<img src="https://img.shields.io/badge/ROUTE-Connected%20Services-0B1F3A?style=for-the-badge"/>
<img src="https://img.shields.io/badge/DELIVERY-Smart%20Solution-164B78?style=for-the-badge"/>

</div>

---

# 📖 2. Deskripsi Singkat

RuteConnect adalah aplikasi sistem informasi berbasis desktop yang dikembangkan untuk memberikan kemudahan dalam melakukan pemesanan layanan transportasi daring dan pengiriman dokumen secara digital.

Aplikasi ini mengintegrasikan dua kebutuhan layanan dalam satu platform, sehingga pengguna dapat melakukan perjalanan maupun mengirimkan dokumen melalui sistem yang sama.

RuteConnect memiliki tiga jenis hak akses pengguna, yaitu **Pelanggan, Driver, dan Admin**. Setiap pengguna memiliki akses dan tanggung jawab yang berbeda sesuai dengan perannya di dalam sistem.

Sistem dikembangkan menggunakan bahasa pemrograman Java dengan antarmuka Java Swing serta database MySQL untuk menyimpan dan mengelola data aplikasi.

### 🎯 Tujuan Pengembangan

- Mengintegrasikan layanan transportasi dan pengiriman dokumen dalam satu aplikasi.
- Mempermudah proses pemesanan dan pengelolaan layanan secara digital.
- Menyediakan informasi status pesanan secara terstruktur.
- Membantu pengelolaan data pelanggan, driver, layanan, dan transaksi.
- Menerapkan konsep Object-Oriented Programming dalam pengembangan aplikasi.

### 🗺️ Gambaran Konsep RuteConnect

<div align="center">

<img src="https://raw.githubusercontent.com/Sitinursinta04/PRAKTIKUM_PBO/main/Dokumentasi/second/RuteConnect_%20Digital%20Transport%20and%20Delivery.png" width="90%" alt="Ilustrasi Konsep RuteConnect"/>

<em>Ilustrasi konsep perjalanan dan pengiriman pada RuteConnect.</em>

</div>

---

# ⚙️ 3. Fitur Utama Program

RuteConnect menyediakan fitur yang disesuaikan dengan tiga jenis pengguna. Setiap fitur dirancang untuk mendukung proses layanan dan pengelolaan data dalam aplikasi.

## A. Pelanggan

| Fitur | Deskripsi |
|---|---|
| Registrasi | Membuat akun untuk menggunakan layanan RuteConnect. |
| Login | Masuk ke sistem menggunakan akun terdaftar. |
| Pemesanan Transportasi | Membuat pesanan layanan transportasi. |
| Pengiriman Dokumen | Melakukan pemesanan layanan pengiriman dokumen. |
| Pembatalan Pesanan | Membatalkan pesanan sesuai ketentuan sistem. |
| Status Pesanan | Melihat perkembangan status pesanan. |
| Riwayat Pesanan | Melihat daftar pemesanan sebelumnya. |
| Dompet Digital | Melihat dan melakukan pengisian saldo. |
| Riwayat Transaksi | Melihat catatan transaksi dan top up. |

## B. Driver

| Fitur | Deskripsi |
|---|---|
| Login | Masuk ke sistem sebagai driver. |
| Pesanan Tersedia | Melihat daftar pesanan yang tersedia. |
| Detail Pesanan | Melihat informasi pelanggan dan tujuan pesanan. |
| Menerima Pesanan | Menerima pesanan yang tersedia. |
| Menolak Pesanan | Menolak pesanan sesuai mekanisme sistem. |
| Pembaruan Status | Memperbarui status pengerjaan pesanan. |
| Riwayat Pesanan | Melihat pesanan yang pernah ditangani. |

## C. Admin

| Fitur | Deskripsi |
|---|---|
| Login Admin | Masuk ke halaman pengelolaan sistem. |
| Manajemen Pengguna | Mengelola data pelanggan dan driver. |
| Manajemen Layanan | Mengelola informasi dan tarif layanan. |
| Data Pesanan | Melihat dan memantau seluruh pesanan. |
| Data Transaksi | Melihat informasi transaksi pengguna. |
| Laporan | Mengakses informasi laporan sistem. |

---


# 🛠️ 4. Tech Stack

RuteConnect dikembangkan dengan memanfaatkan berbagai teknologi dan tools pendukung untuk membangun aplikasi desktop, mengelola database, serta membantu proses pengembangan sistem.

## A. Core Technologies

Teknologi utama yang digunakan dalam pengembangan aplikasi RuteConnect.

<div align="center">

<img src="https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Java_Swing-123B68?style=for-the-badge"/>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/JDBC-0B1F3A?style=for-the-badge"/>
<img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>

</div>

| Teknologi | Kegunaan |
|---|---|
| Java 21 | Bahasa pemrograman utama aplikasi. |
| Java Swing | Membangun antarmuka aplikasi desktop. |
| MySQL | Menyimpan dan mengelola data aplikasi. |
| JDBC | Menghubungkan aplikasi Java dengan database. |
| Maven | Mengelola dependency dan proses build proyek. |
| NetBeans IDE | Lingkungan pengembangan aplikasi. |

## B. Development Environment & AI Tools

### CORE TECHNOLOGIES

<div align="center">

<img src="https://skillicons.dev/icons?i=java,mysql,maven&theme=light" alt="Java, MySQL, Maven" height="90"/>

<br/>

<strong>Java 21</strong> &nbsp; • &nbsp; <strong>MySQL</strong> &nbsp; • &nbsp; <strong>Maven</strong>

</div>

### DEVELOPMENT ENVIRONMENT

<div align="center">

<img src="https://cdn.simpleicons.org/laragon/0E8A70" height="58" alt="Laragon"/>
&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://cdn.simpleicons.org/apachenetbeanside/1B6AC6" height="58" alt="Apache NetBeans"/>

<br/>

<strong>Laragon</strong> &nbsp; • &nbsp; <strong>Apache NetBeans</strong>

</div>

### AI DEVELOPMENT ASSISTANTS

<div align="center">

<img src="https://cdn.simpleicons.org/anthropic/D97757" height="58" alt="Claude"/>
&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://commons.wikimedia.org/wiki/Special:FilePath/ChatGPT-Logo.svg" height="58" alt="ChatGPT"/>

<br/>

<strong>Claude</strong> &nbsp; • &nbsp; <strong>ChatGPT</strong>

</div>

## C. Service Platform References

<div align="center">

<img src="https://cdn.simpleicons.org/gojek/00AA13" height="65" alt="Gojek"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://cdn.simpleicons.org/grab/00B14F" height="65" alt="Grab"/>

<br/>

<strong>GOJEK</strong> &nbsp;&nbsp; • &nbsp;&nbsp; <strong>GRAB</strong>

<br/>

<sub>Transportation & Delivery Platform References</sub>

</div>

> **Catatan:** Gojek dan Grab merupakan referensi konsep layanan, bukan integrasi API. Claude dan ChatGPT merupakan tools pendukung pengembangan, bukan komponen yang dijalankan di dalam aplikasi.

## D. Referensi Pengembangan README

Beberapa sumber yang dapat digunakan untuk membantu pengembangan tampilan dan dokumentasi README:

- [Readme.so](https://readme.so/) – Membantu menyusun struktur README.
- [Shields.io](https://shields.io/) – Membuat badge teknologi dan informasi proyek.
- [Simple Icons](https://simpleicons.org/) – Menyediakan ikon berbagai brand dan teknologi.
- [Capsule Render](https://capsule-render.vercel.app/) – Membuat banner GitHub README.
- [Awesome README](https://github.com/matiassingers/awesome-readme) – Referensi README proyek yang menarik dan informatif.

---

# 🏗️ 5. Perancangan Sistem

Perancangan sistem dilakukan sebagai dasar dalam proses pengembangan RuteConnect. Tahapan ini bertujuan untuk menggambarkan alur kerja, interaksi pengguna, serta hubungan antar-data yang digunakan dalam aplikasi.

## A. Flowchart

Flowchart menggambarkan alur proses aplikasi mulai dari pengguna membuka aplikasi, melakukan login, memilih layanan, hingga proses pemesanan dan transaksi.

<div align="center">

<img src="docs/flowchart/flowchart-ruteconnect.png" width="90%" alt="Flowchart RuteConnect"/>

<em>Gambar 1. Flowchart Sistem RuteConnect</em>

</div>

## B. Use Case Diagram

Use Case Diagram menggambarkan hubungan antara aktor dengan fitur yang tersedia dalam sistem.

Aktor utama dalam aplikasi RuteConnect terdiri atas:

- **Pelanggan:** melakukan pemesanan transportasi dan pengiriman dokumen.
- **Driver:** menerima serta mengelola pesanan.
- **Admin:** mengelola data pengguna, layanan, pesanan, dan transaksi.

<div align="center">

<img src="docs/diagram/use-case-ruteconnect.png" width="90%" alt="Use Case RuteConnect"/>

<em>Gambar 2. Use Case Diagram RuteConnect</em>

</div>

## C. Entity Relationship Diagram (ERD)

Entity Relationship Diagram (ERD) digunakan untuk menggambarkan struktur database serta hubungan antar-entitas yang mendukung proses pengelolaan data aplikasi.

Entitas utama dalam perancangan database RuteConnect meliputi:

1. User
2. Pelanggan
3. Driver
4. Admin
5. Layanan
6. Pesanan
7. Transportasi
8. Dokumen
9. Transaksi
10. Top_Up

<div align="center">

<img src="docs/diagram/erd-ruteconnect.png" width="100%" alt="ERD RuteConnect"/>

<em>Gambar 3. Entity Relationship Diagram RuteConnect</em>

</div>

---

# 📂 6. Struktur Folder/Package

Struktur folder dan package dibuat untuk memisahkan komponen aplikasi berdasarkan tanggung jawabnya. Pemisahan ini bertujuan agar kode program lebih terorganisasi, mudah dipahami, dan lebih mudah dikembangkan.

### Struktur Proyek

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
│   ├── images/
│   ├── flowchart/
│   ├── diagram/
│   └── screenshots/
│
├── pom.xml
└── README.md
```

### Penjelasan Package

| Package | Fungsi |
|---|---|
| `config` | Mengatur konfigurasi koneksi database. |
| `model` | Menyimpan class yang merepresentasikan objek dan data sistem. |
| `dao` | Menangani operasi pengambilan, penyimpanan, perubahan, dan penghapusan data. |
| `service` | Menangani logika proses bisnis aplikasi. |
| `view` | Menampilkan antarmuka pengguna menggunakan Java Swing. |
| `resources` | Menyimpan aset pendukung seperti gambar dan ikon. |

> Struktur di atas merupakan contoh susunan package. Sesuaikan nama folder, package, dan file dengan struktur proyek RuteConnect yang sebenarnya.

---

# 🧩 7. Penerapan 4 Pilar OOP

Pengembangan RuteConnect menerapkan konsep Object-Oriented Programming (OOP) yang terdiri atas empat pilar utama, yaitu Encapsulation, Inheritance, Polymorphism, dan Abstraction.

## 1. Encapsulation (Enkapsulasi)

Encapsulation merupakan konsep membungkus data dan metode dalam sebuah class serta membatasi akses langsung terhadap atribut tertentu.

Dalam RuteConnect, konsep ini dapat diterapkan pada class seperti `User`, `Pelanggan`, `Driver`, dan `Pesanan` dengan menggunakan access modifier `private`.

**Contoh penerapan:**

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

**Penjelasan:**

Atribut dibuat `private` agar tidak dapat diakses secara langsung dari luar class. Pengaksesan dan perubahan data dilakukan melalui method getter dan setter.

## 2. Inheritance (Pewarisan)

Inheritance merupakan konsep pewarisan atribut dan method dari satu class kepada class lainnya.

Dalam RuteConnect, class `Pelanggan`, `Driver`, dan `Admin` dapat mewarisi atribut serta method umum dari class induk `User`.

**Contoh penerapan:**

```java
public class Pelanggan extends User {
    private String alamat;

    public String getAlamat() {
        return alamat;
    }
}
```

**Penjelasan:**

Class `Pelanggan` mewarisi atribut dan method yang dimiliki oleh class `User`, sehingga tidak perlu mendefinisikan kembali seluruh atribut umum pengguna.

## 3. Polymorphism (Polimorfisme)

Polymorphism merupakan konsep yang memungkinkan satu method memiliki perilaku berbeda berdasarkan objek yang menggunakannya.

**Contoh penerapan:**

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

**Penjelasan:**

Method `tampilkanDashboard()` memiliki nama yang sama, tetapi menghasilkan perilaku berbeda sesuai dengan jenis objek yang menjalankannya.

## 4. Abstraction (Abstraksi)

Abstraction merupakan konsep menyembunyikan detail implementasi dan hanya menampilkan fungsi utama yang diperlukan.

**Contoh penerapan:**

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

**Penjelasan:**

Class `Layanan` menjadi class abstrak yang mendefinisikan method `prosesPesanan()`. Class turunannya kemudian memberikan implementasi sesuai dengan jenis layanan.

> Contoh kode di atas merupakan ilustrasi penerapan konsep OOP. Sesuaikan dengan implementasi sebenarnya pada source code RuteConnect agar penjelasan sesuai dengan program yang dikembangkan.

---

# 🖥️ 8. Cara Penggunaan Program

Bagian ini menjelaskan tahapan penggunaan aplikasi RuteConnect berdasarkan tampilan antarmuka yang tersedia.

## A. Tampilan Awal Aplikasi

Halaman awal merupakan tampilan yang muncul ketika aplikasi RuteConnect pertama kali dijalankan. Halaman ini menjadi tampilan pembuka sebelum pengguna mengakses fitur yang tersedia.

<div align="center">

<img src="docs/screenshots/01-halaman-awal.png" width="85%" alt="Halaman Awal RuteConnect"/>

<em>Gambar 4. Halaman Awal RuteConnect</em>

</div>

## B. Login Pengguna

Pengguna memasukkan email dan password yang telah terdaftar untuk mengakses fitur sesuai dengan hak akses masing-masing.

<div align="center">

<img src="docs/screenshots/02-halaman-login.png" width="85%" alt="Halaman Login"/>

<em>Gambar 5. Halaman Login</em>

</div>

## C. Dashboard Pelanggan

Pelanggan dapat mengakses layanan transportasi dan pengiriman dokumen, melihat informasi akun, serta memantau aktivitas pemesanan.

<div align="center">

<img src="docs/screenshots/03-dashboard-pelanggan.png" width="85%" alt="Dashboard Pelanggan"/>

<em>Gambar 6. Dashboard Pelanggan</em>

</div>

## D. Halaman Pemesanan

Pelanggan memilih jenis layanan dan mengisi informasi yang diperlukan untuk membuat pesanan.

<div align="center">

<img src="docs/screenshots/04-halaman-pemesanan.png" width="85%" alt="Halaman Pemesanan"/>

<em>Gambar 7. Halaman Pemesanan</em>

</div>

## E. Dashboard Driver

Driver dapat melihat pesanan yang tersedia, memeriksa detail pesanan, serta menerima dan memperbarui status pengerjaan.

<div align="center">

<img src="docs/screenshots/05-dashboard-driver.png" width="85%" alt="Dashboard Driver"/>

<em>Gambar 8. Dashboard Driver</em>

</div>

## F. Dashboard Admin

Admin memiliki akses untuk mengelola data pengguna, layanan, pesanan, serta informasi transaksi dalam sistem.

<div align="center">

<img src="docs/screenshots/06-dashboard-admin.png" width="85%" alt="Dashboard Admin"/>

<em>Gambar 9. Dashboard Admin</em>

</div>

---

# 👥 9. Kontributor

Proyek RuteConnect dikembangkan secara berkelompok sebagai bagian dari penyelesaian tugas mata kuliah **Pemrograman Berorientasi Objek (PBO)**.

<div align="center">

| No. | Nama | NIM | Peran |
|:---:|---|---|---|
| 1 | [Nama Anggota 1] | [NIM] | Project Leader |
| 2 | [Nama Anggota 2] | [NIM] | System Development |
| 3 | [Nama Anggota 3] | [NIM] | System Development |
| 4 | [Nama Anggota 4] | [NIM] | System Development |
| 5 | [Nama Anggota 5] | [NIM] | System Development |

<br/>

**PROGRAM STUDI SISTEM INFORMASI**  
**FAKULTAS TEKNIK**  
**UNIVERSITAS MULAWARMAN**

2026

</div>

---

<div align="center">

<img src="https://img.shields.io/badge/RUTECONNECT-Connecting%20People-0B1F3A?style=for-the-badge"/>

<h2>RUTECONNECT</h2>

<strong>Connecting People, Delivering Possibilities.</strong>

<em>One Platform, Connected Routes, Better Services.</em>

<br/><br/>

<sub>Developed with Java | Sistem Informasi | Universitas Mulawarman © 2026</sub>

</div>

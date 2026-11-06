
<!-- ====================================================== -->
<!--                    RUTECONNECT HEADER                  -->
<!-- ====================================================== -->

<div align="center">

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/Dokumentasi/second/RuteConnect_%20Journey%20and%20Delivery%20Network.png" width="100%" alt="RuteConnect Banner"/>

<br/>

<p>
  <strong>INTEGRATED TRANSPORTATION & DOCUMENT DELIVERY SYSTEM</strong>
</p>

<p><em>Connecting People, Delivering Possibilities.</em></p>

<br/>

<img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Java%20Swing-GUI-123B68?style=for-the-badge"/>
<img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/Maven-Build%20Tool-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>

</div>

---

# 📑 DAFTAR ISI

| No. | Bagian | Deskripsi |
|:---:|---|---|
| 01 | [Nama Program](#-1-nama-program) | Identitas aplikasi |
| 02 | [Kontributor](#-2-kontributor) | Tim pengembang |
| 03 | [Deskripsi Singkat](#-3-deskripsi-singkat) | Gambaran umum sistem |
| 04 | [Fitur Utama Program](#-4-fitur-utama-program) | Fitur berdasarkan pengguna |
| 05 | [Tech Stack](#-5-tech-stack) | Teknologi yang digunakan |
| 06 | [Perancangan Sistem](#-6-perancangan-sistem) | Flowchart, Use Case, dan ERD |
| 07 | [Struktur Folder/Package](#-7-struktur-folderpackage) | Arsitektur program |
| 08 | [Penerapan 4 Pilar OOP](#-8-penerapan-4-pilar-oop) | Konsep OOP dalam aplikasi |
| 09 | [Cara Penggunaan Program](#-9-cara-penggunaan-program) | Panduan penggunaan GUI |

---

# 🚀 1. Nama Program

## RuteConnect

**RuteConnect: Sistem Informasi Terintegrasi Pemesanan Transportasi Daring dan Pengiriman Dokumen Berbasis Digital**

RuteConnect merupakan aplikasi desktop yang mengintegrasikan layanan pemesanan transportasi daring dan pengiriman dokumen dalam satu platform digital.

Nama RuteConnect berasal dari gabungan kata *Rute* dan *Connect*, yang menggambarkan kemudahan menghubungkan pengguna dengan layanan transportasi dan pengiriman melalui sistem yang terintegrasi.

<div align="center">

<img src="https://img.shields.io/badge/ROUTE-Connected%20Services-0B1F3A?style=for-the-badge"/>
<img src="https://img.shields.io/badge/DELIVERY-Smart%20Solution-164B78?style=for-the-badge"/>

</div>

---

# 👥 2. Kontributor

Proyek RuteConnect dikembangkan secara berkelompok sebagai bagian dari penyelesaian tugas mata kuliah Pemrograman Berorientasi Objek (PBO).

| No. | Nama | NIM | Peran |
|:---:|---|---|---|
| 1 | [Nama Anggota 1] | [NIM] | Project Leader |
| 2 | [Nama Anggota 2] | [NIM] | System Development |
| 3 | [Nama Anggota 3] | [NIM] | System Development |
| 4 | [Nama Anggota 4] | [NIM] | System Development |
| 5 | [Nama Anggota 5] | [NIM] | System Development |

<div align="center">

**Program Studi Sistem Informasi**  
**Fakultas Teknik – Universitas Mulawarman**  
**2026**

</div>

---

# 📖 3. Deskripsi Singkat

RuteConnect adalah aplikasi sistem informasi berbasis desktop yang dikembangkan untuk memberikan kemudahan dalam melakukan pemesanan layanan transportasi daring dan pengiriman dokumen secara digital.

Aplikasi ini mengintegrasikan dua kebutuhan layanan dalam satu platform, sehingga pengguna dapat melakukan perjalanan maupun mengirimkan dokumen melalui sistem yang sama.

RuteConnect memiliki tiga jenis hak akses pengguna, yaitu **Pelanggan, Driver, dan Admin**. Setiap pengguna memiliki akses dan tanggung jawab yang berbeda sesuai dengan perannya di dalam sistem.

Sistem ini dikembangkan menggunakan bahasa pemrograman Java dengan antarmuka Java Swing serta database MySQL untuk menyimpan dan mengelola data aplikasi.

### 🎯 Tujuan Pengembangan

- Mengintegrasikan layanan transportasi dan pengiriman dokumen dalam satu aplikasi.
- Mempermudah proses pemesanan dan pengelolaan layanan secara digital.
- Menyediakan informasi status pesanan secara terstruktur.
- Membantu pengelolaan data pelanggan, driver, layanan, dan transaksi.
- Menerapkan konsep Object-Oriented Programming dalam pengembangan aplikasi.

### 🗺️ Gambaran Konsep RuteConnect

<div align="center">

<img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/Dokumentasi/second/RuteConnect_%20Digital%20Transport%20and%20Delivery.png" width="90%" alt="Ilustrasi Konsep RuteConnect"/>

<em>Ilustrasi konsep perjalanan dan pengiriman pada RuteConnect.</em>

</div>

---

# ⚙️ 4. Fitur Utama Program

RuteConnect memiliki fitur yang disesuaikan dengan tiga jenis pengguna.

## A. Pelanggan

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

# 🛠️ 5. Tech Stack

RuteConnect dikembangkan menggunakan beberapa teknologi yang mendukung proses pembuatan antarmuka, pengolahan data, dan pengelolaan database.

| Teknologi | Kegunaan |
|---|---|
| Java 21 | Bahasa pemrograman utama untuk membangun logika aplikasi. |
| Java Swing | Membuat antarmuka aplikasi desktop. |
| MySQL | Menyimpan dan mengelola data aplikasi. |
| JDBC | Menghubungkan aplikasi Java dengan database MySQL. |
| Maven | Mengelola dependency dan proses build proyek. |
| NetBeans IDE | Lingkungan pengembangan aplikasi Java. |

### Teknologi Pendukung

<div align="center">

<img src="https://skillicons.dev/icons?i=java,mysql,maven,netbeans,git,github" alt="Technology Stack"/>

</div>

---

# 🏗️ 6. Perancangan Sistem

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

ERD digunakan untuk menggambarkan struktur database serta hubungan antar-entitas yang mendukung proses pengelolaan data aplikasi.

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

# 📂 7. Struktur Folder/Package

Struktur package dibuat untuk memisahkan komponen aplikasi berdasarkan tanggung jawabnya. Pemisahan ini bertujuan agar kode program lebih terorganisasi, mudah dipahami, dan lebih mudah dikembangkan.

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
│   │   ├── ruteconnect-banner.png
│   │   └── route-illustration.png
│   │
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

> Sesuaikan struktur folder dan nama package dengan struktur proyek yang sebenarnya.

---

# 🧩 8. Penerapan 4 Pilar OOP

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

> Contoh kode di atas merupakan ilustrasi. Sesuaikan dengan implementasi sebenarnya pada source code RuteConnect.

---

# 🖥️ 9. Cara Penggunaan Program

Bagian ini menjelaskan tahapan penggunaan aplikasi RuteConnect berdasarkan tampilan antarmuka yang tersedia.

## A. Tampilan Awal Aplikasi

Halaman awal merupakan tampilan yang muncul ketika aplikasi RuteConnect pertama kali dijalankan.

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

<div align="center">

<h2>RUTECONNECT</h2>

**Connecting People, Delivering Possibilities.**

<em>One Platform, Connected Routes, Better Services.</em>

<br/><br/>

<sub>Developed with Java | Sistem Informasi | Universitas Mulawarman © 2026</sub>

</div>

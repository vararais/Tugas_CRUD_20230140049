package com.example.praktikum2.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "ktp")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Ktp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Sesuai instruksi: Int, Primary Key, Auto Increment

    @Column(name = "nomor_ktp", unique = true, nullable = false)
    private String nomorKtp;

    @Column(name = "nama_lengkap", nullable = false)
    private String namaLengkap;

    @Column(name = "alamat", nullable = false)
    private String alamat;

    @Column(name = "tanggal_lahir", nullable = false)
    private LocalDate tanggalLahir;

    @Column(name = "jenis_kelamin", nullable = false)
    private String jenisKelamin;
}
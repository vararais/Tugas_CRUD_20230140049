package com.example.praktikum2.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class KtpAddRequest {
    @NotBlank(message = "Nomor KTP tidak boleh kosong")
    private String nomorKtp;

    @NotBlank(message = "Nama lengkap tidak boleh kosong")
    private String namaLengkap;

    @NotBlank(message = "Alamat tidak boleh kosong")
    private String alamat;

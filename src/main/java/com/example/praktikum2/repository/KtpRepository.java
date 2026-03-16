package com.example.praktikum2.repository;

import com.example.praktikum2.model.entity.Ktp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface KtpRepository extends JpaRepository<Ktp, Integer> {
    // Custom query method untuk mengecek duplikat nomor KTP
    boolean existsByNomorKtp(String nomorKtp);
    Optional<Ktp> findByNomorKtp(String nomorKtp);
}

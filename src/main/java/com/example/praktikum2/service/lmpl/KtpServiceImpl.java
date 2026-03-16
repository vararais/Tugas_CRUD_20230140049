package com.example.praktikum2.service.lmpl;

import com.example.praktikum2.mapper.KtpMapper;
import com.example.praktikum2.model.dto.KtpAddRequest;
import com.example.praktikum2.model.dto.KtpDto;
import com.example.praktikum2.model.entity.Ktp;
import com.example.praktikum2.repository.KtpRepository;
import com.example.praktikum2.service.KtpService;
import com.example.praktikum2.util.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class KtpServiceImpl implements KtpService {

    @Autowired
    private KtpRepository ktpRepository;

    @Autowired
    private ValidationUtil validationUtil;

    @Override
    public KtpDto addKtp(KtpAddRequest request) {
        validationUtil.validate(request);

        if (ktpRepository.existsByNomorKtp(request.getNomorKtp())) {
            throw new RuntimeException("Gagal: Nomor KTP " + request.getNomorKtp() + " sudah terdaftar!");
        }

        Ktp ktp = Ktp.builder()
                .nomorKtp(request.getNomorKtp())
                .namaLengkap(request.getNamaLengkap())
                .alamat(request.getAlamat())
                .tanggalLahir(request.getTanggalLahir())
                .jenisKelamin(request.getJenisKelamin())
                .build();

        ktpRepository.save(ktp);
        return KtpMapper.MAPPER.toKtpDto(ktp);
    }

    @Override
    public List<KtpDto> getAllKtp() {
        List<Ktp> ktpList = ktpRepository.findAll();
        List<KtpDto> ktpDtos = new ArrayList<>();
        for (Ktp ktp : ktpList) {
            ktpDtos.add(KtpMapper.MAPPER.toKtpDto(ktp));
        }
        return ktpDtos;
    }

    @Override
    public KtpDto getKtpById(Integer id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP dengan ID " + id + " tidak ditemukan!"));
        return KtpMapper.MAPPER.toKtpDto(ktp);
    }

    @Override
    public KtpDto updateKtp(Integer id, KtpAddRequest request) {
        validationUtil.validate(request);

        Ktp existingKtp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP dengan ID " + id + " tidak ditemukan!"));

        Optional<Ktp> checkKtp = ktpRepository.findByNomorKtp(request.getNomorKtp());
        if (checkKtp.isPresent() && !checkKtp.get().getId().equals(id)) {
            throw new RuntimeException("Gagal: Nomor KTP " + request.getNomorKtp() + " sudah dipakai data lain!");
        }

        existingKtp.setNomorKtp(request.getNomorKtp());
        existingKtp.setNamaLengkap(request.getNamaLengkap());
        existingKtp.setAlamat(request.getAlamat());
        existingKtp.setTanggalLahir(request.getTanggalLahir());
        existingKtp.setJenisKelamin(request.getJenisKelamin());

        ktpRepository.save(existingKtp);
        return KtpMapper.MAPPER.toKtpDto(existingKtp);
    }

    @Override
    public void deleteKtp(Integer id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP dengan ID " + id + " tidak ditemukan!"));
        ktpRepository.delete(ktp);
    }
}

package com.example.praktikum2.controller;

import com.example.praktikum2.model.dto.KtpAddRequest;
import com.example.praktikum2.model.dto.KtpDto;
import com.example.praktikum2.service.KtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ktp") // Sesuai instruksi tugas: Endpoint /ktp
public class KtpController {

    @Autowired
    private KtpService ktpService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> addKtp(@RequestBody KtpAddRequest request) {
        KtpDto result = ktpService.addKtp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "status", "success",
                "data", result
        ));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getAllKtp() {
        List<KtpDto> result = ktpService.getAllKtp();
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "status", "success",
                "data", result
        ));
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> getKtpById(@PathVariable("id") Integer id) {
        KtpDto result = ktpService.getKtpById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "status", "success",
                "data", result
        ));
    }

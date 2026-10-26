package com.lattesscore.backend.controller;

import com.lattesscore.backend.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/lattes")
public class LattesController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping("/importar")
    public ResponseEntity<String> importarCurriculo(
            @RequestParam("idLattes") String idLattes,
            @RequestParam("file") MultipartFile file) {
        try {
            
            String xmlContent = new String(file.getBytes());
            
            avaliacaoService.processarCurriculoLattes(idLattes, xmlContent);
            
            return ResponseEntity.ok("Currículo importado e enviado para processamento com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao processar o ficheiro XML: " + e.getMessage());
        }
    }
}
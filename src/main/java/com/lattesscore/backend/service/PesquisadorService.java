package com.lattesscore.backend.service;

import com.lattesscore.backend.entity.Pesquisador;
import com.lattesscore.backend.repository.PesquisadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PesquisadorService {

    @Autowired
    private PesquisadorRepository pesquisadorRepository;

    public Pesquisador salvarPesquisador(Pesquisador pesquisador) {
        return pesquisadorRepository.save(pesquisador);
    }

    public Optional<Pesquisador> buscarPorLattes(String idLattes) {
        return pesquisadorRepository.findByIdLattes(idLattes);
    }
}
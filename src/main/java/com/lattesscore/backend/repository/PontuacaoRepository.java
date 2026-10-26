package com.lattesscore.backend.repository;

import com.lattesscore.backend.entity.Pontuacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontuacaoRepository extends JpaRepository<Pontuacao, Long> {}
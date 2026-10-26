package com.lattesscore.backend.repository;

import com.lattesscore.backend.entity.ItemPontuacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPontuacaoRepository extends JpaRepository<ItemPontuacao, Long> {}
package com.lattesscore.backend.repository;

import com.lattesscore.backend.entity.Producao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProducaoRepository extends JpaRepository<Producao, Long> {
    List<Producao> findByPesquisadorIdPesquisador(Long idPesquisador);
}
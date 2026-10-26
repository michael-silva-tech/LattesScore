package com.lattesscore.backend.repository;

import com.lattesscore.backend.entity.Pesquisador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PesquisadorRepository extends JpaRepository<Pesquisador, Long> {
    Optional<Pesquisador> findByCpf(String cpf);
    Optional<Pesquisador> findByIdLattes(String idLattes);
}
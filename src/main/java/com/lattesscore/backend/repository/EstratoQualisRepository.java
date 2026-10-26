package com.lattesscore.backend.repository;

import com.lattesscore.backend.entity.EstratoQualis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EstratoQualisRepository extends JpaRepository<EstratoQualis, Long> {
    Optional<EstratoQualis> findByIssnAndAreaAvaliacaoAndPeriodoQualis(String issn, String areaAvaliacao, String periodoQualis);
}
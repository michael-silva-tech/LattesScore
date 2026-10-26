package com.lattesscore.backend.repository;

import com.lattesscore.backend.entity.RegraBarema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegraBaremaRepository extends JpaRepository<RegraBarema, Long> {}
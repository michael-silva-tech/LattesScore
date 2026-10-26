package com.lattesscore.backend.repository;

import com.lattesscore.backend.entity.Barema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BaremaRepository extends JpaRepository<Barema, Long> {
    List<Barema> findByAtivoTrue();
}
package com.tsa.spring.payroll.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tsa.spring.payroll.entity.MasterDivisi;

@Repository
public interface MasterDivisiRepo extends JpaRepository<MasterDivisi,Long>{

   @Query("SELECT m.exportType FROM MasterDivisi m WHERE LOWER(m.nama) = LOWER(:nama)")
    Optional<String> findExportTypeByNamaIgnoreCase(@Param("nama") String nama);

}

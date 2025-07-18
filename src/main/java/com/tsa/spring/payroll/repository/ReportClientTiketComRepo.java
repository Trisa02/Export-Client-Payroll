package com.tsa.spring.payroll.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tsa.spring.payroll.entity.ReportClientTiketCOM;

@Repository
public interface ReportClientTiketComRepo extends JpaRepository<ReportClientTiketCOM,Long>,JpaSpecificationExecutor<ReportClientTiketCOM>{

    Optional<ReportClientTiketCOM> findFirstByOrderByNoAsc();

    @Query(value = """
        SELECT periode_start, periode_end
        FROM report_client_tiketcom
        WHERE bulan = :bulan AND tahun = :tahun 
        LIMIT 1
        """, nativeQuery = true)
        List<Object[]> findWorkingPeriode(@Param("bulan") String monthpayroll, @Param("tahun") String yearpayroll);
} 

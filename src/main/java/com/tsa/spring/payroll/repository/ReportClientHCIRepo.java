package com.tsa.spring.payroll.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tsa.spring.payroll.entity.ReportClientHCI;

public interface ReportClientHCIRepo extends JpaRepository<ReportClientHCI,Long>,JpaSpecificationExecutor<ReportClientHCI>{

    Optional<ReportClientHCI> findFirstByOrderByNoAsc();

    @Query(value = """
        SELECT periode_start, periode_end
        FROM report_client_hci
        WHERE bulan = :bulan AND tahun = :tahun 
        LIMIT 1
        """, nativeQuery = true)
        List<Object[]> findWorkingPeriode(@Param("bulan") String monthpayroll, @Param("tahun") String yearpayroll);
}

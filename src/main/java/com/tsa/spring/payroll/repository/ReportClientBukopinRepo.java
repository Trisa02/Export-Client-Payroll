package com.tsa.spring.payroll.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.tsa.spring.payroll.entity.ReportClientBukopin;

@Repository
public interface ReportClientBukopinRepo extends JpaRepository<ReportClientBukopin,Long>,JpaSpecificationExecutor<ReportClientBukopin>{

    Optional<ReportClientBukopin> findFirstByOrderByNoAsc();
    
} 
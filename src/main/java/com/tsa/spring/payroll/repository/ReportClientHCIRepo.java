package com.tsa.spring.payroll.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tsa.spring.payroll.entity.ReportClientHCI;

public interface ReportClientHCIRepo extends JpaRepository<ReportClientHCI,Long>,JpaSpecificationExecutor<ReportClientHCI>{

    Optional<ReportClientHCI> findFirstByOrderByNoAsc();
}

package com.tsa.spring.payroll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.tsa.spring.payroll.entity.ReportClientAFI;

@Repository
public interface ReportClientAFIRepo extends JpaRepository<ReportClientAFI,Long>,JpaSpecificationExecutor<ReportClientAFI>{

   List<ReportClientAFI> findByDivisionContainingIgnoreCase(String division);
}

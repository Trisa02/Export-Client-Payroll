package com.tsa.spring.payroll.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.tsa.spring.payroll.entity.ReportClientAdiraFinance;

@Repository
public interface ReportClientAdiraFinanceRepo extends JpaRepository<ReportClientAdiraFinance,Long>,JpaSpecificationExecutor<ReportClientAdiraFinance>{

    Optional<ReportClientAdiraFinance> findFirstByOrderByNoAsc();
}

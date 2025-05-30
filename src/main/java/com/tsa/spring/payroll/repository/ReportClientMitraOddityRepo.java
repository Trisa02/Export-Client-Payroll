package com.tsa.spring.payroll.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tsa.spring.payroll.entity.ReportClientMitraOddity;

public interface ReportClientMitraOddityRepo extends JpaRepository<ReportClientMitraOddity,Long>,JpaSpecificationExecutor<com.tsa.spring.payroll.entity.ReportClientMitraOddity>{

    Optional<ReportClientMitraOddity> findFirstByOrderByNoAsc();
}

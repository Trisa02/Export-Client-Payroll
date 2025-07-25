package com.tsa.spring.payroll.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.tsa.spring.payroll.entity.ReportClientTigerSnus;

@Repository
public interface ReportClientTigerSnusRepo extends JpaRepository<ReportClientTigerSnus,Long>,JpaSpecificationExecutor<ReportClientTigerSnus>{

    Optional<ReportClientTigerSnus> findFirstByOrderByNoAsc();
}

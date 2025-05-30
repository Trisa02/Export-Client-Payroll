package com.tsa.spring.payroll.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.tsa.spring.payroll.entity.ReportClientBenihBerkah;


@Repository
public interface ReportClientBenihBerkahRepo extends JpaRepository<ReportClientBenihBerkah,Long>,JpaSpecificationExecutor<ReportClientBenihBerkah>{

    Optional<ReportClientBenihBerkah> findFirstByOrderByNoAsc();
   
}

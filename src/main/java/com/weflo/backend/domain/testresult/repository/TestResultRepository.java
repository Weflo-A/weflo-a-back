package com.weflo.backend.domain.testresult.repository;

import com.weflo.backend.domain.testresult.domain.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    List<TestResult> findAllByDroneId(Long droneId);
}

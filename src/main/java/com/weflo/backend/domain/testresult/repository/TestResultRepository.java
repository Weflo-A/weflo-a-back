package com.weflo.backend.domain.testresult.repository;

import com.weflo.backend.domain.testresult.domain.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    List<TestResult> findAllByDroneId(Long droneId);
    @Query("SELECT tr FROM TestResult tr WHERE tr.drone.id = :droneId " +
            "AND YEAR(tr.createDate) = :year AND MONTH(tr.createDate) = :month AND DAY(tr.createDate) = :day")
    Optional<TestResult> findByDroneIdAndCreateDateYearAndCreateDateMonthAndCreateDateDay(
            @Param("droneId") Long droneId,
            @Param("year") int year,
            @Param("month") int month, @Param("day") int day
    );
    //아니 이거 왜안됨???? 개빡치넹
    Optional<TestResult> findByDroneIdAndCreateDate(Long droneId, LocalDateTime createDate);
    TestResult findFirstByDroneIdOrderByCreateDateDesc(Long droneId);

    @Query("SELECT tr FROM TestResult tr WHERE tr.createDate <= :dateTime ORDER BY tr.createDate DESC LIMIT 1")
    TestResult findClosestPreviousByCreateDate(@Param("dateTime") LocalDateTime dateTime);
    @Query("SELECT tr FROM TestResult tr WHERE tr.drone.id = :droneId " +
            "AND YEAR(tr.createDate) = :year AND MONTH(tr.createDate) = :month")
    List<TestResult> findByDroneIdAndCreateDateYearAndCreateDateMonth(
            @Param("droneId") Long droneId,
            @Param("year") int year,
            @Param("month") int month
    );
//    List<TestResult> findByDroneIdAndCreateDateYearAndCreateDateMonth(Long droneId, int year, int month);


}

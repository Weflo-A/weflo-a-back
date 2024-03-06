package com.weflo.backend.domain.cost.repository;

import com.weflo.backend.domain.cost.domain.DroneGroupMonthCost;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneGroupMonthCostRepository extends JpaRepository<DroneGroupMonthCost, Long> {
    List<DroneGroupMonthCost> findByYear(Long year);

    List<DroneGroupMonthCost> findByYearAndMonth(Long year, Long month);
}

package com.weflo.backend.domain.cost.service;

import com.mysql.cj.PreparedQuery;
import com.weflo.backend.domain.cost.domain.DroneGroupMonthCost;
import com.weflo.backend.domain.cost.dto.MonthCostResponse;
import com.weflo.backend.domain.cost.repository.DroneGroupMonthCostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CostService {
    private final DroneGroupMonthCostRepository droneGroupMonthCostRepository;

    @Transactional(readOnly = true)
    public void getDroneGroupMonthCosts() {

    }

    public MonthCostResponse getAllCosts(Long year) {
        List<DroneGroupMonthCost> allCosts = droneGroupMonthCostRepository.findByYear(year);
        List<Long> monthCosts = allCosts.stream().map(DroneGroupMonthCost::getMonthCost).toList();

        return MonthCostResponse.builder()
                .monthCosts(monthCosts)
                .build();
    }


}

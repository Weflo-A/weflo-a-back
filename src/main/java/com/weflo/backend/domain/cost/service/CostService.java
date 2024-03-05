package com.weflo.backend.domain.cost.service;

import com.mysql.cj.PreparedQuery;
import com.weflo.backend.domain.cost.domain.DroneGroupMonthCost;
import com.weflo.backend.domain.cost.dto.MonthCostResponse;
import com.weflo.backend.domain.cost.repository.DroneGroupMonthCostRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CostService {
    private final DroneGroupMonthCostRepository droneGroupMonthCostRepository;

    @Transactional(readOnly = true)
    public List<MonthCostResponse> getDroneGroupMonthCosts(Long year, Long month) {
        List<DroneGroupMonthCost> findMonthCosts;

        if (month == null) {
            findMonthCosts = droneGroupMonthCostRepository.findByYear(year);
            return MonthCostResponse.ofList(findMonthCosts);
        }

        findMonthCosts = droneGroupMonthCostRepository.findByYearAndMonth(year, month);
        return MonthCostResponse.ofList(findMonthCosts);
    }
}

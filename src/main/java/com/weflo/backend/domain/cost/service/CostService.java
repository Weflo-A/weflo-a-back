package com.weflo.backend.domain.cost.service;

import com.weflo.backend.domain.cost.domain.DroneGroupMonthCost;
import com.weflo.backend.domain.cost.dto.ComponentCostAvgTimeLine;
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
    public List<ComponentCostAvgTimeLine> getMonthTotalCost(Long year) {
        List<DroneGroupMonthCost> findMonthCosts = droneGroupMonthCostRepository.findByYear(year);
        List<ComponentCostAvgTimeLine> result = new ArrayList<>();

        int sum = 0;
        int maxMonth = findMonthCosts.stream().mapToInt(cost -> cost.getMonth().intValue()).max()
                .orElseThrow(RuntimeException::new);

        for (int i = 1; i <= maxMonth; i++) {
            List<DroneGroupMonthCost> filterList = new ArrayList<>();
            for (DroneGroupMonthCost target : findMonthCosts) {
                if (target.getMonth().intValue() == i) {
                    filterList.add(target);
                }
            }

            int monthCost = filterList.stream().mapToInt(cost -> cost.getMonthCost().intValue()).sum();
            sum += monthCost;
            result.add(ComponentCostAvgTimeLine.of(i, sum, monthCost));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<MonthCostResponse> getMonthCost(Long year, Long month) {
        List<DroneGroupMonthCost> findMonthCosts = droneGroupMonthCostRepository.findByYearAndMonth(year, month);
        return MonthCostResponse.ofList(findMonthCosts);
    }
}

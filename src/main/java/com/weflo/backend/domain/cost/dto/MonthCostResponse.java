package com.weflo.backend.domain.cost.dto;

import com.weflo.backend.domain.cost.domain.DroneGroupMonthCost;
import java.util.List;
import lombok.Builder;

@Builder
public record MonthCostResponse(
        String name,
        String purpose,
        Long droneCount,
        Long monthCost
) {

    public static List<MonthCostResponse> ofList(List<DroneGroupMonthCost> costs) {
        return costs.stream().map(cost -> builder()
                .name(cost.getName())
                .monthCost(cost.getMonthCost())
                .droneCount(cost.getDroneCount())
                .purpose(cost.getPurpose())
                .build()
        ).toList();
    }

}

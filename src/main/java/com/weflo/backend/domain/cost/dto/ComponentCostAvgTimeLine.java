package com.weflo.backend.domain.cost.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ComponentCostAvgTimeLine {
    private int month;
    private int totalAvgCost;
    private int groupAvgCost;
    public static ComponentCostAvgTimeLine of(int month, int totalAvgCost, int groupAvgCost){
        return ComponentCostAvgTimeLine.builder()
                .month(month)
                .totalAvgCost(totalAvgCost)
                .groupAvgCost(groupAvgCost)
                .build();
    }
}

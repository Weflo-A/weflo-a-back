package com.weflo.backend.domain.cost.dto;

import java.util.List;
import lombok.Builder;

@Builder
public record MonthCostResponse(
        List<Long> monthCosts
) {
}

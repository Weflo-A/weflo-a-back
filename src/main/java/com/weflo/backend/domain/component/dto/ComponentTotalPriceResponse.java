package com.weflo.backend.domain.component.dto;

import java.util.List;
import lombok.Builder;

public record ComponentTotalPriceResponse(
        int totalCost,
        List<ComponentResponse> checkedComponentList
) {
}

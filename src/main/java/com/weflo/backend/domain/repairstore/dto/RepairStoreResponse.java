package com.weflo.backend.domain.repairstore.dto;

import java.util.List;
import lombok.Builder;

@Builder
public record RepairStoreResponse(
        String name,
        String image,
        List<String> features,
        Long minPrice,
        Long maxPrice
) {
}

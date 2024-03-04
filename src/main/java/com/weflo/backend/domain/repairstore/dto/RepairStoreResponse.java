package com.weflo.backend.domain.repairstore.dto;

import com.weflo.backend.domain.repairstore.domain.RepairStore;
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

    public static RepairStoreResponse of(RepairStore store, List<String> features) {
        return builder()
                .image(store.getImage())
                .name(store.getName())
                .features(features)
                .minPrice(store.getExpectedMinPrice())
                .maxPrice(store.getExpectedMaxPrice())
                .build();
    }

}

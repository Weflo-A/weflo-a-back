package com.weflo.backend.domain.component.dto;

import com.weflo.backend.domain.component.domain.ComponentType;
import com.weflo.backend.domain.drone.domain.DroneModel;
import java.util.List;
import java.util.Map;
import lombok.Builder;

@Builder
public record ComponentsByModelsResponse(
        DroneModel modelName,
        Map<ComponentType, Long> componentStatus
) {

    public static ComponentsByModelsResponse of(DroneModel modelName, Map<ComponentType, Long> componentStatus) {
        return builder().modelName(modelName)
                .componentStatus(componentStatus)
                .build();
    }
}

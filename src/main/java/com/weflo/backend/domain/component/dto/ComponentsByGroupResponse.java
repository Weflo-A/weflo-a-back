package com.weflo.backend.domain.component.dto;

import com.weflo.backend.domain.component.domain.ComponentType;
import com.weflo.backend.domain.drone.domain.DroneModel;
import java.util.Map;
import lombok.Builder;

@Builder
public record ComponentsByGroupResponse(
        String groupName,
        Map<String, Long> componentStatus
) {

    public static ComponentsByGroupResponse of(String groupName, Map<String, Long> componentStatus) {
        return builder().groupName(groupName)
                .componentStatus(componentStatus)
                .build();
    }
}

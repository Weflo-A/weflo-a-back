package com.weflo.backend.domain.drone.dto.response;

import lombok.Builder;

@Builder
public record DroneModelInfoResponse(
        Long droneId,
        String modelName
) {
}

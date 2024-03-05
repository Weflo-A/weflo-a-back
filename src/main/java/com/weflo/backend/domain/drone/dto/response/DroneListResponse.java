package com.weflo.backend.domain.drone.dto.response;

import com.weflo.backend.domain.drone.domain.Drone;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneListResponse {
    private String name;

    public static DroneListResponse of(Drone drone){
        return DroneListResponse.builder()
                .name(drone.getName())
                .build();
    }
}

package com.weflo.backend.domain.drone.dto.response;

import com.weflo.backend.domain.drone.domain.Drone;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneListResponse {
    private Long id;
    private String name;

    public static DroneListResponse of(Drone drone){
        return DroneListResponse.builder()
                .id(drone.getId())
                .name(drone.getName())
                .build();
    }
}

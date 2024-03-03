package com.weflo.backend.domain.drone.dto.response;

import com.weflo.backend.domain.drone.domain.Drone;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneInfoResponse {
    private String name;
    private String productionYear;
    private String model;
    private String purpose;

    public static DroneInfoResponse of(Drone drone){
        return DroneInfoResponse.builder()
                .name(drone.getName())
                .productionYear(String.valueOf(drone.getProductionYear()))
                .model(drone.getModel())
                .purpose(drone.getPurpose())
                .build();
    }
}

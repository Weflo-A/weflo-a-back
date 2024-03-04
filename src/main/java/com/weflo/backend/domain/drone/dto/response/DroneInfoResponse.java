package com.weflo.backend.domain.drone.dto.response;

import com.weflo.backend.domain.drone.domain.Drone;
import lombok.Builder;
import lombok.Getter;

import static com.weflo.backend.domain.drone.domain.DroneModel.getEnumDroneModelFromStringModel;

@Builder
@Getter
public class DroneInfoResponse {
    private String name;
    private String productionYear;
    private String model;
    private String purpose;
    private int cost;
    private int accident;

    public static DroneInfoResponse of(Drone drone){
        return DroneInfoResponse.builder()
                .name(drone.getName())
                .productionYear(String.valueOf(drone.getProductionYear()))
                .model(String.valueOf(drone.getModel()))
                .purpose(drone.getPurpose())
                .build();
    }
}
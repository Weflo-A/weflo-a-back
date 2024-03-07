package com.weflo.backend.domain.drone.dto.response;

import com.weflo.backend.domain.drone.domain.Drone;
import lombok.Builder;
import lombok.Getter;

import static com.weflo.backend.domain.drone.domain.DroneModel.getEnumDroneModelFromStringModel;

@Builder
@Getter
public class    DroneInfoResponse {
    private String name;
    private int productionYear;
    private String model;
    private String purpose;
    private int cost;
    private int accident;
    private int balance;
    private int totalScore;

    public static DroneInfoResponse of(Drone drone, int totalScore){
        return DroneInfoResponse.builder()
                .name(drone.getName())
                .productionYear(drone.getProductionYear().getYear())
                .model(String.valueOf(drone.getModel()))
                .purpose(drone.getPurpose())
                .totalScore(totalScore)
                .build();
    }
}

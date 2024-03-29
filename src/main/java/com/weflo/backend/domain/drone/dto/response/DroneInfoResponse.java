package com.weflo.backend.domain.drone.dto.response;

import com.weflo.backend.domain.drone.domain.Drone;

import com.weflo.backend.domain.testresult.domain.TestResult;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneInfoResponse {
    private String name;
    private int productionYear;
    private String model;
    private String purpose;
    private int cost;
    private int accident;
    private int balance;
    private int totalScore;
    private int motorAvg;
    private int bladeAvg;
    private int escAvg;

    public static DroneInfoResponse of(Drone drone, int totalScore, int motorAvg, int bladeAvg, int escAvg, TestResult testResult){
        return DroneInfoResponse.builder()
                .name(drone.getName())
                .productionYear(drone.getProductionYear())
                .model(String.valueOf(drone.getModel()))
                .purpose(drone.getPurpose())
                .cost(testResult.getTotalCost())
                .accident(20)
                .balance(totalScore-5)
                .totalScore(totalScore)
                .motorAvg(motorAvg)
                .bladeAvg(bladeAvg)
                .escAvg(escAvg)
                .build();
    }
}

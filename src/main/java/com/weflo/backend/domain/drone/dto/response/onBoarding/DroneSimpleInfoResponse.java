package com.weflo.backend.domain.drone.dto.response.onBoarding;

import com.weflo.backend.domain.drone.domain.Drone;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
public class DroneSimpleInfoResponse {
    Long droneId;
    String name;
    String model;
    String purpose;
    int year;
    int cost;
    String date;
    public static DroneSimpleInfoResponse of(Drone drone, LocalDateTime date){
        return DroneSimpleInfoResponse.builder()
                .droneId(drone.getId())
                .name(drone.getName())
                .model(String.valueOf(drone.getModel()))
                .purpose(drone.getPurpose())
                .year(drone.getProductionYear().getYear())
                .cost(drone.getCost())
                .date(date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .build();
    }
}

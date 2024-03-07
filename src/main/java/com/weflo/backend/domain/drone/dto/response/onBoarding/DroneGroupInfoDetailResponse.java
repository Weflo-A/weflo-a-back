package com.weflo.backend.domain.drone.dto.response.onBoarding;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
public class DroneGroupInfoDetailResponse {
    private int droneCount;
    private int totalFlight;
    private String startDate;
    public static DroneGroupInfoDetailResponse of(int droneCount, int totalFlight, LocalDateTime startDate){
        return DroneGroupInfoDetailResponse.builder()
                .droneCount(droneCount)
                .totalFlight(totalFlight)
                .startDate(startDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .build();
    }
}

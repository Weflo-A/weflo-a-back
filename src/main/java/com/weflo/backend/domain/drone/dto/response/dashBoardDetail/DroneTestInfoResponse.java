package com.weflo.backend.domain.drone.dto.response.dashBoardDetail;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.testresult.domain.TestResult;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneTestInfoResponse {
    private String name;
    private String testDate;
    private String stationId;
    public static DroneTestInfoResponse of(Drone drone, TestResult testResult){
        return DroneTestInfoResponse.builder()
                .name(drone.getName())
                .testDate(String.valueOf(testResult.getCreateDate()))
                .stationId(testResult.getStationId())
                .build();
    }
}

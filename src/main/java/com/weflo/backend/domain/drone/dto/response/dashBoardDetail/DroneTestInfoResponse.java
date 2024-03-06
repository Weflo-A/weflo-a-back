package com.weflo.backend.domain.drone.dto.response.dashBoardDetail;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.testresult.domain.TestResult;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DroneTestInfoResponse {
    private String name;
    private List<String> testDate;
    public static DroneTestInfoResponse of(Drone drone, List<String> testDates){
        return DroneTestInfoResponse.builder()
                .name(drone.getName())
                .testDate(testDates)
                .build();
    }
}

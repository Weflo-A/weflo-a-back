package com.weflo.backend.domain.drone.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class DroneDetailResponse {

    private List<DroneListResponse> droneList;
    private List<TimeLineResponse> timeLine;
    private DroneInfoResponse droneInfo;
    private List<TestListResponse> testList;

    public static DroneDetailResponse of(DroneInfoResponse droneInfo,List<TimeLineResponse> timeLine, List<TestListResponse> testList, List<DroneListResponse> droneList){
        return DroneDetailResponse.builder()
                .droneInfo(droneInfo)
                .timeLine(timeLine)
                .testList(testList)
                .droneList(droneList)
                .build();
    }
}

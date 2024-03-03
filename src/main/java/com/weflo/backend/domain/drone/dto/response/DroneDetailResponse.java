package com.weflo.backend.domain.drone.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class DroneDetailResponse {
    private String groupName;
    private DroneInfoResponse droneInfo;
    private List<TimeLineResponse> timeLine;
    private List<TestListResponse> testList;
//    private BrokenTypeResponse brokenTypeResponse;
    private List<DroneListResponse> droneList;

    public static DroneDetailResponse of(DroneInfoResponse droneInfo,List<TimeLineResponse> timeLine, List<TestListResponse> testList,List<DroneListResponse> droneList ){
        return DroneDetailResponse.builder()
                .droneInfo(droneInfo)
                .timeLine(timeLine)
                .testList(testList)
                .droneList(droneList)
                .build();
    }
}

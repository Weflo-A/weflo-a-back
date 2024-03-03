package com.weflo.backend.domain.drone.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class DroneDetailResponse {
    private DroneInfoResponse droneInfo;
    private List<TimeLineResponse> timeLineResponses;
    private List<TestListResponse> testList;
//    private BrokenTypeResponse brokenTypeResponse;
    private List<GroupListResponse> groupListResponses;

    public static DroneDetailResponse of(){
        return DroneDetailResponse.builder()
                .droneInfo()
    }
}

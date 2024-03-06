package com.weflo.backend.domain.drone.dto.response.onBoarding;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DroneGroupDetailResponse {
    private String groupName;
    private DroneGroupInfoResponse groupInfo;
}

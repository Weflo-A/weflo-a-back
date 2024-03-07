package com.weflo.backend.domain.drone.dto.request;

import lombok.Getter;

@Getter
public class DroneInfoListRequest {
    private Long groupId;
    private String filter;
}

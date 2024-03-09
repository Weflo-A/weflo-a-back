package com.weflo.backend.domain.drone.dto.request;

import lombok.Getter;

@Getter
public class CreateDroneGroupRequest {
    private String groupName;
    private String purpose;
}

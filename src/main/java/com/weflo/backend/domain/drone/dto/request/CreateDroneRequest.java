package com.weflo.backend.domain.drone.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class CreateDroneRequest {
    private String droneName;
    private String model;
    private int year;
    private String purpose;
    private List<Long> groupIds;
}

package com.weflo.backend.domain.drone.domain;

import com.weflo.backend.global.error.exception.InvalidValueException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static com.weflo.backend.global.error.ErrorCode.INVALID_MODEL_TYPE;

@RequiredArgsConstructor
@Getter
public enum DroneModel {
    EAGLE("EAGLE"), MDT_1600("MDT-1600"), SHIFT("SHIFT"),
    VL_2240R("VL-2240R");
    private final String model;

    public static DroneModel getEnumDroneModelFromStringModel(String model) {
        return Arrays.stream(values())
                .filter(droneModel -> droneModel.model.equals(model))
                .findFirst()
                .orElseThrow(() -> new InvalidValueException(INVALID_MODEL_TYPE));
    }
}

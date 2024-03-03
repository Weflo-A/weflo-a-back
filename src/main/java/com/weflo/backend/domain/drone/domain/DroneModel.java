package com.weflo.backend.domain.drone.domain;

import com.weflo.backend.global.error.exception.InvalidValueException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static com.weflo.backend.global.error.ErrorCode.INVALID_MODEL_TYPE;

@RequiredArgsConstructor
@Getter
public enum DroneModel {
    MODEL1("model1"), MODEL2("model2"), MODEL3("model3")
    private final String model;

    public static DroneModel getEnumDroneModelFromStringModel(String model) {
        return Arrays.stream(values())
                .filter(droneModel -> droneModel.model.equals(model))
                .findFirst()
                .orElseThrow(() -> new InvalidValueException(INVALID_MODEL_TYPE));
    }
}

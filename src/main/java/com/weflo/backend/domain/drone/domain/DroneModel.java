package com.weflo.backend.domain.drone.domain;

import com.weflo.backend.global.error.exception.InvalidValueException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static com.weflo.backend.global.error.ErrorCode.INVALID_MODEL_TYPE;

@RequiredArgsConstructor
@Getter
public enum DroneModel {
    MODEL1("DJI Mavic Air 2"), MODEL2("Skydio 2"), MODEL3("Parrot Anafi USA"),
    MODEL4("DJI Inspire 2"), MODEL5("Eagle"),  MODEL6("Yuneec Typhoon H Pro"),
    MODEL7("Autel Robotics"), MODEL8("SG 906 PRO2");
    private final String model;

    public static DroneModel getEnumDroneModelFromStringModel(String model) {
        return Arrays.stream(values())
                .filter(droneModel -> droneModel.model.equals(model))
                .findFirst()
                .orElseThrow(() -> new InvalidValueException(INVALID_MODEL_TYPE));
    }
}

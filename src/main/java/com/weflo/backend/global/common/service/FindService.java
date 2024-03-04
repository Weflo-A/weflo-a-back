package com.weflo.backend.global.common.service;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.repository.DroneRepository;
import com.weflo.backend.domain.testresult.domain.TestResult;
import com.weflo.backend.global.error.ErrorCode;
import com.weflo.backend.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindService {
    private final DroneRepository droneRepository;
    public Drone findDroneById(Long droneId){
        return droneRepository.findById(droneId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.DRONE_NOT_FOUND));
    }
    public int getPoint(TestResult testResult){
        return (getEscPoint(testResult)+getBladePoint(testResult)+getMotorPoint(testResult))/3;
    }
    public int getEscPoint(TestResult testResult){
        return (testResult.getPart1Esc()+testResult.getPart2Esc()+testResult.getPart3Esc()+testResult.getPart4Esc())/4;

    }
    public int getBladePoint(TestResult testResult){
        return (testResult.getPart1Blade()+testResult.getPart2Blade()+testResult.getPart3Blade()+testResult.getPart4Blade())/4;

    }
    public int getMotorPoint(TestResult testResult){
        return (testResult.getPart1Motor()+testResult.getPart2Motor()+testResult.getPart3Motor()+testResult.getPart4Motor())/4;

    }
}

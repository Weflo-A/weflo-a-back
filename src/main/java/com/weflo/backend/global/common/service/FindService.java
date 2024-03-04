package com.weflo.backend.global.common.service;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.repository.DroneRepository;
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
}

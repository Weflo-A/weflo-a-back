package com.weflo.backend.domain.drone.service;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneGroup;
import com.weflo.backend.domain.drone.domain.DroneGroupInfo;
import com.weflo.backend.domain.drone.dto.response.DroneGroupListResponse;
import com.weflo.backend.domain.drone.repository.DroneGroupInfoRepository;
import com.weflo.backend.domain.drone.repository.DroneGroupRepository;
import com.weflo.backend.domain.drone.repository.DroneRepository;
import com.weflo.backend.global.error.ErrorCode;
import com.weflo.backend.global.error.exception.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DroneGroupService {
    private final DroneGroupRepository droneGroupRepository;

    @Transactional(readOnly = true)
    public List<DroneGroupListResponse> getAllDroneGroups() {
        List<DroneGroup> allDroneGroups = droneGroupRepository.findAll();

        List<DroneGroupListResponse> result = new ArrayList<>();
        for (DroneGroup group : allDroneGroups) {
            result.add(DroneGroupListResponse.of(group.getName(), null));
        }

        return result;
    }
}

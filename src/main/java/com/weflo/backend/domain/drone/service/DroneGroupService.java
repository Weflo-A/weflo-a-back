package com.weflo.backend.domain.drone.service;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneGroup;
import com.weflo.backend.domain.drone.domain.DroneGroupInfo;
import com.weflo.backend.domain.drone.dto.response.DroneGroupListResponse;
import com.weflo.backend.domain.drone.dto.response.DroneListResponse;
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
    private final DroneGroupInfoRepository droneGroupInfoRepository;
    private final DroneGroupRepository droneGroupRepository;

    @Transactional(readOnly = true)
    public DroneGroupListResponse getDronesByDroneGroup(Long droneGroupId) {
        List<Drone> findDrones = droneGroupInfoRepository.findAllDroneByDroneGroupId(droneGroupId);
        DroneGroup findDroneGroup = droneGroupRepository.findById(droneGroupId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));

        List<DroneListResponse> droneListResponses = findDrones.stream().map(DroneListResponse::of).toList();

        return DroneGroupListResponse.of(findDroneGroup.getName(), droneListResponses);
    }
}

package com.weflo.backend.domain.component.service;

import com.weflo.backend.domain.component.domain.Component;
import com.weflo.backend.domain.component.domain.ComponentType;
import com.weflo.backend.domain.component.domain.Part;
import com.weflo.backend.domain.component.dto.ComponentResponse;
import com.weflo.backend.domain.component.repository.ComponentRepository;
import com.weflo.backend.domain.component.repository.DroneComponentRepository;
import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneComponent;
import com.weflo.backend.domain.drone.domain.DroneModel;
import com.weflo.backend.domain.drone.repository.DroneRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ComponentService {
    private final DroneComponentRepository droneComponentRepository;

    public List<ComponentResponse> getDroneComponentsByPointUp(Long droneId, Long point) {
        List<DroneComponent> findDroneComponents = droneComponentRepository.findByDroneIdAndPointGreaterThanEqual(
                droneId, point);

        return ComponentResponse.ofList(findDroneComponents);
    }

    public List<ComponentResponse> getDroneComponentsByPointDown(Long droneId, Long point) {
        List<DroneComponent> findDroneComponents = droneComponentRepository.findByDroneIdAndPointLessThanEqual(
                droneId, point);

        return ComponentResponse.ofList(findDroneComponents);
    }
}

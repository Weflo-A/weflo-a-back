package com.weflo.backend.domain.component.service;

import com.weflo.backend.domain.component.domain.Component;
import com.weflo.backend.domain.component.domain.ComponentType;
import com.weflo.backend.domain.component.dto.ComponentResponse;
import com.weflo.backend.domain.component.dto.ComponentTotalPriceResponse;
import com.weflo.backend.domain.component.dto.ComponentsByGroupResponse;
import com.weflo.backend.domain.component.dto.ComponentsByModelsResponse;
import com.weflo.backend.domain.component.dto.DroneComponentResponse;
import com.weflo.backend.domain.component.repository.ComponentRepository;
import com.weflo.backend.domain.component.repository.DroneComponentRepository;
import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneComponent;
import com.weflo.backend.domain.drone.domain.DroneGroup;
import com.weflo.backend.domain.drone.domain.DroneModel;
import com.weflo.backend.domain.drone.dto.response.DroneGroupListResponse;
import com.weflo.backend.domain.drone.dto.response.DroneListResponse;
import com.weflo.backend.domain.drone.repository.DroneGroupRepository;
import com.weflo.backend.domain.drone.repository.DroneRepository;
import com.weflo.backend.domain.drone.service.DroneGroupService;
import com.weflo.backend.global.error.ErrorCode;
import com.weflo.backend.global.error.exception.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ComponentService {
    private final ComponentRepository componentRepository;
    private final DroneComponentRepository droneComponentRepository;
    private final DroneRepository droneRepository;
    private final DroneGroupRepository droneGroupRepository;
    private final DroneGroupService droneGroupService;

    public List<DroneComponentResponse> getDroneComponentsByPointUp(Long droneId, Long point) {
        List<DroneComponent> findDroneComponents = droneComponentRepository.findByDroneIdAndPointGreaterThanEqual(
                droneId, point);

        return DroneComponentResponse.ofList(findDroneComponents);
    }

    public List<ComponentResponse> getComponents() {
        List<Component> allComponents = componentRepository.findAll();

        return ComponentResponse.ofList(allComponents);
    }

    public List<DroneComponentResponse> getDroneComponentsByPointDown(Long droneId, Long point) {
        List<DroneComponent> findDroneComponents = droneComponentRepository.findByDroneIdAndPointLessThanEqual(
                droneId, point);

        return DroneComponentResponse.ofList(findDroneComponents);
    }

    public List<ComponentsByModelsResponse> getDroneComponentsByModels(Long point) {
        List<Drone> allDrones = droneRepository.findAll();
        List<ComponentsByModelsResponse> result = new ArrayList<>();

        while (!allDrones.isEmpty()) {
            Drone firstDrone = allDrones.get(0);
            List<Drone> filteredDrones = allDrones.stream().filter(drone -> drone.getModel().equals(firstDrone.getModel()))
                    .toList();

            Map<String, Long> componentStatus = generateComponentStatus(point, filteredDrones);

            ComponentsByModelsResponse response = ComponentsByModelsResponse.of(firstDrone.getModel(),
                    componentStatus);

            result.add(response);

            allDrones.stream()
                    .filter(drone -> drone.getModel().equals(firstDrone.getModel()))
                    .toList()
                    .forEach(allDrones::remove);
        }

        return result;
    }

    public List<ComponentsByGroupResponse> getDroneComponentsByGroup(Long point) {
        List<DroneGroup> allDroneGroups = droneGroupRepository.findAll();

        List<ComponentsByGroupResponse> result = new ArrayList<>();

        for (DroneGroup droneGroup : allDroneGroups) {
            DroneGroupListResponse dronesByDroneGroup = droneGroupService.getDronesByDroneGroup(droneGroup.getId());
            List<DroneListResponse> droneList = dronesByDroneGroup.getDroneList();

            List<Drone> drones = droneList.stream()
                    .map(drone -> droneRepository.findById(drone.getId()).orElseThrow(() ->
                                    new EntityNotFoundException(ErrorCode.DRONE_NOT_FOUND)))
                    .toList();

            Map<String, Long> componentStatus = generateComponentStatus(point, drones);
            ComponentsByGroupResponse response = ComponentsByGroupResponse.of(droneGroup.getName(), componentStatus);
            result.add(response);
        }

        return result;
    }

    private Map<String, Long> generateComponentStatus(Long point, List<Drone> filteredDrones) {
        Map<String, Long> componentStatus = new HashMap<>();

        for (Drone drone : filteredDrones) {
            List<DroneComponentResponse> findComponents = getDroneComponentsByPointDown(drone.getId(),
                    point);

            for (DroneComponentResponse findComponent : findComponents) {
                if (!componentStatus.containsKey(findComponent.getType())) {
                    componentStatus.put(findComponent.getType(), 1L);
                } else {
                    componentStatus.put(findComponent.getType(), componentStatus.get(findComponent.getType()) + 1L);
                }
            }
        }
        return componentStatus;
    }

    public List<DroneComponentResponse> getDroneComponents() {
        List<DroneComponent> findDroneComponents = droneComponentRepository.findAll();
        return DroneComponentResponse.ofList(findDroneComponents);
    }

    @Transactional(readOnly = true)
    public ComponentTotalPriceResponse getComponentsPriceByNames(String[] names) {
        int totalPrice = 0;
        List<ComponentResponse> componentResponses = new ArrayList<>();

        for (String name : names) {
            Component findComponent = componentRepository.findByName(name);
            totalPrice += findComponent.getPrice();
            componentResponses.add(ComponentResponse.of(findComponent));
        }

        return new ComponentTotalPriceResponse(totalPrice, componentResponses);
    }
}

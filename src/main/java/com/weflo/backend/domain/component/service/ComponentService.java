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
import com.weflo.backend.global.error.ErrorCode;
import com.weflo.backend.global.error.exception.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ComponentService {
    private final ComponentRepository componentRepository;
    private final DroneRepository droneRepository;
    private final DroneComponentRepository droneComponentRepository;

    public List<ComponentResponse> getDroneComponentsByPoint(Long droneId, Long point) {
        List<DroneComponent> findDroneComponents = droneComponentRepository.findByDroneIdAndPointGreaterThanEqual(
                droneId, point);

        List<Component> components = findDroneComponents.stream().map(DroneComponent::getComponent).toList();

        return ComponentResponse.ofList(components);
    }

    @Transactional
    public void setData() {
        Component componentA = Component.builder()
                .name("부품 A")
                .part(Part.PART1)
                .price(1000)
                .description("부품 A 정의")
                .type(ComponentType.ESC)
                .image("부품 A 이미지 경로")
                .build();

        Component componentB = Component.builder()
                .name("부품 A")
                .part(Part.PART2)
                .price(2000)
                .description("부품 B 정의")
                .type(ComponentType.BLADE)
                .image("부품 B 이미지 경로")
                .build();

        Component componentC = Component.builder()
                .name("부품 C")
                .part(Part.PART3)
                .price(3000)
                .description("부품 C 정의")
                .type(ComponentType.MOTOR)
                .image("부품 C 이미지 경로")
                .build();

        Component componentD = Component.builder()
                .name("부품 D")
                .part(Part.PART1)
                .price(4000)
                .description("부품 D 정의")
                .type(ComponentType.BLADE)
                .image("부품 D 이미지 경로")
                .build();

        componentRepository.save(componentA);
        componentRepository.save(componentB);
        componentRepository.save(componentC);
        componentRepository.save(componentD);

        Drone droneA = Drone.builder()
                .name("드론 A")
                .cost(10000)
                .model(DroneModel.MODEL1)
                .purpose("비행 목적")
                .flightCount(200)
                .productionYear(LocalDateTime.of(2000, 4, 4, 6, 30))
                .build();

        Drone droneB = Drone.builder()
                .name("드론 B")
                .cost(10000)
                .model(DroneModel.MODEL2)
                .purpose("비행 목적")
                .flightCount(300)
                .productionYear(LocalDateTime.of(2001, 4, 4, 6, 30))
                .build();

        droneRepository.save(droneA);
        droneRepository.save(droneB);

        DroneComponent droneComponentA = DroneComponent.builder()
                .component(componentA)
                .brokenCount(10)
                .type(componentA.getType())
                .drone(droneA)
                .part(Part.PART1)
                .point(40L)
                .build();

        DroneComponent droneComponentB = DroneComponent.builder()
                .component(componentB)
                .brokenCount(20)
                .type(componentB.getType())
                .drone(droneA)
                .part(Part.PART2)
                .point(70L)
                .build();

        DroneComponent droneComponentC = DroneComponent.builder()
                .component(componentC)
                .brokenCount(30)
                .type(componentC.getType())
                .drone(droneA)
                .part(Part.PART3)
                .point(30L)
                .build();

        DroneComponent droneComponentD = DroneComponent.builder()
                .component(componentD)
                .brokenCount(40)
                .type(componentD.getType())
                .drone(droneA)
                .part(Part.PART4)
                .point(60L)
                .build();

        droneComponentRepository.save(droneComponentA);
        droneComponentRepository.save(droneComponentB);
        droneComponentRepository.save(droneComponentC);
        droneComponentRepository.save(droneComponentD);
    }
}

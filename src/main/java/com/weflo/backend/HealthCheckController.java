package com.weflo.backend;

import com.weflo.backend.domain.component.domain.Component;
import com.weflo.backend.domain.component.domain.ComponentType;
import com.weflo.backend.domain.component.domain.Part;
import com.weflo.backend.domain.component.repository.ComponentRepository;
import com.weflo.backend.domain.component.repository.DroneComponentRepository;
import com.weflo.backend.domain.cost.domain.DroneGroupMonthCost;
import com.weflo.backend.domain.cost.repository.DroneGroupMonthCostRepository;
import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneComponent;
import com.weflo.backend.domain.drone.domain.DroneGroup;
import com.weflo.backend.domain.drone.domain.DroneGroupInfo;
import com.weflo.backend.domain.drone.domain.DroneModel;
import com.weflo.backend.domain.drone.repository.DroneGroupInfoRepository;
import com.weflo.backend.domain.drone.repository.DroneGroupRepository;
import com.weflo.backend.domain.drone.repository.DroneRepository;
import com.weflo.backend.domain.repairstore.domain.RepairStore;
import com.weflo.backend.domain.repairstore.repository.RepairStoreRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "상태 체크 API 모음", description = "상태 체크 API")
@RequestMapping("/api")
@RequiredArgsConstructor
public class HealthCheckController {

    private final RepairStoreRepository repairStoreRepository;
    private final ComponentRepository componentRepository;
    private final DroneRepository droneRepository;
    private final DroneComponentRepository droneComponentRepository;
    private final DroneGroupMonthCostRepository droneGroupMonthCostRepository;
    private final DroneGroupRepository droneGroupRepository;
    private final DroneGroupInfoRepository droneGroupInfoRepository;
    @Operation(
            summary = "데이터 세팅",
            description = "서버 상태를 체크합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "서버가 정상 작동 중입니다."
    )
    @GetMapping("/set-data")
    @Transactional
    public String setting() {
        setData();
        return "set data success";
    }

    @Transactional
    public void setData() {
        RepairStore store1 = RepairStore.builder()
                .image("업체 이미지 URL")
                .expectedMaxPrice(10000L)
                .expectedMinPrice(1000L)
                .name("업체 A")
                .canUpgrade(true)
                .hasModels("모델1 모델2 모델3")
                .hasTypes("")
                .build();

        RepairStore store2 = RepairStore.builder()
                .image("업체 이미지 URL")
                .expectedMaxPrice(98000L)
                .expectedMinPrice(1600L)
                .name("업체 B")
                .canUpgrade(true)
                .hasModels("모델3")
                .hasTypes("Blade")
                .build();

        RepairStore store3 = RepairStore.builder()
                .image("업체 이미지 URL")
                .expectedMaxPrice(60000L)
                .expectedMinPrice(1400L)
                .name("업체 C")
                .canUpgrade(false)
                .hasModels("모델2 모델3")
                .hasTypes("ESC Blade")
                .build();

        RepairStore store4 = RepairStore.builder()
                .image("업체 이미지 URL")
                .expectedMaxPrice(20000L)
                .expectedMinPrice(500L)
                .name("업체 D")
                .canUpgrade(true)
                .hasModels("모델1")
                .hasTypes("ESC Blade")
                .build();

        RepairStore store5 = RepairStore.builder()
                .image("업체 이미지 URL")
                .expectedMaxPrice(3000L)
                .expectedMinPrice(1000L)
                .name("업체 E")
                .canUpgrade(false)
                .hasModels("모델2")
                .hasTypes("ESC Blade Motor")
                .build();

        repairStoreRepository.save(store1);
        repairStoreRepository.save(store2);
        repairStoreRepository.save(store3);
        repairStoreRepository.save(store4);
        repairStoreRepository.save(store5);

        Component componentA = Component.builder()
                .name("부품 A")
                .part(Part.PART1)
                .price(1000)
                .description("부품 A 정의")
                .type(ComponentType.ESC)
                .star(4.5)
                .image("부품 A 이미지 경로")
                .build();

        Component componentB = Component.builder()
                .name("부품 A")
                .part(Part.PART2)
                .price(2000)
                .description("부품 B 정의")
                .type(ComponentType.BLADE)
                .star(3.5)
                .image("부품 B 이미지 경로")
                .build();

        Component componentC = Component.builder()
                .name("부품 C")
                .part(Part.PART3)
                .price(3000)
                .description("부품 C 정의")
                .type(ComponentType.MOTOR)
                .star(1.0)
                .image("부품 C 이미지 경로")
                .build();

        Component componentD = Component.builder()
                .name("부품 D")
                .part(Part.PART1)
                .price(4000)
                .description("부품 D 정의")
                .type(ComponentType.BLADE)
                .star(2.0)
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
                .productionYear(LocalDate.of(2000, 4, 4))
                .build();

        Drone droneB = Drone.builder()
                .name("드론 B")
                .cost(10000)
                .model(DroneModel.MODEL2)
                .purpose("비행 목적")
                .flightCount(300)
                .productionYear(LocalDate.of(2001, 4, 4))
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

        for (int i = 1; i <= 12; i++) {
            DroneGroupMonthCost monthCost = DroneGroupMonthCost.builder()
                    .name("그룹A")
                    .droneCount((long) i * 10)
                    .month((long) i)
                    .year(2023L)
                    .monthCost((long) i * 20)
                    .purpose("비행 목적" + i)
                    .build();

            droneGroupMonthCostRepository.save(monthCost);
        }

        for (int i = 1; i <= 6; i++) {
            DroneGroupMonthCost monthCost = DroneGroupMonthCost.builder()
                    .name("그룹B")
                    .droneCount((long) i * 10)
                    .month((long) i)
                    .year(2023L)
                    .monthCost((long) i * 20)
                    .purpose("비행 목적" + i)
                    .build();

            droneGroupMonthCostRepository.save(monthCost);
        }

        for (int i = 1; i <= 6; i++) {
            DroneGroupMonthCost monthCost = DroneGroupMonthCost.builder()
                    .name("그룹A")
                    .droneCount((long) i * 10)
                    .month((long) i)
                    .year(2024L)
                    .monthCost((long) i * 20)
                    .purpose("비행 목적" + i)
                    .build();

            droneGroupMonthCostRepository.save(monthCost);
        }

        DroneGroup droneGroupA = DroneGroup.builder()
                .name("그룹 A")
                .build();

        DroneGroup droneGroupB = DroneGroup.builder()
                .name("그룹 B")
                .build();

        droneGroupRepository.save(droneGroupA);
        droneGroupRepository.save(droneGroupB);

        DroneGroupInfo droneGroupInfo1 = DroneGroupInfo.builder()
                .drone(droneA)
                .droneGroup(droneGroupA)
                .build();

        DroneGroupInfo droneGroupInfo2 = DroneGroupInfo.builder()
                .drone(droneA)
                .droneGroup(droneGroupB)
                .build();

        DroneGroupInfo droneGroupInfo3 = DroneGroupInfo.builder()
                .drone(droneB)
                .droneGroup(droneGroupA)
                .build();

        droneGroupInfoRepository.save(droneGroupInfo1);
        droneGroupInfoRepository.save(droneGroupInfo2);
        droneGroupInfoRepository.save(droneGroupInfo3);
    }
}

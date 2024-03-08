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
import com.weflo.backend.domain.testresult.domain.TestResult;
import com.weflo.backend.domain.testresult.repository.TestResultRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private final TestResultRepository testResultRepository;
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

        Component componentPart1Blade = Component.builder()
                .name("부품 A")
                .part(Part.PART1)
                .price(1000)
                .description("부품 A 정의")
                .type(ComponentType.ESC)
                .star(4.5)
                .image("부품 A 이미지 경로")
                .build();

        Component componentPart1Motor = Component.builder()
                .name("부품 B")
                .part(Part.PART2)
                .price(2000)
                .description("부품 B 정의")
                .type(ComponentType.BLADE)
                .star(3.5)
                .image("부품 B 이미지 경로")
                .build();

        Component componentPart1Esc = Component.builder()
                .name("부품 C")
                .part(Part.PART3)
                .price(3000)
                .description("부품 C 정의")
                .type(ComponentType.MOTOR)
                .star(1.0)
                .image("부품 C 이미지 경로")
                .build();

        Component componentPart2Blade = Component.builder()
                .name("부품 D")
                .part(Part.PART1)
                .price(1000)
                .description("부품 D 정의")
                .type(ComponentType.ESC)
                .star(4.5)
                .image("부품 D 이미지 경로")
                .build();

        Component componentPart2Motor = Component.builder()
                .name("부품 E")
                .part(Part.PART2)
                .price(2000)
                .description("부품 E 정의")
                .type(ComponentType.BLADE)
                .star(3.5)
                .image("부품 E 이미지 경로")
                .build();

        Component componentPart2Esc = Component.builder()
                .name("부품 F")
                .part(Part.PART3)
                .price(3000)
                .description("부품 F 정의")
                .type(ComponentType.MOTOR)
                .star(1.0)
                .image("부품 F 이미지 경로")
                .build();

        Component componentPart3Blade = Component.builder()
                .name("부품 G")
                .part(Part.PART1)
                .price(1000)
                .description("부품 G 정의")
                .type(ComponentType.ESC)
                .star(4.5)
                .image("부품 G 이미지 경로")
                .build();

        Component componentPart3Motor = Component.builder()
                .name("부품 H")
                .part(Part.PART2)
                .price(2000)
                .description("부품 H 정의")
                .type(ComponentType.BLADE)
                .star(3.5)
                .image("부품 H 이미지 경로")
                .build();

        Component componentPart3Esc = Component.builder()
                .name("부품 I")
                .part(Part.PART3)
                .price(3000)
                .description("부품 I 정의")
                .type(ComponentType.MOTOR)
                .star(1.0)
                .image("부품 I 이미지 경로")
                .build();

        Component componentPart4Blade = Component.builder()
                .name("부품 J")
                .part(Part.PART1)
                .price(1000)
                .description("부품 J 정의")
                .type(ComponentType.ESC)
                .star(4.5)
                .image("부품 J 이미지 경로")
                .build();

        Component componentPart4Motor = Component.builder()
                .name("부품 K")
                .part(Part.PART2)
                .price(2000)
                .description("부품 K 정의")
                .type(ComponentType.BLADE)
                .star(3.5)
                .image("부품 K 이미지 경로")
                .build();

        Component componentPart4Esc = Component.builder()
                .name("부품 L")
                .part(Part.PART3)
                .price(3000)
                .description("부품 L 정의")
                .type(ComponentType.MOTOR)
                .star(1.0)
                .image("부품 L 이미지 경로")
                .build();

        componentRepository.save(componentPart1Blade);
        componentRepository.save(componentPart1Motor);
        componentRepository.save(componentPart1Esc);
        componentRepository.save(componentPart2Blade);
        componentRepository.save(componentPart2Motor);
        componentRepository.save(componentPart2Esc);
        componentRepository.save(componentPart3Blade);
        componentRepository.save(componentPart3Motor);
        componentRepository.save(componentPart3Esc);
        componentRepository.save(componentPart4Blade);
        componentRepository.save(componentPart4Motor);
        componentRepository.save(componentPart4Esc);

        Drone droneA = Drone.builder()
                .name("드론 A")
                .cost(10000)
                .model(DroneModel.MODEL1)
                .purpose("비행 목적")
                .flightCount(200)
                .productionYear(2000)
                .build();

        Drone droneB = Drone.builder()
                .name("드론 B")
                .cost(10000)
                .model(DroneModel.MODEL2)
                .purpose("비행 목적")
                .flightCount(300)
                .productionYear(2001)
                .build();

        droneRepository.save(droneA);
        droneRepository.save(droneB);

        DroneComponent droneComponentA = DroneComponent.builder()
                .component(componentPart1Blade)
                .brokenCount(10)
                .type(componentPart1Blade.getType())
                .drone(droneA)
                .part(Part.PART1)
                .point(40L)
                .build();

        DroneComponent droneComponentB = DroneComponent.builder()
                .component(componentPart1Motor)
                .brokenCount(20)
                .type(componentPart2Blade.getType())
                .drone(droneA)
                .part(Part.PART2)
                .point(70L)
                .build();

        DroneComponent droneComponentC = DroneComponent.builder()
                .component(componentPart3Esc)
                .brokenCount(30)
                .type(componentPart3Esc.getType())
                .drone(droneA)
                .part(Part.PART3)
                .point(30L)
                .build();

        DroneComponent droneComponentD = DroneComponent.builder()
                .component(componentPart4Blade)
                .brokenCount(40)
                .type(componentPart4Blade.getType())
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
                    .purpose("사용 목적" + i)
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
                    .purpose("사용 목적" + i)
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
                    .purpose("사용 목적" + i)
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

        List<Component> components = new ArrayList<>();
        components.add(componentPart1Blade);
        components.add(componentPart1Motor);
        components.add(componentPart1Esc);

        components.add(componentPart2Blade);
        components.add(componentPart2Motor);
        components.add(componentPart2Esc);

        components.add(componentPart3Blade);
        components.add(componentPart3Motor);
        components.add(componentPart3Esc);

        components.add(componentPart4Blade);
        components.add(componentPart4Motor);
        components.add(componentPart4Esc);

        LocalDateTime createDate1 = LocalDateTime.of(2024, 2, 7, 4, 0, 0);
        LocalDateTime createDate2 = LocalDateTime.of(2024, 1, 7, 3, 0, 0);
        TestResult testResultA = TestResult.builder()
                .components(components)
                .expectedDate(LocalDateTime.now())
                .createDate(createDate1)
                .drone(droneA)
                .part1Blade(10)
                .part1Motor(20)
                .part1Esc(30)
                .part2Blade(40)
                .part2Motor(50)
                .part2Esc(60)
                .part3Blade(70)
                .part3Motor(80)
                .part3Esc(90)
                .part4Blade(100)
                .part4Motor(30)
                .part4Esc(50)
                .space("테스트 장소 A")
                .stationId("스테이션 1")
                .point(70)
                .build();

        TestResult testResultB = TestResult.builder()
                .components(components)
                .expectedDate(LocalDateTime.now())
                .createDate(createDate2)
                .drone(droneA)
                .part1Blade(50)
                .part1Motor(30)
                .part1Esc(100)
                .part2Blade(20)
                .part2Motor(70)
                .part2Esc(40)
                .part3Blade(10)
                .part3Motor(60)
                .part3Esc(90)
                .part4Blade(30)
                .part4Motor(20)
                .part4Esc(0)
                .space("테스트 장소 B")
                .stationId("스테이션 2")
                .point(70)
                .build();

        testResultRepository.save(testResultA);
        testResultRepository.save(testResultB);
    }
}

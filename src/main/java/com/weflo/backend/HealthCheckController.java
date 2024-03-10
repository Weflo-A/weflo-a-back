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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
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

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        //업체 정보 넣는 곳
        RepairStore store1 = RepairStore.builder()
                .image("")
                .expectedMaxPrice(99000L)
                .expectedMinPrice(16000L)
                .name("드론 클리닉")
                .canUpgrade(true)
                .hasModels("DJI Mavic Air 2 Skydio 2 Parrot Anafi USA DJI Inspire 2 Eagle SHIFT Pro Autel Robotics SG 906 PRO2")
                .hasTypes("Blade Motor ESC")
                .build();

        RepairStore store2 = RepairStore.builder()
                .image("")
                .expectedMaxPrice(69000L)
                .expectedMinPrice(9000L)
                .name("에어 테크")
                .canUpgrade(true)
                .hasModels("Skydio 2 Parrot Anafi USA DJI Inspire 2 Eagle SHIFT SG 906 PRO2")
                .hasTypes("Blade Motor ESC")
                .build();

        RepairStore store3 = RepairStore.builder()
                .image("")
                .expectedMaxPrice(99000L)
                .expectedMinPrice(6000L)
                .name("모빌리티 원")
                .canUpgrade(false)
                .hasModels("DJI Mavic Air 2 Parrot Anafi USA DJI Inspire 2 Eagle SHIFT Autel Robotics SG 906 PRO2")
                .hasTypes("Blade Motor ESC")
                .build();

        RepairStore store4 = RepairStore.builder()
                .image("")
                .expectedMaxPrice(80000L)
                .expectedMinPrice(24000L)
                .name("노타 드론")
                .canUpgrade(true)
                .hasModels("DJI Mavic Air 2 Skydio 2 Parrot Anafi USA DJI Inspire 2 Eagle Autel Robotics SG 906 PRO2")
                .hasTypes("Motor ESC")
                .build();

        RepairStore store5 = RepairStore.builder()
                .image("")
                .expectedMaxPrice(128000L)
                .expectedMinPrice(48000L)
                .name("한드론텍")
                .canUpgrade(false)
                .hasModels("DJI Mavic Air 2 Skydio 2 Parrot Anafi USA DJI Inspire 2 Eagle Autel Robotics SG 906 PRO2")
                .hasTypes("Blade ESC")
                .build();

        RepairStore store6 = RepairStore.builder()
                .image("")
                .expectedMaxPrice(158000L)
                .expectedMinPrice(60000L)
                .name("픽셀플라이")
                .canUpgrade(true)
                .hasModels("DJI Mavic Air 2 Skydio 2 Parrot Anafi USA DJI Inspire 2 Eagle SHIFT Autel Robotics SG 906 PRO2")
                .hasTypes("Blade Motor")
                .build();

        RepairStore store7 = RepairStore.builder()
                .image("")
                .expectedMaxPrice(78000L)
                .expectedMinPrice(33000L)
                .name("해오름 드론 항공")
                .canUpgrade(false)
                .hasModels("DJI Mavic Air 2 Skydio 2 Parrot Anafi USA DJI Inspire 2 Eagle SHIFT Autel Robotics SG 906 PRO2")
                .hasTypes("Blade Motor ESC")
                .build();

        repairStoreRepository.save(store1);
        repairStoreRepository.save(store2);
        repairStoreRepository.save(store3);
        repairStoreRepository.save(store4);
        repairStoreRepository.save(store5);
        repairStoreRepository.save(store6);
        repairStoreRepository.save(store7);

        //블레이드 넣는 곳
        List<String> componentBladeNames = new ArrayList<>();
        componentBladeNames.add("Gemfan 5040 Propeller");
        componentBladeNames.add("DALPROP T5040 V2 Propeller");
        componentBladeNames.add("HQProp DP5X4.3X3V1S Propeller");
        componentBladeNames.add("RaceKraft 5051 Propeller");
        componentBladeNames.add("T-Motor T5147 Propeller");
        componentBladeNames.add("Ethix S3 Watermelon Propeller");
        componentBladeNames.add("Azure Power JohnnyFPV Propeller");
        componentBladeNames.add("[DUALSKY] 9x5.5/inch Carbon Prop (Version.2)");

        List<Integer> componentBladePrices = new ArrayList<>();
        componentBladePrices.add(20800);
        componentBladePrices.add(124000);
        componentBladePrices.add(135000);
        componentBladePrices.add(54800);
        componentBladePrices.add(79800);
        componentBladePrices.add(66800);
        componentBladePrices.add(34000);
        componentBladePrices.add(118000);

        List<String> componentBladeDescriptions = new ArrayList<>();
        componentBladeDescriptions.add("드론 비행에 안정성과 효율성을 제공하는 고품질 프로펠러입니다. 경량 및 고속 비행에 적합하며, 다양한 드론 유형에서 성능을 극대화합니다. 내구성이 뛰어나고 효과적인 공기 동력으로 안정적인 비행 환경을 조성합니다.");
        componentBladeDescriptions.add("DALPROP T5040 V2는 레이싱 및 프리스타일 드론에 적합한 높은 효율성과 탁월한 제어를 제공하는 프로펠러입니다. 고급 PC+ABS 소재로 제작되어 내구성이 뛰어나며, 다양한 컬러 옵션으로 스타일에 맞게 선택할 수 있습니다. 레이싱 엔스루지애스트들 사이에서 인기 있는 모델 중 하나입니다.");
        componentBladeDescriptions.add("레이싱 드론에 최적화된 고효율 프로펠러로, 고속 비행과 정확한 조종을 가능케합니다. 튼튼한 디자인과 풍선 모양의 블레이드로 안정적인 비행을 지원하며, 다양한 환경에서 뛰어난 성능을 발휘합니다.");
        componentBladeDescriptions.add("T-Motor T5147 프로펠러는 T-Motor의 높은 기술력을 반영한 고성능 부품으로, 안정성과 효율성을 모두 갖추고 있습니다. 튼튼한 구조와 효율적인 공기 다이내믹스로 속도 및 제어에 우수한 성능을 제공하며, 레이싱 비행에 적합합니다.");
        componentBladeDescriptions.add("Azure Power JohnnyFPV 프로펠러는 유명 드론 팔로워인 JohnnyFPV와 협력하여 설계된 모델로, 프리스타일과 액션 촬영에 최적화되었습니다. 고속 비행에서도 안정성을 유지하며, 독특한 컬러 및 디자인이 비행기에 스타일을 더합니다.");
        componentBladeDescriptions.add("고속 레이싱과 프리스타일 비행에 적합한 프로펠러로, 뛰어난 효율성과 제어성을 제공합니다. 소음을 최소화하고 고속 회전에서도 안정적인 비행을 지원하여 드론 엔스루지애스트들 사이에서 평가가 높은 모델 중 하나입니다.");
        componentBladeDescriptions.add("다이내믹한 디자인과 뛰어난 공기 다이내믹스로 레이싱 및 프리스타일 드론에 적합한 프로펠러입니다. 탁월한 풍선 구조로 안정적인 비행을 지원하며, 고강도 소재로 제작되어 내구성이 뛰어납니다.");
        componentBladeDescriptions.add("고속 레이싱과 프리스타일 비행에 적합한 프로펠러로, 뛰어난 효율성과 제어성을 제공합니다. 소음을 최소화하고 고속 회전에서도 안정적인 비행을 지원하여 드론 엔스루지애스트들 사이에서 평가가 높은 모델 중 하나입니다.");

        List<Double> componentBladeStars = new ArrayList<>();
        componentBladeStars.add(5.0);
        componentBladeStars.add(4.5);
        componentBladeStars.add(3.0);
        componentBladeStars.add(2.0);
        componentBladeStars.add(3.5);
        componentBladeStars.add(1.0);
        componentBladeStars.add(5.0);
        componentBladeStars.add(3.0);

        List<String> componentBladeImages = new ArrayList<>();
        componentBladeImages.add("");
        componentBladeImages.add("");
        componentBladeImages.add("");
        componentBladeImages.add("");
        componentBladeImages.add("");
        componentBladeImages.add("");
        componentBladeImages.add("");
        componentBladeImages.add("");

        List<Part> componentParts = new ArrayList<>();
        componentParts.add(Part.PART1);
        componentParts.add(Part.PART2);
        componentParts.add(Part.PART3);
        componentParts.add(Part.PART4);
        componentParts.add(Part.PART1);
        componentParts.add(Part.PART2);
        componentParts.add(Part.PART3);
        componentParts.add(Part.PART4);

        List<Component> components = new ArrayList<>();
        List<Component> componentsBlade = new ArrayList<>();

        for (int i = 0; i < componentBladeNames.size(); i++) {
            Component newComponent = Component.builder()
                    .name(componentBladeNames.get(i))
                    .part(componentParts.get(i))
                    .price(componentBladePrices.get(i))
                    .description(componentBladeDescriptions.get(i))
                    .type(ComponentType.BLADE)
                    .star(componentBladeStars.get(i))
                    .image(componentBladeImages.get(i))
                    .build();

            componentsBlade.add(newComponent);
            components.add(newComponent);
        }

        //모터 넣는 곳
        List<String> componentMotorNames = new ArrayList<>();
        componentMotorNames.add("X2814 900KV 3-5S Brushless Motor");
        componentMotorNames.add("[RTS] S2312-920KV Motor");
        componentMotorNames.add("[XEON] S2312-920KV Motor");
        componentMotorNames.add("1pcs 2212 920kv Brushless Motor");
        componentMotorNames.add("XM7010TE-7.5 MR Motor SS Type (28Pole/330KV)");
        componentMotorNames.add("XM4608MR-10 Brushless Motor (22Pole/590KV)");
        componentMotorNames.add("4112-kv450 Brushless Motor UAV");
        componentMotorNames.add("XM5015HD-7 (28Pole/340KV)");

        List<Integer> componentMotorPrices = new ArrayList<>();
        componentMotorPrices.add(135000);
        componentMotorPrices.add(15800);
        componentMotorPrices.add(15800);
        componentMotorPrices.add(123640);
        componentMotorPrices.add(32000);
        componentMotorPrices.add(14900);
        componentMotorPrices.add(24090);
        componentMotorPrices.add(99000);

        List<String> componentMotorDescriptions = new ArrayList<>();
        componentMotorDescriptions.add("SunnySky 모터는 검정된 제품으로 3D기체나 파일런에 사용시 최고의 만족을 드립니다. SunnySky 모터는 일제 베어링을 사용하여 오랜시간 사용이 가능합니다. 소음도 적습니다. 일본 kawasaki stator steel을 사용 효율성을 높혔습니다.");
        componentMotorDescriptions.add("하이파워 사양의 소형 멀티콥터를 위한 아웃러너 모터(920KV) DJI 및 호환 계열 F330/F450/F550/S500/TBS500 등과 같은 소형 클래스 쿼드, 헥사콥터에 적합한 모터 프롭은 3S/4S 공히 동사의 9x4.5in Self-Lock Propeller (DJI/Universal Type)사용");
        componentMotorDescriptions.add("고효율과 안정성을 자랑하는 E5000은 DJI의 고급 드론에 사용되는 브러시리스 모터입니다. 세련된 제어 및 부드러운 비행을 위해 최적화되었으며, 뛰어난 성능으로 사용자들의 신뢰를 얻고 있습니다. 높은 품질과 기술력을 바탕으로 한 탁월한 선택지입니다.");
        componentMotorDescriptions.add("고품질 자재와 세련된 설계를 통해 안정적이고 고성능의 비행을 제공합니다. 특히, 진동을 최소화하고 향상된 통풍 시스템으로 오버히팅을 방지하여 어떤 환경에서도 뛰어난 성능을 보장합니다. 드론 애호가들 사이에서 널리 사용되는 모터 중 하나입니다.");
        componentMotorDescriptions.add("경량이면서도 높은 출력을 제공하여 드론 레이싱에 이상적인 선택입니다. 뛰어난 가격 대비 성능과 내구성을 자랑하며, 고속 비행 및 다양한 비행 동작에 적합합니다. 진정한 레이서들을 위한 모터로, 안정성과 성능을 동시에 충족시키는데 주력하고 있습니다.");
        componentMotorDescriptions.add("중소형 드론에 이상적인 안정적인 성능을 제공하는 모터입니다. 충분한 추력과 효율성으로 오랜 비행 시간을 가능케하며, 다양한 용도에 활용할 수 있습니다. 가벼운 구조로 인해 드론의 총 무게를 최소화하여 뛰어난 조종 경험을 제공합니다.");
        componentMotorDescriptions.add("프리미엄 드론 모터로, 고출력과 내구성이 특징입니다. 높은 자기 강도를 갖춘 마그네틱 디자인으로 안정적이고 효율적인 성능을 제공하며, 극한 비행 조건에서도 뛰어난 신뢰성을 보여줍니다. 고급 사용자들에게 추천되는 모터 중 하나입니다.");
        componentMotorDescriptions.add("드론 레이싱에 최적화된 고효율 모터로, 안정적인 성능과 빠른 응답 속도를 제공합니다. 강화된 구조와 높은 출력으로 고속 비행에 적합하며, 다양한 비행 스타일을 만족시킬 수 있습니다. Lumenier의 뛰어난 제조 기술과 성능으로 신뢰성 높은 드론 모터 중 하나입니다.");

        List<Double> componentMotorStars = new ArrayList<>();
        componentMotorStars.add(5.0);
        componentMotorStars.add(4.5);
        componentMotorStars.add(3.0);
        componentMotorStars.add(2.0);
        componentMotorStars.add(3.5);
        componentMotorStars.add(1.0);
        componentMotorStars.add(5.0);
        componentMotorStars.add(3.0);

        List<String> componentMotorImages = new ArrayList<>();
        componentMotorImages.add("");
        componentMotorImages.add("");
        componentMotorImages.add("");
        componentMotorImages.add("");
        componentMotorImages.add("");
        componentMotorImages.add("");
        componentMotorImages.add("");
        componentMotorImages.add("");

        List<Component> componentsMotor = new ArrayList<>();

        for (int i = 0; i < componentMotorNames.size(); i++) {
            Component newComponent = Component.builder()
                    .name(componentMotorNames.get(i))
                    .part(componentParts.get(i))
                    .price(componentMotorPrices.get(i))
                    .description(componentMotorDescriptions.get(i))
                    .type(ComponentType.MOTOR)
                    .star(componentMotorStars.get(i))
                    .image(componentMotorImages.get(i))
                    .build();

            componentsMotor.add(newComponent);
            components.add(newComponent);
        }

        //ESC 넣는 곳
        List<String> componentESCNames = new ArrayList<>();
        componentESCNames.add("LittleBee 30A-S BLHeli_S ESC");
        componentESCNames.add("Neuron2 60A ESC");
        componentESCNames.add("DalRC ENGINE 40A");
        componentESCNames.add("Spedix IS30 4-in-1 30A");
        componentESCNames.add("Aikon SEFM 30A");
        componentESCNames.add("Racerstar RS20Ax4 20A 4-in-1");
        componentESCNames.add("Maytech ESC 50A OPTO");
        componentESCNames.add("Emax Bullet 35A");

        List<Integer> componentESCPrices = new ArrayList<>();
        componentESCPrices.add(22300);
        componentESCPrices.add(135000);
        componentESCPrices.add(24090);
        componentESCPrices.add(16800);
        componentESCPrices.add(42000);
        componentESCPrices.add(99000);
        componentESCPrices.add(13800);
        componentESCPrices.add(68900);

        List<String> componentESCDescriptions = new ArrayList<>();
        componentESCDescriptions.add("댐핑 모드, Oneshot125, Oneshot42 및 Multshot 지원 메인 IC: SILABS EFM8BB21F16, MCU 실행 48MHz MOS: 로우 사이드 및 하이 사이드 피트 모두 Nfets 스위칭 속도가 매우 빠릅니다. 전용 드라이버 칩이 있습니다.");
        componentESCDescriptions.add("Skywalker 시리즈가 버전 2로 돌아왔습니다. 이제 훨씬 더 강력해지고 이전 제품과 마찬가지로 가격도 저렴해졌습니다. 여기에는 브레이크 유형, 제동력, 차단 전압, LiPo 셀 설정, 시동 및 검색 모드, 타이밍 및 활성 프리휠링 등이 포함됩니다. ");
        componentESCDescriptions.add("고성능 드론 용으로 최적화된 안정적이고 신뢰성 있는 전자 속도 조절기입니다. 고급 알고리즘과 센서를 활용하여 부드럽고 정확한 모터 제어를 제공하며, 드론의 안정성을 향상시킵니다. 높은 부하에도 효율적으로 작동하여 전문 드론 프로젝트에 적합합니다.");
        componentESCDescriptions.add("뛰어난 성능과 내구성을 제공하는 드론 전자 속도 조절기입니다. 높은 전류 허용치와 고속 프로세싱 능력으로 안정적인 비행을 지원하며, 탁월한 열 효율성으로 장시간 비행이 가능합니다. 특히, 드론 레이싱 및 고급 비행에 적합합니다.");
        componentESCDescriptions.add("경량 및 고성능으로 설계된 모델로, 드론 레이싱 및 에어로빅 비행에 적합합니다. BLHeli_S 프로그램과 Dshot 지원으로 정확하고 빠른 명령을 전달하여 모터의 탁월한 응답을 보장합니다. 고속 모터 회전에 대한 정밀한 제어를 제공하며, 안정적인 비행을 위한 선택으로 평가받고 있습니다.");
        componentESCDescriptions.add("소형 드론 및 레이싱 드론에 최적화된 고성능 ESC로, 높은 주파수로 작동하여 정밀한 조종을 제공합니다. 프로그래밍 가능한 매개변수 및 부드러운 가속 및 감속으로 다양한 비행 스타일에 부합하며, 안정적이고 신뢰성 있는 성능을 자랑합니다.");
        componentESCDescriptions.add("통합된 ESC 및 플라이트 컨트롤러를 제공하여 쉽고 간편한 설치를 가능케 하는 드론 부품입니다. 안정적인 비행 및 빠른 반응 속도를 제공하며, 고급 사용자들에게는 개별 ESC를 사용하는 것보다 간편한 솔루션으로 선택됩니다.");
        componentESCDescriptions.add("경량 및 고성능으로 설계된 모델로, 드론 레이싱 및 에어로빅 비행에 적합합니다. BLHeli_S 프로그램과 Dshot 지원으로 정확하고 빠른 명령을 전달하여 모터의 탁월한 응답을 보장합니다. 고속 모터 회전에 대한 정밀한 제어를 제공하며, 안정적인 비행을 위한 선택으로 평가받고 있습니다.");

        List<Double> componentESCStars = new ArrayList<>();
        componentESCStars.add(5.0);
        componentESCStars.add(4.5);
        componentESCStars.add(3.0);
        componentESCStars.add(2.0);
        componentESCStars.add(3.5);
        componentESCStars.add(1.0);
        componentESCStars.add(5.0);
        componentESCStars.add(3.0);

        List<String> componentESCImages = new ArrayList<>();
        componentESCImages.add("");
        componentESCImages.add("");
        componentESCImages.add("");
        componentESCImages.add("");
        componentESCImages.add("");
        componentESCImages.add("");
        componentESCImages.add("");
        componentESCImages.add("");

        List<Component> componentsESC = new ArrayList<>();

        for (int i = 0; i < componentESCNames.size(); i++) {
            Component newComponent = Component.builder()
                    .name(componentESCNames.get(i))
                    .part(componentParts.get(i))
                    .price(componentESCPrices.get(i))
                    .description(componentESCDescriptions.get(i))
                    .type(ComponentType.ESC)
                    .star(componentESCStars.get(i))
                    .image(componentESCImages.get(i))
                    .build();

            componentsESC.add(newComponent);
            components.add(newComponent);
        }

        //기타 부품 넣는 곳
        List<String> componentOtherNames = new ArrayList<>();
        componentOtherNames.add("Powerboard 고전류 전력관리 모듈 PDB 보드 6s,12s");
        componentOtherNames.add("F450/F550 skid landing");
        componentOtherNames.add("HX4-06009");
        componentOtherNames.add("12A UBEC 스위치 모드 BEC 전압 안정기");
        componentOtherNames.add("Agena HV BEC");
        componentOtherNames.add("K3-A PRO 드론 컨트롤러 (듀얼 GPS / 농업 방제드론)");
        componentOtherNames.add("A7-AG V3.0 농업용 비행 컨트롤러");
        componentOtherNames.add("고휘도 Single LED Module(12V/White)");

        List<Integer> componentOtherPrices = new ArrayList<>();
        componentOtherPrices.add(145000);
        componentOtherPrices.add(91500);
        componentOtherPrices.add(59800);
        componentOtherPrices.add(48500);
        componentOtherPrices.add(69000);
        componentOtherPrices.add(650000);
        componentOtherPrices.add(990000);
        componentOtherPrices.add(3000);

        List<String> componentOtherDescriptions = new ArrayList<>();
        componentOtherDescriptions.add("XT60 커넥터는 드론 배터리와 전원 분배 시스템을 연결하는 데 사용되는 안전하고 효율적인 커넥터입니다. 고전류 전달 및 미끄러짐 방지 디자인으로 안정적인 전력 전달을 제공하며, 드론 비행 중 안전을 보장합니다.");
        componentOtherDescriptions.add("드론을 안전하게 이착륙시키기 위한 휴대용 랜딩 패드로, 방수 및 방진 소재로 제작되어 실외에서의 사용에 이상적입니다. 또한 큰 크기로 인해 비행 중에 시각적 안내 역할도 하며, 드론의 안정성을 높이는 데 도움이 됩니다.");
        componentOtherDescriptions.add("이 보호매트는 드론의 하단 스키드 부분에 부착되어 충격을 완화하고 지상과의 마찰을 줄여 드론의 부품을 보호합니다. 고무나 실리콘 소재로 만들어져 부착 및 제거가 간편하며, 드론의 안정성을 높이는 데 도움이 됩니다.");
        componentOtherDescriptions.add("경량 및 고성능으로 설계된 모델로, 드론 레이싱 및 에어로빅 비행에 적합합니다. BLHeli_S 프로그램과 Dshot 지원으로 정확하고 빠른 명령을 전달하여 모터의 탁월한 응답을 보장합니다. 고속 모터 회전에 대한 정밀한 제어를 제공하며, 안정적인 비행을 위한 선택으로 평가받고 있습니다.");
        componentOtherDescriptions.add("전압 변환, 전류 보호 및 통합된 전자 부품을 내장하여 드론 시스템의 각 부분 간에 원활한 전력 흐름을 제공합니다. 경량 소재로 만들어져 비행 중에 무게를 최소화하고, 동시에 제한된 공간 내에서 효율적으로 작동할 수 있도록 설계됩니다.");
        componentOtherDescriptions.add("주로 드론의 상단 부분을 덮는 외부 케이스나 프레임으로 구성되어 있으며, 충격이나 낙하로부터 드론을 보호합니다. 고강도 플라스틱이나 카본 파이버로 만들어져 내구성을 갖추며, 드론의 비행 안정성을 유지하면서 환경 요소로부터 부품을 보호합니다. 일부 커버에는 카메라, 센서 등을 통합할 수 있는 기능이 있어 비행 중에도 원하는 기능을 수행할 수 있습니다.");
        componentOtherDescriptions.add("주 배터리로부터 전원을 공급받아 안정적인 전압으로 변환하고, 이를 다양한 부품에 전달합니다. BEC는 서보 모터, 수신기 및 조종기와 같은 전자 부품들이 안정적으로 동작하도록 보장하며, 별도의 전원 공급원을 필요로하지 않는 편리한 기능을 제공합니다. 이로써 모델의 전원 관리가 간편해지고, 더 나은 비행 안정성을 유지할 수 있습니다.");
        componentOtherDescriptions.add("단일 LED 소자를 포함한 고밝음 광원 모듈로, 주로 조명 및 표시 용도로 사용됩니다. 이 모듈은 고휘도 LED가 통합되어 있어 강력하고 효율적인 조명을 제공하며, 주로 낮은 전력 소비로 높은 발광 효율을 유지합니다. 설치가 간편하며, 다양한 응용 분야에서 활용되어 안전한 조명 및 시각적 효과를 제공합니다.");

        List<Double> componentOtherStars = new ArrayList<>();
        componentOtherStars.add(4.0);
        componentOtherStars.add(3.5);
        componentOtherStars.add(2.0);
        componentOtherStars.add(5.0);
        componentOtherStars.add(3.5);
        componentOtherStars.add(4.0);
        componentOtherStars.add(5.0);
        componentOtherStars.add(3.0);

        List<String> componentOtherImages = new ArrayList<>();
        componentOtherImages.add("");
        componentOtherImages.add("");
        componentOtherImages.add("");
        componentOtherImages.add("");
        componentOtherImages.add("");
        componentOtherImages.add("");
        componentOtherImages.add("");
        componentOtherImages.add("");

        List<Component> componentsOthers = new ArrayList<>();

        for (int i = 0; i < componentOtherNames.size(); i++) {
            Component newComponent = Component.builder()
                    .name(componentOtherNames.get(i))
                    .part(componentParts.get(i))
                    .price(componentOtherPrices.get(i))
                    .description(componentOtherDescriptions.get(i))
                    .type(ComponentType.OTHER)
                    .star(componentOtherStars.get(i))
                    .image(componentOtherImages.get(i))
                    .build();

            componentsOthers.add(newComponent);
        }

        componentRepository.saveAll(componentsOthers);
        componentRepository.saveAll(components);

        Drone drone1 = Drone.builder()
                .name("Drone Number")
                .cost(1400000)
                .model(DroneModel.EAGLE)
                .purpose("농업용")
                .flightCount(289)
                .productionYear(2021)
                .build();

        Drone drone2 = Drone.builder()
                .name("Drone Number")
                .cost(800000)
                .model(DroneModel.VL_2240R)
                .purpose("농업용")
                .flightCount(91)
                .productionYear(2023)
                .build();

        Drone drone3 = Drone.builder()
                .name("Drone Number")
                .cost(420000)
                .model(DroneModel.SHIFT)
                .purpose("농업용")
                .flightCount(124)
                .productionYear(2019)
                .build();

        Drone drone4 = Drone.builder()
                .name("Drone Number")
                .cost(400000)
                .model(DroneModel.EAGLE)
                .purpose("교육용")
                .flightCount(356)
                .productionYear(2020)
                .build();

        Drone drone5 = Drone.builder()
                .name("Drone Number")
                .cost(560000)
                .model(DroneModel.VL_2240R)
                .purpose("교육용")
                .flightCount(56)
                .productionYear(2022)
                .build();

        Drone drone6 = Drone.builder()
                .name("Drone Number")
                .cost(3000000)
                .model(DroneModel.EAGLE)
                .purpose("교육용")
                .flightCount(23)
                .productionYear(2024)
                .build();

        Drone drone7 = Drone.builder()
                .name("Drone Number")
                .cost(980000)
                .model(DroneModel.SHIFT)
                .purpose("교육용")
                .flightCount(66)
                .productionYear(2021)
                .build();

        Drone drone8 = Drone.builder()
                .name("Drone Number")
                .cost(1400000)
                .model(DroneModel.MDT_1600)
                .purpose("물류 배송")
                .flightCount(91)
                .productionYear(2021)
                .build();

        Drone drone9 = Drone.builder()
                .name("Drone Number")
                .cost(500000)
                .model(DroneModel.MDT_1600)
                .purpose("물류 배송")
                .flightCount(145)
                .productionYear(2023)
                .build();

        Drone drone10 = Drone.builder()
                .name("Drone Number")
                .cost(420000)
                .model(DroneModel.EAGLE)
                .purpose("물류 배송")
                .flightCount(24)
                .productionYear(2017)
                .build();

        droneRepository.save(drone1);
        droneRepository.save(drone2);
        droneRepository.save(drone3);
        droneRepository.save(drone4);
        droneRepository.save(drone5);
        droneRepository.save(drone6);
        droneRepository.save(drone7);
        droneRepository.save(drone8);
        droneRepository.save(drone9);
        droneRepository.save(drone10);

        List<Drone> drones = new ArrayList<>();
        drones.add(drone1);
        drones.add(drone2);
        drones.add(drone3);
        drones.add(drone4);
        drones.add(drone5);
        drones.add(drone6);
        drones.add(drone7);
        drones.add(drone8);
        drones.add(drone9);
        drones.add(drone10);

        for (Drone drone : drones) {
            int start = 0;

            if (random.nextBoolean()) {
                start = 4;
            }

            for (int i = 0; i < 4; i++) {
                Component componentBlade = componentsBlade.get(i + start);
                DroneComponent newDroneComponentBlade = DroneComponent.builder()
                        .component(componentBlade)
                        .brokenCount(random.nextInt(31))
                        .type(componentBlade.getType())
                        .drone(drone)
                        .part(componentBlade.getPart())
                        .point((long)random.nextInt(101))
                        .build();

                Component componentMotor = componentsMotor.get(i + start);
                DroneComponent newDroneComponentMotor = DroneComponent.builder()
                        .component(componentMotor)
                        .brokenCount(random.nextInt(31))
                        .type(componentMotor.getType())
                        .drone(drone)
                        .part(componentMotor.getPart())
                        .point((long)random.nextInt(101))
                        .build();

                Component componentESC = componentsESC.get(i + start);
                DroneComponent newDroneComponentESC = DroneComponent.builder()
                        .component(componentESC)
                        .brokenCount(random.nextInt(31))
                        .type(componentESC.getType())
                        .drone(drone)
                        .part(componentESC.getPart())
                        .point((long)random.nextInt(101))
                        .build();

                    droneComponentRepository.save(newDroneComponentBlade);
                    droneComponentRepository.save(newDroneComponentMotor);
                    droneComponentRepository.save(newDroneComponentESC);
            }
        }

        //월 별 투입 비용 넣는 곳
        for (int year = 2020; year <= 2023; year++) {
            for (int i = 1; i <= 12; i++) {
                DroneGroupMonthCost monthCost1 = DroneGroupMonthCost.builder()
                        .name("농업용 그룹")
                        .droneCount((long)random.nextInt(4))
                        .month((long) i)
                        .year((long)year)
                        .monthCost((long) random.nextInt(40000))
                        .purpose("농업용")
                        .build();

                DroneGroupMonthCost monthCost2 = DroneGroupMonthCost.builder()
                        .name("교육용 그룹")
                        .droneCount((long) random.nextInt(5))
                        .month((long) i)
                        .year((long)year)
                        .monthCost((long) random.nextInt(40000))
                        .purpose("교육용")
                        .build();

                DroneGroupMonthCost monthCost3 = DroneGroupMonthCost.builder()
                        .name("물류 배송용 그룹")
                        .droneCount((long) random.nextInt(4))
                        .month((long) i)
                        .year((long)year)
                        .monthCost((long) random.nextInt(40000))
                        .purpose("물류 배송용")
                        .build();

                droneGroupMonthCostRepository.save(monthCost1);
                droneGroupMonthCostRepository.save(monthCost2);
                droneGroupMonthCostRepository.save(monthCost3);
            }
        }

        for (int i = 1; i <= 3; i++) {
            DroneGroupMonthCost monthCost1 = DroneGroupMonthCost.builder()
                    .name("농업용 그룹")
                    .droneCount((long)random.nextInt(4))
                    .month((long) i)
                    .year(2024L)
                    .monthCost((long) random.nextInt(40000))
                    .purpose("농업용")
                    .build();

            DroneGroupMonthCost monthCost2 = DroneGroupMonthCost.builder()
                    .name("교육용 그룹")
                    .droneCount((long) random.nextInt(5))
                    .month((long) i)
                    .year(2024L)
                    .monthCost((long) random.nextInt(40000))
                    .purpose("교육용")
                    .build();

            DroneGroupMonthCost monthCost3 = DroneGroupMonthCost.builder()
                    .name("물류 배송용 그룹")
                    .droneCount((long) random.nextInt(4))
                    .month((long) i)
                    .year(2024L)
                    .monthCost((long) random.nextInt(40000))
                    .purpose("물류 배송용")
                    .build();

            droneGroupMonthCostRepository.save(monthCost1);
            droneGroupMonthCostRepository.save(monthCost2);
            droneGroupMonthCostRepository.save(monthCost3);
        }


        //드론 그룹 넣는 곳
        DroneGroup droneGroup1 = DroneGroup.builder()
                .name("농업용 그룹")
                .build();

        DroneGroup droneGroup2 = DroneGroup.builder()
                .name("교육용 그룹")
                .build();

        DroneGroup droneGroup3 = DroneGroup.builder()
                .name("물류 배송용 그룹")
                .build();

        DroneGroup droneGroup4 = DroneGroup.builder()
                .name("측량용 그룹")
                .build();

        DroneGroup droneGroup5 = DroneGroup.builder()
                .name("군용 그룹")
                .build();

        DroneGroup droneGroup6 = DroneGroup.builder()
                .name("촬영용 그룹")
                .build();

        droneGroupRepository.save(droneGroup1);
        droneGroupRepository.save(droneGroup2);
        droneGroupRepository.save(droneGroup3);
        droneGroupRepository.save(droneGroup4);
        droneGroupRepository.save(droneGroup5);
        droneGroupRepository.save(droneGroup6);

        DroneGroupInfo droneGroupInfo1 = DroneGroupInfo.builder()
                .drone(drone1)
                .droneGroup(droneGroup1)
                .build();

        DroneGroupInfo droneGroupInfo2 = DroneGroupInfo.builder()
                .drone(drone2)
                .droneGroup(droneGroup1)
                .build();

        DroneGroupInfo droneGroupInfo3 = DroneGroupInfo.builder()
                .drone(drone3)
                .droneGroup(droneGroup1)
                .build();

        DroneGroupInfo droneGroupInfo4 = DroneGroupInfo.builder()
                .drone(drone4)
                .droneGroup(droneGroup2)
                .build();

        DroneGroupInfo droneGroupInfo5 = DroneGroupInfo.builder()
                .drone(drone5)
                .droneGroup(droneGroup2)
                .build();

        DroneGroupInfo droneGroupInfo6 = DroneGroupInfo.builder()
                .drone(drone6)
                .droneGroup(droneGroup2)
                .build();

        DroneGroupInfo droneGroupInfo7 = DroneGroupInfo.builder()
                .drone(drone7)
                .droneGroup(droneGroup2)
                .build();

        DroneGroupInfo droneGroupInfo8 = DroneGroupInfo.builder()
                .drone(drone8)
                .droneGroup(droneGroup3)
                .build();

        DroneGroupInfo droneGroupInfo9 = DroneGroupInfo.builder()
                .drone(drone9)
                .droneGroup(droneGroup3)
                .build();

        DroneGroupInfo droneGroupInfo10 = DroneGroupInfo.builder()
                .drone(drone10)
                .droneGroup(droneGroup3)
                .build();

        DroneGroupInfo droneGroupInfoDuplicated1 = DroneGroupInfo.builder()
                .drone(drone1)
                .droneGroup(droneGroup2)
                .build();

        DroneGroupInfo droneGroupInfoDuplicated2 = DroneGroupInfo.builder()
                .drone(drone1)
                .droneGroup(droneGroup3)
                .build();

        droneGroupInfoRepository.save(droneGroupInfo1);
        droneGroupInfoRepository.save(droneGroupInfo2);
        droneGroupInfoRepository.save(droneGroupInfo3);
        droneGroupInfoRepository.save(droneGroupInfo4);
        droneGroupInfoRepository.save(droneGroupInfo5);
        droneGroupInfoRepository.save(droneGroupInfo6);
        droneGroupInfoRepository.save(droneGroupInfo7);
        droneGroupInfoRepository.save(droneGroupInfo8);
        droneGroupInfoRepository.save(droneGroupInfo9);
        droneGroupInfoRepository.save(droneGroupInfo10);
        droneGroupInfoRepository.save(droneGroupInfoDuplicated1);
        droneGroupInfoRepository.save(droneGroupInfoDuplicated2);

        //검사 결과 넣는 곳
        LocalDateTime createDate1 = LocalDateTime.of(2024, 1, 12, 3, 0, 0);
        LocalDateTime createDate2 = LocalDateTime.of(2024, 2, 12, 3, 0, 0);
        LocalDateTime createDate3 = LocalDateTime.of(2024, 3, 12, 3, 0, 0);

        List<LocalDateTime> createDates = new ArrayList<>();
        for (int i = 1; i <= 12 ; i++) {
            createDates.add(LocalDateTime.of(2023, i, 12, 3, 0, 0));
        }

        List<TestResult> testResults = new ArrayList<>();

        //11월 까지 넣음
        for (int i = 0; i < createDates.size() - 1; i++) {
            List<Component> componentList = drone1.getDroneComponents().stream()
                    .map(DroneComponent::getComponent).toList();

            LocalDateTime nextExpectDate = createDates.get(i + 1);

            TestResult newTestResult = TestResult.builder()
                    .components(componentList)
                    .expectedDate(nextExpectDate)
                    .createDate(createDates.get(i))
                    .drone(drone1)
                    .part1Blade(random.nextInt(101))
                    .part1Motor(random.nextInt(101))
                    .part1Esc(random.nextInt(101))
                    .part2Blade(random.nextInt(101))
                    .part2Motor(random.nextInt(101))
                    .part2Esc(random.nextInt(101))
                    .part3Blade(random.nextInt(101))
                    .part3Motor(random.nextInt(101))
                    .part3Esc(random.nextInt(101))
                    .part4Blade(random.nextInt(101))
                    .part4Motor(random.nextInt(101))
                    .part4Esc(random.nextInt(101))
                    .space("인천")
                    .stationId("SID 1")
                    .build();

            testResults.add(newTestResult);
        }

        //2023년 12월 정보 넣기(2024년 1월 이랑 연결하려고)
        TestResult testResult0 = TestResult.builder()
                .components(components)
                .expectedDate(createDate1)
                .createDate(createDates.get(11))
                .drone(drone1)
                .part1Blade(10)
                .part1Motor(40)
                .part1Esc(26)
                .part2Blade(40)
                .part2Motor(78)
                .part2Esc(30)
                .part3Blade(15)
                .part3Motor(86)
                .part3Esc(27)
                .part4Blade(100)
                .part4Motor(64)
                .part4Esc(38)
                .space("인천")
                .stationId("SID 1")
                .build();

        TestResult testResult1 = TestResult.builder()
                .components(components)
                .expectedDate(createDate2)
                .createDate(createDate1)
                .drone(drone1)
                .part1Blade(10)
                .part1Motor(40)
                .part1Esc(26)
                .part2Blade(40)
                .part2Motor(78)
                .part2Esc(30)
                .part3Blade(15)
                .part3Motor(86)
                .part3Esc(27)
                .part4Blade(100)
                .part4Motor(64)
                .part4Esc(38)
                .space("인천")
                .stationId("SID 1")
                .build();

        TestResult testResult2 = TestResult.builder()
                .components(components)
                .expectedDate(createDate3)
                .createDate(createDate2)
                .drone(drone1)
                .part1Blade(39)
                .part1Motor(67)
                .part1Esc(46)
                .part2Blade(30)
                .part2Motor(17)
                .part2Esc(25)
                .part3Blade(77)
                .part3Motor(46)
                .part3Esc(29)
                .part4Blade(17)
                .part4Motor(66)
                .part4Esc(48)
                .space("부천")
                .stationId("SID 2")
                .build();

        TestResult testResult3 = TestResult.builder()
                .components(components)
                .expectedDate(LocalDateTime.of(2024, 4, 12, 3, 0, 0))
                .createDate(createDate3)
                .drone(drone1)
                .part1Blade(78)
                .part1Motor(24)
                .part1Esc(65)
                .part2Blade(20)
                .part2Motor(38)
                .part2Esc(73)
                .part3Blade(62)
                .part3Motor(49)
                .part3Esc(29)
                .part4Blade(90)
                .part4Motor(74)
                .part4Esc(39)
                .space("부천")
                .stationId("SID 2")
                .build();


        testResultRepository.save(testResult0);
        testResultRepository.save(testResult1);
        testResultRepository.save(testResult2);
        testResultRepository.save(testResult3);
        testResultRepository.saveAll(testResults);
    }
}

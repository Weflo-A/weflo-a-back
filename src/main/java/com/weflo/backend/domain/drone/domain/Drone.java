package com.weflo.backend.domain.drone.domain;

import com.weflo.backend.domain.drone.dto.request.CreateDroneRequest;
import com.weflo.backend.domain.testresult.domain.TestResult;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drone")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drone_id")
    private Long id;

    private String name;

    private LocalDate productionYear;
    @Enumerated(EnumType.STRING)
    private DroneModel model;

    private String purpose;

    private int cost;

    private int flightCount;

    @OneToMany(mappedBy = "drone")
    @Builder.Default
    private List<DroneGroupInfo> droneGroupInfos = new ArrayList<>();

    @OneToMany(mappedBy = "drone")
    @Builder.Default
    private List<TestResult> testResults = new ArrayList<>();

    @OneToMany(mappedBy = "drone")
    @Builder.Default
    private List<DroneComponent> droneComponents = new ArrayList<>();

    public static Drone createDrone(CreateDroneRequest createDroneRequest, DroneModel droneModel) {
        Drone drone = Drone.builder()
                .name(createDroneRequest.getDroneName())
                .model(droneModel)
                .productionYear(LocalDate.ofEpochDay(createDroneRequest.getYear()))
                .purpose(createDroneRequest.getPurpose())
                .build();
        return drone;
    }
}

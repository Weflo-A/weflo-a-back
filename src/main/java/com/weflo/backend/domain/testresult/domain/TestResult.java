package com.weflo.backend.domain.testresult.domain;
import com.weflo.backend.domain.component.domain.Component;
import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "test_result")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_result_id")
    private Long id;

    private String space;

    private String stationId;

    private LocalDateTime expectedDate;

    private int part1Blade;
    private int part1Motor;
    private int part1Esc;

    private int part2Blade;
    private int part2Motor;
    private int part2Esc;

    private int part3Blade;
    private int part3Motor;
    private int part3Esc;

    private int part4Blade;
    private int part4Motor;
    private int part4Esc;

    private LocalDateTime createDate;

    @ManyToMany
    @Builder.Default
    List<Component> components = new ArrayList<>();

    private int totalCost;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;
}

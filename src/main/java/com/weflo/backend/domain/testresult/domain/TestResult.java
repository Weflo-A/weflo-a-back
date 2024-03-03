package com.weflo.backend.domain.testresult.domain;
import com.weflo.backend.domain.component.domain.ComponentType;
import com.weflo.backend.domain.component.domain.Part;
import com.weflo.backend.domain.drone.domain.Drone;
import jakarta.persistence.*;
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

    private Long point;

    private String space;

    private String stationId;

    private LocalDateTime expectedDate;

    @Enumerated(EnumType.STRING)
    private ComponentType type;

    @Enumerated(EnumType.STRING)
    private Part part;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;
}

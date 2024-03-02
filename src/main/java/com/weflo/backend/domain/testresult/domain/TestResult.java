package com.weflo.backend.domain.testresult.domain;
import com.weflo.backend.domain.drone.domain.Dgroup;
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
    private String space;
    private String stationId;
    private LocalDateTime date;
    private LocalDateTime expectedDate;
    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;
}

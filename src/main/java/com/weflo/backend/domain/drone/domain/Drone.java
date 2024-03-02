package com.weflo.backend.domain.drone.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
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
    private LocalDateTime productionYear;
    private String model;
    private String purpose;
    private Long cost;
    private int flightCount;
    @OneToMany(mappedBy = "drone")
    @Builder.Default
    private List<DroneGroup> droneGroups = new ArrayList<>();
    @OneToOne
    private Quotation quotation;
}

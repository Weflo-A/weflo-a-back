package com.weflo.backend.domain.drone.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "drone_group")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class DroneGroup{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drone_group_id")
    private Long id;
    private LocalDateTime configurationDate;
    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;
    @ManyToOne
    @JoinColumn(name = "dgroup_id")
    private Dgroup dgroup;
}

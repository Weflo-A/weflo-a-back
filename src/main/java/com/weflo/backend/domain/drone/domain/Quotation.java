package com.weflo.backend.domain.drone.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "quotation")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class Quotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quotation_id")
    private Long id;
    private String space;
    private String stationId;
    private LocalDateTime date;
    private LocalDateTime expectedDate;
    @OneToOne
    private Drone drone;
}

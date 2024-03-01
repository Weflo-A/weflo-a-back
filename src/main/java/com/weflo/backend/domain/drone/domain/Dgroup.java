package com.weflo.backend.domain.drone.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dgroup")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class Dgroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dgroup_id")
    private Long id;
    private String name;
    private LocalDateTime createDate;
    @OneToMany(mappedBy = "dgroup")
    @Builder.Default
    private List<DroneGroup> droneGroupList = new ArrayList<>();
}

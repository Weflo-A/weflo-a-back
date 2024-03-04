package com.weflo.backend.domain.drone.domain;

import com.weflo.backend.domain.component.domain.Component;
import com.weflo.backend.domain.component.domain.ComponentType;
import com.weflo.backend.domain.component.domain.Part;
import com.weflo.backend.domain.drone.domain.Drone;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "drone_component")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class DroneComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drone_component_id")
    private Long id;
    private Long point;
    private int brokenCount;
    @ManyToOne
    @JoinColumn(name = "component_id")
    private Component component;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;

    @Enumerated(EnumType.STRING)
    private ComponentType type;

    @Enumerated(EnumType.STRING)
    private Part part;
}

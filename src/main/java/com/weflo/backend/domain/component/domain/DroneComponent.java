package com.weflo.backend.domain.component.domain;

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
    private String name;
    private int point;
    private int brokenCount;
    @ManyToOne
    @JoinColumn(name = "component_id")
    private Component component;
}

package com.weflo.backend.domain.component.domain;

import com.weflo.backend.domain.drone.domain.DroneComponent;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "component")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Long id;
    private String name;
    private int price;
    private String description;
    private String image;
    private Double star;
    private Part part;
    private ComponentType type;
    @OneToMany(mappedBy = "component")
    @Builder.Default
    private List<DroneComponent> droneComponents = new ArrayList<>();
}

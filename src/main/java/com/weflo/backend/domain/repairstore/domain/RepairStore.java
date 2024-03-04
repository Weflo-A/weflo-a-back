package com.weflo.backend.domain.repairstore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RepairStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String image;
    private String hasTypes;
    private String hasModels;
    private Boolean canUpgrade;
    private Long expectedMinPrice;
    private Long expectedMaxPrice;

    public List<String> checkTypes(String[] types) {
        List<String> features = new ArrayList<>();
        String suffix = " 부품 보유";

        for (String type : types) {
            if (hasTypes.contains(type)) {
                features.add(type + suffix);
            }
        }

        return features;
    }

    public boolean checkRepairModel(String model) {
        return hasModels.contains(model);
    }
}

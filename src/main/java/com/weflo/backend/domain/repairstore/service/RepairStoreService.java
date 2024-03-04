package com.weflo.backend.domain.repairstore.service;

import com.weflo.backend.domain.repairstore.domain.RepairStore;
import com.weflo.backend.domain.repairstore.dto.RepairStoreResponse;
import com.weflo.backend.domain.repairstore.repository.RepairStoreRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RepairStoreService {
    private final RepairStoreRepository repairStoreRepository;

    @Transactional(readOnly = true)
    public List<RepairStoreResponse> getRepairStoresByModelAndTypes(String model, String[] types) {
        List<RepairStore> allStores = repairStoreRepository.findAll();
        List<RepairStoreResponse> responses = new ArrayList<>();

        for (RepairStore store : allStores) {
            List<String> features = extractFeature(model, types, store);

            if (!features.isEmpty()) {
                RepairStoreResponse repairStoreResponse = RepairStoreResponse.of(store, features);
                responses.add(repairStoreResponse);
            }
        }

        return responses;
    }

    private List<String> extractFeature(String model, String[] types, RepairStore store) {
        List<String> features = new ArrayList<>();

        if (store.checkRepairModel(model)) {
            features.add("수리 가능");
        }

        List<String> canRepairTypes = store.checkTypes(types);
        features.addAll(canRepairTypes);

        return features;
    }
}

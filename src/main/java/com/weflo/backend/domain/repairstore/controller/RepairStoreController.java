package com.weflo.backend.domain.repairstore.controller;

import com.weflo.backend.domain.repairstore.dto.RepairStoreResponse;
import com.weflo.backend.domain.repairstore.service.RepairStoreService;
import com.weflo.backend.global.common.SuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RepairStoreController {
    private final RepairStoreService repairStoreService;

    @GetMapping("/repair-stores")
    public ResponseEntity<SuccessResponse<?>> getRepairStoresByModelAndTypes(
            @RequestParam(value = "model") String model,
            @RequestParam(value = "type", required = false) String[] types) {

        List<RepairStoreResponse> findRepairStores = repairStoreService.getRepairStoresByModelAndTypes(model, types);
        return SuccessResponse.ok(findRepairStores);
    }
}

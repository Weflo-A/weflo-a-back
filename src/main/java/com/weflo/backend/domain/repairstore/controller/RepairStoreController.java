package com.weflo.backend.domain.repairstore.controller;

import com.weflo.backend.domain.repairstore.dto.RepairStoreResponse;
import com.weflo.backend.domain.repairstore.service.RepairStoreService;
import com.weflo.backend.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "수리 업체 관련 API", description = "수리 업체 API")
public class RepairStoreController {
    private final RepairStoreService repairStoreService;

    @Operation(
            summary = "수리 업체 조회 API",
            description = "모델 정보와 부품 정보들을 기준으로 수리 가능한 업체들을 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "요청이 성공했습니다."
    )
    @GetMapping("/repair-stores")
    public ResponseEntity<SuccessResponse<?>> getRepairStoresByModelAndTypes(
            @RequestParam(value = "model") String model,
            @RequestParam(value = "type", required = false) String[] types) {

        List<RepairStoreResponse> findRepairStores = repairStoreService.getRepairStoresByModelAndTypes(model, types);
        return SuccessResponse.ok(findRepairStores);
    }
}

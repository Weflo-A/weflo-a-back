package com.weflo.backend.domain.drone.controller;

import com.weflo.backend.domain.drone.dto.response.DroneGroupListResponse;
import com.weflo.backend.domain.drone.service.DroneGroupService;
import com.weflo.backend.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "그룹 내 드론 조회 API", description = "드론 그룹 관련 API")
public class DroneGroupController {
    private final DroneGroupService droneGroupService;

    @Operation(
            summary = "드론 그룹 내 드론 조회 API",
            description = "드론 그룹에 속한 드론을 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "요청이 성공했습니다."
    )
    @GetMapping("/drone-groups/{droneGroupId}/drones")
    public ResponseEntity<SuccessResponse<?>> getDronesByGroup(@PathVariable(value = "droneGroupId") Long droneGroupId){
        DroneGroupListResponse droneGroupListResponses = droneGroupService.getDronesByDroneGroup(droneGroupId);
        return SuccessResponse.ok(droneGroupListResponses);
    }
}

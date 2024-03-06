package com.weflo.backend.domain.drone.controller;

import com.weflo.backend.domain.drone.dto.response.DroneGroupListResponse;
import com.weflo.backend.domain.drone.service.DroneGroupService;
import com.weflo.backend.global.common.SuccessResponse;
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
public class DroneGroupController {
    private final DroneGroupService droneGroupService;

    @GetMapping("/drone-groups/{droneGroupId}/drones")
    public ResponseEntity<SuccessResponse<?>> getDronesByGroup(
            @PathVariable(value = "droneGroupId") Long droneGroupId) {
        List<DroneGroupListResponse> droneGroupListResponses = droneGroupService.getDronesByDroneGroup(droneGroupId);
        return SuccessResponse.ok(droneGroupListResponses);
    }
}

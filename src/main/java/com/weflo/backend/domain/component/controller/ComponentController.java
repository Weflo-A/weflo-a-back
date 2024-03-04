package com.weflo.backend.domain.component.controller;

import com.weflo.backend.domain.component.dto.ComponentResponse;
import com.weflo.backend.domain.component.service.ComponentService;
import com.weflo.backend.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ComponentController {

    private final ComponentService componentService;

    @Operation(
            summary = "점수 기준 부품 리스트 조회 API",
            description = "해당 드론의 쿼리 파라미터(point) 점수 이상의 부품 리스트가 반환됩니다. (전체 조회 시 점수 = 0 사용)"
    )
    @ApiResponse(
            responseCode = "200",
            description = "요청이 성공했습니다."
    )
    @GetMapping("/drones/{droneId}/components")
    public ResponseEntity<SuccessResponse<?>> getDroneComponentsByPoint(@PathVariable("droneId") Long droneId,
                                                                 @RequestParam(value = "point") Long point) {

        List<ComponentResponse>findComponents = componentService.getDroneComponentsByPoint(droneId, point);
        return SuccessResponse.ok(findComponents);
    }

    @GetMapping("/set-data")
    public void setData() {

        componentService.setData();
    }
}

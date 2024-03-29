package com.weflo.backend.domain.component.controller;

import com.weflo.backend.domain.component.dto.ComponentResponse;
import com.weflo.backend.domain.component.dto.ComponentTotalPriceResponse;
import com.weflo.backend.domain.component.dto.ComponentsByGroupResponse;
import com.weflo.backend.domain.component.dto.ComponentsByModelsResponse;
import com.weflo.backend.domain.component.dto.DroneComponentResponse;
import com.weflo.backend.domain.component.service.ComponentService;
import com.weflo.backend.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
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
@Tag(name = "부품 & 드론 부품 관련 API 모음", description = "부품 관련 API")
public class ComponentController {

    private final ComponentService componentService;

    @Operation(
            summary = "점수 기준 부품 리스트 조회 API",
            description = "해당 드론의 쿼리 파라미터(point) 점수 이상의 부품 리스트가 반환됩니다. (전체 조회 시 점수 = 0 사용)"
                    + "than 파라미터 값으로 MORE -> 기준 점수 이상, LESS -> 기준 점수 이하의 부품이 반환됩니다. "
    )
    @ApiResponse(
            responseCode = "200",
            description = "요청이 성공했습니다."
    )
    @GetMapping("/drones/{droneId}/components")
    public ResponseEntity<SuccessResponse<?>> getDroneComponentsByPoint(@PathVariable("droneId") Long droneId,
                                                                        @RequestParam(value = "point") Long point,
                                                                        @RequestParam(value = "than") String than) {
        List<DroneComponentResponse> findComponents = new ArrayList<>();

        if ("MORE".equals(than)) {
            findComponents = componentService.getDroneComponentsByPointUp(droneId, point);
        } else if ("LESS".equals(than)) {
            findComponents = componentService.getDroneComponentsByPointDown(droneId, point);
        }

        return SuccessResponse.ok(findComponents);
    }

    @Operation(
            summary = "드론 모델 & 그룹 별 점수 기준 부품 리스트 조회 API",
            description = "쿼리 파라미터로 넘어온 점수 이하의 부품들을 모델(그룹) 별로 나누어 반환합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "요청이 성공했습니다."
    )
    @GetMapping("/drone-components")
    public ResponseEntity<SuccessResponse<?>> getComponentsByModels(@RequestParam(value = "point", required = false) Long point,
                                                                    @RequestParam(value = "mode", required = false) String mode
    ) {
        if (point == null || mode == null) {
            List<DroneComponentResponse> droneComponentsResponses = componentService.getDroneComponents();
            return SuccessResponse.ok(droneComponentsResponses);
        }
        if ("MODEL".equals(mode)) {
            List<ComponentsByModelsResponse> responses = componentService.getDroneComponentsByModels(point);
            return SuccessResponse.ok(responses);
        }

        if ("GROUP".equals(mode)) {
            List<ComponentsByGroupResponse> responses = componentService.getDroneComponentsByGroup(point);
            return SuccessResponse.ok(responses);
        }

        return null;
    }

    @Operation(
            summary = "부품 가격 총합 조회 API",
            description = "부품 이름으로 넘어온 부품들의 가격 총합을 반환합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "요청이 성공했습니다."
    )
    @GetMapping("/components")
    public ResponseEntity<SuccessResponse<?>> getComponentsByType(@RequestParam(value = "names", required = false) String[] names) {
        if (names == null) {
            List<ComponentResponse> componentResponses = componentService.getComponents();
            return SuccessResponse.ok(componentResponses);
        } else {
            ComponentTotalPriceResponse totalPriceResponse = componentService.getComponentsPriceByNames(names);
            return SuccessResponse.ok(totalPriceResponse);
        }
    }
}

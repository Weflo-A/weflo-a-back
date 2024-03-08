package com.weflo.backend.domain.cost.controller;

import com.weflo.backend.domain.cost.dto.MonthCostResponse;
import com.weflo.backend.domain.cost.service.CostService;
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
@Tag(name = "투입 비용 현황 관련 API 모음", description = "투입 비용 현황 API")
public class CostController {
    private final CostService costService;

    @Operation(
            summary = "연도, 월 기준 투입 비용 조회",
            description = "연도와 월을 기준으로 투입 비용을 조회합니다."
                    + "월은 생략 가능, 연도는 생략 불가 ex) /year=2024&month= 혹은 /year=2024"
    )
    @ApiResponse(
            responseCode = "200",
            description = "서버가 정상 작동 중입니다."
    )
    @GetMapping("/month-costs")
    public ResponseEntity<SuccessResponse<?>> getDroneGroupMonthCosts(@RequestParam("year") Long year,
                                                                      @RequestParam(value = "month", required = false) Long month) {
        List<MonthCostResponse> monthCostResponses = costService.getDroneGroupMonthCosts(year, month);
        return SuccessResponse.ok(monthCostResponses);
    }

}

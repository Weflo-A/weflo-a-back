package com.weflo.backend.domain.cost.controller;

import com.weflo.backend.domain.cost.dto.MonthCostResponse;
import com.weflo.backend.domain.cost.service.CostService;
import com.weflo.backend.global.common.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CostController {
    private final CostService costService;

    @GetMapping("/month-costs")
    public ResponseEntity<SuccessResponse<?>> getDroneGroupMonthCosts() {
        costService.getDroneGroupMonthCosts();
        return SuccessResponse.ok(null);
    }

    @GetMapping("/all-costs")
    public ResponseEntity<SuccessResponse<?>> getAllCosts(@RequestParam("year") Long year) {
        MonthCostResponse monthCostResponse = costService.getAllCosts(year);
        return SuccessResponse.ok(monthCostResponse);
    }
}

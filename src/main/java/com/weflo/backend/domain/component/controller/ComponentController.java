package com.weflo.backend.domain.component.controller;

import com.weflo.backend.domain.component.dto.ComponentResponse.ExchangeComponentResponse;
import com.weflo.backend.domain.component.service.ComponentService;
import com.weflo.backend.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/component")
public class ComponentController {
    
    private final ComponentService componentService;

    @Operation(
            summary = "교체용 부품 리스트 조회(전체 부품 조회)",
            description = "교체용 부품 리스트를 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "get components success"
    )
    @GetMapping("/exchange-components")
    public ResponseEntity<SuccessResponse<?>> getExchangeComponents() {
        List<ExchangeComponentResponse> exchangeComponentResponses = componentService.getAllComponents();

        return SuccessResponse.ok(exchangeComponentResponses);
    }

    @GetMapping("/set-data")
    public void setData() {

        componentService.setData();
    }
}

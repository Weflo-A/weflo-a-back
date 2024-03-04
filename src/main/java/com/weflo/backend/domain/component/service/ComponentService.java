package com.weflo.backend.domain.component.service;

import com.weflo.backend.domain.component.domain.Component;
import com.weflo.backend.domain.component.domain.ComponentType;
import com.weflo.backend.domain.component.domain.Part;
import com.weflo.backend.domain.component.dto.ComponentResponse.ExchangeComponentResponse;
import com.weflo.backend.domain.component.repository.ComponentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ComponentService {
    private final ComponentRepository componentRepository;

    @Transactional(readOnly = true)
    public List<ExchangeComponentResponse> getAllComponents() {
        List<Component> allComponents = componentRepository.findAll();

        return ExchangeComponentResponse.ofList(allComponents);
    }

    @Transactional
    public void setData() {
        Component componentA = Component.builder()
                .name("부품 A")
                .part(Part.PART1)
                .price(1000)
                .description("부품 A 정의")
                .type(ComponentType.ESC)
                .image("부품 A 이미지 경로")
                .build();

        Component componentB = Component.builder()
                .name("부품 A")
                .part(Part.PART2)
                .price(2000)
                .description("부품 B 정의")
                .type(ComponentType.BLADE)
                .image("부품 B 이미지 경로")
                .build();

        Component componentC = Component.builder()
                .name("부품 C")
                .part(Part.PART3)
                .price(3000)
                .description("부품 C 정의")
                .type(ComponentType.MOTOR)
                .image("부품 C 이미지 경로")
                .build();

        Component componentD = Component.builder()
                .name("부품 D")
                .part(Part.PART1)
                .price(4000)
                .description("부품 D 정의")
                .type(ComponentType.BLADE)
                .image("부품 D 이미지 경로")
                .build();

        componentRepository.save(componentA);
        componentRepository.save(componentB);
        componentRepository.save(componentC);
        componentRepository.save(componentD);
    }
}

package com.weflo.backend.domain.component.dto;

import com.weflo.backend.domain.component.domain.Part;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.type.ComponentType;

@Builder
@Getter
public record ExchangeComponentResponse(
        String image,
        ComponentType type,
        Part part,
        String name,
        String description,
        int price
) {
}

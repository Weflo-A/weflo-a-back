package com.weflo.backend.domain.component.repository;

import com.weflo.backend.domain.component.domain.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<Component, Long> {

}

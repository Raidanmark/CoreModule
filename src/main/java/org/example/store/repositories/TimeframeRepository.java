package org.example.store.repositories;

import org.example.store.entities.TimeframeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeframeRepository extends JpaRepository<TimeframeEntity, Long> {
}

package org.example.store.repositories;

import org.example.store.entities.TimeframeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface TimeframeRepository extends JpaRepository<TimeframeEntity, Long> {
    Optional<TimeframeEntity> findByCode(String code);
    Stream<TimeframeEntity> getAllBy();
}

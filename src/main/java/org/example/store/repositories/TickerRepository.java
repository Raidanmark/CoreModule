package org.example.store.repositories;

import org.example.store.entities.TickerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TickerRepository extends JpaRepository<TickerEntity, Long> {
}

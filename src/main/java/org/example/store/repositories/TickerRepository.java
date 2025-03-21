package org.example.store.repositories;

import org.example.store.entities.TickerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface TickerRepository extends JpaRepository<TickerEntity, Long> {

    Optional<TickerEntity> findByName(String name);
    Stream<TickerEntity> streamAllBy();
}

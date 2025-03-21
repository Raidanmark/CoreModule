package org.example.store.repositories;

import org.example.store.entities.CandleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandleRepository extends JpaRepository<CandleEntity, Long> {


}

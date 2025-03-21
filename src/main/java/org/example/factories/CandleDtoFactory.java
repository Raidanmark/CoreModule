package org.example.factories;

import org.example.dto.CandleDto;
import org.example.store.entities.CandleEntity;
import org.springframework.stereotype.Component;

@Component
public class CandleDtoFactory {
    public CandleDto makeCandleDto(CandleEntity entity) {
        return CandleDto.builder()
                .id(entity.getId())
                .open(entity.getOpen())
                .close(entity.getClose())
                .low(entity.getLow())
                .high(entity.getHigh())
                .volume(entity.getVolume())
                .build();
    }
}

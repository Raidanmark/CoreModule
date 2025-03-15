package org.example.factories;

import org.example.dto.TickerDto;
import org.example.store.entities.TickerEntity;
import org.springframework.stereotype.Component;

@Component
public class TickerDtoFactory {
    public TickerDto makeTickerDto (TickerEntity entity) {
        return TickerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .symbol(entity.getSymbol())
                .build();
    }
}

package org.example.factories;

import org.example.dto.TimeframeDto;
import org.example.store.entities.TimeframeEntity;
import org.springframework.stereotype.Component;

@Component
public class TimeframeDtoFactory {
    public TimeframeDto makeTimeframeDto (TimeframeEntity entity) {
        return TimeframeDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .build();
    }
}

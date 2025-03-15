package org.example.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CandleDto {
    @NonNull
    Long id;

    @NonNull
    double open, close, low, high, vol;
}

package org.example.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswDto {
    Boolean answer;

    public static AnswDto makeDefault(Boolean answer) {
        return builder()
                .answer(answer)
                .build();
    }
}

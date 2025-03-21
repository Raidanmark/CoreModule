package org.example.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "candle")
public class CandleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    @NonNull
    double open, close, low, high, volume;



}



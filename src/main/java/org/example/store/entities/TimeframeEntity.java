package org.example.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "timeframe")
public class TimeframeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)

    Long id;
    @Column(unique = true)
    String code;

    @OneToMany
    @JoinColumn (name = "timeframe_id")
    List<CandleEntity> candles = new ArrayList<>();

}

package org.example.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "timeframe")
public class TimeframeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)

    private Long id;
    @Column(unique = true)
    private String code;

    @OneToMany
    @JoinColumn (name = "timeframe_id")
    private List<CandleEntity> candles = new ArrayList<>();




}

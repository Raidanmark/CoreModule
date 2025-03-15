package org.example.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticker")
public class TickerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(unique = true)
    private String name;
    private String symbol;

    @OneToMany
    @JoinColumn (name = "ticker_id")
    private List<CandleEntity> candles = new ArrayList<>();


}

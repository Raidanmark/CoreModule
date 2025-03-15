package org.example.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.dto.TickerDto;
import org.example.exceptions.BadRequestException;
import org.example.factories.TickerDtoFactory;
import org.example.store.entities.TickerEntity;
import org.example.store.repositories.TickerRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class TickerController {
    TickerRepository tickerRepository;

    TickerDtoFactory tickerDtoFactory;

    public static final  String CREATE_TICKER = "/api/tickers";

    @PostMapping(CREATE_TICKER)
    public TickerDto createTicker(@RequestParam String name, String symbol) {

        tickerRepository
                .findByName(name)
                .ifPresent(ticker -> {
                    throw new BadRequestException(String.format("Ticker \" %s\" already exists.", name));
                });

        TickerEntity ticker = tickerRepository.saveAndFlush(
                TickerEntity.builder()
                        .name(name)
                        .symbol(symbol)
                        .build()
        );

          return tickerDtoFactory.makeTickerDto(ticker);
    }
}

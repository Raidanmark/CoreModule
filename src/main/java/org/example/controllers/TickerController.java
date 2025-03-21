package org.example.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.dto.AnswDto;
import org.example.dto.TickerDto;
import org.example.exceptions.BadRequestException;
import org.example.exceptions.NotFoundException;
import org.example.factories.TickerDtoFactory;
import org.example.store.entities.TickerEntity;
import org.example.store.repositories.TickerRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class TickerController {
    TickerRepository tickerRepository;

    TickerDtoFactory tickerDtoFactory;

    public static final  String CREATE_TICKER = "/api/tickers";
    public static final  String EDIT_TICKER = "/api/tickers";
    public static final  String FETCH_TICKERS = "/api/tickers";
    public static final  String DELETE_TICKER = "/api/tickers";


    @PostMapping(CREATE_TICKER)
    public TickerDto createTicker(@RequestParam String name, String symbol) {

        //Name & symbol validation
        if (name.trim().isEmpty() || symbol.trim().isEmpty()) {
            throw new BadRequestException("Ticker name or symbol cannot be empty");
        }

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

    @PutMapping(EDIT_TICKER)
    public TickerDto editPatch(
            @RequestParam("ticker_id") String tickerId,
            @RequestParam String name, @RequestParam String symbol) {

        if (name.trim().isEmpty() || symbol.trim().isEmpty()) {
            throw new BadRequestException("Ticker name or symbol cannot be empty");
        }

        TickerEntity ticker = tickerRepository
                .findById(Long.valueOf(tickerId))
                .orElseThrow(() ->
                        new NotFoundException(String.format(
                                "Ticker with \" %s \" doesn't exist.",
                                tickerId
                        ))
                );

        tickerRepository
                .findByName(name)
                .filter(anotherTicker -> !Objects.equals(anotherTicker.getName(), name))
                .filter(anotherTicker -> !Objects.equals(anotherTicker.getSymbol(), symbol))
                .ifPresent(anotherTicker -> {
                    throw new BadRequestException(String.format(
                            "Ticker \" %s\" already exists.",
                            name
                    ));
                });

        ticker.setName(name);
        ticker.setSymbol(symbol);

        ticker = tickerRepository.saveAndFlush(ticker);
        return tickerDtoFactory.makeTickerDto(ticker);
    }

    @GetMapping(FETCH_TICKERS)
    public List<TickerDto> fetchTickers() {
        return tickerRepository.streamAllBy()
                .map(tickerDtoFactory::makeTickerDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping(DELETE_TICKER)
    public AnswDto deleteTicker(@RequestParam("ticker_id") String tickerId) {
        TickerEntity ticker = tickerRepository
                .findById(Long.valueOf(tickerId))
                .orElseThrow(() ->
                        new NotFoundException(String.format(
                                "Timeframe \" %s\" not found", tickerId))
                );

        tickerRepository.deleteById(Long.valueOf(tickerId));
        return AnswDto.makeDefault(true);

    }
}

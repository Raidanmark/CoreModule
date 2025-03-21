package org.example.controllers;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.dto.AnswDto;
import org.example.dto.TimeframeDto;
import org.example.exceptions.BadRequestException;
import org.example.exceptions.NotFoundException;
import org.example.factories.TimeframeDtoFactory;
import org.example.store.entities.TimeframeEntity;
import org.example.store.repositories.TimeframeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class TimeframeController {

    TimeframeRepository timeframeRepository;
    TimeframeDtoFactory timeframeDtoFactory;

    public static final String CREATE_TIMEFRAME = "/api/timeframes";
    public static final String UPDATE_TIMEFRAME = "/api/timeframes";
    public static final String DELETE_TIMEFRAME = "/api/timeframes";
    public static final String GET_TIMEFRAMES = "/api/timeframes";

    @PostMapping(CREATE_TIMEFRAME)
    public TimeframeDto createTimeframe(@RequestParam String code) {
        if (code.isEmpty()) {
            throw new BadRequestException("Time frame code cannot be empty");
        }

        timeframeRepository
                .findByCode(code)
                .ifPresent(timeframe -> {
                    throw new BadRequestException(String.format("Timeframe \" %s\" already exists", code));
                });
        TimeframeEntity timeframe = timeframeRepository.saveAndFlush(
                TimeframeEntity.builder()
                        .code(code)
                        .build()
        );

        return timeframeDtoFactory.makeTimeframeDto(timeframe);

    }

    @PutMapping(UPDATE_TIMEFRAME)
    public TimeframeDto updateTimeframe(
            @RequestParam("timeframe_id") String timeframeId,
            @RequestParam String code) {

        if (code.isEmpty()) {
            throw new BadRequestException("Time frame code cannot be empty");
        }

        TimeframeEntity timeframe = timeframeRepository
                .findById(Long.valueOf(timeframeId))
                .orElseThrow(() ->
                        new NotFoundException(String.format("Timeframe \" %s\" not found", timeframeId
                        ))
                );


        timeframe.setCode(code);
        timeframe = timeframeRepository.saveAndFlush(timeframe);
        return timeframeDtoFactory.makeTimeframeDto(timeframe);
    }

    @GetMapping(GET_TIMEFRAMES)
    public List<TimeframeDto> getAllTimeframes() {
        return timeframeRepository.getAllBy()
                .map(timeframeDtoFactory::makeTimeframeDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping(DELETE_TIMEFRAME)
    public AnswDto deleteTimeframe(@RequestParam("timeframe_id") String timeframeId) {
        TimeframeEntity timeframe = timeframeRepository
                .findById(Long.valueOf(timeframeId))
                .orElseThrow(() ->
                        new NotFoundException(String.format("Timeframe \" %s\" not found", timeframeId
                        ))
                );

        timeframeRepository.deleteById(Long.valueOf(timeframeId));
        return AnswDto.makeDefault(true);
    }

}

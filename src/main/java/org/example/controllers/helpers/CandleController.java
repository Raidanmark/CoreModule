package org.example.controllers.helpers;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.dto.CandleDto;
import org.example.factories.CandleDtoFactory;
import org.example.store.entities.CandleEntity;
import org.example.store.repositories.CandleRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class CandleController {
    CandleRepository candleRepository;
    CandleDtoFactory candleDtoFactory;

    public static final String CREATE_CANDLE = "api/candles/post";
    public static final String UPDATE_CANDLE = "api/candles/update";
    public static final String DELETE_CANDLE = "api/candles/delete";
    public static final String GET_CANDLE = "api/candles/get";

    @PostMapping(CREATE_CANDLE)
    public CandleDto createCandle(@RequestParam("ticker_name") String tickerName,
                                  @RequestParam("timeframe_code") String timeframeCode,
                                  @RequestParam double open, double close, double high, double low, double volume ) {

        CandleEntity candleEntity = candleRepository.saveAndFlush(
                CandleEntity.builder()
                        .open(open)
                        .close(close)
                        .high(high)
                        .low(low)
                        .volume(volume)
                        .build()
        );

        return candleDtoFactory.makeCandleDto(candleEntity);
    }
}

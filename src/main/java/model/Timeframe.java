package model;

import java.util.ArrayList;
import java.util.List;

public class Timeframe {
    private Long id;
    private String code;

    private List<Candle> candles = new ArrayList<>();

    public Timeframe(String code){
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Candle> getCandles() {
        return candles;
    }

    public void setCandles(List<Candle> candles) {
        this.candles = candles;
    }

}

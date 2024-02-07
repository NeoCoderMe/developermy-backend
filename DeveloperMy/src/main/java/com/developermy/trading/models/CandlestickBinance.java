package com.developermy.trading.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * Kline/Candlestick bars for a symbol. Klines are uniquely identified by their open time.
 */
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder()
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CandlestickBinance {

  private Long openTime;

  private Double open;

  private Double high;

  private Double low;

  private Double close;

  private Double volume;

  private Long closeTime;

  private Double quoteAssetVolume;

  private Long numberOfTrades;

  private Double takerBuyBaseAssetVolume;

  private Double takerBuyQuoteAssetVolume;

   
}

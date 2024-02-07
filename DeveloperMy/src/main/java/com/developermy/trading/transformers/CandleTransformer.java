package com.developermy.trading.transformers;

import com.developermy.trading.models.CandleDTO;
import com.developermy.trading.models.CandlestickBinance;
import com.developermy.trading.models.TickerBitso;

public final class CandleTransformer {
	private CandleTransformer() {

	}

	public static CandleDTO toCandleDTO(CandlestickBinance candlestickBinance) {
		return CandleDTO.builder().close(candlestickBinance.getClose()).open(candlestickBinance.getOpen())
				.higher(candlestickBinance.getHigh()).lower(candlestickBinance.getLow()).build();
	}
	
	public static CandleDTO toCandleDTO(TickerBitso tickerBitso) {
		return CandleDTO.builder().close(tickerBitso.getMVwap()).open(tickerBitso.getMLast())
				.higher(tickerBitso.getMHigh()).lower(tickerBitso.getMLow()).build();
	}
}

package com.developermy.trading.adapter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.developermy.trading.clients.BinanceClient;
import com.developermy.trading.models.CandleDTO;
import com.developermy.trading.transformers.CandleTransformer;

@Service
public class BinanceAdapter {

	
	public static List<CandleDTO> getCandles(String symbol) {
		return BinanceClient.getCandles(symbol).stream().map(CandleTransformer::toCandleDTO).toList();
	}
}

package com.developermy.trading.adapter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.developermy.trading.clients.BitsoClient;
import com.developermy.trading.models.CandleDTO;
import com.developermy.trading.transformers.CandleTransformer;

@Service
public class BitsoAdapter {

	public static List<CandleDTO> getCandles(String symbol) {
		return BitsoClient.getCandles(symbol).stream().map(CandleTransformer::toCandleDTO).toList();
	}
}
